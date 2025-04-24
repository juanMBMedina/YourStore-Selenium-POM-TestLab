package us.opencart.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import us.opencart.constants.AddToCartPageConstants;
import us.opencart.models.SearchItemNavBar;
import us.opencart.pages.HomePage;
import utils.DriverFactory;

public class AddToCartStepDefinitions {
    // If the page doesn't have a security certificates available
    private final HomePage homePage = new HomePage(DriverFactory.getDriverWithInsecureCerts());
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

    @Then("the user should see a successful comparison item message")
    public void theUserShouldSeeASuccessfulComparisonItemMessage() {
        String expectedText = String.format(AddToCartPageConstants.COMPARISON_MESSAGE_FORMAT, itemName);
        Assert.assertTrue("Alert Text: " + homePage.getAlertText() + " doesn't have the text: " + expectedText, homePage.getAlertText().contains(expectedText));
    }

}
