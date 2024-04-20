package api_responses_validation;
import io.restassured.specification.*;
import io.restassured.builder.*;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import pojo_EcommerceAPITest.*;

public class ECommerceAPITest {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

	System.out.println("============================Login Request======================================================");
	RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
//	ResponseSpecification res = new ResponseSpecBuilder().build();
	
	LoginRequest login = new LoginRequest();

	RequestSpecification reqLogin = given().spec(req).body(login);
	
	LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().extract().as(LoginResponse.class);
	
	String token = loginResponse.getToken();
	String userId = loginResponse.getUserId();
	
	System.out.println(loginResponse);
	

	System.out.println("============================ADD Product======================================================");
	//ADD Product
	
	RequestSpecification reqBaseIDAddProduct = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).build();
	
	//String desc = files.reusableMethods.GenerateStringFromResource("/Users/amit/eclipse-workspace/demoProject/description.txt");
	
	//System.out.println(desc);
	
	RequestSpecification reqAddProduct = given().spec(reqBaseIDAddProduct).header("Content-Type","multipart/form-data").
	param("productName","MacBook Pro M3 Max").
	param("productAddedBy",userId).
	param("productCategory","Electronics").
    param("productSubCategory","Laptops").	
    param("productPrice","312992").
    param("productDescription", files.reusableMethods.GenerateStringFromResource("/Users/amit/eclipse-workspace/demoProject/description.txt")).
    param("productFor","men").
    multiPart("productImage", new File("//Users/amit/eclipse-workspace/demoProject/MacBook M3 Pro Max.jpeg"));
	
	String response =reqAddProduct.when().post("api/ecom/product/add-product").then().extract().response().asPrettyString();
	
	String productId=files.reusableMethods.rawToJson(response).getString("productId");
	
	System.out.println(response);

	System.out.println("==================================Create Order==========================================================");
	
	//Create Order
	
	

	OrdersDetails details = new OrdersDetails();
	
	details.setCountry("India");
	details.setProductOrderedId(productId);
	
	List<OrdersDetails> orderDetailsList = new ArrayList<OrdersDetails>();
	
	orderDetailsList.add(details);
	
	Orders order = new Orders();
	order.setOrder(orderDetailsList);
	
	RequestSpecification reqBaseCreateOrder = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization", token).setContentType(ContentType.JSON).build();	
	
	RequestSpecification reqCreateOrder = given().spec(reqBaseCreateOrder).body(order);
	  
	String responseCreateOrder=reqCreateOrder.when().post("api/ecom/order/create-order").then().extract().response().asPrettyString();
	
	System.out.println(responseCreateOrder);

	System.out.println("=========================================Delete Order=====================================================");
	
  //Delete Order
  
    RequestSpecification reqBaseDeleteOrder = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization",token).build();
    
    RequestSpecification reqDeleteOrder = given().spec(reqBaseDeleteOrder).pathParam("productId",productId);
    
    		String deleteProductResponse = reqDeleteOrder.when().delete("/api/ecom/product/delete-product/{productId}").then().extract().response().asPrettyString(); 
    		
    		String message = files.reusableMethods.rawToJson(deleteProductResponse).get("message");
    		
    		Assert.assertEquals("Product Deleted Successfully",message);
    		
    		System.out.println(message);
    		
  
	
	

	
	
	
	
	

	
	
	}

}
