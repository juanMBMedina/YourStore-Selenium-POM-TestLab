package us.opencart.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import us.opencart.pages.AccountPage;
import us.opencart.pages.HomePage;
import us.opencart.pages.LoginPage;
import utils.DriverFactory;

public class LoginStepDefinitions {

    // If the page doesn't have a security certificates available
    HomePage homePage = new HomePage(DriverFactory.getDriverWithInsecureCerts());
    LoginPage loginPage;
    AccountPage accPage;

    @Given("the user is on the login page of Your Store")
    public void theUserIsOnTheLoginPageOfYourStore() {
        loginPage = homePage.goToLoginPage();
    }

    @Given("the user enters valid credentials with username {string} and password {string}")
    public void theUserEntersValidCredentialsWithUsernameAndPassword(String userName, String userPassword) {
        loginPage.fillForm(userName, userPassword);
    }

    @When("the user submits the login form")
    public void theUserSubmitsTheLoginForm() {
        accPage = loginPage.submitForm();
    }

    @Then("the user should see a successful login message")
    public void theUserShouldSeeASuccessfulLoginMessage() {
        Assert.assertTrue("Login unsuccessful: \"My Account\" title is not displayed.", accPage.isDisplayedTitle());
        Assert.assertTrue("Login unsuccessful: The right sidebar of the My Account page is not displayed.", accPage.isDisplayedRightBar());
    }

}
