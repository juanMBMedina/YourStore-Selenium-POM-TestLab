package hooks;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utils.WebDriverManager;

public class Hooks {
    private static final String URL="https://the-internet.herokuapp.com/login";

    @Before
    public void setUp(){
        WebDriver driver = WebDriverManager.getDriver();
        driver.get(URL);
    }

    @After
    public void  tearDown(){
        WebDriverManager.quitDriver();
    }
}
