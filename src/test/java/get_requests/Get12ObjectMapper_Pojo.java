package get_requests;

import base_urls.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static put_request.utils.ObjectMapperUtils.convertJsonToJava;


public class Get12ObjectMapper_Pojo extends JsonPlaceHolderBaseUrl {
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
    public void get12(){// best practice--> pojo class + object mapper
        spec.pathParams("first","todos","second",198);

        String str="{\n" +
                "\"userId\": 10,\n" +
                "\"id\": 198,\n" +
                "\"title\": \"quis eius est sint explicabo\",\n" +
                "\"completed\": true\n" +
                "}";
       JsonPlaceHolderPojo expectedData = convertJsonToJava(str, JsonPlaceHolderPojo.class);
        System.out.println("exp"+expectedData);


       Response response = given(spec).get("{first}/{second}");
        JsonPlaceHolderPojo actualData = convertJsonToJava(response.asString(),JsonPlaceHolderPojo.class);
        System.out.println("act"+actualData);


        assertEquals(200,response.statusCode());

        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());
        assertEquals(expectedData.getUserId(),actualData.getUserId());

    }
}
