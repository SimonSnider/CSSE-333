import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import sodabase.ui.SodaByRestaurant;

public class TeamManagementService {
	private Connection conn;
	
public TeamManagementService(Connection connection) {
	this.conn = connection;
}
void InsertTeam(String SnitchCatcherID, String County, String State, String Name) {
	try{
		CallableStatement stmt = conn.prepareCall("{? = call [Insert_Team](?, ?, ?, ?)}");
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
		CallableStatement stmt = conn.prepareCall("{? = call [Update_Team](?, ?, ?, ?, ?)}");
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
		CallableStatement stmt = conn.prepareCall("{? = call [Delete_Team](?)}");
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

public ArrayList<String> getTeams(){
	try{
		ArrayList<String> teams = new ArrayList<>();
		CallableStatement statement = this.conn.prepareCall("{? = call [Get_Teams]()}");
		statement.registerOutParameter(1, Types.INTEGER);
		ResultSet rs = statement.executeQuery();
		while(rs.next()) {
 			teams.add(rs.getInt("TeamID") + ", " + rs.getString("name"));
		}
		for(String current: teams) {
			System.out.println(current);
		}
		return teams;
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
	
	
	
	
}

//private ArrayList<SodaByRestaurant> parseResults(ResultSet rs) {
//	try {
//		ArrayList<SodaByRestaurant> sodasByRestaurants = new ArrayList<SodaByRestaurant>();
//		int restNameIndex = rs.findColumn("Restaurant");
//		int sodaNameIndex = rs.findColumn("Soda");
//		int manfIndex = rs.findColumn("Manufacturer");
//		int restContactIndex = rs.findColumn("RestaurantContact");
//		int priceIndex = rs.findColumn("Price");
//		while (rs.next()) {
//			sodasByRestaurants.add(new SodaByRestaurant(rs.getString(restNameIndex), rs.getString(sodaNameIndex),
//					rs.getString(manfIndex), rs.getString(restContactIndex), rs.getDouble(priceIndex)));
//		}
//		System.out.println(sodasByRestaurants.size());
//		return sodasByRestaurants;
//	} catch (SQLException ex) {
//		JOptionPane.showMessageDialog(null,
//				"An error ocurred while retrieving sodas by restaurants. See printed stack trace.");
//		ex.printStackTrace();
//		return new ArrayList<SodaByRestaurant>();
//	}
//
//}


}
