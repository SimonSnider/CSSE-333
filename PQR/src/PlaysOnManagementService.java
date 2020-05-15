import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class PlaysOnManagementService {
	private Connection cmd;
	
	public PlaysOnManagementService(Connection cmd) {
		this.cmd = cmd;
	}
	
	void InsertPlaysOn(String AthleteID, String TeamID, String Position, String JoinedDate, String LeftDate) {
		try {
			CallableStatement cs = cmd.prepareCall("{? = call [Insert_PlaysOn](?, ?, ?, ?, ?)}");
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
			CallableStatement cs = cmd.prepareCall("{? = call [Update_PlaysOn](?, ?, ?, ?, ?)}");
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
				JOptionPane.showMessageDialog(null, "Successfully Updated");
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
			CallableStatement cs = cmd.prepareCall("{? = call [delete_PlaysOn](?, ?)}");
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
	
	public ArrayList<String> getPlaysOn(){
		try{
			ArrayList<String> matches = new ArrayList<>();
			CallableStatement statement = this.cmd.prepareCall("{? = call [Get_PlaysOn]()}");
			statement.registerOutParameter(1, Types.INTEGER);
			ResultSet rs = statement.executeQuery();
			int AIDC = rs.findColumn("AthleteID");
			int ANC = rs.findColumn("AthleteName");
			int TIDC = rs.findColumn("TeamID");
			int TNC = rs.findColumn("TeamName");
			while(rs.next()) {
	 			matches.add(rs.getString(ANC) + "(" + rs.getString(AIDC) + "); " + rs.getString(TNC) + "(" + rs.getString(TIDC) + ")");
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
			CallableStatement cs = cmd.prepareCall("{ ? = call [Get_PlaysOn_Data](?, ?) }");
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
			int ANC = rs.findColumn("AthleteName");
			int TIDC = rs.findColumn("TeamID");
			int TNC = rs.findColumn("TeamName");
			int PC = rs.findColumn("Position");
			int JDC = rs.findColumn("JoinedDate");
			int LDC = rs.findColumn("LeftDate");
			int[] arr = {AIDC, ANC, TIDC, TNC, PC, JDC, LDC};
			ArrayList<String> tempInfo = new ArrayList<String>();
			/*for(int y = 0; y < 7; y++) {
				tempInfo.add(y, rs.getString(tempColumns.get(y)));
				y++;
			}*/
			while(rs.next()) {
				for(int y = 0; y < 7; y++) {
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
