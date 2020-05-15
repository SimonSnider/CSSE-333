import java.io.File;
import java.sql.Connection;
import java.io.FileInputStream;  
import java.util.Iterator;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.Row;  
import org.apache.poi.xssf.usermodel.XSSFSheet;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ibm.icu.text.SimpleDateFormat; 

public class ImportManager {
	
	File file;
	private Connection conn;
	
	public ImportManager(Connection conn) {
		this.conn = conn;
		this.file = new File("C:\\Users\\snidersa\\git\\CSSE-333\\PQR data.xlsx");
	}
	
	public boolean importBroomsticks() {
		try {
			BroomstickManagementService bms = new BroomstickManagementService(this.conn);
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheet("Broomstick");
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
			
			
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				
				while (cellIterator.hasNext()) {
					String make = cellIterator.next().getStringCellValue();
					if (make.isEmpty()) {break;}
					int temp = (int) cellIterator.next().getNumericCellValue();
					String model = Integer.toString(temp);
					String date = dateFormat.format(cellIterator.next().getDateCellValue());
					
					System.out.println(make + ", " + model + ", " + date);
				}
				
			}
			wb.close();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
}
