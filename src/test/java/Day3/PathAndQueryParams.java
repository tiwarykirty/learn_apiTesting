package Day3;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class PathAndQueryParams {
	
	@Test
	public void testQueryAndPathParams(){
		
		 given()
			.pathParam("myPath","users")
			.queryParam("page",2)
			.queryParam("id",2)
		.when()
			.get("https://reqres.in/api/{myPath}")
		.then()
			.statusCode(200)
			.body("data.last_name",equalTo("Weaver"));
		
	}
	

}
