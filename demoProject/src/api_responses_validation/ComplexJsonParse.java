package api_responses_validation;

import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;


/*
 * 1. Print No of courses returned by API

 * 2. Print Purchase Amount

 * 3. Print Title of the first course

 * 4. Print All course titles and their respective Prices

 * 5. Print no of copies sold by RPA Course

 * 6. Verify if Sum of all Course prices matches with Purchase Amount
 * 
 * */

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(payload.CoursePrice());
		
		int count = js.getInt("courses.size()");  //1st
		
		System.out.println("Number of Courses are : "+ count + "\n");
		
		int PurchaseAmount = js.getInt("dashboard.purchaseAmount"); //2nd
		
		System.out.println("Printing Purchase Amount : " + PurchaseAmount + "\n");
	
		String titleFirstCourse = js.get("courses[0].title"); //3rd
		
		System.out.println("Title of the first Course : " + titleFirstCourse + "\n");
		
		for(int i=0; i<count; i++) {      //4th
			System.out.println("------------------------------------------");	
			System.out.println("Title : "+ js.get("courses["+i+"].title"));
			System.out.println("Price : "+ js.get("courses["+i+"].price"));
		}
		System.out.println("------------------------------------------");
		
		for(int i=0;i<count;i++) { 		//5th
			if(js.getString("courses["+i+"].title").equalsIgnoreCase("RPA")){
				System.out.println("No. of copies sold by RPA Course : "+js.getInt("courses["+i+"].price"));
				break;
		}
	}
		System.out.println("\n "+"------------------------------------------");
		
		int sumOfAllCoursePrices =0;
		
		for(int i=0; i<count; i++) {
		
		int	temp = js.getInt("courses["+i+"].price") * js.getInt("courses["+i+"].copies");
			
		sumOfAllCoursePrices +=temp;
		
		}
		
		Assert.assertEquals(sumOfAllCoursePrices,PurchaseAmount);
		System.out.println("Sum of ALl the course prices is equals to the purchase amount : " + sumOfAllCoursePrices);
		
	
	
	}
	

}
