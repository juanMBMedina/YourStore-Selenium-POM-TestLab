package us.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import us.opencart.models.RegisterUser;

public class RegisterPage extends BasePage {

    private static final By FIRST_NAME_FIELD_TEXT = By.id("input-firstname");
    private static final By LAST_NAME_FIELD_TEXT = By.id("input-lastname");
    private static final By EMAIL_FIELD_TEXT = By.id("input-email");
    private static final By PHONE_FIELD_TEXT = By.id("input-telephone");
    private static final By PASSWORD_FIELD_TEXT = By.id("input-password");
    private static final By CONFIRM_PASSWORD_FIELD_TEXT = By.id("input-confirm");
    private static final By SELECT_SUBSCRIBE_YES = By.xpath("//input[@name='newsletter' and @value='1']");
    private static final By SELECT_SUBSCRIBE_NO = By.xpath("//input[@name='newsletter' and @value='0']");
    private static final By SELECT_PRIVACY_POLICY = By.name("agree");
    private static final By REGISTER_BUTTON = By.xpath("//input[@type='submit']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void fillForm(RegisterUser dataUser) {
        sendKeys(FIRST_NAME_FIELD_TEXT, dataUser.getFirstName());
        sendKeys(LAST_NAME_FIELD_TEXT, dataUser.getLastName());
        sendKeys(EMAIL_FIELD_TEXT, dataUser.getEmail());
        sendKeys(PHONE_FIELD_TEXT, dataUser.getTelephone());
        sendKeys(PASSWORD_FIELD_TEXT, dataUser.getPassword());
        sendKeys(CONFIRM_PASSWORD_FIELD_TEXT, dataUser.getPasswordConfirm());
        selectSubscribe(dataUser.getSubscribe());
        selectOptionCheckBox(SELECT_PRIVACY_POLICY, dataUser.getPrivacy());
    }

    private void selectSubscribe(Boolean status) {
        if (status) {
            clickOn(SELECT_SUBSCRIBE_YES);
        } else {
            clickOn(SELECT_SUBSCRIBE_NO);
        }
    }

    public AccountPage submitForm() {
        clickOn(REGISTER_BUTTON);
        return new AccountPage(getDriver());
    }

}
