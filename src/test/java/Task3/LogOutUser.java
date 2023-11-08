package Task3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LogOutUser {
    @Test
    public void verifyLogout() {
        // Define base URL
        RestAssured.baseURI = "https://petstore.swagger.io";

        // Send GET request
        Response response = given()
                .when()
                .get("/v2/user/logout");

        // Validate the response status is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Test Failed! The expected status code was 200, but got: " + response.getStatusCode());
    }
}
