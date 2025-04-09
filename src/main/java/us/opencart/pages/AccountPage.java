package us.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {
    // For future this class maybe extends of BasePageWithRight Bar
    private static final String CONTENT_BODY_FORMAT = "//div[@id='content']" + CONTAINS_TEXT_FORMAT;
    private static final String RIGHT_BAR_FORMAT = "//aside[@id='column-right']" + CONTAINS_TEXT_FORMAT;
    private static final By MY_ACCOUNT_LABEL = getByContainsText(CONTENT_BODY_FORMAT,"My Account");
    private static final By LOGOUT_TOP_BAR = getByContainsText(TOP_NAV_ITEM_FORMAT, "Logout");
    private static final By RIGHT_BAR = By.id("column-right");
    private static final By LOGOUT_RIGHT_BAR = getByContainsText(RIGHT_BAR_FORMAT, "Logout");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isDisplayedTitle(){
        return isDisplayed(MY_ACCOUNT_LABEL);
    }

    public boolean isDisplayedRightBar() {
        return isDisplayed(RIGHT_BAR);
    }

    public void logoutByTopBarOption() {
        clickOn(MY_ACCOUNT);
        clickOn(LOGOUT_TOP_BAR);
    }

    public void logoutByRightOption() {
        clickOn(LOGOUT_RIGHT_BAR);
    }
}
