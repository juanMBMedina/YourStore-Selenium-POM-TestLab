package us.opencart.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import us.opencart.constants.AddToCartPageConstants;
import us.opencart.models.SearchItemNavBar;
import us.opencart.pages.AddToCartPage;
import us.opencart.pages.HomePage;
import us.opencart.pages.WishListPage;
import utils.DriverFactory;

public class AddToCartStepDefinitions {
    // If the page doesn't have a security certificates available
    private final HomePage homePage = new HomePage(DriverFactory.getDriverWithInsecureCerts());
    private WishListPage wishListPage;
    private AddToCartPage addToCartPage;
    private String itemName;

    @Given("the user searches for an item in the navigation bar")
    public void theUserSearchesForAnItemInTheNavigationBar(SearchItemNavBar option) {
        homePage.selectItemNavBarOption(option);
        itemName = option.getItemName();
    }

    @When("the user clicks the comparison link for the item")
    public void theUserClicksTheComparisonLinkForTheItem() {
        homePage.clickOnComparisonButton(itemName);
    }

    @When("the user clicks the {string} link for the item")
    public void theUserClicksTheLinkForTheItem(String option) {
        homePage.clickOnItemOption(itemName, option);
    }

    @Then("the user should see a successful comparison item message")
    public void theUserShouldSeeASuccessfulComparisonItemMessage() {
        homePage.validateAlertText(String.format(AddToCartPageConstants.COMPARISON_MESSAGE_FORMAT, itemName));
    }

    @Then("the user should see a successful item added to the Wish List message")
    public void theUserShouldSeeASuccessfulItemAddedToTheWishListMessage() {
        homePage.validateAlertText(String.format(AddToCartPageConstants.WISH_LIST_MESSAGE_FORMAT, itemName));
    }

    @Then("the user should see the selected item in the Wish List")
    public void theUserShouldSeeTheSelectedItemInTheWishList() {
        Assert.assertTrue("Items table didn't display", wishListPage.isDisplayedItemsTable());
        Assert.assertTrue(String.format("Item: %s wasn't display", itemName), wishListPage.isVisibleInTable(itemName));
    }

    @Given("the user is on the Wish List page")
    public void theUserIsOnTheWishListPage() {
        wishListPage = homePage.goToWishListPage();
    }

    @When("the user clicks the {string} link for the item called {string} in Wish List page")
    public void theUserClicksTheLinkForTheItemCalledInWishListPage(String option, String itemName) {
        wishListPage.clickOnItemAction(itemName, option);
    }

    @Then("the user should see a message confirming the successful removal from the Wish List")
    public void theUserShouldSeeAMessageConfirmingTheSuccessfulRemovalFromTheWishList() {
        homePage.validateAlertText(AddToCartPageConstants.WISH_LIST_CHANGED_MESSAGE_FORMAT);
    }

    @Then("the user should see a successful Add to Cart item message")
    public void theUserShouldSeeASuccessfulAddToCartItemMessage() {
        homePage.validateAlertText(String.format(AddToCartPageConstants.ADD_TO_CART_MESSAGE_FORMAT, itemName));
    }

    @Then("the user is on the add to Cart Page")
    public void theUserIsOnTheAddToCartPage() {
        addToCartPage = homePage.goToAddToCart();
    }

    @Then("the user should see the selected item in the Add to Cart")
    public void theUserShouldSeeTheSelectedItemInTheAddToCart() {
        Assert.assertTrue("Items table didn't display", addToCartPage.isDisplayedItemsTable());
        Assert.assertTrue(String.format("Item: %s wasn't display", itemName), addToCartPage.isVisibleInTable(itemName));
    }

    @When("the user clicks the {string} link for the item called {string} in Add to Cart page")
    public void theUserClicksTheLinkForTheItemCalledInAddToCartPage(String option, String itemName) throws InterruptedException {
        addToCartPage.clickOnItemAction(itemName, option);
    }

    @Then("the user should see a message confirming the successful removal from the Add to Cart")
    public void theUserShouldSeeAMessageConfirmingTheSuccessfulRemovalFromTheAddToCart() {
        Assert.assertFalse(String.format("Item: %s was display", itemName), addToCartPage.isNotVisibleInTable(itemName));
    }
}
