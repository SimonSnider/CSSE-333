import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class BroomstickManagementService {
	private Connection conn;
	
	public BroomstickManagementService(Connection conn) {
		this.conn = conn;
	}
	
	void InsertBroomstick(String make, String model, String date) {
		try {
			CallableStatement cs = this.conn.prepareCall("{? = call [Insert_Broomstick](?, ?, ?)}");
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
				JOptionPane.showMessageDialog(null, "Error Code 2: Release Date cannot be after today's date");
				break;
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Something was input wrong. Please follow the indicated formats.");
			e.printStackTrace();
		}
	}
	
	void UpdateBroomstick(String BroomstickID, String make, String model, String date) {
		try {
			CallableStatement cs = conn.prepareCall("{? = call [Update_Broomstick](?, ?, ?, ?)}");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, BroomstickID);
			cs.setString(3, make);
			cs.setString(4, model);
			cs.setString(5, date);
			cs.execute();
			
			int status = Integer.parseInt(cs.getString(1));
			switch(status) {
			case 0:
				JOptionPane.showMessageDialog(null, "Successfully Updated");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Error Code 1: Release date cannot be after today's date");
				break;
			}
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Something was input wrong. Please follow the indicated formats.");
			e.printStackTrace();
		}
	}
	
	void DeleteBroomstick(String BroomstickID) {
		try {
			CallableStatement cs = this.conn.prepareCall("{? = call [Delete_Broomstick](?)}");
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
	public ArrayList<String> getBroomsticks(){
		try{
			ArrayList<String> broomsticks = new ArrayList<>();
			CallableStatement statement = this.conn.prepareCall("{? = call [Get_Broomsticks]()}");
			statement.registerOutParameter(1, Types.INTEGER);
			ResultSet rs = statement.executeQuery();
			int IDC = rs.findColumn("BroomID");
			int MC = rs.findColumn("Make");
			int MDC = rs.findColumn("Model");
			while(rs.next()) {
	 			broomsticks.add(rs.getString(IDC) + "," + rs.getString(MC) + ", " + rs.getString(MDC));
			}
			return broomsticks;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	///FIX
	public ArrayList<String> getInfoFromID(String ID) {
		try {
			String[] temparr = {"Make", "Model", "ReleaseDate"};
			CallableStatement cs = conn.prepareCall("{ ? = call [Get_Broomstick_Data](?) }");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, ID);
			ResultSet rs =  cs.executeQuery();
			ArrayList<Integer> tempColumns = new ArrayList<Integer>();
			/*int col = 0;
			for (int z = 0; z < 7; z++) {
				col = rs.findColumn(athleteFields[z]);
				tempColumns.add(col);
			}*/
			int MC = rs.findColumn(temparr[0]);
			int MDC = rs.findColumn(temparr[1]);
			int RDC = rs.findColumn(temparr[2]);
			int[] arr = {MC, MDC, RDC};
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
}
