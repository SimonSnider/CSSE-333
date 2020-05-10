import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class AthleteManagementService {
	private Connection conn;
	
	public AthleteManagementService(Connection conn) {
		this.conn = conn;
	}

	void InsertAthlete(String name, String bludgerHits, String grade, String pointsScored, String school, String fouls, String ejections, String injuries) {
		try {
			CallableStatement cs = conn.prepareCall("{ ? = call [Insert_Athlete](?, ?, ?, ?, ?, ?, ?, ?) }");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, name);
			cs.setString(3, bludgerHits);
			cs.setString(4, grade);
			cs.setString(5, pointsScored);
			cs.setString(6, school);
			cs.setString(7, fouls);
			cs.setString(8, ejections);
			cs.setString(9, injuries);
			cs.execute();
			int status = Integer.parseInt(cs.getString(1));
			switch(status) {
			case 0:
				JOptionPane.showMessageDialog(null, "Successfully inserted!");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Error Code 1: Integer values cannot be negative values.");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void UpdateAthlete(String athleteID, String name, String bludgerHits, String grade, String pointsScored, String school, String fouls, String ejections, String injuries) {
		try {
			CallableStatement cs = conn.prepareCall("{ ? = call [Update_Athlete](?, ?, ?, ?, ?, ?, ?, ?, ?) }");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, athleteID);
			cs.setString(3, name);
			cs.setString(4, bludgerHits);
			cs.setString(5, grade);
			cs.setString(6, pointsScored);
			cs.setString(7, school);
			cs.setString(8, fouls);
			cs.setString(9, ejections);
			cs.setString(10, injuries);
			cs.execute();
			int status = Integer.parseInt(cs.getString(1));
			switch(status) {
			case 0:
				JOptionPane.showMessageDialog(null, "Successfully updated!");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Error Code 1: AthleteID cannot be null.");
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "Error Code 2: Invalid AthleteID.");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void DeleteAthlete(String athleteID) {
		try {
			CallableStatement cs = conn.prepareCall("{ ? = call [Delete_Athlete](?) }");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, athleteID);
			cs.execute();
			int status = Integer.parseInt(cs.getString(1));
			switch(status) {
			case 0:
				JOptionPane.showMessageDialog(null, "Successfully deleted!");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Error Code 1: AthleteID cannot be null.");
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "Error Code 2: Invalid AthleteID.");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getInfoFromID(String ID) {
		try {
			String[] temparr = {"BludgerHits", "Grade", "PointsScored", "School", "Injuries", "Fouls", "Ejections"};
			CallableStatement cs = conn.prepareCall("{ ? = call [Get_Athlete_data](?) }");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, ID);
			ResultSet rs =  cs.executeQuery();
			ArrayList<Integer> tempColumns = new ArrayList<Integer>();
			/*int col = 0;
			for (int z = 0; z < 7; z++) {
				col = rs.findColumn(athleteFields[z]);
				tempColumns.add(col);
			}*/
			int BHC = rs.findColumn(temparr[0]);
			int GC = rs.findColumn(temparr[1]);
			int PSC = rs.findColumn(temparr[2]);
			int SC = rs.findColumn(temparr[3]);
			int IC = rs.findColumn(temparr[4]);
			int FC = rs.findColumn(temparr[5]);
			int EC = rs.findColumn(temparr[6]);
			int[] arr = {BHC, GC, PSC, SC, IC, FC, EC};
			ArrayList<String> tempInfo = new ArrayList<String>();
			/*for(int y = 0; y < 7; y++) {
				tempInfo.add(y, rs.getString(tempColumns.get(y)));
				y++;
			}*/
			while(rs.next()) {
				for(int y = 0; y < 7; y++) {
					tempInfo.add(y, rs.getString(arr[y]));
				}
			/*tempInfo.add(y++, rs.getString(BHC));
			tempInfo.add(y++, rs.getString(GC));
			tempInfo.add(y++, rs.getString(PSC));
			tempInfo.add(y++, rs.getString(SC));
			tempInfo.add(y++, rs.getString(IC));
			tempInfo.add(y++, rs.getString(FC));
			tempInfo.add(y++, rs.getString(EC));*/
			}
			return tempInfo;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Uh oh, something went wrong.");
			e.printStackTrace();
			return null;
		}
	}

	public ArrayList<String> getNameAndIndex() {
		try {
			CallableStatement cs = conn.prepareCall("{ ? = call [Get_Athletes]() }");
			cs.registerOutParameter(1, Types.INTEGER);
			ResultSet rs = cs.executeQuery();
			int IDColumn;
			IDColumn = rs.findColumn("AthleteID");
			int nameColumn = rs.findColumn("Name");
			ArrayList<String> temps = new ArrayList<String>();
			while(rs.next()) {
				temps.add(rs.getString(IDColumn) + "," + rs.getString(nameColumn));
			}
			return temps;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Uh oh, something went wrong.");
			e.printStackTrace();
			return null;
		}
	}
}
