import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

public class MatchManagementService {
	private Connection conn;
	
	public MatchManagementService(Connection conn) {
		this.conn = conn;
	}

	void InsertMatch(String date, String stadium) {
		try {
			CallableStatement cs = conn.prepareCall("{ ? = call [Insert_Match](?, ?) }");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, date);
			cs.setString(3, stadium);
			cs.execute();
			int status = Integer.parseInt(cs.getString(1));
			switch(status) {
			case 0:
				JOptionPane.showMessageDialog(null, "Successfully inserted!");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Error Code 1: The Date entered cannot be from after the current date.");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void UpdateMatch(String matchID, String date, String stadium) {
		try {
			CallableStatement cs = conn.prepareCall("{ ? = call [Update_Match](?, ?, ?) }");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, matchID);
			cs.setString(3, date);
			cs.setString(4, stadium);
			cs.execute();
			int status = Integer.parseInt(cs.getString(1));
			switch(status) {
			case 0:
				JOptionPane.showMessageDialog(null, "Successfully updated!");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Error Code 1: MatchID cannot be null.");
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "Error Code 2: Invalid MatchID.");
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "Error Code 3: Cannot insert a future date into the database.");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void DeleteMatch(String matchID) {
		try {
			CallableStatement cs = conn.prepareCall("{ ? = call [Delete_Match](?) }");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, matchID);
			cs.execute();
			int status = Integer.parseInt(cs.getString(1));
			switch(status) {
			case 0:
				JOptionPane.showMessageDialog(null, "Successfully deleted!");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Error Code 1: MatchID cannot be null.");
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "Error Code 2: Invalid MatchID.");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
