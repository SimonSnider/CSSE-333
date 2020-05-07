import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

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
}
