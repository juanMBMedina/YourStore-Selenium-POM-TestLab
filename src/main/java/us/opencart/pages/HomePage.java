package us.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends BasePage {

    private static final By LOGIN = getByContainsText(TOP_NAV_ITEM_FORMAT, "Login");
    private static final By REGISTER = getByContainsText(TOP_NAV_ITEM_FORMAT, "Register");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage goToLoginPage() {
        clickOn(MY_ACCOUNT);
        clickOn(LOGIN);
        return new LoginPage(getDriver());
    }

}
