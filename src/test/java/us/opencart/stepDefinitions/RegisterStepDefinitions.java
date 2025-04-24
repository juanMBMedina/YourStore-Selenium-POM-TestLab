package us.opencart.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import us.opencart.builders.RegisterUserBuilder;
import us.opencart.models.RegisterUser;
import us.opencart.pages.AccountPage;
import us.opencart.pages.HomePage;
import us.opencart.pages.RegisterPage;
import utils.DriverFactory;
import utils.TestDataLoader;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static us.opencart.constants.RegisterPageConstants.SUCCESS_REGISTER;
import static us.opencart.constants.RegisterPageConstants.WITHOUT_PARAMS;
import static us.opencart.constants.RegisterPageConstants.USER_EXIST;
import static us.opencart.constants.RegisterPageConstants.WITHOUT_PRIVACY;

public class RegisterStepDefinitions {
    private static final Logger logger = Logger.getLogger(RegisterStepDefinitions.class.getName());
    // If the page doesn't have a security certificates available
    private final HomePage homePage = new HomePage(DriverFactory.getDriverWithInsecureCerts());
    private RegisterUser generatedRegisterUser;
    private RegisterPage registerPage;

    @Given("the user is on the registration page of Your Store")
    public void theUserIsOnTheRegistrationPageOfYourStore() {
        registerPage = homePage.goToRegisterPage();
    }

    @Given("the user can create a random user")
    public void theUserCanCreateARandomUser() {
        generatedRegisterUser = RegisterUserBuilder.registerAnUser(Boolean.TRUE, Boolean.TRUE);
        logger.log(Level.INFO, "generatedRegisterUser = " + generatedRegisterUser);
    }

    @Given("the user enters the random user's data")
    public void theUserEntersTheRandomUserSData() {
        registerPage.fillForm(generatedRegisterUser);
    }

    @When("the user submits the registration form")
    public void theUserSubmitsTheRegistrationForm() {
        AccountPage accPage = registerPage.submitForm();
    }

    @Then("the user should see a successful registration message")
    public void theUserShouldSeeASuccessfulRegistrationMessage() {
        assertTrue(String.format("Text {%s} doesn't view in the current page.", SUCCESS_REGISTER), registerPage.isVisibleText(SUCCESS_REGISTER));
    }

    @Given("the user enters an existing user with empty params")
    public void theUserEntersAnExistingUserWithEmptyParams(RegisterUser user) {
        registerPage.fillForm(user);

    }

    @Then("the user should see an error message {string} is void")
    public void theUserShouldSeeAnErrorMessageIsVoid(String paramText) {
        assertTrue(String.format("The %s no contains %s", registerPage.getDangerMssgs(), WITHOUT_PARAMS.get(paramText)),
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
        assertTrue("\"Alert Message\" is not displayed.", registerPage.isDisplayedAlertMssg());
        assertEquals("\"Alert Message\" is different.", USER_EXIST, registerPage.getAlertText());
    }

    @Then("the user should see a an error message when the register form doesn't have a privacy check OK")
    public void theUserShouldSeeAAnErrorMessageWhenTheRegisterFormDoesnTHaveAPrivacyCheckOK() {
        assertTrue("\"Alert Message\" is not displayed.", registerPage.isDisplayedAlertMssg());
        assertEquals("\"Alert Message\" is different.", WITHOUT_PRIVACY, registerPage.getAlertText());
    }

}
