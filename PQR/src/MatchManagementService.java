import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MatchManagementService {
	private Connection conn;
	
	public MatchManagementService(Connection conn) {
		this.conn = conn;
	}

	int InsertMatch(String homeTeamID, String awayTeamID, String homeTeamScore, String awayTeamScore, String date, String stadium) {
		try {
			CallableStatement cs = conn.prepareCall("{ ? = call [Insert_Match](?, ?, ?, ?, ?, ?) }");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, homeTeamID);
			cs.setString(3, awayTeamID);
			cs.setString(4, homeTeamScore);
			cs.setString(5, awayTeamScore);
			cs.setString(6, date);
			cs.setString(7, stadium);
			cs.execute();
			int status = Integer.parseInt(cs.getString(1));
			return status;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Something was input wrong. Please follow the indicated formats.");
			e.printStackTrace();
			return -1;
		}
	}
	
	void handleInsertStatus(int status) {
		switch(status) {
		case 0:
			JOptionPane.showMessageDialog(null, "Successfully inserted!");
			break;
		case 1:
			JOptionPane.showMessageDialog(null, "Error Code 1: The Date entered cannot be from after the current date.");
			break;
		}
	}
	
	int UpdateMatch(String matchID, String homeTeamID, String awayTeamID, String homeTeamScore, String awayTeamScore, String date, String stadium) {
		try {
			CallableStatement cs = conn.prepareCall("{ ? = call [Update_Match](?, ?, ?, ?, ?, ?, ?) }");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, matchID);
			cs.setString(3, homeTeamID);
			cs.setString(4, awayTeamID);
			cs.setString(5, homeTeamScore);
			cs.setString(6, awayTeamScore);
			cs.setString(7, date);
			cs.setString(8, stadium);
			cs.execute();
			int status = Integer.parseInt(cs.getString(1));
			return status;
			//JOptionPane.showMessageDialog(null, cs.getString(1));
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Something was input wrong. Please follow the indicated formats.");
			e.printStackTrace();
			return -1;
		}
	}
	
void handleUpdateStatus(int status) {
	switch(status) {
	case 0:
		JOptionPane.showMessageDialog(null, "Successfully updated!");
		break;
	case 1:
		JOptionPane.showMessageDialog(null, "Error Code 1: MatchID cannot be null.");
		break;
	case 2:
		JOptionPane.showMessageDialog(null, "Error Code 2: Invalid MatchID.");
		break;
	case 3:
		JOptionPane.showMessageDialog(null, "Error Code 3: Cannot insert a future date into the database.");
		break;
	}
	}

	int DeleteMatch(String matchID) {
		try {
			CallableStatement cs = conn.prepareCall("{ ? = call [Delete_Match](?) }");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, matchID);
			cs.execute();
			int status = Integer.parseInt(cs.getString(1));
			return status;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
void handleDeleteStatus(int status) {
	switch(status) {
	case 0:
		JOptionPane.showMessageDialog(null, "Successfully deleted!");
		break;
	case 1:
		JOptionPane.showMessageDialog(null, "Error Code 1: MatchID cannot be null.");
		break;
	case 2:
		JOptionPane.showMessageDialog(null, "Error Code 2: Invalid MatchID.");
		break;
	}
	}
	
	public ArrayList<String> getInfoFromID(String ID) {
		try {
			String[] temparr = {"HomeTeam", "AwayTeam", "HomeScore", "AwayScore", "Date", "Stadium", "HomeTeamID", "AwayTeamID"};
			CallableStatement cs = conn.prepareCall("{ ? = call [Get_Match_Data](?) }");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, ID);
			ResultSet rs =  cs.executeQuery();
			ArrayList<Integer> tempColumns = new ArrayList<Integer>();
			/*int col = 0;
			for (int z = 0; z < 7; z++) {
				col = rs.findColumn(athleteFields[z]);
				tempColumns.add(col);
			}*/
			int HTC = rs.findColumn(temparr[0]);
			int ATC = rs.findColumn(temparr[1]);
			int HSC = rs.findColumn(temparr[2]);
			int ASC = rs.findColumn(temparr[3]);
			int DC = rs.findColumn(temparr[4]);
			int SC = rs.findColumn(temparr[5]);
			int HTIC = rs.findColumn(temparr[6]);
			int ATIC = rs.findColumn(temparr[7]);
			int[] arr = {HTC, ATC, HSC, ASC, DC, SC, HTIC, ATIC};
			ArrayList<String> tempInfo = new ArrayList<String>();
			/*for(int y = 0; y < 7; y++) {
				tempInfo.add(y, rs.getString(tempColumns.get(y)));
				y++;
			}*/
			while(rs.next()) {
				for(int y = 0; y < 8; y++) {
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
	
	public ArrayList<String> getMatches(){
		try{
			ArrayList<String> matches = new ArrayList<>();
			CallableStatement statement = this.conn.prepareCall("{? = call [Get_Matches]()}");
			statement.registerOutParameter(1, Types.INTEGER);
			ResultSet rs = statement.executeQuery();
			int MIC = rs.findColumn("MatchID");
			int HTC = rs.findColumn("HomeTeam");
			int HTIC = rs.findColumn("HomeTeamID");
			int ATC = rs.findColumn("AwayTeam");
			int ATIC = rs.findColumn("AwayTeamID");
			int DC = rs.findColumn("Date");
			while(rs.next()) {
	 			matches.add(rs.getString(MIC) + ", " + rs.getString(HTC) + "(" + rs.getString(HTIC) + "), " + rs.getString(ATC) + "(" + rs.getString(ATIC) + "), " + rs.getDate(DC));
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
}
