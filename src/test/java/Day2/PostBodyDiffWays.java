package Day2;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class PostBodyDiffWays {
	
	
	@Test
	public void postUsingHashMap() {
		HashMap data = new HashMap();
		
		data.put("title", "Meluha");
		data.put("body", "Immortals of Meluha");
		data.put("userId", 1);
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post("https://jsonplaceholder.typicode.com/posts")
		.then()
			.statusCode(201)
			.body("userId", equalTo(1))
			.body("title",equalTo("Meluha"))
			.log().all();
	}
	
	@Test
	public void postUsingPojo() {
		
		BookPOJO data = new BookPOJO();
		data.setTitle("Atomic Habits");
		data.setBody("Daily Habits amounting to only 1% better can achive huge results");
		data.setUserId(121);
		
		given()
		.contentType("application/json")
		.body(data)
	.when()
		.post("https://jsonplaceholder.typicode.com/posts")
	.then()
		.statusCode(201)
		.body("userId", equalTo(121))
		.body("title",equalTo("Atomic Habits"))
		.log().all();
	}

}
