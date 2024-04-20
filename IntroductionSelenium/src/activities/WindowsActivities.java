package activities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WindowsActivities {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://google.com");
		driver.navigate().to("https://rahulshettyacademy.com");
		Thread.sleep(1000);
		driver.navigate().back();
		Thread.sleep(1000);
	
		driver.navigate().refresh();
		
		driver.navigate().forward();
		
	    driver.quit();
		

	}

}
