package Task3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UserLogin {
    @Test
    public void verifyLogin() {
        // Define base URL
        RestAssured.baseURI = "https://petstore.swagger.io";

        // Define user array
        String users = "[{\"id\": 1, \"username\": \"user1\", \"firstName\": \"firstName1\", \"lastName\": \"lastName1\", \"email\": \"email@test.com\", \"password\": \"Password1\", \"phone\": \"0123456789\", \"userStatus\": 1}]";

        // Send POST request & get response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(users)
                .post("/v2/user/createWithArray");

        // Send GET request and get response
        Response resp = given()
                .queryParam("username", "user1")
                .queryParam("password", "Password1")
                .when()
                .get("/v2/user/login");

        // Verify that the response status is 200
        Assert.assertEquals(resp.getStatusCode(), 200, "Test Failed! The expected status code was 200, but got: " + resp.getStatusCode());
    }
}
