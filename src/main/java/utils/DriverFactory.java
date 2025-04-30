package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.Setter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.time.Duration;

public class DriverFactory {
    private static final long MAX_TIME_WAIT = 10;
    private static final String WINDOW_SIZE = "window-size=1920,1080";
    @Setter
    private static WebDriver driver;

    private DriverFactory() {
    }

    public static void createDriver() {

        String browser = System.getenv().getOrDefault("BROWSER", "chrome").toLowerCase();
        boolean headless = Boolean.parseBoolean(System.getenv().getOrDefault("HEADLESS", "true"));
        boolean acceptInsecureCerts = Boolean.parseBoolean(System.getenv().getOrDefault("INSECURE_CERTS", "true"));

        switch (browser) {
            case "firefox":
                setDriver(setupFirefoxDriver(headless, acceptInsecureCerts));
                break;
            case "edge":
                setDriver(setupEdgeDriver(headless, acceptInsecureCerts));
                break;
            case "chrome":
                setDriver(setupChromeDriver(headless, acceptInsecureCerts));
                break;
            default:
                throw new IllegalArgumentException(String.format("This browser {%s} is doesn't available.", browser));
        }
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(MAX_TIME_WAIT));
    }

    private static WebDriver setupChromeDriver(boolean headless, boolean insecureCerts) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments(WINDOW_SIZE);
        } else {
            options.addArguments("start-maximized");
        }
        options.setAcceptInsecureCerts(insecureCerts);
        return new ChromeDriver(options);
    }

    private static WebDriver setupFirefoxDriver(boolean headless, boolean insecureCerts) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (headless) {
            options.addArguments("-headless");
            options.addArguments("--width=1920");
            options.addArguments("--height=1080");
        } else {
            options.addPreference("browser.fullscreen.autohide", true);
            options.addPreference("browser.fullscreen.animateUp", 0);
        }
        options.setAcceptInsecureCerts(insecureCerts);
        FirefoxProfile profile = new FirefoxProfile();
        options.setProfile(profile);
        return new FirefoxDriver(options);
    }


    private static WebDriver setupEdgeDriver(boolean headless, boolean insecureCerts) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments(WINDOW_SIZE);
        } else {
            options.addArguments("start-maximized");
        }
        options.setAcceptInsecureCerts(insecureCerts);
        return new EdgeDriver(options);
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized. Call create(browser) first.");
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
