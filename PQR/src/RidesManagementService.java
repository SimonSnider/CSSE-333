import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

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
			cs.setString(2, AthleteID);
			cs.setString(3, BroomstickID);
			cs.execute();
			
			int status = Integer.parseInt(cs.getString(1));
			switch(status) {
			case 0:
				JOptionPane.showMessageDialog(null, "Successfully Updated");
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
	
	public ArrayList<String> getRides(){
		try{
			ArrayList<String> matches = new ArrayList<>();
			CallableStatement statement = this.conn.prepareCall("{? = call [Get_Rides]()}");
			statement.registerOutParameter(1, Types.INTEGER);
			ResultSet rs = statement.executeQuery();
			int AIDC = rs.findColumn("AthleteID");
			int BIDC = rs.findColumn("BroomID");
			int ANC = rs.findColumn("Name");
			int BMC = rs.findColumn("Make");
			int BMDC = rs.findColumn("Model");
			while(rs.next()) {
	 			matches.add(rs.getString(ANC) + "(" + rs.getString(AIDC) + "); " + rs.getString(BMC) + ", " + rs.getString(BMDC) + "(" + rs.getString(BIDC) + ")");
			}
			for(String current: matches) {
				System.out.println(current);
			}
			return matches;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<String> getInfoFromID(String AID, String BID) {
		try {
			String[] temparr = {"AthleteID", "BroomID", "Name", "Make", "Model"};
			CallableStatement cs = conn.prepareCall("{ ? = call [Get_Rides_Data](?, ?) }");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(3, AID);
			cs.setString(2, BID);
			ResultSet rs =  cs.executeQuery();
			ArrayList<Integer> tempColumns = new ArrayList<Integer>();
			/*int col = 0;
			for (int z = 0; z < 7; z++) {
				col = rs.findColumn(athleteFields[z]);
				tempColumns.add(col);
			}*/
			int AIDC = rs.findColumn("AthleteID");
			int BIDC = rs.findColumn("BroomID");
			int ANC = rs.findColumn("Name");
			int BMC = rs.findColumn("Make");
			int BMDC = rs.findColumn("Model");
			int[] arr = {AIDC, BIDC, ANC, BMC, BMDC};
			ArrayList<String> tempInfo = new ArrayList<String>();
			/*for(int y = 0; y < 7; y++) {
				tempInfo.add(y, rs.getString(tempColumns.get(y)));
				y++;
			}*/
			while(rs.next()) {
				for(int y = 0; y < 5; y++) {
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
}
