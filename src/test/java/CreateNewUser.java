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

public class CreateNewUser extends PropertyFileClass {

static int Id = 0;
	
	public final static Logger logger = Logger.getLogger(CreateNewUser.class);
	@Test
	public static void CreateUser() {

		logger.info("------------------Creation Of New User Begins------------------");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "Saurabh");
		map.put("gender", "Male");
		map.put("email", "saurabh1234455664512@gmail.com");
		map.put("status", "Active");
		
		RestAssured.baseURI = prop.getProperty("URI");
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.when().header("content-type", "application/json")
				.header("Authorization", prop.getProperty("Token")).body(map)
				.post(prop.getProperty("BaseURI"));
		
		JsonPath body = DataConversion.rawToJson(response);	
		int statusCode = body.get("code");
		Assert.assertEquals(statusCode,201);
		
		Id = body.get("data.id");
		Reporter.log("Generated New Id: " + Id, true);
		
		logger.info("------------------Creation Of New User Ends------------------");
		
	}
}
