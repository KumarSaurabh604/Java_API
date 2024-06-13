import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.sun.istack.logging.Logger;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UpdateUserDetails extends PropertyFileClass {
	
	public final static Logger logger = Logger.getLogger(UpdateUserDetails.class);
	@Test(priority = 1)
	public static void UpdateUserdetails() {

	logger.info("------------------Updation Of User's Data Begins------------------");

		Map<String, String> map = new HashMap<String, String>();
		map.put("email", "saurabhkumar131123@gmail.com");

		RestAssured.baseURI = prop.getProperty("URI");
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("content-type", "application/json")
				.header("Authorization", prop.getProperty("Token")).body(map)
				.put("/public-api/users/" + CreateNewUser.Id);

		JsonPath body = DataConversion.rawToJson(response);
		int statusCode = body.get("code");
		Assert.assertEquals(statusCode, 200);
		Reporter.log("Updated UserEmail: " + body.get("data.email"), true);

		logger.info("------------------Updation Of User's Data Ends------------------");

	}

	@Test(priority = 2)
	public static void UpdateUserdetailsNegative() {

		logger.info("------------------Updation Of User's(Negative) Data Begins------------------");

		Map<String, String> map = new HashMap<String, String>();
		map.put("email", "");//empty value for the key

		RestAssured.baseURI = prop.getProperty("URI");
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("content-type", "application/json")
				.header("Authorization", prop.getProperty("Token")).body(map)
				.put(prop.getProperty("BaseURI1") + CreateNewUser.Id);

		JsonPath body = DataConversion.rawToJson(response);
		int statusCode = body.get("code");
		Assert.assertEquals(statusCode, 422);
		Reporter.log("Status Code is : "+statusCode, true);

		logger.info("------------------Updation Of User's(Negative) Data Ends------------------");

	}
}
