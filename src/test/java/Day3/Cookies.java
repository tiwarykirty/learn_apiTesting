package Day3;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

public class Cookies {
	
	@Test
	public void getAllCookies() {
		Response response = given().when().get("https://www.google.com/");
		
		Map<String,String> cookies = response.getCookies();
		System.out.println(cookies.keySet());
		
		String cookie = cookies.get("AEC");
		System.out.println("AEC Cookie is :"+cookies.get("AEC"));
		
	}

}
