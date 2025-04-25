package us.opencart.pages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import us.opencart.models.SearchItemNavBar;

import java.util.List;
import java.util.stream.Collectors;

import static us.opencart.constants.AddToCartPageConstants.DEFAULT_VALUE;

@Getter
@AllArgsConstructor
public abstract class BasePage {

    private WebDriver driver;

    protected static final String CONTAINS_TEXT_FORMAT = "//*[contains(text(),'%s')]";
    protected static final String TOP_NAV_ITEM_FORMAT = "//nav[@id='top']" + CONTAINS_TEXT_FORMAT;
    protected static final String NAV_BAR_ITEM_FORMAT = "//ul[@class='nav navbar-nav']" + CONTAINS_TEXT_FORMAT;
    protected static final By MSSG_ALERT_DIV = By.className("alert");
    private static final By MSSG_DANGER_DIV = By.className("text-danger");
    protected static final By MY_ACCOUNT = getByContainsText(TOP_NAV_ITEM_FORMAT, "My Account");
    protected static final By WISH_LIST = getByContainsText(TOP_NAV_ITEM_FORMAT, "Wish List");
    protected static final By SHOPPING_CART = getByContainsText(TOP_NAV_ITEM_FORMAT, "Shopping Cart");

    public static By getByContainsText(String xpathFormat, String containsText) {
        // Format for: '%s' -> Text
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

    public void validateAlertText(String expectedText){
        Assert.assertTrue("Alert Text: " + getAlertText() + " doesn't have the text: " + expectedText, getAlertText().contains(expectedText));
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

    public void selectItemNavBarOption(SearchItemNavBar option){
        clickOn(getByContainsText(NAV_BAR_ITEM_FORMAT, option.getCategory()));
        if(!option.getSubcategory().equals(DEFAULT_VALUE)){
            clickOn(getByContainsText(NAV_BAR_ITEM_FORMAT, option.getSubcategory()));
        }
    }
}
