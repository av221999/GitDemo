package Framework.ExcelDriven;

import java.io.IOException;
import java.util.ArrayList;

public class testSample {

	public static void main(String[] args) throws IOException {
		
		
		dataDriven dd = new dataDriven();
		ArrayList<String> data = dd.getData("Add profile","testData");
		
		System.out.println(data.get(0));
		System.out.println(data.get(1));
		System.out.println(data.get(2));
		System.out.println(data.get(3));
		

	}

}
