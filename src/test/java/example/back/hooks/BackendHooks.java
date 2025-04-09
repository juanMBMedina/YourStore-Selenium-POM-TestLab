package example.back.hooks;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;

public class BackendHooks {
    @Before
    public void Logs(){
        RestAssured.filters(
                new RequestLoggingFilter(LogDetail.ALL),
                new ResponseLoggingFilter(LogDetail.ALL)
        );
    }
}
