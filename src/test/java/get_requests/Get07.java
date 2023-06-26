package get_requests;

import base_urls.PetStoreBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class Get07 extends PetStoreBaseUrl {
     /*
    Given
        https://petstore.swagger.io/v2/pet/3467889
    When
         User send a GET request to the URL
    Then
        HTTP Status Code should be 200
    And
        Response content type is "application/json"
    And
        Response body should be like;
           {
            "id": 3467889,
            "category": {
                "id": 0,
                "name": "Bird"
            },
            "name": "Tweety",
            "photoUrls": [
                "string"
            ],
            "tags": [
                {
                    "id": 0,
                    "name": "string"
                }
            ],
            "status": "available"
           }

     */

    @Test//Import @Test annotation from Junit
    public void get07() {
        //Set the url
        spec.pathParams("first","pet","second",3467889);

        //Set the expected data

        //Send the request and get the response

        Response response = given(spec).get("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        //1st Way:
        response.
                then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("category.name",equalTo("Bird"),
                        "name",equalTo("Tweety"),
                        "status",equalTo("available"));



        //2nd Way:
        JsonPath jsonPath=response.jsonPath();
assertEquals("Bird",jsonPath.getString("category.name"));
        assertEquals("Tweety", jsonPath.getString("name"));
        assertEquals("available", jsonPath.getString("status"));


    }
}
