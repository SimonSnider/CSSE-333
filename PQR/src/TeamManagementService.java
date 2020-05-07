import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;

import javax.swing.JOptionPane;

public class TeamManagementService {
	private ConnectionManagementDevice cmd;
	
public TeamManagementService(ConnectionManagementDevice cmd) {
	this.cmd = cmd;
}
void InsertTeam(String SnitchCatcherID, String County, String State, String Name) {
	try{
		CallableStatement stmt = cmd.getConnection().prepareCall("{? = call [Insert_Team](?, ?, ?, ?)}");
		stmt.registerOutParameter(1, Types.INTEGER);
		stmt.setString(2, SnitchCatcherID);
		stmt.setString(3, County);
		stmt.setString(4, State);
		stmt.setString(5, Name);
		
		int status = Integer.parseInt(stmt.getString(1));
		switch(status){
		case 0:
			JOptionPane.showMessageDialog(null, "Successfully Inserted Team");
			break;
		case 1:
			JOptionPane.showMessageDialog(null, "Error Code 1: Provided Incorrect Parameters!");
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "Error Code 2: SnitchCatcherID must be a valid AthleteID!");;
			break;
		}
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
}
void UpdateTeam(String SnitchCatcherID, String County, String State, String Name, String TeamID) {
	try{
		CallableStatement stmt = cmd.getConnection().prepareCall("{? = call [Update_Team](?, ?, ?, ?, ?)}");
		stmt.registerOutParameter(1, Types.INTEGER);
		stmt.setString(2, SnitchCatcherID);
		stmt.setString(3, County);
		stmt.setString(4, State);
		stmt.setString(5, Name);
		stmt.setString(6, TeamID);

		int status = Integer.parseInt(stmt.getString(1));
		switch(status){
		case 0:
			JOptionPane.showMessageDialog(null, "Successfully Updated Team");
			break;
		case 1:
			JOptionPane.showMessageDialog(null, "Error Code 1: Provided Incorrect Parameters!");
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "Error Code 1: Provided TeamID does not exist!");
			break;
		case 3:
			JOptionPane.showMessageDialog(null, "Error Code 3: SnitchCatcherID must be a valid AthleteID!");;
			break;
		}
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
}

void Delete(String TeamID) {
	try{
		CallableStatement stmt = cmd.getConnection().prepareCall("{? = call [Delete_Team](?)}");
		stmt.registerOutParameter(1, Types.INTEGER);
		stmt.setString(2, TeamID);

		int status = Integer.parseInt(stmt.getString(1));
		switch(status){
		case 0:
			JOptionPane.showMessageDialog(null, "Successfully Deleted Team");
			break;
		case 1:
			JOptionPane.showMessageDialog(null, "Error Code 1: Provided Incorrect Parameters!");
			break;
		case 2:
			JOptionPane.showMessageDialog(null, "Error Code 1: SnitchCatcherID must be a valid AthleteID!");
			break;
		}
	}
	catch(SQLException e) {
		e.printStackTrace();
	}
}


}
