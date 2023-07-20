package delete_request;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static put_request.utils.ObjectMapperUtils.convertJsonToJava;

public class Delete01 extends JsonPlaceHolderBaseUrl {
     /*
        Given
            https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */

    @Test
    public void delete01() {
        //Set the url
        spec.pathParams("first", "todos", "second", 198);

        //Set the expected data
        Map<String, String> expectedData = new HashMap<>();
        System.out.println("expectedData = " + expectedData);

        //Send the request and get the response
        Response response = given(spec).delete("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        Map<String, String> actualData = convertJsonToJava(response.asString(), HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200, response.statusCode());

        //1st Way:
        assertEquals(expectedData, actualData);

        //2nd Way:
        assertTrue(actualData.isEmpty());

        //3rd Way:
        assertEquals(0, actualData.size());

        //Be careful when you do delete request. Do not delete anyone else's data. First create data and delete it.
    }
}