package api_responses_validation;

import static io.restassured.RestAssured.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.testng.Assert;

import pojo_deserialization.*;
public class oAuth_Deserialization{

	public static void main(String[] args){
		String response=given().
		formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W").
		formParam("grant_type","client_credentials").
		formParam("scope","trust").
		when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").
		then().assertThat().statusCode(200).extract().response().asString();	
		
		String token = files.reusableMethods.rawToJson(response).get("access_token").toString();
		
		String[] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};
		
		GetCourse  js = given().queryParams("access_token", token).when().
		get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
		System.out.println(js);
		
		System.out.println(js.getCourses().getApi().get(1).getCourseTitle()); //print the course title under API at index 1

		List<Api> api = js.getCourses().getApi(); //print the price of API course where the title equals to "SoapUI WebServices testing"
		for(int i=0; i<api.size();i++) {
			if(api.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			{	
				int a = Integer.parseInt(api.get(i).getPrice());
				System.out.println(a);
			}		
		}
		
		//print the titles of all the courses under webAutomation.
		ArrayList<String> a = new ArrayList<String>();  // Temporary list to  save the actual CourseTitles
		
		List<WebAutomation> webAutomation = js.getCourses().getWebAutomation();
		
		for(int i=0; i< webAutomation.size();i++)
		{
			a.add(webAutomation.get(i).getCourseTitle());
		}
		
		List<String> expectedList = Arrays.asList(courseTitles); // for converting array into list
		
		
		Assert.assertEquals(a, expectedList);
		 
		
		// Step 1: Obtain an iterator from the list 'a'
        Iterator<String> iterator = a.iterator();

        // Step 2: Iterate through the elements of the list 'a'
        while (iterator.hasNext()) {
            // Step 3: Retrieve and print each element
            System.out.println(iterator.next());
        }


	}
	
}
