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
    private static final String HEADLESS_FLAG = "--headless";
    private static WebDriver driver;

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        boolean withCerts = Boolean.parseBoolean(System.getProperty("withoutCerts", "false"));
        return getDriver(browser, isHeadless, withCerts);
    }

    public static WebDriver getDriver(String browser, Boolean isHeadless, Boolean withInsecureCerts) {
        if (driver == null) {
            switch (browser) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setAcceptInsecureCerts(withInsecureCerts);
                    if (isHeadless) {
                        firefoxOptions.addArguments(HEADLESS_FLAG);
                    }
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setAcceptInsecureCerts(withInsecureCerts);
                    if (isHeadless) {
                        edgeOptions.addArguments(HEADLESS_FLAG);
                    }
                    edgeOptions.addArguments("start-maximized");
                    driver = new EdgeDriver(edgeOptions);
                    break;
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.setAcceptInsecureCerts(withInsecureCerts);
                    if (isHeadless) {
                        chromeOptions.addArguments(HEADLESS_FLAG);
                        chromeOptions.addArguments("--disable-gpu");
                        chromeOptions.addArguments("--window-size=1920x1080");
                    }
                    driver = new ChromeDriver(chromeOptions);
                    break;
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }

        return driver;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
