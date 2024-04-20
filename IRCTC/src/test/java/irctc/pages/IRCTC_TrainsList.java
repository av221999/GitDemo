package irctc.pages;

 

import java.io.IOException;

import java.sql.SQLException;

import java.time.Duration;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;

import java.util.List;

 

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

 

import irctc.utils.Common;

 

public class IRCTC_TrainsList {

              

               WebDriver driver;

               String expectedMessage;

              

               public IRCTC_TrainsList(WebDriver driver){

                              this.driver = driver;

                              PageFactory.initElements(driver, this);

               }

               @FindBy(xpath="//*[@id='journeyQuota']//child::li/span")

               private List<WebElement> QuotaStatus;

               @FindBy(xpath="//*[@id='journeyQuota']//div[contains(@class,'ui-dropdown-label')]//span")

               public WebElement quotaStatus;

              

               @FindBy(xpath="//*[text()='Railway Pass Concession']")

               private WebElement checkBoxRPC;

              

               @FindBy(xpath="//*[text()='Person With Disability Concession']")

               private WebElement checkBoxPWD;

              

               @FindBy(xpath="//*[contains(@class,'ui-confirmdialog-message')][1]")

               private WebElement dialogMessage;

              

               @FindBy(xpath="//*[@id='concessionBooking']")

               private WebElement boxPWD;

              

               @FindBy(xpath="//*[@id='passBooking']")

               private WebElement boxRPC;

              

               @FindBy(xpath ="//*[@id=\"divMain\"]/div/app-train-list/div[4]/div/div[1]/span/strong")

               private WebElement dateString;

              

               @FindBy(xpath="//*[text()='Cancel']")

               private WebElement cancelButton;

              

              

               @FindBy(xpath="//span[text()='OK']")

               private WebElement okButton;

              

//            public WebElement getTatkalStatus() {

//                           return tatkalStatus;

//            }

               public WebElement getOkButton() {

                              return okButton;

               }

               public WebElement getDateString() {

                              return dateString;

               }

               public WebElement getCancelButton() {

                              return cancelButton;

               }

               public WebElement getDialogMessage(){

                              return dialogMessage;

               }

               public WebElement getCheckBoxRPC() {

                              return checkBoxRPC;

               }

               public WebElement getCheckBoxPWD() {

                              return checkBoxPWD;

               }

              

               public void dateValidation(String dateToValidate){   

                              String temp1 = getDateString().getText();

                   String dateFinal = temp1.split("\\|")[1].trim();

                   Assert.assertEquals(dateFinal, dateToValidate);

                   System.out.println("date got validated");

               }

 

               public void validateEnteredDate(LocalDate journeyDate) throws InterruptedException {

                             String dateToValidate = journeyDate.format(DateTimeFormatter.ofPattern("E, dd MMM yyyy"));

                   dateValidation(dateToValidate);

               }

               public void checkOrNot(String quota) throws ClassNotFoundException, InterruptedException, SQLException, IOException {

                              if(!( quota.equalsIgnoreCase("TATKAL") || quota.equalsIgnoreCase("PREMIUM TATKAL"))) {

                                             validateCheckBoxPWD();

                                             validateCheckBoxRPC();

                                             System.out.println("Checkboxes got validated");

                              }

                              else

                                             System.out.println("No need to validate the checkboxes");

               }

              

               public void validateCheckBoxPWD() throws InterruptedException, ClassNotFoundException, SQLException, IOException {

                              getCheckBoxPWD().click();

                              Thread.sleep(2000);

//                           expectedMessage="Specially abled availing the concession need to carry Photo Identity card issued by Railways which is to be produced for On-board / off-board verification during journey. Escort passengers also need to carry photo identity card mentioned at the time of booking.";

                              expectedMessage =Common.getDataSQL("Person With Disability Concession");

                              Assert.assertEquals(expectedMessage, getDialogMessage().getText());

                              getOkButton().click();

                              Assert.assertEquals(true, boxPWD.isSelected());

                              System.out.println("Assertion for Person with Disability got passed >>>>>>>>>" + boxPWD.isSelected());

               }

              

               public void validateCheckBoxRPC() throws InterruptedException, ClassNotFoundException, SQLException, IOException {

                             

                              getCheckBoxRPC().click();

                              Thread.sleep(2000);

 

//                           expectedMessage="You are booking ticket in Railway Pass Concession. Do you want to continue?";

                              expectedMessage =Common.getDataSQL("Railway Pass Concession");

                              Assert.assertEquals(expectedMessage,getDialogMessage().getText());

                              getCancelButton().click();

                             

 

                              Assert.assertEquals(false,boxRPC.isSelected());

                              System.out.println("Assertion for Railway Pass Concession got passed >>>>>>>>>" + boxRPC.isSelected());

               }

               public void validateTatkalCB() {

                              // TODO Auto-generated method stub

//                           System.out.println("RPC SHOULD BE FALSE >>> " +boxRPC.isEnabled());

//                           for(WebElement quotas : QuotaStatus ) {

                                            

                              if(quotaStatus.getText().equalsIgnoreCase("TATKAL") || quotaStatus.getText().equalsIgnoreCase("PREMIUM TATKAL")){

//                                          System.out.println("RPC SHOULD BE FALSE >>> " +boxRPC.isEnabled());

                                             Assert.assertTrue(boxPWD.isEnabled());

                                             Assert.assertEquals(false,boxRPC.isEnabled());

                                             System.out.println("Checkoxes are disabled");                                   

                              }

                              else {

                                             System.out.println("quota is not Tatkal");

                              }

//                           }

               }

}

