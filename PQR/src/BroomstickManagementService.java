import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

import java.sql.CallableStatement;

public class BroomstickManagementService {
	private ConnectionManagementDevice cmd;
	
	public BroomstickManagementService(ConnectionManagementDevice cmd) {
		this.cmd = cmd;
	}
	
	void InsertBroomstick(String make, String model, String date) {
		try {
			CallableStatement cs = cmd.getConnection().prepareCall("{? = call [Insert_Broomstick](?, ?, ?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, make);
			cs.setString(3, model);
			cs.setString(4, date);
			cs.execute();
			
			int status = Integer.parseInt(cs.getString(1));
			switch(status) {
			case 0:
				JOptionPane.showMessageDialog(null, "Successfully Inserted");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Error Code 1: Broomstick Make and Model already exists");
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "Error Code 2: Release Date cannot be after today's date");;
				break;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	void UpdateBroomstick(String BroomstickID, String make, String model, String date) {
		try {
			CallableStatement cs = cmd.getConnection().prepareCall("{? = call [Update_Broomstick](?, ?, ?, ?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, BroomstickID);
			cs.setString(3, make);
			cs.setString(4, model);
			cs.setString(5, date);
			cs.execute();
			
			int status = Integer.parseInt(cs.getString(1));
			switch(status) {
			case 0:
				JOptionPane.showMessageDialog(null, "Successfully Inserted");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Error Code 1: Release date cannot be after today's date");
				break;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	void DeleteBroomstick(String BroomstickID) {
		try {
			CallableStatement cs = cmd.getConnection().prepareCall("{? = call [Delete_Broomstick](?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, BroomstickID);
			cs.execute();
			
			int status = Integer.parseInt(cs.getString(1));
			switch(status) {
			case 0:
				JOptionPane.showMessageDialog(null, "Successfully deleted");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Error Code 1: BroomstickID does not exist");
				break;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
