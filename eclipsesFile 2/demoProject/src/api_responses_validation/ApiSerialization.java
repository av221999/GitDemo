package api_responses_validation;

import static io.restassured.RestAssured.*;
import java.util.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo_serialization.AddPlace;
import pojo_serialization.Location;

public class ApiSerialization {
	public static void main(String[] args) {

		// TODO Auto-generated method stub
		AddPlace p = new AddPlace();
		
		Location loc = new Location();
		loc.setLng(75.8738707758);
		loc.setLat(22.7595483581);
		
		p.setLcation(loc);
		
		p.setAccuracy(50);
		
		p.setName("AKV Villa");
		
		p.setPhone_number("(+91) 7000 66 7310");
		
		p.setAddress("E-115, Lav-Kush Vihar, Sukhlia, Indore- 452010");
		
		List<String> listTypes = new ArrayList<>();
		listTypes.add("shoe park");
		listTypes.add("shop");
		p.setTypes(listTypes);
		
		p.setWebsite("http://google.com");
		
		p.setLanguage("English-IN");
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		Response response = given().log().all().queryParam("key","qaclick123").body(p).
				when().post("maps/api/place/add/json").
				then().assertThat().statusCode(200).extract().response();
		String res = response.asPrettyString();
		
		System.out.print(res);
		
		
			
	}
}
