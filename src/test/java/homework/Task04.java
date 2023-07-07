package homework;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

public class Task04 extends PetStoreBaseUrl {
    /*
   Given
       https://petstore.swagger.io/v2/pet/findByStatus?status=available
   When
       User sens Get request
   Then
       Assert that number of pets whose status is "available" is more than 100
    */
    @Test
    public void petStoreAvailablePets(){
        spec.pathParams("first","pet","second","findByStatus").
                queryParam("status","available");

        Response response=given(spec).get("{first}/{second}");
        response.prettyPrint();

        int numberOfAvailablePet = response.jsonPath().getList("id").size();
        assertTrue(numberOfAvailablePet>100);

    }
}
