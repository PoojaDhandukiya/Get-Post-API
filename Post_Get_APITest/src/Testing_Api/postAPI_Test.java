package Testing_Api;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class postAPI_Test {

	public static void main(String[] args) {
		//Step 1 : Declare Base URI and request body variables
		String BaseURI = "https://dummy.restapiexample.com";
		
		String requestBody = "{\r\n"
				+ "\"name\":\"test\"\r\n"
				+ ",\"salary\":\"123\"\r\n"
				+ ",\"age\":\"23\"\r\n"
				+ "}";
		
		//Fetch request Body parameter values.
		
		JsonPath jsprequest = new JsonPath(requestBody);   
		String req_name = jsprequest.getString("name");
		String req_salary = (String)jsprequest.get("salary");
		int sal = Integer.parseInt(req_salary);
		String req_age = (String)jsprequest.get("age");
		int age = Integer.parseInt(req_age);
		
		//Declare Base URI
				RestAssured.baseURI= BaseURI;
				
				// Configure Request Body
				int statusCode=given().header("Content-Type","application/json").body(requestBody)
						.when().post("/api/v1/create").then().extract().statusCode();
				
				
				String responseBody = given().header("Content-Type","application/json").body(requestBody)
						.when().post("/api/v1/create").then().extract().response().asString();
				
				System.out.println(statusCode);
				System.out.println(responseBody);
				
				//Step :3 Parse the response body
				JsonPath js = new JsonPath(responseBody);
				String res_name = js.getString(req_name);
				String res_salary = (String)js.get(req_salary);
				int val = Integer.parseInt(res_salary);
				String res_age = (String)js.get(req_age);
				int value = Integer.parseInt(res_age);
				
				//Step 4: Validate the response body parameters
			    Assert.assertEquals(statusCode, 200);
				Assert.assertEquals(res_name, req_name);
				Assert.assertEquals(val, sal);
				Assert.assertEquals(value, age);
				
	}

}

	


