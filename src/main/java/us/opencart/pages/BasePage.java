package us.opencart.pages;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


@Getter
@AllArgsConstructor
public abstract class BasePage {
    private WebDriver driver;

    protected static final String TOP_NAV_ITEM_FORMAT = "//nav[@id='top']//*[contains(text(),'%s')]";
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
        getDriver().findElement(selector).click();
    }

    public String getText(By selector) {
        return getDriver().findElement(selector).getText();
    }

    public void sendKeys(By selector, String text) {
        getDriver().findElement(selector).clear();
        getDriver().findElement(selector).sendKeys(text);
    }

    public Boolean isDisplayed(By selector){
        return getDriver().findElement(selector).isDisplayed();
    }

}
