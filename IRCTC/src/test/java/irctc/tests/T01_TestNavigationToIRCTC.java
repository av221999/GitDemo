package irctc.tests;

 

import java.io.IOException;

import java.sql.SQLException;

import java.time.Duration;

import java.time.LocalDate;

 

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebDriverException;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;

import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

 

import irctc.pages.IRCTC_HomePage;

import irctc.pages.IRCTC_TrainsList;

import irctc.utils.Common;

 

public class T01_TestNavigationToIRCTC extends BaseTest {

IRCTC_HomePage pageH;


  @Test(priority = 0, dataProvider ="dataToInput")

  public void valuesInputIRCTC(String from,String to, String coach, String quota,LocalDate journeyDate) throws InterruptedException, IOException, ClassNotFoundException, SQLException {

                 navigateToIRCTC(); 

                 pageH = new IRCTC_HomePage(driver);

                 Thread.sleep(2000);

                 pageH.getFrom().sendKeys(from);

                 pageH.selectStationWebElement(from).click();

                 pageH.getTo().sendKeys(to); 

                 pageH.selectStationWebElement(to).click();

                 pageH.selectJourneyDate(journeyDate);

                 pageH.selectDropdown(pageH.getJourneyQuota(),quota);

                 pageH.selectDropdown(pageH.getJourneyClass(),coach);

                 pageH.getSeachButton().click();

                 Thread.sleep(2000);

                 IRCTC_TrainsList pageL = new IRCTC_TrainsList(driver);

                 pageL.validateTatkalCB();

                 pageL.validateEnteredDate(journeyDate);

                 pageL.checkOrNot(quota);

                 Thread.sleep(3000);

               }

  @Test

  public void testPassing() {

                 Assert.assertTrue(true);

                 System.out.println("Test got Passed");

  }

 

  

  

  @DataProvider(name = "dataToInput")

  public Object[][] getLoginData() throws IOException{

                              LocalDate journeyDate1 = LocalDate.parse(Common.getValue("date",1));              

                              LocalDate journeyDate2 = LocalDate.parse(Common.getValue("date",2));

                              LocalDate journeyDate3 = LocalDate.parse(Common.getValue("date",3));

                

                Object[][] obj1 =new Object[][] {

                                {Common.getValue("from",1),Common.getValue("to",1),Common.getValue("coach",1),Common.getValue("quota",1),journeyDate1},

                                {Common.getValue("from",2),Common.getValue("to",2),Common.getValue("coach",2),Common.getValue("quota",2),journeyDate2},

                                {Common.getValue("from",3),Common.getValue("to",3),Common.getValue("coach",3),Common.getValue("quota",3),journeyDate3}

                               

                 };

//              PREMIUM TATKAL

                 return obj1;

                

  }

//  @DataProvider(name = "dates")

//  public Object[][] getJourneyDates(){

//             

//            Object[][] obj1 =new Object[][] {

//                             {journeyDate1},

//                             {journeyDate2}

//              };

//             

//              return obj1;

//             

//  }

 

// @Test(priority = 1, dataProvider="dates")

//  public void validate_Date_Checkbox(LocalDate journeyDate) throws InterruptedException, WebDriverException, IOException, ClassNotFoundException, SQLException{

//              IRCTC_TrainsList pageL = new IRCTC_TrainsList(driver);

//              pageL.validateEnteredDate(journeyDate);

//              pageL.validateCheckBoxRPC();

//              pageL.validateCheckBoxPWD();

//              Common.takeScreenShot(driver);

//              Thread.sleep(3000);

//             

//  }


}
