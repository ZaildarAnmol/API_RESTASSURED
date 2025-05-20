package test;

import java.util.HashMap;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Login {
    static String token = null;

    @Test
    public void login() {
        HashMap<String, String> payload = new HashMap<String, String>();
        payload.put("username", "anmol@tekarch.com");
        payload.put("password", "zaildar");
        RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
        Response loginResponse = RestAssured.given().header("Content-Type", ContentType.JSON).when()
                .body(payload)
                .post("/login");
        System.out.println(loginResponse.statusCode());
        loginResponse.then().assertThat().statusCode(201);
        token = loginResponse.jsonPath().get("[0].token");
        System.out.println(token);
    }

    @Test(dependsOnMethods = "login")
    public void getUsers() {
        RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
        Response getUsersResponse = RestAssured.given().header("Content-Type", ContentType.JSON)
                .header("token", token).get("/getdata");
        System.out.println(getUsersResponse.statusCode());
        getUsersResponse.then().assertThat().statusCode(200);
        getUsersResponse.prettyPrint();
    }


}