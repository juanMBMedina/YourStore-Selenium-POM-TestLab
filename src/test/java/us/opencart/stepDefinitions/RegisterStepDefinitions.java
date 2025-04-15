package us.opencart.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import us.opencart.builders.RegisterUserBuilder;
import us.opencart.models.RegisterUser;
import us.opencart.pages.AccountPage;
import us.opencart.pages.HomePage;
import us.opencart.pages.RegisterPage;
import utils.DriverFactory;
import utils.TestDataLoader;

import static us.opencart.constants.RegisterPageConstants.*;

public class RegisterStepDefinitions {

    // If the page doesn't have a security certificates available
    HomePage homePage = new HomePage(DriverFactory.getDriverWithInsecureCerts());
    RegisterUser generatedRegisterUser;
    RegisterPage registerPage;
    AccountPage accPage;

    @Given("the user is on the registration page of Your Store")
    public void theUserIsOnTheRegistrationPageOfYourStore() {
        registerPage = homePage.goToRegisterPage();
    }

    @Given("the user can create a random user")
    public void theUserCanCreateARandomUser() {
        generatedRegisterUser = RegisterUserBuilder.registerAnUser(Boolean.TRUE, Boolean.TRUE);
    }

    @Given("the user enters the random user's data")
    public void theUserEntersTheRandomUserSData() {
        registerPage.fillForm(generatedRegisterUser);
    }

    @When("the user submits the registration form")
    public void theUserSubmitsTheRegistrationForm() {
        accPage = registerPage.submitForm();
    }

    @Then("the user should see a successful registration message")
    public void theUserShouldSeeASuccessfulRegistrationMessage() {
        Assert.assertTrue(String.format("Text {%s} doesn't view in the current page.", SUCCESS_REGISTER), registerPage.isVisibleText(SUCCESS_REGISTER));
    }

    @Given("the user enters an existing user with empty params")
    public void theUserEntersAnExistingUserWithEmptyParams(RegisterUser user) {
        registerPage.fillForm(user);

    }

    @Then("the user should see an error message {string} is void")
    public void theUserShouldSeeAnErrorMessageIsVoid(String paramText) {
        Assert.assertTrue(String.format("The %s no contains %s", registerPage.getDangerMssgs(), WITHOUT_PARAMS.get(paramText)),
                registerPage.getDangerMssgs().contains(WITHOUT_PARAMS.get(paramText)));
    }

    @Given("the user enters an existing user in a test file")
    public void theUserEntersAnExistingUserInATestFile() {
        registerPage.fillForm(TestDataLoader.load("dataRegisterFeature.json", "registerUser", RegisterUser.class));
    }

    @Given("the user enters an existing user in a test file without privacy")
    public void theUserEntersAnExistingUserInATestFileWithoutPrivacy() {
        registerPage.fillForm(TestDataLoader.load("dataRegisterFeature.json", "registerUserNoPrivacy", RegisterUser.class));
    }

    @Then("the user should see a user already exists error message")
    public void theUserShouldSeeAUserAlreadyExistsErrorMessage() {
        Assert.assertTrue("\"Alert Message\" is not displayed.", registerPage.isDisplayedAlertMssg());
        Assert.assertEquals("\"Alert Message\" is different.", USER_EXIST, registerPage.getAlertText());
    }

    @Then("the user should see a an error message when the register form doesn't have a privacy check OK")
    public void theUserShouldSeeAAnErrorMessageWhenTheRegisterFormDoesnTHaveAPrivacyCheckOK() {
        Assert.assertTrue("\"Alert Message\" is not displayed.", registerPage.isDisplayedAlertMssg());
        Assert.assertEquals("\"Alert Message\" is different.", WITHOUT_PRIVACY, registerPage.getAlertText());
    }


}
