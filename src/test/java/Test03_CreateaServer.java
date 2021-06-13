//Third video
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONObject.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.path.json.mapping.JsonObjectDeserializer;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

//Note: we are running the test for reqres.in. Endpoints are present on this site.

//note: we can import this thing to if we want this line //Response response = RestAssured.get("https://reqres.in/api/users?page=2");
//import io.restassured.RestAssured.*;
public class Test03_CreateaServer {



	//@Test
	public void testGet() {
		baseURI="http://localhost:3000";
		given().get("/users").then().statusCode(200).log().all();
		//will print the subjects array item, of name automation parameters.
		given().param("name","automation").get("/subjects").then().statusCode(200).log().all();
	}


	@Test(priority = 1)
	public void testPost() {
		baseURI="http://localhost:3000";
		JSONObject jo=new JSONObject();
		jo.put("firstname", "Santosh");
		jo.put("last name", "Cooper");
		jo.put("subjectID", 1);
		


		given().contentType(ContentType.JSON).accept(ContentType.JSON).header("Content-type","application/json").
		body(jo.toJSONString()).
		when().post("/users").


		then().statusCode(201);
	}
	
	//Also, another difference is that when you want to update a resource with PUT request, you have to send the full payload as the request whereas with PATCH, you only send the parameters which you want to update.
	@Test(priority = 2)
	public void testPut() {
		baseURI="http://localhost:3000";
		JSONObject jo=new JSONObject();
		jo.put("firstname", "Santosh");
		jo.put("last name", "Don1");
		jo.put("subjectID", 1);


		given().contentType(ContentType.JSON).accept(ContentType.JSON).header("Content-type","application/json").
		body(jo.toJSONString()).
		when().put("/users/4").


		then().statusCode(200).log().all();
	}
	

	@Test(priority = 3)
	public void testPatch() {
		baseURI="http://localhost:3000";
		JSONObject jo=new JSONObject();

		jo.put("subjectID", 999);


		given().contentType(ContentType.JSON).accept(ContentType.JSON).header("Content-type","application/json").
		body(jo.toJSONString()).
		when().patch("/users/1").


		then().statusCode(200).log().all();
	}
	
	//@Test(priority=4)
	public void testDelete() {
		baseURI="http://localhost:3000";
		given().when().delete("/users/4").then().statusCode(204).log().all();
	}
}