package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static put_request.utils.ObjectMapperUtils.convertJsonToJava;

public class Get12 extends JsonPlaceHolderBaseUrl {

            /*
               Given
                   https://jsonplaceholder.typicode.com/todos/198
               When
                    I send GET Request to the URL
                Then
                    Status code is 200
                    And response body is like {
                                               "userId": 10,
                                               "id": 198,
                                               "title": "quis eius est sint explicabo",
                                               "completed": true
                                             }
             */

    @Test
    public void get12(){

        spec.pathParams("first","todos","second",198);

        String str="{\n" +
                "\"userId\": 10,\n" +
                "\"id\": 198,\n" +
                "\"title\": \"quis eius est sint explicabo\",\n" +
                "\"completed\": true\n" +
                "}";
       Map<String,Object> expectedData = convertJsonToJava(str, HashMap.class);
        System.out.println("exp : "+expectedData);

       Response response = given(spec).get("{first}/{second}");
       response.prettyPrint();
       //do assertion
        Map<String,Object>  actualData = convertJsonToJava(response.asString(),HashMap.class);
        System.out.println("act:"+actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
    }
}
