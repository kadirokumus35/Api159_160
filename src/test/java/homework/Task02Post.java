package homework;

import base_urls.RegresBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.RegresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static test_data.RegresTestData.regresTestDataMethod;

public class Task02Post extends RegresBaseUrl {

	  /*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
     */
    @Test
    public void Post02(){
        //set the url
        spec.pathParam("first","users");

        //set the expected data
        RegresTestData regresTestData = new RegresTestData();
        Map<String,String > expectedData=regresTestData.regresTestDataMethod("name","job");
        System.out.println("expectedData = " + expectedData);


        //send the request and post the response
       Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        Map<String ,String > actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);

        //Do Assertion
        assertEquals(201,response.statusCode());
        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"),actualData.get("job"));

    }
}
