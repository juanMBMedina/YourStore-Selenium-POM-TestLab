package us.opencart.hooks;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import org.openqa.selenium.WebDriver;
import us.opencart.builders.RegisterUserBuilder;
import us.opencart.models.RegisterUser;
import utils.DriverFactory;

import java.util.Map;

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

    @DataTableType
    public RegisterUser toRegisterUser(Map<String, String> data){
        return RegisterUserBuilder.registerAnUser(data);
    }

}
