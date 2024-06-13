import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class DataConversion {

	public static XmlPath rawToXML(Response r) {
		String response = r.asString();
		XmlPath x = new XmlPath(response);
		r.prettyPeek();
		return x;
	}

	public static JsonPath rawToJson(Response r) {
		String response = r.asString();
		JsonPath x = new JsonPath(response);
		r.prettyPeek();
		return x;
	}

}
