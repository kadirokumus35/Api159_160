package get_requests;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get06 extends HerOkuAppBaseUrl {
          /*
        Given
            https://restful-booker.herokuapp.com/booking/974
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is "application/json"
        And
            Response body should be like;
                        {
                            "firstname": "John",
                            "lastname": "Smith",
                            "totalprice": 111,
                            "depositpaid": true,
                            "bookingdates": {
                                "checkin": "2018-01-01",
                                "checkout": "2019-01-01"
                            },
                            "additionalneeds": "Breakfast"
                        }
     */

    @Test
    public void Get06(){
        //set the url
        spec.pathParams("first","booking","second","974");

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
                body("firstname",equalTo("John"),
                        "lastname",equalTo("Smith"),
                        "totalprice",equalTo(111),
                        "depositpaid",equalTo(true),
                        "bookingdates.checkin",equalTo("2018-01-01"),
                        "bookingdates.checkout",equalTo("2019-01-01"),
                        "additionalneeds",equalTo("Breakfast"));

        //2nd Way: We will use JsonPath class to extract the data outside the response body
        //Create JsonPath Object
        JsonPath jsonPath = response.jsonPath();

        //Get the data
        assertEquals("John",jsonPath.getString("firstname"));
        assertEquals("Smith", jsonPath.getString("lastname"));
        assertEquals(111, jsonPath.getInt("totalprice"));
        assertEquals("2018-01-01", jsonPath.getString("bookingdates.checkin"));
        assertEquals("2019-01-01", jsonPath.getString("bookingdates.checkout"));
        assertEquals("Breakfast", jsonPath.getString("additionalneeds"));

        //Soft Assertion --> Test NG soft assertion
//To do soft assert follow these 3 steps:
//1st step: Create SoftAssert object
        SoftAssert softAssert = new SoftAssert();

//2nd step: Do assertion by softAssert object
        softAssert.assertEquals(jsonPath.getString("firstname"),"John","firstname did not match");
        softAssert.assertEquals(jsonPath.getString("lastname"),"Smith","lastname did not match");
        softAssert.assertEquals(jsonPath.getInt("totalprice"),111,"totalprice did not match");
        softAssert.assertTrue(jsonPath.getBoolean("depositpaid"),"depositpaid did not match");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"),"2018-01-01","checkin did not match");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"),"2019-01-01","checkout did not match");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"),"Breakfast","additionalneeds did not match");

//3rd step: Use assertAll() method
        softAssert.assertAll();
    }
}
