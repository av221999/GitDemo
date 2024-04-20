package irctc.tests;

 

import java.io.File;

import java.io.IOException;

import java.time.Duration;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

 

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebDriverException;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.ITestResult;

import org.testng.annotations.*;

 

import com.aventstack.extentreports.ExtentReports;

import com.aventstack.extentreports.ExtentTest;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;

 

import irctc.utils.Common;

 

 

public class BaseTest {

               String time;

               WebDriver driver;

               ExtentReports report;

               ExtentTest test;

               int count = 1;

              

               @BeforeSuite

               public void afterSuit() {

                              time =LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy_HH_mm_ss"));

               }
              

               @BeforeTest

               public void beforeTest() {

                              ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir")+ "//testResults//reports//" + time +"//report" + System.currentTimeMillis() + ".html");

                              spark.config().setReportName("Automation Test Report");

                              report = new ExtentReports();

                              report.attachReporter(spark);

               }

              

               @BeforeMethod

               public void beforeMethod(ITestResult result) {

                              String currentMethod = result.getMethod().getMethodName();;

                              test= report.createTest(currentMethod + count++);

                             

                             

               }

              

               @BeforeClass

               public void setDriver() throws IOException {

                              driver = Common.getDriver();

                              driver.manage().window().maximize();

                              driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

                             

               }

               public void navigateToIRCTC() throws IOException {

                                             driver.get(Common.getPropertiesValue("applicationURL"));

               }

 

               @AfterTest

               public void afterTest() {

                              report.flush();

                              driver.quit();

               }

 


               @AfterMethod

               public void afterMethod(ITestResult result) throws Exception {

 

                              if(result.getStatus() == ITestResult.FAILURE) {

                                             test.fail(result.getThrowable());

                                             test.addScreenCaptureFromPath(Common.takeScreenShotAndGetPath(driver,time));

                              }

 

               }

 

              

              

              

              

              

              

              

              

              

              

              

              

              

              

              

//            protected WebDriver driver;

//

//            @BeforeMethod

//            public void before() throws IOException {

//                           driver = Common.getDriver();

//                           driver.manage().window().maximize();

//                           driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

//                          

//            }

//            public void navigateToIRCTC() throws IOException {

//                                          driver.get(Common.getPropertiesValue("applicationURL"));

//            }

//            @AfterMethod

//            public void after(ITestResult result) throws WebDriverException, IOException {

//                           result.getMethod().getMethodName();

//                           if(result.getStatus() == ITestResult.FAILURE) {

//                                          Common.takeScreenShot(driver);

//                           }

//                           driver.quit();

//            }

              

              

              

              

 

}

