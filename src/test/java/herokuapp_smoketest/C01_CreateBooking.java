package herokuapp_smoketest;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import pojos.BookingdatesPojo;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static utils.ObjectMapperUtils.convertJsonToJava;

public class C01_CreateBooking extends HerOkuAppBaseUrl {
/*
    Given
            https://restful-booker.herokuapp.com/booking
    And
                {
                        "firstname" : "John",
                        "lastname" : "Doe",
                        "totalprice" : 100,
                        "depositpaid" : true,
                        "bookingdates" : {
                                            "checkin" : "2023-01-01",
                                            "checkout" : "2024-01-01"
                                            },
                        "additionalneeds" : "Extra Pillow"
                  }
     When
        Send the post request
     Then
          Status code is 200
     And
        Response body is;
                 {
                 "bookingid": 89,
                    "booking": {
                                  "firstname": "John",
                                    "lastname": "Doe",
                                    "totalprice": 100,
                                    "depositpaid": true,
                                    "bookingdates": {
                                                      "checkin": "2023-01-01",
                                                       "checkout": "2024-01-01"
                                                     },
                   "additionalneeds": "Extra Pillow"

                                 }

                    }

 */

public static Integer bookingId;//This is the bookingId of created booking, so we can use it in next classes.
    @Test
    public void post01(){
        //Set the url
        spec.pathParam("first", "booking");

        //Set the expected data
        BookingdatesPojo bookingdatesPojo = new BookingdatesPojo("2023-01-01","2024-01-01");
        BookingPojo expectedData = new BookingPojo("John","Doe",100,true,bookingdatesPojo,"Extra Pillow");
        System.out.println("expectedData = "+expectedData);

        //Send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        response.prettyPrint();

        //Do assertion
        BookingResponsePojo actualData= convertJsonToJava(response.asString(),BookingResponsePojo.class);
        System.out.println("actualData = "+actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getBooking().getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getBooking().getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getBooking().getDepositpaid());
        assertEquals(bookingdatesPojo.getCheckin(), actualData.getBooking().getBookingdates().getCheckin());
        assertEquals(bookingdatesPojo.getCheckout(), actualData.getBooking().getBookingdates().getCheckout());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getBooking().getAdditionalneeds());

        bookingId= actualData.getBookingid();

    }

}
