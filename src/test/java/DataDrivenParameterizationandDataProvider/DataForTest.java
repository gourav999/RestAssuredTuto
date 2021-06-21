package DataDrivenParameterizationandDataProvider;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
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

public class DataForTest {
	@DataProvider(name = "dataFromDataProviderDifferentClass")
	public Object[][] dataForPost1() {
		return new Object[][] { 
			{ "Aarti", "Modi", "1" }, 
			{ "Bharati", "Jain", "999" },
			{ "Mina", "Jain", "999" },
			{ "Manisha", "Singhai", "999" } };

	}

	@DataProvider(name = "deletingID")
	public Object[] dataFordelete() {
		return new Object[] { 11, 12, 13, 14 };
	}
}