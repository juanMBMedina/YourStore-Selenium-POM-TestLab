package us.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountPage extends BasePage {
    // For future this class maybe extends of BasePageWithRight Bar
    private static final String CONTENT_BODY_FORMAT = "//div[@id='content']//*[contains(text(),'%s')]";
    private static final By MY_ACCOUNT_LABEL = getByContainsText(CONTENT_BODY_FORMAT,"My Account");
    private static final By RIGHT_BAR = By.id("column-right");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isDisplayedTitle(){
        return isDisplayed(MY_ACCOUNT_LABEL);
    }

    public boolean isDisplayedRightBar() {
        return isDisplayed(RIGHT_BAR);
    }
}
