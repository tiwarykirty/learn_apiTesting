package Day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import org.json.JSONObject;
public class ParsingData {
	
	
	//using then() methods.
	@Test
	public void validateResponse() {
		
		given().
		when()
		 .get("https://reqres.in/api/users")
		.then()
			.statusCode(200)
			.body("data[0].id", equalTo(1))
			.header("Content-Type",equalTo("application/json; charset=utf-8"));
		
	}
	
	@Test
	public void validateResponseUsingResponse() {
		Response response = given().
		when()
		 .get("https://reqres.in/api/users");
		
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.header("Content-Type"),"application/json; charset=utf-8");
		
		String emailId = response.jsonPath().get("data[4].email").toString();
		Assert.assertEquals(emailId,"charles.morris@reqres.in");
		
	}
	
	@Test
	public void validateResponseUsingJsonObject() {
		Response response = given().contentType(ContentType.JSON).
		when()
		 .get("https://reqres.in/api/users");
		
		Assert.assertEquals(response.getStatusCode(),200);
		Assert.assertEquals(response.header("Content-Type"),"application/json; charset=utf-8");
		
		JSONObject obj = new JSONObject(response.asString());
		boolean found = false;
		for(int i = 0 ; i<obj.getJSONArray("data").length();i++) {
			String first_name = obj.getJSONArray("data").getJSONObject(i).get("first_name").toString();
			System.out.println(first_name);
			if(first_name.equals("Charles")) {
			found = true;
			System.out.println("Expected name found at index: "+i);
			}
		}
		Assert.assertEquals(found,true);
		
	}

}
