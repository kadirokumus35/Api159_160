package get_requests;

import base_urls.HerOkuAppBaseUrl;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.BookingPojo;
import pojos.BookingdatesPojo;


import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Get11 extends HerOkuAppBaseUrl {
/*
            Given
                https://restful-booker.herokuapp.com/booking/49
            When
                 I send GET Request to the URL
             Then
                 Status code is 200
             And
                 Response body is like {
                           {
                                 "firstname": "Jane",
                                 "lastname": "Doe",
                                 "totalprice": 111,
                                  "depositpaid": true,
                                "bookingdates": {
                                                     "checkin": "2018-01-01",
                                                     "checkout": "2019-01-01"
                                               },
                                "additionalneeds": "Extra pillows please"
                            }
         */

@Test
    public void Get11(){
    //set the url
    spec.pathParams("first","booking","second",49);

    //set the expexted data
    BookingdatesPojo bookingdatesPojo = new BookingdatesPojo("2018-01-01","2019-01-01");
    BookingPojo expectedData = new BookingPojo("Josh","Allen",111,true,bookingdatesPojo,"super bowls");
    System.out.println("expected data : "+expectedData);

    //send the request and get the response
    Response response = given(spec).get("{first}/{second}");
    response.prettyPrint();

//Do assertion
    BookingPojo actualData = response.as(BookingPojo.class);
assertEquals(200,response.statusCode());
assertEquals(expectedData.getFirstname(),actualData.getFirstname());
assertEquals(expectedData.getLastname(),actualData.getLastname());
assertEquals(expectedData.getTotalprice(),actualData.getTotalprice());
assertEquals(expectedData.getDepositpaid(),actualData.getDepositpaid());
assertEquals(bookingdatesPojo.getCheckin(),actualData.getBookingdates().getCheckin());
assertEquals(bookingdatesPojo.getCheckout(),actualData.getBookingdates().getCheckout());
assertEquals(expectedData.getAdditionalneeds(),actualData.getAdditionalneeds());





}

}
