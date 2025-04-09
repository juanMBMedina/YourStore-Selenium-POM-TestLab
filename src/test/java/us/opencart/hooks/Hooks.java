package us.opencart.hooks;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

public class Hooks {
    private static final String URL = "http://opencart.abstracta.us/index.php?route=common/home";

    private WebDriver driver;

    @Before
    public void setUp() {
        // If the page doesn't have a security certificates available
        WebDriver driver = DriverFactory.getDriverWithInsecureCerts();
        driver.get(URL);
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
