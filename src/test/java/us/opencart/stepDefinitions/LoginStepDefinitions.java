package us.opencart.stepDefinitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import us.opencart.pages.AccountPage;
import us.opencart.pages.HomePage;
import us.opencart.pages.LoginPage;
import utils.DriverFactory;

import static us.opencart.constants.LoginPageConstants.*;

public class LoginStepDefinitions {

    // If the page doesn't have a security certificates available
    HomePage homePage = new HomePage(DriverFactory.getDriverWithInsecureCerts());
    LoginPage loginPage;
    AccountPage accPage;

    @Given("the user is on the login page of Your Store")
    public void theUserIsOnTheLoginPageOfYourStore() {
        loginPage = homePage.goToLoginPage();
    }

    @Given("the user enters credentials with username {string} and password {string}")
    public void theUserEntersCredentialsWithUsernameAndPassword(String userName, String userPassword) {
        loginPage.fillForm(userName, userPassword);
    }

    @When("the user submits the login form")
    public void theUserSubmitsTheLoginForm() {
        accPage = loginPage.submitForm();
    }

    @When("the user sends credentials with username {string} and password {string} for {int} attempts")
    public void theUserSendsCredentialsWithUsernameAndPasswordForAttempts(String userName, String userPassword, Integer attemps) {
        for (int i = 1; i <= attemps; i++) {
            theUserEntersCredentialsWithUsernameAndPassword(userName, userPassword);
            theUserSubmitsTheLoginForm();
        }
    }

    @When("the user can do logout by Top Bar option")
    public void theUserCanDoLogoutByTopBarOption() {
        accPage.logoutByTopBarOption();
    }

    @When("the user can do logout by Right Bar option")
    public void theUserCanDoLogoutByRightBarOption() {
        accPage.logoutByRightOption();
    }

    @Then("the user should see a successful login message")
    public void theUserShouldSeeASuccessfulLoginMessage() {
        Assert.assertTrue("Login unsuccessful: \"My Account\" title is not displayed.", accPage.isDisplayedTitle());
        Assert.assertTrue("Login unsuccessful: The right sidebar of the My Account page is not displayed.", accPage.isDisplayedRightBar());
    }

    @Then("the user should see a unsuccessful login message")
    public void theUserShouldSeeAUnsuccessfulLoginMessage() {
        Assert.assertTrue("\"Alert Message\" is not displayed.", loginPage.isDisplayedAlertMssg());
        if (loginPage.getAlertText().equals(MAX_ATTEMPTS_MESSAGE)) {
            throw new PendingException(EXCEPTION_ATTEMPTS_MESSAGE);
        }
        Assert.assertEquals("\"Alert Message\" is different.", UNSUCCESSFULLY_MESSAGE, loginPage.getAlertText());
    }

    @Then("the user should see an error message indicating that the maximum number of login attempts has been reached")
    public void theUserShouldSeeAnErrorMessageIndicatingThatTheMaximumNumberOfLoginAttemptsHasBeenReached() {
        Assert.assertTrue("\"Alert Message\" is not displayed.", loginPage.isDisplayedAlertMssg());
        Assert.assertEquals("\"Alert Message\" is different.", MAX_ATTEMPTS_MESSAGE, loginPage.getAlertText());
    }

    @Then("the user should see a successful logout message")
    public void theUserShouldSeeASuccessfulLogoutMessage() {
        Assert.assertTrue("\"Logout Message\" is not visible.", accPage.isVisibleText(SUCCESSFULLY_LOGOUT));
    }
}
