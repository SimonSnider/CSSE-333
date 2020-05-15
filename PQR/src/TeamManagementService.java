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
int InsertTeam(String County, String State, String Name) {
	try{
		CallableStatement stmt = conn.prepareCall("{? = call [Insert_Team](?, ?, ?)}");
		stmt.registerOutParameter(1, Types.INTEGER);
		stmt.setString(2, County);
		stmt.setString(3, State);
		stmt.setString(4, Name);
		stmt.execute();
		int status = Integer.parseInt(stmt.getString(1));
		return status;
	}
	catch(SQLException e) {
		e.printStackTrace();
		return -1;
	}
}

void handleInsertStatus(int status) {
	switch(status){
	case 0:
		JOptionPane.showMessageDialog(null, "Successfully Inserted Team");
		break;
	case 1:
		JOptionPane.showMessageDialog(null, "Error Code 1: Provided Incorrect Parameters!");
		break;
	}
}

int UpdateTeam(String County, String State, String Name, String TeamID) {
	try{
		CallableStatement stmt = conn.prepareCall("{? = call [Update_Team](?, ?, ?, ?)}");
		stmt.registerOutParameter(1, Types.INTEGER);
		stmt.setString(2, County);
		stmt.setString(3, State);
		stmt.setString(4, Name);
		stmt.setString(5, TeamID);
		stmt.execute();
		int status = Integer.parseInt(stmt.getString(1));
		return status;
	}
	catch(SQLException e) {
		e.printStackTrace();
		return -1;
	}
}

void handleUpdateStatus(int status) {
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
	}
}

int DeleteTeam(String TeamID) {
	try{
		CallableStatement stmt = conn.prepareCall("{? = call [Delete_Team](?)}");
		stmt.registerOutParameter(1, Types.INTEGER);
		stmt.setString(2, TeamID);
		stmt.execute();
		int status = Integer.parseInt(stmt.getString(1));
		return status;
	}
	catch(SQLException e) {
		JOptionPane.showMessageDialog(null, "You cannot delete a team that has competed in a match.");
		e.printStackTrace();
		return -1;
	}
}

void handleDeleteStatus(int status) {
	switch(status) {
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

/*
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
*/

public ArrayList<String> getInfoFromID(String ID) {
	try {
		String[] temparr = {"Name", "State", "County"};
		CallableStatement cs = conn.prepareCall("{ ? = call [Get_Team_data](?) }");
		cs.registerOutParameter(1, Types.INTEGER);
		cs.setString(2, ID);
		ResultSet rs =  cs.executeQuery();
		ArrayList<Integer> tempColumns = new ArrayList<Integer>();
		/*int col = 0;
		for (int z = 0; z < 7; z++) {
			col = rs.findColumn(athleteFields[z]);
			tempColumns.add(col);
		}*/
		int NC = rs.findColumn(temparr[0]);
		int SC = rs.findColumn(temparr[1]);
		int CC = rs.findColumn(temparr[2]);
		int[] arr = {NC, SC, CC};
		ArrayList<String> tempInfo = new ArrayList<String>();
		/*for(int y = 0; y < 7; y++) {
			tempInfo.add(y, rs.getString(tempColumns.get(y)));
			y++;
		}*/
		while(rs.next()) {
			for(int y = 0; y < 3; y++) {
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
		CallableStatement cs = conn.prepareCall("{ ? = call [Get_Teams]() }");
		cs.registerOutParameter(1, Types.INTEGER);
		ResultSet rs = cs.executeQuery();
		int IDColumn;
		IDColumn = rs.findColumn("TeamID");
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
