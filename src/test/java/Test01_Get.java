import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

//note: we can import this thing to if we want this line //Response response = RestAssured.get("https://reqres.in/api/users?page=2");
//import io.restassured.RestAssured.*;
public class Test01_Get {
	String URL = "https://reqres.in/api/users?page=2";

	@Test
	public void verifyTheResponse() {

		// Hitting the get request and getting the response.
		// Response response = RestAssured.get("https://reqres.in/api/users?page=2");
		Response response = get(URL);
		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		System.out.println(response.asString());
		System.out.println(response.getStatusLine());
		System.out.println();

		System.out.println(response.asString());

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		
	}

	@Test
	public void verifyTheResponseByGivenMethod() {
		given().get(URL).then().statusCode(200);
	}

	@Test
	public void verifyTheResponseByGivenMethodWithDiffStatusCode() {
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
	
		
	   
		
	}
}