package api_responses_validation;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.payload;
import files.reusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test(dataProvider="booksData")
	public String addBook(String isbn, String aisle) {		
		RestAssured.baseURI = "http://216.10.245.166";
		String response =given().log().all().header("Content-Type","application/json").
		body(payload.AddBook(isbn, aisle)).
		when().post("Library/Addbook.php").
		then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js= reusableMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println(id);	
		return id;
				
	}
	
	@Test(dataProvider ="booksData")
	public void deleteBook(String isbn, String aisle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String response = given().log().all().headers("Content-type","application/json").
				body("{\n"
						+ " \n"
						+ "\"ID\" : \""+addBook(isbn , aisle)+"\"\n"
						+ " \n"
						+ "}\u00A0\n"
						+ "").
				when().post("Library/DeleteBook.php").
				then().log().all().assertThat().extract().response().asString();
		System.out.println("--------------------" +"\n" + response);
	}
	
	
	@DataProvider(name = "booksData") 
	public Object[][] getData(){
		
		return new Object[][]{
			{"amit","22"},
			{"amit","27"},
			{"amit","27"}
		};
	}
}