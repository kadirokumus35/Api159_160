package post_request;

import base_urls.JsonPlaceHolderBaseUrl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;



import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Post06ObjectMappaer_Pojo extends JsonPlaceHolderBaseUrl {
      /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2) {
               "userId": 55,
               "title": "Tidy your room",
               "completed": false
               }

            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */

    @Test
    public void post06() throws JsonProcessingException {
        //set the urk
        spec.pathParam("first","todos");

        //set the expected data
       JsonPlaceHolderPojo expectedData =  new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println("expectedData : "+expectedData);

        //set the request and get the response
       Response response =  given(spec).body(expectedData).post("{first}");

       //do assertion
        JsonPlaceHolderPojo actualData = new ObjectMapper().readValue(response.asString(),JsonPlaceHolderPojo.class);
        System.out.println("actualData :"+actualData);


        assertEquals(201,response.statusCode());
        assertEquals(expectedData.getCompleted(),actualData.getCompleted());
        assertEquals(expectedData.getTitle(),actualData.getTitle());
        assertEquals(expectedData.getUserId(),actualData.getUserId());
    }

}
