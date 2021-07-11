package DataDrivenParameterizationandDataProvider;

//Fourth video
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
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
import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Test04_Parameterization_dataDriven extends DataForTest {
	
	//Below code connectionCheck is by mine
	@BeforeTest
	public void connectionCheck() throws IOException, InterruptedException {
		
		baseURI = "http://localhost:3000";
		int statusCode=given().when().get("/users").getStatusCode();
		Assert.assertEquals(statusCode, 200);
		if(statusCode!=200){
			System.out.println("The server is down!!!");
		}else {
			System.out.println("Servier is up and Running :) :) :)");
		}
	}

	// First of all this code is commented not because its wrong, its because of
	// learning.
	// Return two dimentional object (row and array) we have created one excel
	// undertest-data for example.
	// we are creating here static.
	@DataProvider(name ="dataFromDataProviderBaba")
	public Object[][] dataForPost() {
		Object[][] data = new Object[2][3];// We are passing here 2 and 3, because as an example on the excel 2 rows and
											// 3 columsn we have in excel
		data[0][0] = "Anil";// Row index start from 0
		data[0][1] = "Jain";
		data[0][2] = 1;//Note: here we are putting subject id as int. we can also put string also like data[-][2]="1", Then on function you hve to define subject id as string.

		data[1][0] = "Nutan";
		data[1][1] = "Jain";
		data[1][2] = 999;

		return data;
	}

	@Test(dataProvider="dataFromDataProviderBaba")
	public void insertPost(String fname, String Lname, int subjectID) {
		baseURI = "http://localhost:3000";
		JSONObject jo = new JSONObject();
		jo.put("firstname", fname);
		jo.put("last name", Lname);
		jo.put("subjectID", subjectID);
		given().contentType(ContentType.JSON).accept(ContentType.JSON).header("Content-type", "application/json")
				.body(jo.toJSONString()).when().post("/users").

				then().statusCode(201);

	}
//##############################################################################################################//
//##############################################################################################################//
//##############################################################################################################//
	@DataProvider(name = "dataFromSameClass")
	public Object[][] dataForPost1() {
		return new Object[][] { { "Chiku", "Jain", "1" }, { "Piku", "Jain", "999" }, { "Prachi", "Shah", "999" },
				{ "Paryul", "Shah", "999" } };

	}

	// The dataProvider is mentioned on the same class
	@Test(dataProvider = "dataFromSameClass", priority = 1)
	public void testPostDataprviderInSameClass(String firstName, String lastName, String subjectID) {
		baseURI = "http://localhost:3000";
		JSONObject jo = new JSONObject();
		jo.put("firstname", firstName);
		jo.put("last name", lastName);
		jo.put("subjectID", subjectID);

		given().contentType(ContentType.JSON).accept(ContentType.JSON).header("Content-type", "application/json")
				.body(jo.toJSONString()).when().post("/users").

				then().statusCode(201);
	}

//##############################################################################################################//
//##############################################################################################################//
//##############################################################################################################//

	//The dataProvider is mentioned on the class DataForTest.java
	@Test(dataProvider = "dataFromDataProviderDifferentClass", priority = 1)
	public void testPost(String firstName, String lastName, String subjectID) {
		baseURI = "http://localhost:3000";
		JSONObject jo = new JSONObject();
		jo.put("firstname", firstName);
		jo.put("last name", lastName);
		jo.put("subjectID", subjectID);

		given().contentType(ContentType.JSON).accept(ContentType.JSON).header("Content-type", "application/json")
				.body(jo.toJSONString()).when().post("/users").

				then().statusCode(201);
	}
	//##############################################################################################################//
	//##############################################################################################################//
	//##############################################################################################################//

	@DataProvider(name = "deletingID")
	public Object[] dataFordelete() {
		return new Object[] { 11, 12, 13, 14 };
	}

	@Test(dataProvider = "deletingID")
	public void testDelete(int userID) {
		baseURI = "http://localhost:3000";

		given().when().delete("/users/" + userID).then().statusCode(200).log().all();
	}
	//##############################################################################################################//
	//##############################################################################################################//
	//##############################################################################################################//

	//Parmeterization-this suerID is defined in testng.xml
	@Parameters({ "userID" })
	@Test
	public void testDelete2(int userID) {
		System.out.println("User id is from testng.xml file -->" + userID);
		baseURI = "http://localhost:3000";

		given().when().delete("/users/" + userID).then().statusCode(200).log().all();
	}

}