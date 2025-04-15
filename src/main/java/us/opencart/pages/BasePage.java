package us.opencart.pages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;


@Getter
@AllArgsConstructor
public abstract class BasePage {

    private WebDriver driver;

    protected static final String CONTAINS_TEXT_FORMAT = "//*[contains(text(),'%s')]";
    protected static final String TOP_NAV_ITEM_FORMAT = "//nav[@id='top']" + CONTAINS_TEXT_FORMAT;
    protected static final By MSSG_ALERT_DIV = By.className("alert");
    private static final By MSSG_DANGER_DIV = By.className("text-danger");
    protected static final By MY_ACCOUNT = getByContainsText(TOP_NAV_ITEM_FORMAT, "My Account");
    protected static final By WISH_LIST = getByContainsText(TOP_NAV_ITEM_FORMAT, "Wish List");
    protected static final By SHOPPING_CART = getByContainsText(TOP_NAV_ITEM_FORMAT, "Shopping Cart");

    public static By getByContainsText(String xpathFormat, String containsText) {
        // Format for: //[contains(text(), '%s')]
        return By.xpath(String.format(xpathFormat, containsText));
    }

    public WebElement getElementBy(By selector) {
        return getDriver().findElement(selector);
    }

    public void clickOn(By selector) {
        getElementBy(selector).click();
    }

    public String getText(By selector) {
        return getElementBy(selector).getText();
    }

    public void sendKeys(By selector, String text) {
        getElementBy(selector).clear();
        getElementBy(selector).sendKeys(text != null ? text : "");
    }

    public String getAlertText() {
        return getText(MSSG_ALERT_DIV);
    }

    public Boolean isDisplayedAlertMssg() {
        return isDisplayed(MSSG_ALERT_DIV);
    }

    public Boolean isVisibleText(String text) {
        return isDisplayed(By.xpath(String.format(CONTAINS_TEXT_FORMAT, text)));
    }

    public Boolean isDisplayed(By selector) {
        return getElementBy(selector).isDisplayed();
    }

    public void selectOptionCheckBox(By selector, Boolean status) {
        WebElement checkBox = getElementBy(selector);
        if (checkBox.isSelected() != status) {
            checkBox.click();
        }
    }

    public List<String> getDangerMssgs() {
        return getDriver().findElements(MSSG_DANGER_DIV).stream()
                .map(WebElement::getText).collect(Collectors.toList());
    }
}
