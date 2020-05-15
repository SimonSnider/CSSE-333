import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.Connection;

import javax.swing.JOptionPane;

public class PlaysOnManagementService {
	private Connection conn;
	
	public PlaysOnManagementService(Connection conn) {
		this.conn = conn;
	}
	
	int InsertPlaysOn(String AthleteID, String TeamID, String Position, String JoinedDate, String LeftDate) {
		try {
			CallableStatement cs = conn.prepareCall("{? = call [Insert_PlaysOn](?, ?, ?, ?, ?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, AthleteID);
			cs.setString(3, TeamID);
			cs.setString(4, Position);
			cs.setString(5, JoinedDate);
			cs.setString(6, LeftDate);
			cs.execute();
			
			int status = Integer.parseInt(cs.getString(1));
			return status;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	void handleInsertStatus(int status) {
		switch(status) {
		case 0:
			JOptionPane.showMessageDialog(null, "Successfully Inserted");
			break;
		case 1:
			JOptionPane.showMessageDialog(null, "AthledeID does not exist");
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "TeamID does not exist");
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "Athlete already plays on a team, use update procedure instead");
			break;
		case 4:
			JOptionPane.showMessageDialog(null, "Check that JoinedDate and LeftDate obey the laws of time");
			break;
		}
	}
	
	int UpdatePlaysOn(String AthleteID, String TeamID, String Position, String JoinedDate, String LeftDate) {
		try {
			CallableStatement cs = conn.prepareCall("{? = call [Update_PlaysOn](?, ?, ?, ?, ?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, AthleteID);
			cs.setString(3, TeamID);
			cs.setString(4, Position);
			cs.setString(5, JoinedDate);
			cs.setString(6, LeftDate);
			cs.execute();
			
			int status = Integer.parseInt(cs.getString(1));
			return status;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	void handleUpdateStatus(int status) {
		switch(status) {
		case 0:
			JOptionPane.showMessageDialog(null, "Successfully Inserted");
			break;
		case 1:
			JOptionPane.showMessageDialog(null, "AthleteID does not exist");
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "TeamID does not exist");
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "Athlete has not played on given team, use insert instead");
			break;
		case 4:
			JOptionPane.showMessageDialog(null, "Check that Joined date and Left date obey the laws of time");
			break;
		}
	}
	
	int DeletePlaysOn(String AthleteID, String TeamID) {
		try {
			CallableStatement cs = conn.prepareCall("{? = call [delete_PlaysOn](?, ?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, AthleteID);
			cs.setString(3, TeamID);
			cs.execute();
			
			int status = Integer.parseInt(cs.getString(1));
			return status;
			
		} catch(SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	void handleDeleteStatus(int status) {
		switch(status) {
		case 0:
			JOptionPane.showMessageDialog(null, "Successfully deleted");
			break;
		case 1:
			JOptionPane.showMessageDialog(null, "AthleteID does not exist");
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "TeamID does not exist");
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "Athlete has not played on given team");
			break;
		}
	}
}
