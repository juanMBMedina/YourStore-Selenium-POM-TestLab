package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {
    private static WebDriver driver;

    public static WebDriver getDriverWithInsecureCerts() {
        return getDriver(true);
    }

    public static WebDriver getDriverWithoutInsecureCerts() {
        return getDriver(false);
    }

    public static WebDriver getDriver() {
        return getDriverWithoutInsecureCerts();
    }

    public static WebDriver getDriver(Boolean withInsecureCerts) {
        if (driver == null) {
            String browser = System.getProperty("browser", "chrome").toLowerCase();
            switch (browser) {
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setAcceptInsecureCerts(withInsecureCerts);
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    EdgeOptions edgeOptions = new EdgeOptions();
                    edgeOptions.setAcceptInsecureCerts(withInsecureCerts);
                    edgeOptions.addArguments("start-maximized");
                    driver = new EdgeDriver(edgeOptions);
                    break;
                case "chrome":
                default:
                    WebDriverManager.chromedriver().setup();
                    ChromeOptions options = new ChromeOptions();
                    options.setAcceptInsecureCerts(withInsecureCerts);
                    options.addArguments("--start-maximized");
                    driver = new ChromeDriver(options);
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
