package us.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WishListPage extends BasePage {

    public static final String TABLE_XPATH_BASE = "//div[@class='table-responsive']//tbody";
    public static final String TEXT_XPATH_BASE = "//div[@class='table-responsive']//tbody" + CONTAINS_TEXT_FORMAT;
    public static final By ITEMS_TABLE = By.xpath(TABLE_XPATH_BASE);

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getTableElementBy(String text){
        return getElementBy(By.xpath(String.format(TEXT_XPATH_BASE, text)));
    }

    public Boolean isDisplayedItemsTable() {
        return getElementBy(ITEMS_TABLE).isDisplayed();
    }
}
