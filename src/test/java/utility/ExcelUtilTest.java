//Fifth video
/*Here we are calling the methods written in ExcelUtils.java class*/
package utility;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

public class ExcelUtilTest {


	public static String excelPath = "./test-data/Test-data.xlsx";
	public static String sheetName = "Sheet1";

	public static void main(String[] args) {
		ExcelUtils excel = new ExcelUtils(excelPath, sheetName);

		
		try {
			excel.getRowCount();
			excel.getCellData(1, 0);
			excel.getCellData(1, 1);
			excel.getCellData(1, 2);
			excel.getCellDataFrom_XLS_FileExtension(1,0);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
