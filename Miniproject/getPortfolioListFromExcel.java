package Miniproject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class getPortfolioListFromExcel {

	public static List<String> getData() throws IOException {
		try {
			File file = new File("D:\\\\portfolioList.xlsx"); // creating a new file instance
			FileInputStream fis = new FileInputStream(file); // obtaining bytes from the file
//creating Workbook instance that refers to .xlsx file  

			//File (Excel file) - -FInputStream --- XSSFWorkbook  (wb) --- XSSFSheet (sheet) --- Row -- Cell
			XSSFWorkbook wb = new XSSFWorkbook(fis);

			XSSFSheet sheet = wb.getSheetAt(0); // creating a Sheet object to retrieve object
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file
			//sheet.itera
			while (itr.hasNext()) {
				Row row = itr.next();

				//row.cell
				Iterator<Cell> cellIterator = row.cellIterator(); // iterating over each column
				List <String> data= new ArrayList<String>();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					switch (cell.getCellType()) {
					case STRING:
						
						// field that represents string cell type
						//System.out.print(cell.getStringCellValue() + " || ");
						data.add(cell.getStringCellValue());
						break;
						
					case NUMERIC: // field that represents number cell type
						//System.out.print(cell.getNumericCellValue() + "  || ");
						data.add(""+cell.getNumericCellValue());
						break;
												
					/*case BOOLEAN:
						System.out.print(cell.getBooleanCellValue() + " || ");
						*/
					default:
					}
				}
				return data;
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
        

		

	}

}
