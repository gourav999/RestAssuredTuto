import org.testng.annotations.Test;
import io.restassured.*;
import io.restassured.response.Response;

public class Test01_Get {

	@Test
	public void test01() {

		// Hitting the get request and getting the response.
		Response response = RestAssured.get("https://reqres.in/api/users?page=2");

		System.out.println(response.getStatusCode());
		System.out.println(response.getBody());
		System.out.println(response.asString());
		System.out.println(response.getStatusLine());
		System.out.println();

		System.out.println(response.asString());
	}
}