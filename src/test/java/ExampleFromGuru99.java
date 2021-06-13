
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.restassured.RestAssured;

//given(). -> No headers required, no query or path param.
//when(). -> No specific condition setup
//get('http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1'). ->only the url needs to be supplied
//then(). -> No specific assertions required
//log(). all() -> Once all the response is fetched, log response, headers, essentially everything that the request returns to you.

public class ExampleFromGuru99 {
	String URL = "https://reqres.in/api/users?page=2";

	@Test
	public void getResponseBody() throws InterruptedException {
		// pring the respoinse in console
		System.out.println("Printing the entire response");
		given().when()
				.get("http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1")
				.then().log().all();
		System.out.println("=====================================");

		// Now notice that the URL used is long and less readable, if you look closely,
		// you will notice that 3 query parameters are being used which are
		// It will not print anything this will just verify the response.
		System.out.println("Verifying the reponse is 200 or not");
		given().when()
				.get("http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1")
				.then().statusCode(200);
		System.out.println("=====================================");

		// query parameters--Rest Assured, helps us pass every part(query, path, header
		// param) separately, making the code more readable and easy to maintain. Also,
		// we can parameterize the data from an external file as required.
		// For using query param, we go back to our definition of the syntax and see
		// that all of them are passed as a part of given.
		System.out.println("Request with query parameter");
		given().queryParam("CUSTOMER_ID", "68195").queryParam("PASSWORD", "1234!").queryParam("Account_No", "1").when()
				.get("http://demo.guru99.com/V4/sinkministatement.php").then().log().all();
		System.out.println("=====================================");

		// Print only the body of request
		System.out.println("Printing the body only");
		given().queryParam("CUSTOMER_ID", "68195").queryParam("PASSWORD", "1234!").queryParam("Account_No", "1").when()
				.get("http://demo.guru99.com/V4/sinkministatement.php").then().log().body();
		System.out.println("=====================================");

		// Printing the status only
		System.out.println("Printing the status");
		given().queryParam("CUSTOMER_ID", "68195").queryParam("PASSWORD", "1234!").queryParam("Account_No", "1").when()
				.get("http://demo.guru99.com/V4/sinkministatement.php").then().log().status();
		System.out.println("=====================================");

		// Taking status on variable
		System.out.println("Printing the status code of API");
		int statusofAPI = given().queryParam("CUSTOMER_ID", "68195").queryParam("PASSWORD", "1234!")
				.queryParam("Account_No", "1").when().get("http://demo.guru99.com/V4/sinkministatement.php")
				.getStatusCode();
		System.out.println("This the respoinse of API--" + statusofAPI);

		// Taking status line on variable
		System.out.println("Printing the status linie of API");
		String statusLine = given().queryParam("CUSTOMER_ID", "68195").queryParam("PASSWORD", "1234!")
				.queryParam("Account_No", "1").when().get("http://demo.guru99.com/V4/sinkministatement.php")
				.getStatusLine();
		System.out.println("This the status line response of API--" + statusLine);
		System.out.println("=====================================");

		// verifyig the status line is correct or not. Then is used for verifying the
		// status.
		System.out.println("Verifying the status Line");
		given().queryParam("CUSTOMER_ID", "68195").queryParam("PASSWORD", "1234!").queryParam("Account_No", "1").when()
				.get("http://demo.guru99.com/V4/sinkministatement.php").then().statusLine("HTTP/1.1 200 OK");
		System.out.println("=====================================");

		System.out.println("Asserting the API with Assert statment");
		String URL = "http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1";
		given().when().get(URL).then().assertThat().statusCode(200);
		System.out.println("=====================================");

		System.out.println("Taking the status code in return");
		int statusCode = given().when().get(URL).getStatusCode();
		System.out.println("This is the status code-->  " + statusCode);
		System.out.println("=====================================");

		System.out.println("Taking the Response time");
		System.out.println("The response time i-->   " + get(URL).timeIn(TimeUnit.MILLISECONDS) + " miliSeconds ");
		System.out.println("=====================================");

		System.out.println("Extract Keyword");
		// Fetching response body and response status code is already covered in the
		// above segment. It is worthy to note that to fetch different parts of the
		// response,
		// the keyword 'extract' is very important.
		System.out.println("The headers in the response-->   " + get(URL).then().extract().headers());
		System.out.println("The content type of response-->   " + get(URL).then().extract().contentType());

		System.out.println("=====================================");

	}

}