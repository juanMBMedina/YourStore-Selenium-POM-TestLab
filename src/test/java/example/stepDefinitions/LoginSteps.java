package example.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import com.example.pages.LoginPage;

import static com.example.constants.HomeConstant.ALERT_MESSAGE;
import static com.example.constants.HomeConstant.UNSUCCESSFULL_MESSAGE;
import static utils.DriverFactory.getDriver;


public class LoginSteps {

    LoginPage loginPage = new LoginPage(getDriver());

    @Given("the username {string} and password {string}")
    public void theUsernameAndPassword(String userName, String password) {
        loginPage.loginWithCredentials(userName,password);

    }
    @When("the user do a login")
    public void theUserDoALogin() {

    }
    @Then("validate the success message and home page")
    public void validateTheSuccessMessageAndHomePage() {
        Assert.assertEquals(ALERT_MESSAGE, loginPage.getAlertMessage().trim().replaceAll("\\n",""));
    }

    @Then("validate the unsuccessfull with {string} message")
    public void validateTheUnsuccessfullWithMessage(String messageInfo) {
        Assert.assertEquals(String.format(UNSUCCESSFULL_MESSAGE,messageInfo), loginPage.getAlertMessage().trim().replaceAll("\\n",""));
    }

}
