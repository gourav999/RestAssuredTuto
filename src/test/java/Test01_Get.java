
//First video
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
//note: we can import this thing to if we want this line //Response response = RestAssured.get("https://reqres.in/api/users?page=2");
;

public class Test01_Get {
	String URL = "https://reqres.in/api/users?page=2";

	@Test
	public void verifyTheResponseWithRestAssuredObject() throws InterruptedException {
		// Hitting the get request and getting the response.
		Response response = RestAssured.get("https://reqres.in/api/users?page=2");

		System.out.println("This is the content Type" + response.getContentType());
		System.out.println("This is the content Type" + response.contentType());

		System.out.println(response.getSessionId());
		System.out.println(response.getStatusCode());// Printing 200
		System.out.println(response.getStatusLine());// Printing HTTP/1.1 200 OK
		System.out.println("Response time is" + response.getTime());// Printing the milisecond of response
		//System.out.println(response.getBody().asPrettyString());// print the body of response as string.
		System.out.println(response.getClass());
		System.out.println("This is the cookie" + response.getCookies());
		System.out.println("This is the detailed Cookie" + response.getDetailedCookies());
		System.out.println("This is the header" + response.getHeaders());// print the headers

	}

	@Test
	public void verifyTheResponse() {

		Response response = get(URL);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		System.out.println(response.asString());
		System.out.println(response.getStatusLine());



		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

	}

	 @Test
	public void verifyTheResponseByGivenMethod() {

		given().get(URL).then().statusCode(200);
		// This will print the response on the console
		given().get(URL).then().statusCode(200).log().all();
	}

	@Test
	public void verifyTheResponseByGivenMethodWithDiffStatusCode() {
		// This will fail because the status code is 200
		given().get(URL).then().statusCode(201);

	}

	 @Test
	public void verifyTheBodyContent() {
		given().get(URL).then().statusCode(200).body("data.id[0]", equalTo(7));
	}

	@Test
	public void verify_BodyContentwith_DiffentParameters() {
		given().get(URL).then().statusCode(200).body("data.id[1]", equalTo(8));
		given().get(URL).then().statusCode(200).body("data.first_name[0]", equalTo("Michael"));
		given().get(URL).then().statusCode(200).body("data.first_name", hasItems("Michael", "Lindsay", "Tobias"));
		given().get(URL).then().statusCode(200).body("data.support.url", notNullValue());
		System.out.println("Gourav"+get(URL).then().log().all());


	}
}