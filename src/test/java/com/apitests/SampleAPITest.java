package com.example.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SampleAPITest {

    @BeforeClass
    public void setup() {
        // Base URL for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void verifyGetPostAPI() {
        Response response = RestAssured
                .given()
                .when()
                .get("/posts/1")
                .then()
                .extract()
                .response();

        // Print response for debugging
        System.out.println("Response: " + response.asString());

        // Basic validations
        Assert.assertEquals(response.statusCode(), 200, "Status code mismatch!");
        Assert.assertTrue(response.jsonPath().getString("title") != null, "Title is missing!");
    }

    @Test
    public void verifyUserAPI() {
        Response response = RestAssured
                .given()
                .when()
                .get("/users/1")
                .then()
                .extract()
                .response();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("username"), "Bret");
    }
}
