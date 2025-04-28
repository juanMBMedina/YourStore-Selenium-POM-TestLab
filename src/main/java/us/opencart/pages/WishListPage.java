package us.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import us.opencart.constants.AddToCartPageConstants;
import us.opencart.exceptions.TypeOptionItemException;
import utils.ByUtils;

public class WishListPage extends BasePage {

    public static final String TABLE_XPATH_BASE = "//div[@class='table-responsive']//tbody";
    public static final String XPATH_TABLE_BASE = TABLE_XPATH_BASE + EQUALS_TEXT_FORMAT + "/ancestor::tr//td[%s]";
    public static final String XPATH_ACTIONS_ITEM = "//*[@data-original-title='%s']";
    public static final By ITEMS_TABLE = By.xpath(TABLE_XPATH_BASE);

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    protected By getByOf(String nameItem, Integer indexColumn) {
        return By.xpath(String.format(XPATH_TABLE_BASE, nameItem, indexColumn.toString()));
    }

    public By getItemNameInTable(String itemName) {
        return getByOf(itemName, 2);
    }

    public By getItemActionsInTable(String itemName) {
        return getByOf(itemName, 6);
    }

    public By getItemActionInTable(String itemName, String option) {
        By baseBy = getItemActionsInTable(itemName);
        By childBy = By.xpath(String.format(XPATH_ACTIONS_ITEM, option));
        return ByUtils.combineXpathDescendant(baseBy, childBy);
    }

    public void clickOnItemAction(String itemName, String option) {
        switch (option) {
            case AddToCartPageConstants.REMOVE_ITEM_TEXT:
            case AddToCartPageConstants.ADD_TO_CART_TEXT:
                clickOn(getItemActionInTable(itemName, option));
                break;
            default:
                throw new TypeOptionItemException(String.format("This option: %s doesn't exist for an item.", option));
        }
    }

    public Boolean isDisplayedItemsTable() {
        return getElementBy(ITEMS_TABLE).isDisplayed();
    }

    public Boolean isVisibleInTable(String itemName) {
        return getElementBy(getItemNameInTable(itemName)).isDisplayed();
    }

    public Boolean isNotVisibleInTable(String itemName) {
        return getElementsBy(getItemNameInTable(itemName)).isEmpty();
    }
}
