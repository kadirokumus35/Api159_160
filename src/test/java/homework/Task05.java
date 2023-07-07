package homework;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class Task05 {
    /*
    Given
        https://automationexercise.com/api/productsList
    When
        User sends Get request
    Then
        Assert that number of "Women" usertype is 12

*/

    @Test
    public void automationExerciseTest(){
        String url="https://automationexercise.com/api/productsList";
       // spec.pathParam("first","productsList");
        Response response = given().get(url);
        response.jsonPath().prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        //Assert that number of "Women" usertype is 12
        int numberOfWomanUserType = jsonPath.getList("products.findAll{it.category.usertype.usertype=='Women'}").size();
        assertEquals(12,numberOfWomanUserType);

    }
}
