import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PlayedInManagementService {
	private Connection conn;
	
	public PlayedInManagementService(Connection conn) {
		this.conn = conn;
	}

	void InsertPlayedIn(String AthleteID, String MatchID, String TeamID, String BludgerHits, String PointsScored, String Injuries, String Fouls, String Ejections) {
		try {
			CallableStatement cs = conn.prepareCall("{ ? = call [Insert_PlayedIn](?, ?, ?, ?, ?, ?, ?, ?) }");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, AthleteID);
			cs.setString(3, MatchID);
			cs.setString(4, TeamID);
			cs.setString(5, BludgerHits);
			cs.setString(6, PointsScored);
			cs.setString(7, Injuries);
			cs.setString(8, Fouls);
			cs.setString(9, Ejections);
		
			cs.execute();
			int status = Integer.parseInt(cs.getString(1));
			switch(status) {
			case 0:
				JOptionPane.showMessageDialog(null, "Successfully inserted!");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Error Code 1: AthleteID cannot be null.");
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "Error Code 2: MatchID cannot be NULL.");
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "Error Code 3: TeamID cannot be NULL.");
				break;
			case 4:
				JOptionPane.showMessageDialog(null, "Error Code 4: Invalid AthleteID!");
				break;
			case 5:
				JOptionPane.showMessageDialog(null, "Error Code 5: Invalid MatchID!");
				break;
			case 6:
				JOptionPane.showMessageDialog(null, "Error Code 6: Invalid TeamID!");
				break;
			case 7:
				JOptionPane.showMessageDialog(null, "Error Code 7: Integers cannot be negative values!");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	void UpdatePlayedIn(String AthleteID, String MatchID, String TeamID, String BludgerHits, String PointsScored, String Injuries, String Fouls, String Ejections) {
		try {
			CallableStatement cs = conn.prepareCall("{ ? = call [Update_PlayedIn](?, ?, ?, ?, ?, ?, ?, ?) }");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, AthleteID);
			cs.setString(3, MatchID);
			cs.setString(4, TeamID);
			cs.setString(5, BludgerHits);
			cs.setString(6, PointsScored);
			cs.setString(7, Injuries);
			cs.setString(8, Fouls);
			cs.setString(9, Ejections);
			
		
			cs.execute();
			int status = Integer.parseInt(cs.getString(1));
			switch(status) {
			case 0:
				JOptionPane.showMessageDialog(null, "Successfully updated!");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Error Code 1: AthleteID cannot be NULL");
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "Error Code 2: MatchID cannot be NULL.");
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "Error Code 3: TeamID cannot be NULL.");
				break;
			case 4:
				JOptionPane.showMessageDialog(null, "Error Code 4: Invalid AthleteID!");
				break;
			case 5:
				JOptionPane.showMessageDialog(null, "Error Code 5: Invalid MatchID!");
				break;
			case 6:
				JOptionPane.showMessageDialog(null, "Error Code 6: Invalid TeamID!");
				break;
			case 7:
				JOptionPane.showMessageDialog(null, "Error Code 7: Integers cannot be negative values!");
				break;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void DeletePlayedIn(String AthleteID, String MatchID, String TeamID) {
		try {
			CallableStatement cs = conn.prepareCall("{ ? = call [Delete_PlayedIn](?, ?, ?) }");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, AthleteID);
			cs.setString(3,  MatchID);
			cs.setString(4, TeamID);
			cs.execute();
			int status = Integer.parseInt(cs.getString(1));
			switch(status) {
			case 0:
				JOptionPane.showMessageDialog(null, "Successfully deleted!");
				break;
			case 1:
				JOptionPane.showMessageDialog(null, "Error Code 1: AthleteID cannot be NULL");
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "Error Code 2: MatchID cannot be NULL.");
				break;
			case 3:
				JOptionPane.showMessageDialog(null, "Error Code 3: TeamID cannot be NULL.");
				break;
			case 4:
				JOptionPane.showMessageDialog(null, "Error Code 4: Invalid AthleteID!");
				break;
			case 5:
				JOptionPane.showMessageDialog(null, "Error Code 5: Invalid MatchID!");
				break;
			case 6:
				JOptionPane.showMessageDialog(null, "Error Code 6: Invalid TeamID!");
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getPlayedIn(){
		try{
			ArrayList<String> matches = new ArrayList<>();
			CallableStatement statement = conn.prepareCall("{? = call [Get_PlayedIn]()}");
			statement.registerOutParameter(1, Types.INTEGER);
			ResultSet rs = statement.executeQuery();
			int AIDC = rs.findColumn("AthleteID");
			int ANC = rs.findColumn("AthleteName");
			int MIDC = rs.findColumn("MatchID");
			int TIDC = rs.findColumn("TeamID");
			int TNC = rs.findColumn("TeamName");
			while(rs.next()) {
	 			matches.add(rs.getString(MIDC) + "; " + rs.getString(ANC) + "(" + rs.getString(AIDC) + "); " + rs.getString(TNC) + "(" + rs.getString(TIDC) + ")");
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
	
	public ArrayList<String> getInfoFromID(String AID, String MID, String TID) {
		try {
			//String[] temparr = {"AthleteID", "MakeID", "TeamID", "Make", "Model"};
			CallableStatement cs = conn.prepareCall("{ ? = call [Get_PlayedIn_Data](?, ?, ?) }");
			cs.registerOutParameter(1, Types.INTEGER);
			cs.setString(2, AID);
			cs.setString(3, MID);
			cs.setString(4, TID);
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
			int BHC = rs.findColumn("BludgerHits");
			int PSC = rs.findColumn("PointsScored");
			int IC = rs.findColumn("Injuries");
			int FC = rs.findColumn("Fouls");
			int EC = rs.findColumn("Ejections");
			int[] arr = {AIDC, ANC, TIDC, TNC, BHC, PSC, IC, FC, EC};
			ArrayList<String> tempInfo = new ArrayList<String>();
			/*for(int y = 0; y < 7; y++) {
				tempInfo.add(y, rs.getString(tempColumns.get(y)));
				y++;
			}*/
			while(rs.next()) {
				for(int y = 0; y < 9; y++) {
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

	
	
	
