package locators;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;


public class Locators {

	public static void main(String[] args) throws InterruptedException {
		String name = "Amit";
		//System.setProperty("webdriver.chrome.driver", "/Users/amit/eclipse-workspace/IntroductionSelenium/Drivers/chromedriver");
		WebDriver driver = new ChromeDriver();
		String password = getPassword(driver);
		/*	implicit wait- for waiting for a perticular time for the locators to get appeared.
		 *  			   if the locator appears.. it starts processing without waiting for the complete duration.
		 */
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		
		driver.get("https://rahulshettyacademy.com/locatorspractice");
		driver.findElement(By.id("inputUsername")).sendKeys(name);
		driver.findElement(By.name("inputPassword")).sendKeys("hello123");
		driver.findElement(By.className("signInBtn")).click(); 
		/*  
		 * Syntax for CSS-Selectors ==> tagName.className, tagname#id 
		 * 
		 * in general we follow this ==> Tagname[attribute = 'value']
		 * 							==>Tagname[attribute='value']:nth-child(indexValue)
		 * 																		
		 * for this
		 * <input type="text" placeholder="Username" id="inputUsername" value="">
		 * cssSelector could be ==>  input[placeholder = 'Username']
		 * 						==> input[type='text']:nth-child(3) ==>> be careful while selecting index number in cssSelector
		 * 
		 * */
		
		driver.findElement(By.cssSelector("p.error")).getText();
		
		System.out.println();
		
		driver.findElement(By.linkText("Forgot your password?")).click();
		Thread.sleep(1000);
		
		/* Syntax for Xpath ==>  
		 * 
		 *  " //Tagname[@attribute='value']  "
		 *   
		 *   <input type="text" placeholder="Username" id="inputUsername" value="">
		 * Xpath could be ==>  //input[@placeholder = 'Username']
		 * 				 ==> //input[@placeholder = 'Username'][indexValue]
		 *   
		 *   
		 * 
		 * 
		 * */
		
		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys(name);
		
		driver.findElement(By.cssSelector("input[placeholder='Email']")).sendKeys("john@akv.com");
		driver.findElement(By.xpath("//input[@type='text'][2]")).clear();
		driver.findElement(By.cssSelector("input[type='text']:nth-child(3)")).sendKeys("locators@akv.com");
		driver.findElement(By.xpath("//form/input[3]")).sendKeys("6969696969");
		driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
		driver.findElement(By.cssSelector("form p")).getText();
		
		driver.findElement(By.cssSelector("div button:first-child")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//div/a[text()='Forgot your password?']")).click();
		Thread.sleep(1000);
		
		driver.findElement(By.xpath("//div[@class='forgot-pwd-btn-conainer']/button[1]")).click();
		
		Thread.sleep(1000);
		
		driver.findElement(By.cssSelector("#inputUsername")).sendKeys(name);
		driver.findElement(By.cssSelector("input[placeholder*='Pass']")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[contains(@class,'submit')]")).click();
		Thread.sleep(1000);
		
		
		Assert.assertEquals(driver.findElement(By.tagName("p")).getText(), "You are successfully logged in.");
		Assert.assertEquals(driver.findElement(By.cssSelector("div[class='login-container'] h2")).getText(), "Hello "+name+",");
		

		driver.quit();

	}

	private static String getPassword(WebDriver driver) throws InterruptedException {
	driver.get("https://rahulshettyacademy.com/locatorspractice");
	driver.findElement(By.linkText("Forgot your password?")).click();
	Thread.sleep(1000);
	driver.findElement(By.cssSelector(".reset-pwd-btn")).click();
	String tempPassword = driver.findElement(By.cssSelector("form p")).getText();
	/*
	 * String we get in tempPassword will be "Please use temporary password 'rahulshettyacademy' to Login"
	 * Here for this, we will use the split method with "'" as a delimiter, that returns an array of length 2 .
	 * String[]	passwordArray = tempPassword.split("'");
	 * passwordArray[0] ===> "Please use temporary password "
	 * passwordArray[1] ===> "rahulshettyacademy' to Login"
	 * 
	 * String[] passwordArray2 = passwordArray[1].split("'");
	 * 
	 * passwordArray2[0] ===> "rahulshettyacademy"
	 * passwordArray2[1] ===> "to Login"
	 * we can simply write it like that in below
	 * 
	 * 
	 * */
	String password = tempPassword.split("'")[1].split("'")[0];
	System.out.println(password);
	
	return password;
		
		
	}

}
