package Day1;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPRequests {

	int id;

	@Test(priority = 0)
	void getUser() {

		given().baseUri("https://reqres.in/api/users/2").when().get().then().statusCode(200)
				.body("data.first_name", equalTo("Janet")).log().all();
	}

	@Test(priority = 1)
	void postUser() {
		HashMap<String, String> data = new HashMap<>();
		data.put("name", "Kirty");
		data.put("job", "Learner");

		id = given().contentType("application/json").body(data).when().post("https://reqres.in/api/users").then()
				.statusCode(201).log().all().extract().jsonPath().getInt("id");

		System.out.println("Extracted id : " + id);
	}

	@Test(priority = 2, dependsOnMethods = "postUser")
	void updateUser() {
		HashMap<String, String> data = new HashMap<>();
		data.put("name", "Kirty Tiwary");
		data.put("job", "Decent Learner");

		given().contentType("application/json").body(data).pathParam("userId", id).when()
				.put("https://reqres.in/api/users/{userId}").then().statusCode(200);

	}

	@Test(priority = 3, dependsOnMethods = "postUser")
	void deleteUser() {

		given().contentType("application/json").pathParam("userId", id).when()
				.delete("https://reqres.in/api/users/{userId}").then().statusCode(204);

	}

}
