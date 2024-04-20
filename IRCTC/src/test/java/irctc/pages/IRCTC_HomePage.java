package irctc.pages;

 

import java.io.IOException;

import java.time.LocalDate;

import java.time.Month;

import java.util.List;

 

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

 

import irctc.utils.Common;

 

public class IRCTC_HomePage{

     WebDriver driver;

    

     public IRCTC_HomePage(WebDriver driver) {

          this.driver = driver;

          PageFactory.initElements(driver, this);

     }

    

     @FindBy(xpath ="//*[@id='origin']/span/input")

     private WebElement from;

    

     public WebElement getFrom() {

          return from;

     }

     @FindBy(xpath ="//*[@id='destination']/span/input")

     private WebElement to;

    

     public WebElement getTo() {

          return to;

     }

    

     public void fillingData(){

         

     }

    

 

     public void selectJourneyDate(LocalDate journeyDate) throws InterruptedException{

         

     driver.findElement(By.xpath("//*[@id='jDate']/span/input")).click();    

         

          Thread.sleep(3000);

          Month month = journeyDate.getMonth();

          int year = journeyDate.getYear();

          int dayOfMonth = journeyDate.getDayOfMonth();

         

          String presentMonth = driver.findElement(By.xpath("//*[contains(@class,'ui-datepicker-month')]")).getText();

          Month calenderMonth = Month.valueOf(presentMonth.toUpperCase());

          String presentYear= driver.findElement(By.xpath("//*[contains(@class,'ui-datepicker-year')]")).getText();

          int calenderYear =Integer.valueOf(presentYear.toString());

         

          while(year!=calenderYear) {

               if(year>calenderYear) {

 

                    driver.findElement(By.xpath("//*[contains(@class,'ui-datepicker-next')]")).click();

               }

               else {

 

                    driver.findElement(By.xpath("//*[contains(@class,'ui-datepicker-prev')]")).click();

               }

         

               presentYear= driver.findElement(By.xpath("//*[contains(@class,'ui-datepicker-year')]")).getText();

               calenderYear =Integer.valueOf(presentYear.toString());

          }

          while (!month.equals(calenderMonth)) {

               if (calenderMonth.compareTo(month)<0) {

                    Thread.sleep(1000);

                    driver.findElement(By.xpath("//*[contains(@class,'ui-datepicker-next')]")).click();

                    Thread.sleep(5000);

              

               }

               else {

                    driver.findElement(By.xpath("//*[contains(@class,'ui-datepicker-')]")).click();

                    Thread.sleep(5000);

               }

 

               presentMonth = driver.findElement(By.xpath("//*[contains(@class,'ui-datepicker-month')]")).getText();

               calenderMonth = Month.valueOf(presentMonth.toUpperCase());

          }

         

          List<WebElement> dateSelect=driver.findElements(By.xpath("//td/a[text()='" + dayOfMonth + "']"));

 

          if(!dateSelect.isEmpty()){

               driver.findElement(By.xpath("//td/a[text()='" + dayOfMonth + "']")).click();

          }

          else{

               System.out.println("The entered date is disabled and can't be selected");

          }

         

         

     }

 

    

     @FindBy(xpath="//*[@id='journeyQuota']")

     private WebElement journeyQuota;

    

     @FindBy(xpath="//*[@id='journeyClass']")

     private WebElement journeyClass;

    

 

     public WebElement getJourneyQuota() {

          return journeyQuota;

     }

 

     public WebElement getJourneyClass() {

          return journeyClass;

     }

     public  WebElement selectType(String coach) {

          return driver.findElement(By.xpath("//*/li/span[text() ='"+coach+"']"));

     }

     public void selectDropdown(WebElement type, String str) {

          type.click();

          selectType(str).click();

     }

    

     @FindBy(xpath ="//button[text()='Search']")

     private WebElement searchButton;

    

     public WebElement getSeachButton() {

          return searchButton;

     }

     public WebElement selectStationWebElement(String stnInput) {

               return driver.findElement(By.xpath("//span[contains(text(),'"+stnInput+"')]"));

     }

 

    

 

}