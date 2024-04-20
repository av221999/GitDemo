package Framework.ExcelDriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class dataDriven {
	
	/**
	 * Identify the "TestCases" column by scanning the entire 1st row.
	 * 
	 * Once column is identified then scan entire testCase column to identify "Purchase" testCase row
	 * 
	 * After you grab purchase testCase row, pull all the data of that row and feed into the test.
	 * @throws IOException 
	 * 
	 * 
	 **/
		
		public ArrayList<String> getData(String testcaseType, String sheetName) throws IOException{
			
		ArrayList<String> a = new ArrayList();
		
		FileInputStream fis = new FileInputStream("/Users/amit/eclipse-workspace/ExcelDriven/demoData.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
//		HSSFWorkbook  wb= new HSSFWorkbook(fis);
//		HSSFSheet sh = wb.getSheetAt(0);
		
		int sheets = workbook.getNumberOfSheets();
		
		for(int i=0; i<sheets; i++) {
			
			if( workbook.getSheetName(i).equalsIgnoreCase(sheetName) ){
				
				XSSFSheet sheet = workbook.getSheetAt(i);
				
				//Identify the "TestCases" column by scanning the entire 1st row.
				
				Iterator<Row> rows = sheet.iterator();   //sheet is collection of rows
				Row firstRow = rows.next();
				
				Iterator<Cell> cells = firstRow.cellIterator();  //Row is collection of cells
				
				int column=0;
				int k=0;
				while(cells.hasNext()) {
					Cell value=cells.next();
					
					if(value.getStringCellValue().equalsIgnoreCase("Testcases"))
					{
						column=k;
						break;
					}
					k++;
				}
		
				 
			//	 Once column is identified then scan entire testCase column to identify "Purchase" testCase row
				
				while(rows.hasNext()) {
					
					Row r = rows.next();
					
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseType)){
						
						// After you grab purchase testCase row, pull all the data of that row and feed into the test.
						
						Iterator<Cell> data = r.cellIterator();
						while(data.hasNext())
						{	
							Cell c= data.next();
							if(c.getCellType() == CellType.STRING){
								
								a.add(c.getStringCellValue());
								
							}else if(c.getCellType() == CellType.NUMERIC) {
								
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							
							}
							
						}
						
					}
				
					}	

				}
				
			}
		
		return a;
		}	

 	
  
}
