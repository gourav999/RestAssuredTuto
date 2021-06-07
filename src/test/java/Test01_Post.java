import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

import org.json.simple.JSONObject;
import org.json.simple.JSONObject.*;

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
public class Test01_Post {
	String POST_URL = "https://reqres.in/api/users";
	String PUT_URL_PatchURL = "https://reqres.in/api/users/2";

	@Test
	public void verifyPost() {

		// Creating a map
		// Map<String, Object> m1 = new HashMap<String, Object>();
		// m1.put("name", "Gourav");
		// m1.put("job", "QA ");

		// Created one jsonobject and adding map on req
		// JSONObject req = new JSONObject(m1);
		JSONObject req = new JSONObject();
		// System.out.println(req);
		// System.out.println(req.toJSONString());

		// instead of thsi you can directly put values
		req.put("name", "Sourabh");
		req.put("job", "Elect Eng");
		System.out.println(req);
		System.out.println(req.toJSONString());

		// This is for post request
		given().header("Content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(req.toJSONString()).post(POST_URL).then().statusCode(201);
	}

	@Test
	public void verifyPut() {

		// Creating a map
		// Map<String, Object> m1 = new HashMap<String, Object>();
		// m1.put("name", "Gourav");
		// m1.put("job", "QA ");

		// Created one jsonobject and adding map on req
		// JSONObject req = new JSONObject(m1);
		JSONObject req = new JSONObject();
		// System.out.println(req);
		// System.out.println(req.toJSONString());

		// instead of map you can directly put values
		req.put("name", "Sourabh");
		req.put("job", "Elect Eng in BORL Refineries");
		System.out.println(req);
		System.out.println(req.toJSONString());

		// This is for hitting the PUT request
		given().header("Content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(req.toJSONString()).put(PUT_URL_PatchURL).then().statusCode(200).log().all();

		// This is for fetching the response
		ResponseBody b = given().when().get("https://reqres.in/api/users/2").getBody();
		System.out.println("this is output" + b.asString());

	}

	@Test
	public void verifyPatch() {

		// Creating a map
		// Map<String, Object> m1 = new HashMap<String, Object>();
		// m1.put("name", "Gourav");
		// m1.put("job", "QA ");

		// Created one jsonobject and adding map on req
		// JSONObject req = new JSONObject(m1);
		JSONObject req = new JSONObject();
		// System.out.println(req);
		// System.out.println(req.toJSONString());

		// instead of map you can directly put values
		req.put("name", "Sourabh");
		req.put("job", "Elect Eng in BORL Refineries and Manager");
		System.out.println(req);
		System.out.println(req.toJSONString());

		// This is for hitting the PUT request
		given().header("Content-type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(req.toJSONString()).patch(PUT_URL_PatchURL).then().statusCode(200).log().all();

		// This is for fetching the response
		ResponseBody b = given().when().get("https://reqres.in/api/users/2").getBody();
		System.out.println("this is output" + b.asString());

	}

	@Test
	public void verifyDelete() {
		given().when().delete("https://reqres.in/api/users/2").then().statusCode(204).log().all();

	}

}