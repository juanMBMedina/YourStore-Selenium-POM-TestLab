package us.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private static final By USER_NAME_FIELD_TEXT = By.id("input-email");
    private static final By USER_PASS_FIELD_TEXT = By.id("input-password");
    private static final By LOGIN_BUTTON = By.xpath("//input[@value='Login']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void fillForm(String userName, String userPassword) {
        sendKeys(USER_NAME_FIELD_TEXT, userName);
        sendKeys(USER_PASS_FIELD_TEXT, userPassword);
    }

    public AccountPage submitForm() {
        clickOn(LOGIN_BUTTON);
        return new AccountPage(getDriver());
    }

}
