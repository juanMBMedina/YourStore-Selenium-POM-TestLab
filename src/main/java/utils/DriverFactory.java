package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.time.Duration;

public class DriverFactory {
    private static final String HEADLESS_FLAG = "--headless";
    private static final long MAX_TIME_WAIT = 10;
    private static WebDriver driver;

    private DriverFactory() {}

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = System.getProperty("browser", "chrome").toLowerCase();
            boolean isHeadless = Boolean.parseBoolean(System.getProperty("headless", "false"));
            boolean withCerts = Boolean.parseBoolean(System.getProperty("withoutCerts", "false"));
            return getDriver(browser, isHeadless, withCerts);
        }
        return driver;
    }

    public static WebDriver getDriver(String browser, boolean isHeadless, Boolean withInsecureCerts) {
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

    private static WebDriver createChromeDriver(boolean isHeadless, Boolean withInsecureCerts) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        configureCommonOptions(chromeOptions, isHeadless, withInsecureCerts);
        chromeOptions.addArguments("--user-data-dir=" + getUserDataDir("chrome"));
        return new ChromeDriver(chromeOptions);
    }

    private static WebDriver createEdgeDriver(boolean isHeadless, Boolean withInsecureCerts) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions edgeOptions = new EdgeOptions();
        configureCommonOptions(edgeOptions, isHeadless, withInsecureCerts);
        edgeOptions.addArguments("--user-data-dir=" + getUserDataDir("edge"));
        edgeOptions.addArguments("start-maximized");
        return new EdgeDriver(edgeOptions);
    }

    private static WebDriver createFirefoxDriver(boolean isHeadless, Boolean withInsecureCerts) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        configureCommonOptions(firefoxOptions, isHeadless, withInsecureCerts);
        firefoxOptions.addArguments("-profile");
        firefoxOptions.addArguments(getUserDataDir("firefox"));
        return new FirefoxDriver(firefoxOptions);
    }

    private static void configureCommonOptions(ChromeOptions options, boolean isHeadless, Boolean withInsecureCerts) {
        options.setAcceptInsecureCerts(withInsecureCerts);
        if (isHeadless) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-logging");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("start-maximized");
        }
    }

    private static void configureCommonOptions(EdgeOptions options, boolean isHeadless, Boolean withInsecureCerts) {
        options.setAcceptInsecureCerts(withInsecureCerts);
        if (isHeadless) {
            options.addArguments(HEADLESS_FLAG);
            options.addArguments("--disable-gpu");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }
    }

    private static void configureCommonOptions(FirefoxOptions options, boolean isHeadless, Boolean withInsecureCerts) {
        options.setAcceptInsecureCerts(withInsecureCerts);
        if (isHeadless) {
            options.addArguments(HEADLESS_FLAG);
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-logging");
            options.addArguments("--window-size=1920,1080");
        }
    }

    private static String getUserDataDir(String browser) {
        String os = System.getProperty("os.name").toLowerCase();
        String userDataDir;

        if (os.contains("win")) {
            userDataDir = "C:\\temp\\" + browser + "-profile-" + System.currentTimeMillis();
            new File(userDataDir).mkdirs();
        } else {
            userDataDir = "/tmp/" + browser + "-profile-" + System.currentTimeMillis();
        }

        return userDataDir;
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
