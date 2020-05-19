import java.sql.Connection;

public class DataGetter {
	Connection conn = null;
	DataGetter(Connection conn){
		this.conn = conn;
	}
	//Calls have column first, asc/dsc second. "Asc" or "Desc" respectively
	public Object[][] getData(String[] array, String arrayName, String columnSort, String ascDesc) {
		switch(arrayName) {
		case("athleteAndTeam"):
			return getAthleteTeam(arrayName, columnSort, ascDesc);
		case(""):
		
		default:
			
		return null;
		}
	}
	private Object[][] getAthleteTeam(String arrayName, String columnSort, String ascDesc) {
		
		return null;
	}
}
