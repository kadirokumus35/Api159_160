package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.BookingdatesPojo;

import static herokuapp_smoketest.C01_CreateBooking.bookingId;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;


    public class C03_ReadBooking extends HerOkuAppBaseUrl {
    /*
    Given
        https://restful-booker.herokuapp.com/booking/:id
    When
        Send get request
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
        public void get01() {
            //Set the url
            spec.pathParams("first", "booking", "second", bookingId);

            //Set the expected data
            BookingdatesPojo bookingDatesPojo = new BookingdatesPojo("2023-01-01", "2024-01-01");
            BookingPojo expectedData = new BookingPojo("Mark","Twain",200,true,bookingDatesPojo,"Dinner");
            System.out.println("expectedData = " + expectedData);

            //Send the request and get the response
            Response response = given(spec).get("{first}/{second}");
            response.prettyPrint();

            //Do assertion
            BookingPojo actualData = convertJsonToJava(response.asString(), BookingPojo.class);
            System.out.println("actualData = " + actualData);

            assertEquals(200, response.statusCode());
            assertEquals(expectedData.getFirstname(), actualData.getFirstname());
            assertEquals(expectedData.getLastname(), actualData.getLastname());
            assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
            assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
            assertEquals(bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
            assertEquals(bookingDatesPojo.getCheckout(), actualData.getBookingdates().getCheckout());
            assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

        }


}
