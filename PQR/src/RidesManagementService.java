import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

public class RidesManagementService {
private Connection conn;
	
	public RidesManagementService(Connection conn) {
		this.conn = conn;
	}
	
	int InsertRides(String AthleteID, String BroomID) {
		try {
			CallableStatement cs = this.conn.prepareCall("{? = call [Insert_Rides](?, ?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, AthleteID);
			cs.setString(3, BroomID);
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
			JOptionPane.showMessageDialog(null, "Athlete already rides a broomstick, use Update procedure instead.");
			break;
		}
	}
	
	int UpdateRides(String AthleteID, String BroomstickID) {
		try {
			CallableStatement cs = this.conn.prepareCall("{? = call [Update_Rides](?, ?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, BroomstickID);
			cs.setString(3, BroomstickID);
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
			JOptionPane.showMessageDialog(null, "BroomstickID does not exist");
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "Athlete not in Rides table, use Insert instead");
			break;
		}
	}
	
	int DeleteRides(String AthleteID) {
		try {
			CallableStatement cs = this.conn.prepareCall("{? = call [delete_Rides](?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, AthleteID);
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
			JOptionPane.showMessageDialog(null, "AthleteID does not exist in Rides table");
			break;
		}
	}
}
