package restUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RequestUtils {
    String token = null;

    public RequestUtils() {
        RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net";
        token = this.getToken();

    }

    public String getToken() {
        String payload = "{\"username\": \"mithun@ta.com\", \"password\": \"mithun\"}";
        String endpoint = "/login";
        Response res = RestAssured.given().header("Content-Type", ContentType.JSON).when().body(payload).post(endpoint);
        return res.jsonPath().get("[0].token");
    }

    public Response postRequest(String payload, String endpoint) {
        return RestAssured.given().header("Content-Type", ContentType.JSON).header("token", token).when().body(payload)
                .post(endpoint);
    }

    public Response getRequest(String endpoint) {
        return RestAssured.given().header("Content-Type", ContentType.JSON).header("token", token).when()
                .get(endpoint);
    }

}
