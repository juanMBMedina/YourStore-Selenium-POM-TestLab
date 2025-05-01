package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Setter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DriverFactory {

    private static final long MAX_TIME_WAIT = 10;
    private static final String WINDOW_SIZE = "window-size=1920,1080";
    private static final String SELENIUM_GRID_URL = "http://selenium-hub:4444/wd/hub";

    @Setter
    private static WebDriver driver;

    private DriverFactory() {
    }

    public static void createDriver() {
        String browser = System.getenv().getOrDefault("BROWSER", "chrome").toLowerCase();
        boolean headless = Boolean.parseBoolean(System.getenv().getOrDefault("HEADLESS", "true"));
        boolean acceptInsecureCerts = Boolean.parseBoolean(System.getenv().getOrDefault("INSECURE_CERTS", "true"));
        boolean useGrid = Boolean.parseBoolean(System.getenv().getOrDefault("SELENIUM_GRID", "true"));
        switch (browser) {
            case "firefox":
                setDriver(setupFirefoxDriver(headless, acceptInsecureCerts, useGrid));
                break;
            case "edge":
                setDriver(setupEdgeDriver(headless, acceptInsecureCerts, useGrid));
                break;
            case "chrome":
                setDriver(setupChromeDriver(headless, acceptInsecureCerts, useGrid));
                break;
            default:
                throw new IllegalArgumentException(String.format("This browser {%s} is not available.", browser));
        }
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(MAX_TIME_WAIT));
    }

    private static WebDriver setupChromeDriver(boolean headless, boolean insecureCerts, boolean useGrid) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        if (headless) {
            options.addArguments("--headless=new", WINDOW_SIZE);
        } else {
            options.addArguments("start-maximized");
        }
        options.setAcceptInsecureCerts(insecureCerts);

        return useGrid ? createRemoteWebDriver(options) : new ChromeDriver(options);
    }

    private static WebDriver setupFirefoxDriver(boolean headless, boolean insecureCerts, boolean useGrid) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("-headless", "--width=1920", "--height=1080");
        }
        options.addArguments("--no-remote");
        options.setProfile(new FirefoxProfile());
        options.setAcceptInsecureCerts(insecureCerts);

        return useGrid ? createRemoteWebDriver(options) : new FirefoxDriver(options);
    }

    private static WebDriver setupEdgeDriver(boolean headless, boolean insecureCerts, boolean useGrid) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        if (headless) {
            options.addArguments("--headless=new", WINDOW_SIZE);
        } else {
            options.addArguments("start-maximized");
        }
        options.setAcceptInsecureCerts(insecureCerts);

        return useGrid ? createRemoteWebDriver(options) : new EdgeDriver(options);
    }

    private static WebDriver createRemoteWebDriver(Capabilities options) {
        try {
            return new RemoteWebDriver(new URL(SELENIUM_GRID_URL), options);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Selenium Grid URL: " + SELENIUM_GRID_URL, e);
        }
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized. Call createDriver() first.");
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
