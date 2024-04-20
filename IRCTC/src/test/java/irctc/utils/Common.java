package irctc.utils;

 

import java.io.File;

import java.io.FileInputStream;

import java.io.IOException;

import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.sql.Statement;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import java.util.ArrayList;

import java.util.HashMap;

import java.util.Iterator;

import java.util.Properties;

 

import org.apache.commons.io.FileUtils;

import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.CellType;

import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.util.NumberToTextConverter;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebDriverException;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.edge.EdgeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;

 

public class Common {

                             

//            private static int ssCount=1;

//            private static Connection connection = null;

               private static String username;

               private static String password;

               private static String database;

               private static String sqlURI;

 

               public static String getPropertiesValue(String Key) throws IOException {

                              Properties prop = new Properties();

                              String propertiesFilePath = System.getProperty("user.dir") + "//resources/global.properties";

                              FileInputStream file = new FileInputStream(propertiesFilePath);

                              prop.load(file);

                             

                              return prop.getProperty(Key);

               }

              

               public static String takeScreenShotAndGetPath(WebDriver driver, String time) throws WebDriverException, IOException {

                              File ss = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);     

                              String ssPath = System.getProperty("user.dir")+"//testResults/screenshots/" + time +"//screenshot" +System.currentTimeMillis()+ ".png";

                              File destPath=new File(ssPath);

                              FileUtils.copyFile(ss , destPath);

                              return destPath.getAbsolutePath();

               }

              

               public static WebDriver getDriver() throws IOException {

                              String browserName = getPropertiesValue("browser");

                              if(browserName.equalsIgnoreCase("firefox")) {

                                             return new FirefoxDriver();

                              }

                              else if(browserName.equalsIgnoreCase("edge")) {

                                             return new EdgeDriver();

                              }

                              else

                                             return new ChromeDriver();

               }

               public static String getDataSQL(String identifier)  throws ClassNotFoundException, SQLException, IOException{

                              username= Common.getPropertiesValue("username");

                              password= Common.getPropertiesValue("password");

                              database= Common.getPropertiesValue("database");

                              sqlURI = "jdbc:mysql://localhost:3306/"+database;

                             

                              Connection connection = null;

       

                               Class.forName("com.mysql.cj.jdbc.Driver");

         connection = DriverManager.getConnection(sqlURI,username, password);

 

        

//         Statement statement;

//         statement = connection.createStatement();

         PreparedStatement myStmt;

         myStmt = connection.prepareStatement("Select ExpectedMessage FROM checkboxMessage Where CheckBoxIdentifier = ?");

        

         

         ResultSet resultSet;

       

         myStmt.setString(1,identifier);

        

         resultSet = myStmt.executeQuery();

//         resultSet = statement.executeQuery(

//             "Select ExpectedMessage \r\n"

//             + "FROM checkboxMessage\r\n"

//             + "Where CheckBoxIdentifier = \""+identifier+"\";");

       

         HashMap<String, String> map = new HashMap<>();

//         String identifier;

         String expectedMessage="";

//        int i=0;

//         resultSet.next();

         while (resultSet.next()) {

//       

//           identifier = resultSet.getString("CheckBoxIdentifier");

//           System.out.println(identifier);

               expectedMessage= resultSet.getString("ExpectedMessage");

//           System.out.println(expectedMessage);

//           System.out.println(identifier   + ">>>>>>" + expectedMessage);

//            map.put(identifier,expectedMessage);

             return expectedMessage;

         }

        

         resultSet.close();

         myStmt.close();

         connection.close();

         return expectedMessage;

     }

               public static String getValue(String value,int dataIndex) throws IOException {

                              return getData(Common.getPropertiesValue("test01Data"),value, "sheet1").get(dataIndex);

               }

              

               public static ArrayList<String> getData(String dataFileName,String testCasetype, String sheetName) throws IOException {

                //List to store the column cell values

                ArrayList<String> list = new ArrayList<String>();

                //for Importing the File

                String dataFilePath = System.getProperty("user.dir") + "/testsData/" + dataFileName;

//            System.out.println(dataFilePath);

                FileInputStream fis = new FileInputStream(dataFilePath);

                

                XSSFWorkbook workbook = new XSSFWorkbook(fis);

                

                int sheets = workbook.getNumberOfSheets();

                

                for(int i=0; i<sheets; i++) {

                               if(workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {

                                              XSSFSheet sheet = workbook.getSheetAt(i);

                                              

                                              Iterator<Row> rows = sheet.iterator();

                                              Row firstRow =rows.next();

                                              

                                              Iterator<Cell>   cells = firstRow.cellIterator();

                                              

                                              int column =0;

                                              int k=0;

                                              while(cells.hasNext()) {

                                                             Cell value = cells.next();

                                                             

                                                             if(value.getStringCellValue().equalsIgnoreCase("Variables"))

                                                             {

                                                                            column = k;

                                                                            break;

                                                             }

                                                             k++;

                                              }

                                              

                                              while(rows.hasNext()) {

                                                             Row r = rows.next();

//                                                         System.out.println(r.getCell(column).getStringCellValue());

                                                             if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testCasetype)) {

//                                                                        System.out.println(r.getCell(column).getStringCellValue());

                                                                            Iterator<Cell> data = r.cellIterator();

                                                                            while(data.hasNext())

                                                                            {

                                                                                           Cell c = data.next();

                                                                                           if(c.getCellType()==CellType.STRING) {

//                                                                                                      System.out.println();

                                                                                                          list.add(c.getStringCellValue());

                                                                                         

                                                                                           }else if(c.getCellType() == CellType.NUMERIC)

                                                                                           {

//                                                                                                      list.add(String.valueOf(c.getNumericCellValue()));

                                                                                                          list.add(NumberToTextConverter.toText(c.getNumericCellValue()));

                                                                                           }

                                                                            }

                                                                            

                                                             }

                                              }

                                              

                               }

                }

                return list;

    }

 

}