import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

public class PlaysOnManagementService {
	private ConnectionManagementDevice cmd;
	
	public PlaysOnManagementService(ConnectionManagementDevice cmd) {
		this.cmd = cmd;
	}
	
	void InsertPlaysOn(String AthleteID, String TeamID, String Position, String JoinedDate, String LeftDate) {
		try {
			CallableStatement cs = cmd.getConnection().prepareCall("{? = call [Insert_PlaysOn](?, ?, ?, ?, ?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, AthleteID);
			cs.setString(3, TeamID);
			cs.setString(4, Position);
			cs.setString(5, JoinedDate);
			cs.setString(6, LeftDate);
			cs.execute();
			
			int status = Integer.parseInt(cs.getString(1));
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
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	void UpdatePlaysOn(String AthleteID, String TeamID, String Position, String JoinedDate, String LeftDate) {
		try {
			CallableStatement cs = cmd.getConnection().prepareCall("{? = call [Update_PlaysOn](?, ?, ?, ?, ?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, AthleteID);
			cs.setString(3, TeamID);
			cs.setString(4, Position);
			cs.setString(5, JoinedDate);
			cs.setString(6, LeftDate);
			cs.execute();
			
			int status = Integer.parseInt(cs.getString(1));
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
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	void DeletePlaysOn(String AthleteID, String TeamID) {
		try {
			CallableStatement cs = cmd.getConnection().prepareCall("{? = call [delete_PlaysOn](?, ?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, AthleteID);
			cs.setString(3, TeamID);
			cs.execute();
			
			int status = Integer.parseInt(cs.getString(1));
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
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
