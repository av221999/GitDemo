package files;

public class payload{
	
	public static String AddPlace(){
		
		return "{\n"
				+ "\"location\": {\n"
				+ "\"lat\": 22.759414,\n"
				+ "\"lng\": 75.873694\n"
				+ "},\n"
				+ "\"accuracy\": 50,\n"
				+ "\"name\": \"Amit Kumar Vishwakarma\",\n"
				+ "\"phone_number\": \"(+91) 7000667310\",\n"
				+ "\"address\":\"E-115/2, LavKush Vihar Sukhlia, Indore\",\n"
				+ "\"types\": [\n"
				+ "\"shoe park\",\n"
				+ "\"shop\"\n"
				+ " ],\n"
				+ "\"website\": \"http://rahulshettyacademy.com\",\n"
				+ "\"language\": \"English-IN\"\n"
				+ "\n"
				+ "}";
	}
	
	public static String CoursePrice()
	{
		
		return "{\r\n" + 
				"  \"dashboard\": {\r\n" + 
				"    \"purchaseAmount\": 1162,\r\n" + 
				"    \"website\": \"rahulshettyacademy.com\"\r\n" + 
				"  },\r\n" + 
				"  \"courses\": [\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Selenium Python\",\r\n" + 
				"      \"price\": 50,\r\n" + 
				"      \"copies\": 6\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"Cypress\",\r\n" + 
				"      \"price\": 40,\r\n" + 
				"      \"copies\": 4\r\n" + 
				"    },\r\n" + 
				"    {\r\n" + 
				"      \"title\": \"RPA\",\r\n" + 
				"      \"price\": 45,\r\n" + 
				"      \"copies\": 10\r\n" + 
				"    },\r\n" + 
				"     {\r\n" + 
				"      \"title\": \"Appium\",\r\n" + 
				"      \"price\": 36,\r\n" + 
				"      \"copies\": 7\r\n" + 
				"    }\r\n" + 
				"    \r\n" + 
				"    \r\n" + 
				"    \r\n" + 
				"  ]\r\n" + 
				"}\r\n" + 
				"";		
	}
	
	public static String AddBook(String isbn, String aisle) {
		
		String payLoad = "{\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\n"
				+ "\"isbn\":\""+isbn+"\",\n"
				+ "\"aisle\":\""+aisle+"\",\n"
				+ "\"author\":\"John foe\"\n"
				+ "}\n"
				+ "";
		return payLoad;
		
	}
	
	
}
