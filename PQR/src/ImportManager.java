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
		this.file = new File("C:\\Users\\hernanre\\git\\CSSE-333(new)\\PQR data.xlsx");
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
					bms.InsertBroomstick(make, model, date);
				}
				
			}
			wb.close();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean importTeams() {
		try {
			TeamManagementService tms = new TeamManagementService(this.conn);
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheet("Team");
			
			
			
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				
				while (cellIterator.hasNext()) {
					String name = cellIterator.next().getStringCellValue();
					String state = cellIterator.next().getStringCellValue();
					String county = cellIterator.next().getStringCellValue();
					
					System.out.println(name + ", " + state + ", " + county);
					tms.InsertTeam(county, state, name);
				}
				
			}
			wb.close();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean importAthletes() {
		try {
			AthleteManagementService ams = new AthleteManagementService(this.conn);
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheet("Athlete");
			
			
			
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				
				while (cellIterator.hasNext()) {
					String name = cellIterator.next().getStringCellValue();
					if (name.isEmpty()) {break;}
					String bludgerHits = Integer.toString((int)cellIterator.next().getNumericCellValue());
					String grade = Integer.toString((int)cellIterator.next().getNumericCellValue());
					String pointsScored = Integer.toString((int)cellIterator.next().getNumericCellValue());
					String school = cellIterator.next().getStringCellValue();
					String injuries = Integer.toString((int)cellIterator.next().getNumericCellValue());
					String fouls = Integer.toString((int)cellIterator.next().getNumericCellValue());
					String ejections = Integer.toString((int)cellIterator.next().getNumericCellValue());
										

					
					System.out.println(name + ", " + bludgerHits + ", " + grade + ", " + pointsScored + ", " + school + ", " + injuries + ", " + fouls + ", " + ejections);
					ams.InsertAthlete(name, bludgerHits, grade, pointsScored, school, fouls, ejections, injuries);
				}
				
			}
			wb.close();
			return true;
		} catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	public boolean importRides() {
		try {
			RidesManagementService rms = new RidesManagementService(this.conn);
			FileInputStream fis = new FileInputStream(file);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sheet = wb.getSheet("Athlete");
			
			
			
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				
				while (cellIterator.hasNext()) {
				
					String athleteID = Integer.toString((int)cellIterator.next().getNumericCellValue());
					String matchID = Integer.toString((int)cellIterator.next().getNumericCellValue());
				
					
					System.out.println(athleteID + ", " + matchID);
					rms.InsertRides(athleteID, matchID);
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
