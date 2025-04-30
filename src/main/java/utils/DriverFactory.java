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

public class DriverFactory {
    @Setter
    private static WebDriver driver;

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
    }

    private static WebDriver setupChromeDriver(boolean headless, boolean insecureCerts) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (headless) options.addArguments("--headless=new");
        options.setAcceptInsecureCerts(insecureCerts);
        return new ChromeDriver(options);
    }

    private static WebDriver setupFirefoxDriver(boolean headless, boolean insecureCerts) {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (headless) options.addArguments("-headless");
        options.setAcceptInsecureCerts(insecureCerts);
        return new FirefoxDriver(options);
    }

    private static WebDriver setupEdgeDriver(boolean headless, boolean insecureCerts) {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        if (headless) options.addArguments("--headless=new");
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
