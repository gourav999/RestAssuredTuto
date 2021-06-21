//Fifth video
package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;

public class ExcelUtils {
	public static String excelPath = "./test-data/Test-data.xlsx";
	public static String excelPath_WithExtension_XLS = "./test-data/Test-data-filexlsformat.xls";
	public static String sheetName = "Sheet1";
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;

	// ############################################################//
	// When your file extension is .xls
	static HSSFWorkbook workbook_xls_FileExtension;
	static HSSFSheet sheet_xls_FileExtension;
	// ############################################################//

	// Constructor
	public ExcelUtils(String excelPath, String sheetName) {
		try {

			// ############################################################//
			// When your file extension is .xls
			InputStream file = new FileInputStream(excelPath_WithExtension_XLS);
			workbook_xls_FileExtension = new HSSFWorkbook(new POIFSFileSystem(file));
			sheet_xls_FileExtension = workbook_xls_FileExtension.getSheet(sheetName);
			// ############################################################//

			workbook = new XSSFWorkbook(excelPath);
			sheet = workbook.getSheet("Sheet1");
		} catch (Exception ex) {
			ex.getMessage();
			ex.getCause();
			ex.printStackTrace();
		}
	}

	public static void getRowCount() {
		try {

			// ############################################################//
			// This will provide the project direocty and return type is user direcotry
			String projDir = System.getProperty("user.dir");
			System.out.println("This is the project directory" + projDir);
			// ############################################################//

			int printTheRowCount = sheet.getPhysicalNumberOfRows();
			System.out.println("Total no of rows in the excel file-->" + printTheRowCount);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println(ex.getCause());
			ex.printStackTrace();
		}
	}

	public static void getCellData(int rowNumber, int colunmNumber) throws IOException {

		// dataformatter is for getting the data as it is stored in sheet. if its string
		// it would come as string, if its integer it will come as interger.
		DataFormatter formatter = new DataFormatter();
		Object value = formatter.formatCellValue(sheet_xls_FileExtension.getRow(rowNumber).getCell(colunmNumber));
		System.out.println("This is the value of cell-->" + value);

	}

	public void getCellDataFrom_XLS_FileExtension(int rowNumber, int colunmNumber) {
		DataFormatter formatter = new DataFormatter();
		Object value = formatter.formatCellValue(sheet.getRow(rowNumber).getCell(colunmNumber));
		System.out.println("This is the value of cell-->" + value);
	}

	public static void main(String[] args) throws IOException {

		ExcelUtils ec = new ExcelUtils(excelPath, sheetName);

		ec.getRowCount();
		ec.getCellData(1, 1);
	}
}
