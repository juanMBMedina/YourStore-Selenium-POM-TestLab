package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {
    private static final long MAX_TIME_WAIT = 10;
    private static WebDriver driver;

    private DriverFactory() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser", "chrome").toLowerCase();
            boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));
            boolean withInsecureCerts = Boolean.parseBoolean(System.getProperty("withoutCerts", "false"));
            return getDriver(browser, isHeadless, withInsecureCerts);
        }
        return driver;
    }

    public static WebDriver getDriver(String browser, boolean isHeadless, boolean withInsecureCerts) {
        if (driver == null) {
            switch (browser) {
                case "firefox":
                    driver = createFirefoxDriver(isHeadless, withInsecureCerts);
                    break;
                case "edge":
                    driver = createEdgeDriver(isHeadless, withInsecureCerts);
                    break;
                case "chrome":
                default:
                    driver = createChromeDriver(isHeadless, withInsecureCerts);
                    break;
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(MAX_TIME_WAIT));
        }
        return driver;
    }

    private static WebDriver createChromeDriver(boolean isHeadless, boolean withInsecureCerts) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(withInsecureCerts);

        if (isHeadless) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-logging");
            options.addArguments("--remote-allow-origins=*");
        } else {
            options.addArguments("start-maximized");
        }

        return new ChromeDriver(options);
    }

    private static WebDriver createEdgeDriver(boolean isHeadless, boolean withInsecureCerts) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.setAcceptInsecureCerts(withInsecureCerts);

        if (isHeadless) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-extensions");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("start-maximized");
        }

        return new EdgeDriver(options);
    }

    private static WebDriver createFirefoxDriver(boolean isHeadless, boolean withInsecureCerts) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.setAcceptInsecureCerts(withInsecureCerts);
        if (isHeadless) {
            options.addArguments("--headless");
            options.addArguments("--no-remote");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-software-rasterizer");
        }
        return new FirefoxDriver(options);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
