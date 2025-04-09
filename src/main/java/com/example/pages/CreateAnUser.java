package com.example.pages;

import com.example.models.CreateAnUserBack;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateAnUser {
    private static final String BASE_PATH = "/users";

    public static Response withInfo(CreateAnUserBack userBack){
        return given()
                .header("Content-Type", "application/json")
                .body(userBack)
                .when()
                .post(BASE_PATH);
    }
}
