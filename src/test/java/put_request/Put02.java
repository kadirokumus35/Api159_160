package put_request;

import base_urls.DummyApiBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyPojo;
import pojos.DummyResponsePojo;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Put02 extends DummyApiBaseUrl {
     /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
        */
        /*
          Given

                https://dummy.restapiexample.com/api/v1/update/21
                {
                 "employee_name": "Tom Hanks",
                 "employee_salary": 111111,
                 "employee_age": 23,
                 "profile_image": "Perfect image"
                }

           When
                   User send the PUT request
           Then
                   Status code is 200
           And
                  Response body should be like the following
                 {
                    "status": "success",
                    "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                             },
                     "message": "Successfully! Record has been updated."
                    }

     */


    @Test
    public void Put02(){
        //set the url
        spec.pathParams("first","update","second",21);

        //set the expected data
        DummyPojo expectedData = new DummyPojo("Ali Can",111111,23,"Perfect image");
        System.out.println("expectedData ; "+expectedData);


        //send the request and get the response
       Response response = given(spec).body(expectedData).put("{first}/{second}");
       // response.prettyPrint();

        //do assertion
       DummyResponsePojo actualData = response.as(DummyResponsePojo.class);

       assertEquals(200,response.statusCode());

       assertEquals(expectedData.getEmployee_name(),actualData.getData().getEmployee_name());
       assertEquals(expectedData.getEmployee_salary(),actualData.getData().getEmployee_salary());
       assertEquals(expectedData.getEmployee_age(),actualData.getData().getEmployee_age());
       assertEquals(expectedData.getProfile_image(),actualData.getData().getProfile_image());

       assertEquals("success",actualData.getStatus());
       assertEquals("Successfully! Record has been updated.",actualData.getMessage());


    }

}
