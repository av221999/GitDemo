package seleniumIntro;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SelIntroduction {
	
	public static void main(String[] args) {
		
		/* Invoking Browser
		 * 
		 * 	Chrome- ChromeDriver-->Methods (this class contains all the methods for automating in Chrome browser
		 *                                     i.e- Hitting the URL, Close the Driver)
		 *  Firefox - FirefoxDriver --->Methods(this will contain the same methods as the chromeDriver, 
		 *                                and it's same for all the browser drivers provided by other browsers)
		 *  Safari - SafariDriver  --->Methods(same as above, just provided by above classes
		 *                            
		 *                            
		 *  WebDriver here is the interface and only provides the blueprint, that is implemented by the different drivers i.e ChromeDriver ,                          
  		 * 
  		 * ChromeDriver also contains its own class methods apart from the methods implemented from the WebDriver Interface,
  		 * so it is always good to use reference of Webdriver instead of any perticular browsers driver in order to maintain the genericity to use like this.
  		 * 
  		 * We do it because, We don't want to use the methods that is only available in a specific brower (ChromeBrowser),
  		 * because those methods may vary from the different driver vethods
  		 * 
		 * 
		 * */
		
		/*
		 * 
		 * ChromeDriver -->Chrome Browser (such file helps in invoking the brower integrated to them)
		 * 
		 * we can integrate the ChromeDriver manually using System.setProperties and if we don't, 
		 * Selenium Manager can do this thing for us;
		 * 
		 * */
		
		/**
		* Step to invoke chromeDriver
		* System.setProperty("webdriver.chrome.driver","/Users/amit/eclipse-workspace/IntroductionSelenium/Drivers/chromedrive");
		*/
		WebDriver driverChrome = new ChromeDriver();
		
		/**
		 * Step to invoke FirefoxDriver
		 * System.setProperty("webdriver.gecko.driver","/Users/amit/eclipse-workspace/IntroductionSelenium/Drivers/geckodriver");
		 **/
		 WebDriver driverFirefox = new FirefoxDriver();
		
		
		 driverChrome.get("https://rahulshettyacademy.com");
		
		System.out.println(driverChrome.getTitle());
		
		System.out.println(driverChrome.getCurrentUrl());
		
		//driver.close();  ->>>It will close the current window
		
		driverChrome.quit(); // if my script invokes multiple windows then to close all the associated windows we will use quit method
		
		
		driverFirefox.get("https://rahulshettyacademy.com");
		
		System.out.println(driverFirefox.getTitle());
		
		System.out.println(driverFirefox.getCurrentUrl());
		
		//driver.close();  ->>>It will close the current window
		
		driverFirefox.quit(); // if my script invokes multiple windows then to close all the associated windows we will use quit method

		
		
	}

}
