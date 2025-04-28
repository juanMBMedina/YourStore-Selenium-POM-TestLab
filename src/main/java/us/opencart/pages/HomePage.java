package us.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import us.opencart.constants.AddToCartPageConstants;
import us.opencart.exceptions.TypeOptionItemException;


public class HomePage extends BasePage {
    private static final String ITEM_CONTAINER_FORMAT = "//div[@class='product-thumb'][.//h4/a[normalize-space(text())='%s']]";
    private static final String ADD_TO_CART_SINGLE_XPATH = "String.format(CONTAINS_TEXT_FORMAT, 'Add to Cart')";
    private static final String ADD_WISH_LIST_SINGLE_XPATH = "//button[@data-original-title='Add to Wish List']";
    private static final String COMPARISON_SINGLE_XPATH = "//button[@data-original-title='Compare this Product']";
    private static final By LOGIN = getByContainsText(TOP_NAV_ITEM_FORMAT, "Login");
    private static final By REGISTER = getByContainsText(TOP_NAV_ITEM_FORMAT, "Register");
    private static final By WISH_LIST = getByContainsText(TOP_NAV_ITEM_FORMAT, "Wish List");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage goToLoginPage() {
        clickOn(MY_ACCOUNT);
        clickOn(LOGIN);
        return new LoginPage(getDriver());
    }

    public RegisterPage goToRegisterPage() {
        clickOn(MY_ACCOUNT);
        clickOn(REGISTER);
        return new RegisterPage(getDriver());
    }

    private By getAddToCartButtonBy(String itemName) {
        return getByContainsText(ITEM_CONTAINER_FORMAT + ADD_TO_CART_SINGLE_XPATH, itemName);
    }

    private By getAddToWishListButtonBy(String itemName) {
        return getByContainsText(ITEM_CONTAINER_FORMAT + ADD_WISH_LIST_SINGLE_XPATH, itemName);
    }

    private By getComparisonButtonBy(String itemName) {
        return getByContainsText(ITEM_CONTAINER_FORMAT + COMPARISON_SINGLE_XPATH, itemName);
    }

    public void clickOnAddToCartButton(String itemName) {
        clickOn(getAddToCartButtonBy(itemName));
    }

    public void clickOnWishListButton(String itemName) {
        clickOn(getAddToWishListButtonBy(itemName));
    }

    public void clickOnComparisonButton(String itemName) {
        clickOn(getComparisonButtonBy(itemName));
    }

    public WishListPage goToWishListPage() {
        clickOn(WISH_LIST);
        return new WishListPage(getDriver());
    }

    public void clickOnItemOption(String itemName, String option) {
        switch (option) {
            case AddToCartPageConstants.COMPARISON_TEXT:
                clickOnComparisonButton(itemName);
                break;
            case AddToCartPageConstants.WISH_LIST_TEXT:
                clickOnWishListButton(itemName);
                break;
            case AddToCartPageConstants.ADD_TO_CART_TEXT:
                clickOnAddToCartButton(itemName);
                break;
            default:
                throw new TypeOptionItemException(String.format("This option: %s doesn't exist for an item.", option));
        }
    }
}
