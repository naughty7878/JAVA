package test.hd.poi;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;



public class UpdateExcel {
	
	public static void main(String[] args) throws EncryptedDocumentException, InvalidFormatException, IOException {
		
		InputStream inputStream = new FileInputStream("C:/Users/H__D/Desktop/workbook.xls");
	    //InputStream inp = new FileInputStream("workbook.xlsx");

	    Workbook workbook = WorkbookFactory.create(inputStream);
	    Sheet sheet = workbook.getSheetAt(0);
	    Row row = sheet.getRow(2);
	    Cell cell = row.getCell(3);
	    if (cell == null)
	        cell = row.createCell(3);
	    cell.setCellType(Cell.CELL_TYPE_STRING);
	    cell.setCellValue("a test");

	    // Write the output to a file
	    FileOutputStream fileOut = new FileOutputStream("C:/Users/H__D/Desktop/workbook.xls");
	    workbook.write(fileOut);
	    fileOut.close();
		
	}
	
}
