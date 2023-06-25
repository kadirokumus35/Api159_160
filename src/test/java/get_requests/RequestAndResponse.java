package get_requests;

public class RequestAndResponse {
    /*
    1) Postman is used for manual API testing
    2) We use Rest Assured Library for API automation testing
    3) To type automaton script follow these steps:
        a) Understand the requirements
        b) Type the test case
            To type test cases We use 'Gherkin Language'
            The keyword şs,n Gherkin Language :
                x) Given: ıt is used for pre-conditions(Endpoint)
                y) When: It is used for actions(Request)
                z) Then: It is used for output(Assertion)
                t) And: It is used for multiple usage of keywords
         c) Start to type Automation script:
                i) Set the URL
                ii) Set the expected data
                iii) Send the request and get the response
                iv) Do Assertion

     */

    /*
    Given
            https://restful-booker.herokuapp.com/booking/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK

     */
    public static void main(String[] args) {

        //   i) Set the URL
        String url="https://restful-booker.herokuapp.com/booking/3";
        //   ii) Set the expected data
                //we will do this step later

        //   iii) Send the request and get the response

        //   iv) Do Assertion

    }
}
