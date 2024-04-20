package api_responses_validation;

import io.restassured.RestAssured;
import static org.hamcrest.Matchers.*;

import java.io.IOException;

import org.testng.Assert;

import static io.restassured.RestAssured.*;

import files.reusableMethods;

public class Basics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		//validate if ADD Place API is working as expected
		
		//given - all input details
		//when - Submit the API ..used for Response , HTTP methods
		//then - validate the response
		//content of the file to string==> content of file can be converted into byte code ==> byte code into string
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key","qacheck123").header("Content-Type","application/json")
		.body(reusableMethods.GenerateStringFromResource("/Users/amit/eclipse-workspace/demoProject/resources/file.json"))
		.when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
	
		System.out.println(response);
		
		String place_id = reusableMethods.rawToJson(response).getString("place_id");
		
		System.out.println(place_id);
		
		System.out.println("<----------Updating place in API------------>");
		//Update_Place
		String newAddress = "E-115/2, LavKush Vihar, Sukhlia, Indore-452010";
		
		given().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body("{\n"
				+ "    \"place_id\": \"" +place_id + "\",\n"
				+ "    \"address\": \"" + newAddress + "\",\n"
				+ "    \"key\":\"qaclick123\"\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		System.out.println("<----------Get place in API------------>");
	  // Get Place
		
		String getPlaceResponse = given().log().all().param("key","qaclick123").param("place_id",place_id)
		.when().get("maps/api/place/get/json").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
	
	String updatedAddress = reusableMethods.rawToJson(getPlaceResponse).getString("address");

	Assert.assertEquals(updatedAddress, newAddress);
	System.out.println(updatedAddress);
		
	}
	
}