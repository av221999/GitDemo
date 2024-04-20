package api_responses_validation;

import static io.restassured.RestAssured.*;
import java.util.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo_serialization.AddPlace;
import pojo_serialization.Location;

public class SpecBuilderTest {
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

	RequestSpecification req =	new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
								addQueryParam("key","qaclick123").setContentType(ContentType.JSON).setBody(p).build();
		
	ResponseSpecification res = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
	
	Response response= given().spec(req).when().post("maps/api/place/add/json").then().spec(res).extract().response();
	
	String result = response.asPrettyString();	
	System.out.print(result);
	

	System.out.println("---------------- or-----------------------");
	
	
//	System.out.println( given().spec(req).when().post("maps/api/place/add/json").then().spec(res).extract().response().asPrettyString());
	
		
			
	}
}
