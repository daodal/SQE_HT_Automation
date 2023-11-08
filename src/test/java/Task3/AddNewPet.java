package Task3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddNewPet {
    @Test
    public void verifyAddPet() {
        // Base URL
        RestAssured.baseURI = "https://petstore.swagger.io";

        // JSON data
        String petDetails = "{\n" +
                "  \"id\": 10,\n" +
                "  \"category\": {\n" +
                "    \"id\": 10,\n" +
                "    \"name\": \"dogs\"\n" +
                "  },\n" +
                "  \"name\": \"husky\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"https://images.wagwalkingweb.com/media/daily_wag/blog_articles/hero/1685787498.877709/fun-facts-about-siberian-huskies-1.png\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 10,\n" +
                "      \"name\": \"husky\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";

        // Send POST request and get response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(petDetails)
                .when()
                .post("/v2/pet");

        // Validate that the response status is 200
        Assert.assertEquals(response.getStatusCode(), 200, "Test Failed! The expected status code was 200, but got: " + response.getStatusCode());
    }
}

