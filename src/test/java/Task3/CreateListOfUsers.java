package Task3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateListOfUsers {
    @Test
    public void verifyCreateUser() {

        // Define base URI
        RestAssured.baseURI = "https://petstore.swagger.io";

        // Define user array
        String users = "[{\"id\": 1, \"username\": \"user1\", \"firstName\": \"firstName1\", \"lastName\": \"lastName1\", \"email\": \"email1@test.com\", \"password\": \"Password1\", \"phone\": \"0123456789\", \"userStatus\": 1},"+
                "{\"id\": 2, \"username\": \"user2\", \"firstName\": \"firstName2\", \"lastName\": \"lastName2\", \"email\": \"email2@test.com\", \"password\": \"Password1\", \"phone\": \"0123456789\", \"userStatus\": 1},"+
                "{\"id\": 3, \"username\": \"user3\", \"firstName\": \"firstName3\", \"lastName\": \"lastName3\", \"email\": \"email3@test.com\", \"password\": \"Password1\", \"phone\": \"0123456789\", \"userStatus\": 1}]";

        // Send POST request & get response
        Response resp = given()
                .contentType(ContentType.JSON)
                .body(users)
                .post("/v2/user/createWithArray");

        // Verification
        Assert.assertEquals(resp.getStatusCode(), 200, "Test Failed! The expected status code was 200, but got: " + resp.getStatusCode());
    }
}
