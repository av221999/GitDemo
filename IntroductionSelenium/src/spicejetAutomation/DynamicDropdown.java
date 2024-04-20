package spicejetAutomation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DynamicDropdown {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		WebDriver driver = new FirefoxDriver();
		
		driver.get("https://www.spicejet.com/");
		
		driver.findElement(By.xpath("//div[text()='From']")).click();
		
		
		/* //div[text()='JLR']
		 * //div[contains(text(),'JLR')]
		 * //div[text()='IDR']
		 * //div[text()='Jabalpur']
		 * */
		driver.findElement(By.xpath("//div[text()='JLR']")).click();
		
//		driver.findElement(By.xpath("//div[text()='To']")).click();
		
		Thread.sleep(2000L);
		
		driver.findElement(By.xpath("//div[text()='IDR']")).click();
		
		
		driver.quit();
	}

}
