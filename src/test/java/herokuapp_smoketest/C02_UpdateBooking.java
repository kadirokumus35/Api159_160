package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.BookingdatesPojo;

import static herokuapp_smoketest.C01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C02_UpdateBooking extends HerOkuAppBaseUrl {

 /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    And
        {
            "firstname" : "Mark",
            "lastname" : "Twain",
            "totalprice" : 200,
            "depositpaid" : true,
            "bookingdates" : {
                "checkin" : "2023-01-01",
                "checkout" : "2024-01-01"
            },
            "additionalneeds" : "Dinner"
        }
    When
        Send put request
    Then
        Status code is 200
    And
        Response body is:
        {
            "firstname": "Mark",
            "lastname": "Twain",
            "totalprice": 200,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2023-01-01",
                "checkout": "2024-01-01"
            },
            "additionalneeds": "Dinner"
        }
     */

    @Test
    public void put01(){
        //set the url
        spec.pathParams("first","booking","second",bookingId);

        //set the expectedData
        BookingdatesPojo datesPojo = new BookingdatesPojo("2023-01-01","2024-01-01");
        BookingPojo pojo = new BookingPojo("Mark","Twain",200,true,datesPojo,"Dinner");

        //send the request and get the response
        Response response = given(spec).body(pojo).put("{first}/{second}");
        response.prettyPrint();

        //Do assertion
        BookingPojo actualData = convertJsonToJava(response.asString(), BookingPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200, response.statusCode());
        assertEquals(pojo.getFirstname(), actualData.getFirstname());
        assertEquals(pojo.getLastname(), actualData.getLastname());
        assertEquals(pojo.getTotalprice(), actualData.getTotalprice());
        assertEquals(pojo.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(datesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(datesPojo.getCheckout(), actualData.getBookingdates().getCheckout());
        assertEquals(pojo.getAdditionalneeds(), actualData.getAdditionalneeds());


    }
}
