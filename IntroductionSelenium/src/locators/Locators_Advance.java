package locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Locators_Advance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
	
		driver.get("https://rahulshettyacademy.com/AutomationPractice");
		
		/*
		 * Sibling Traverse ==> used to traverse from one sibling to another within the same parent.
		 *     //{sibling}/following-sibling::{anotherSibling}
		 *     
		 * Child to Parent Traverse ==>   
		 *  //{child}/parent::{parentName}/parent::{grandParentName}
		 * 
		 * */
		
		System.out.println(driver.findElement(By.xpath("//header/div/button[1]")).getText() + " ==> child1 of parent");
		
		System.out.println(driver.findElement(By.xpath("//header/div/button[1]/following-sibling::button[1]")).getText() + " ==> child2 of the same parent as of child1 aka sibling of child1 withing following-sibling method");
		
		
		
		System.out.println(driver.findElement(By.xpath("//header/div/button[1]")).getText() + " ==> child1 of parent");
		
		System.out.println(driver.findElement(By.xpath("//header/div/button[1]/parent::div/button[2]")).getText() + " ==> child2 of the same parent as of child1 aka sibling of child1 with the child-parent traverse method");
		
		
		
		driver.quit();
	}

}
