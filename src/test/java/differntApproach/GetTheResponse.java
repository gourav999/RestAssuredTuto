//Source of this tutorial: https://toolsqa.com/rest-assured/rest-assured-examples/
//This class i mainly deveoped where we are fetching the particular value from json. also json array ke andar kee valiues and json arrya ka first object niklana.

package differntApproach;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import org.testng.Assert;   //used to validate response status

public class GetTheResponse {

    @Test
    public void GetBooksDetails() {
        // Specify the base URL to the RESTful web service
        //RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
        RestAssured.baseURI = "http://ergast.com/api/f1/2017/circuits.json";
        // Get the RequestSpecification of the request to be sent to the server.
        RequestSpecification httpRequest = RestAssured.given();
        // specify the method type (GET) and the parameters if any.
        //In this case the request does not take any parameters
        Response response = httpRequest.request(Method.GET, "");
        // Print the status and message body of the response received from the server
        System.out.println("Status received => " + response.getStatusLine());
        System.out.println("Response=>" + response.prettyPrint());
        // Response.asString method will directly return the content of the body
        // as String.
        System.out.println("Response Body is as string =>  " + response.asString());

        // Get the status code of the request.
        //If request is successful, status code will be 200
        int statusCode = response.getStatusCode();

        // Assert that correct status code is returned.
        Assert.assertEquals(statusCode /*actual value*/, 200 /*expected value*/,
                "Correct status code returned");

        //VIMp part. Circuits array hai.yahan circuits ke andar kee pahli wali print hogi value.
        String value1 = response.getBody().path("MRData.CircuitTable.Circuits[1]").toString();

        //Cicuits ke anndar ke sari chize print hogi.
        String value2 = response.then().extract().path("MRData.CircuitTable.Circuits").toString();
        System.out.println(value1);
        System.out.println(value2);

        // Other alternative of printint the array values: Get response body as array
        String firstCircuit_name = response.jsonPath().getString("MRData.CircuitTable.Circuits[0].circuitName");

        // Print first circuit name
        System.out.println("First circuit: " + firstCircuit_name);

        String allCircutis = response.jsonPath().getString("MRData.CircuitTable.Circuits");
        System.out.println("First circuit: " + allCircutis);

        // second number wala cicuit ke andar ka poora print karna.
        String secondCompleteCircutKeAndar = response.jsonPath().getString("MRData.CircuitTable.Circuits[2]");
        System.out.println("second complete circuit: " + secondCompleteCircutKeAndar);

    }
}

//}
