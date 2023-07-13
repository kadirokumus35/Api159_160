package post_request;

import base_urls.HerOkuAppBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;
import pojos.BookingdatesPojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Post05 extends HerOkuAppBaseUrl {
     /*
         Given
          1)  https://restful-booker.herokuapp.com/booking
          2)   {
                            "firstname": "kadir",
                             "lastname": "okumus",
                            "totalprice": 111,
                            "depositpaid": true,
                     "bookingdates": {
                                         "checkin": "2018-01-01",
                                         "checkout": "2019-01-01"
                                      },
                             "additionalneeds": "super bowls"
                }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
                                  "bookingid": 14838,
                                            "booking": {
                                                          "firstname": "kadir",
                                                         "lastname": "okumus",
                                                         "totalprice": 111,
                                                         "depositpaid": true,
                                                         "bookingdates": {
                                                                        "checkin": "2018-01-01",
                                                                        "checkout": "2019-01-01"
                                                                      },
                                                          "additionalneeds": "super bowls"
                                                         }
                }
     */

    @Test
    public void Post05(){
        //set the url
        spec.pathParam("first","booking");

        //set the expected data
        BookingdatesPojo bookingdatesPojo = new BookingdatesPojo("2018-01-01","2019-01-01");
        BookingPojo expectedData = new BookingPojo("kadir","okumus",111,true,bookingdatesPojo,"super bowls");
        System.out.println(expectedData);

        //send the request and get the response
        Response response = given(spec).body(expectedData).post("{first}");
        //response.prettyPrint();

        //Do assertion
        BookingResponsePojo actualData = response.as(BookingResponsePojo.class);
        System.out.println(actualData);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstname(),actualData.getBooking().getFirstname());
   assertEquals(expectedData.getLastname(),actualData.getBooking().getLastname());
   assertEquals(expectedData.getTotalprice(),actualData.getBooking().getTotalprice());
   assertEquals(expectedData.getDepositpaid(),actualData.getBooking().getDepositpaid());
   assertEquals(bookingdatesPojo.getCheckin(),actualData.getBooking().getBookingdates().getCheckin());
   assertEquals(bookingdatesPojo.getCheckout(),actualData.getBooking().getBookingdates().getCheckout());
   assertEquals(expectedData.getAdditionalneeds(),actualData.getBooking().getAdditionalneeds());


    }
}
