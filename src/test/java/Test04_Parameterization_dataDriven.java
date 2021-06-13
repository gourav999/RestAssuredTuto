
//Fourth video
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
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

//we will use apache poi for featching data from excel
//data provider testng.RUn test multiple times with different sets of data. 

public class Test04_Parameterization_dataDriven {

	// Return two dimational object (rwo and array) we have careted one excel on
	// test-data for example.
	// we are careting here static.
	// @DataProvider(name="dataFromDataProviderBaba")
	// public Object[][] dataForPost() {
	// Object[][] data = new Object[2][3];// We are passing here 2 and 3, because 2
	// rows and 3 columsn we have in excel
	// data[0][0] = "Albert";// Row index start from 0
	// data[0][1] = "Eistein";
	// data[0][2] = "1";
	//
	// data[1][0] = "Deepak";
	// data[1][1] = "Singh";
	// data[1][2] = "999";
	//
	// return data;
	// }

	@DataProvider(name = "dataFromDataProviderBaba1")
	public Object[][] dataForPost1() {
		return new Object[][] { 
			{ "Albert", "Eistein", "1" }, 
			{ "Deepak", "Singh", "999" },
            { "Graham", "Bell", "999" }, 
            { "Henry", "Ford", "999" } 
            };

	}

	//@Test(dataProvider = "dataFromDataProviderBaba1", priority = 1)
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

	@DataProvider(name = "deletingID")
	public Object[] dataFordelete() {
		return new Object[] { 11, 12, 13, 14 };
	}

	@Test(dataProvider = "deletingID")
	public void testDelete(int userID) {
		baseURI = "http://localhost:3000";

		given().when().delete("/users/" + userID).then().statusCode(200).log().all();
	}

}