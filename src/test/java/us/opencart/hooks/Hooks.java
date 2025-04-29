package us.opencart.hooks;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import us.opencart.builders.RegisterUserBuilder;
import us.opencart.models.RegisterUser;
import us.opencart.models.SearchItemNavBar;
import utils.DriverFactory;

import java.util.Map;

import static us.opencart.constants.AddToCartPageConstants.DEFAULT_VALUE;

@Getter
public class Hooks {
    private static final String URL = "http://opencart.abstracta.us/index.php?route=common/home";
    private WebDriver driver;

    @Before
    public void setUp() {
        WebDriver driver = DriverFactory.getDriver();
        driver.get(URL);
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    @DataTableType
    public RegisterUser toRegisterUser(Map<String, String> data) {
        return RegisterUserBuilder.registerAnUser(data);
    }

    @DataTableType
    public SearchItemNavBar toItemNavBar(Map<String, String> data) {
        return new SearchItemNavBar(
                data.get("category"),
                data.getOrDefault("subcategory", DEFAULT_VALUE),
                data.get("itemName"));
    }
}
