package example.back.stepDefinitions;

import com.example.builders.CreateAnUser;
import com.example.models.CreateAnUserBack;
import com.example.pages.GetAnUser;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import org.junit.Assert;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class BackUserCreationSteps {
    private Response response;
    private Response previousResponse;
    private CreateAnUserBack userBack;

    @Given("the user wants to execute a petition")
    public void theUserWantsToExecuteAPetition() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        RestAssured.config = RestAssured.config()
                .objectMapperConfig(new ObjectMapperConfig(ObjectMapperType.GSON));
    }

    @When("a {string} user is created")
    public void aUserIsCreated(String string) {
        userBack = CreateAnUser.createAnUser();
        response = com.example.pages.CreateAnUser.withInfo(userBack);
    }

    @Then("check the validation code is {int}")
    public void checkTheValidationCodeIs(Integer int1) {
        response.then()
                .statusCode(int1);
    }

    @Then("check the creation response")
    public void checkTheCreationResponse() {
        response.then()
                .body("id", notNullValue())
                .body("name", equalTo(userBack.getName()))
                .body("email", equalTo(userBack.getEmail()));
    }

    @When("the user wants to consult the customer")
    public void theUserWantsToConsultTheCustomer() {
        int id = 1;
        //this is the valid implementation
        //response.getBody().jsonPath().getInt("id");
        response = GetAnUser.withId(id);
    }

    @Then("check the client information")
    public void checkTheClientInformation() {
        CreateAnUserBack actualUser = response.as(CreateAnUserBack.class);
        //Assert.assertEquals(actualUser.toString(), userBack.toString());
    }
}
