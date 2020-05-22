import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DataGetter {
	Connection conn = null;
	DataGetter(Connection conn){
		this.conn = conn;
	}
	
	public ArrayList<String> getColumns(String arrayName, String[] viewColumns) {
		ArrayList<String> matches = new ArrayList<>();
		int i = 0;
		int max = viewColumns.length;
		while (i < max) {
			matches.add(viewColumns[i++]);
		}
		return matches;
	}
	
	//Calls have column first, asc/dsc second. "Asc" or "Desc" respectively
	public Object[][] getData(String[] colNames, String arrayName, String columnSort, String ascDesc) {
		if (ascDesc.compareTo("Ascending") == 0)
			ascDesc = "Asc";
		else if (ascDesc.compareTo("Descending") == 0) {
			ascDesc = "Desc";
		}
		/*switch(arrayName) {
		case("Get_TeamsAndAthletes_View"):
			return getAthleteTeam(colNames, arrayName, columnSort, ascDesc);
		case("Get_Athletes_In_Matches_View"):
			return getAthleteMatch(colNames, arrayName, columnSort, ascDesc);
		case("Get_Teams_View"):
			return getTeams(colNames, arrayName, columnSort, ascDesc);
		case("Get_Rides_View"):
			return getRides(colNames, arrayName, columnSort, ascDesc);
		case("Get_Match_View"):
			return getMatch(colNames, arrayName, columnSort, ascDesc);
		case("Get_Broomsticks_View"):
			return getBroomsticks(colNames, arrayName, columnSort, ascDesc);
		case("Get_Athlete_Statistics_View"):
			return getAthleteStatistics(colNames, arrayName, columnSort, ascDesc);
		default:
			JOptionPane.showMessageDialog(null, "Somehow your selection box has managed to evade the range of selectable options. Wpw. Impressive.");
		return null;
		}*/
		return getAthleteTeam(colNames, arrayName, columnSort, ascDesc);
	}
	private Object[][] getAthleteTeam(String[] colNames, String arrayName, String columnSort, String ascDesc) {
		try{
			//ArrayList<String> matches = new ArrayList<>();
			Object[][] returner;
			CallableStatement statement = this.conn.prepareCall("{? = call [" + arrayName + "](?, ?)}");
			statement.registerOutParameter(1, Types.INTEGER);
			statement.setString(2, columnSort);
			statement.setString(3, ascDesc);
			ResultSet rs = statement.executeQuery();
			int len = colNames.length;
			//Use len to keep track of how to input into array
			int columnIndex = 0;
			int totalLength = 0;
			int rows = 0;
			ArrayList<Object> temp = new ArrayList<Object>();
			while(rs.next()) {
				columnIndex = 1;
				while(columnIndex <= len) {
					temp.add(rs.getString(columnIndex++));
					totalLength++;
				}
				rows++;
			}
			
			returner = new Object[rows][len];
			
			int currentRow = 0;
			int currentColumn = 0;
			for(int count = 0; count < totalLength; count++) {
				Object tmp = temp.get(count);
				if (tmp == null)
					returner[currentRow][currentColumn++] = "null";
				else
					returner[currentRow][currentColumn++] = temp.get(count);
				if (currentColumn == len) {
					currentColumn = 0;
					currentRow++;
				}
			}
			
			return returner;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	private Object[][] getAthleteMatch(String[] colNames, String arrayName, String columnSort, String ascDesc) {
		
		return null;
	}
	private Object[][] getTeams(String[] colNames, String arrayName, String columnSort, String ascDesc) {
	
		return null;
	}
	private Object[][] getRides(String[] colNames, String arrayName, String columnSort, String ascDesc) {
	
		return null;
	}
	private Object[][] getMatch(String[] colNames, String arrayName, String columnSort, String ascDesc) {
	
		return null;
	}
	private Object[][] getBroomsticks(String[] colNames, String arrayName, String columnSort, String ascDesc) {
	
		return null;
	}
	private Object[][] getAthleteStatistics(String[] colNames, String arrayName, String columnSort, String ascDesc) {
		
		return null;
	}
}
