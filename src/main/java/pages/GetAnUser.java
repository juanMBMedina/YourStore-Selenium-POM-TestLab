package pages;

import io.restassured.response.Response;
import models.CreateAnUserBack;

import static io.restassured.RestAssured.given;

public class GetAnUser {
    private static final String BASE_PATH = "/users/{id}";

    public static Response withId(int id){
        return given()
                .pathParam("id", id)
                .when()
                .get(BASE_PATH);
    }
}
