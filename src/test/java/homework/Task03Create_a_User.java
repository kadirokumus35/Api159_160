package homework;

import base_urls.PetStoreBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Task03Create_a_User  extends PetStoreBaseUrl {
    /*
    //Task 03: Type automation test code to create a 'user' by using "https://petstore.swagger.io/"" documantation.

Given
1)https://petstore.swagger.io/v2/user
2){
                  "username": "JohnDoe",
                  "firstName": "John",
                  "lastName": "Doe",
                  "email": "john@doe.com",
                  "password": "1234",
                  "phone": "1234",
                  "userStatus": 123
                }

                When
                I send POST request to the url
                Then
                Status code is 200
                And response body should be like
                {
                                                "code": 200,
                                                "type": "unknown",
                                                "message": "6874988058"
                                             }


     */
    @Test
    public void post01() {
        spec.pathParam("first", "user");

        Map<String,Object> expectedData = new HashMap<>();
        expectedData.put("username", "JohnDoe");
        expectedData.put("firstName", "John");
        expectedData.put("lastName", "Doe");
        expectedData.put("email", "john@doe.com");
        expectedData.put("password", "1234");
        expectedData.put("phone", "1234");
        expectedData.put("userStatus", 123);


       Response response = given(spec).body(expectedData).post("{first}");
response.prettyPrint();


    Map<String,Object> actualData = response.as(HashMap.class);
    assertEquals(200,response.statusCode());
    assertEquals(200,actualData.get("code"));
    assertEquals("unknown",actualData.get("type"));


    }

}
