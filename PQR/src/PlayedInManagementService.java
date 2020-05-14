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
	
	void UpdateCompetedIn(String AthleteID, String MatchID, String TeamID, String BludgerHits, String PointsScored, String Injuries, String Fouls, String Ejections) {
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

	void DeleteCompetedIn(String AthleteID, String MatchID, String TeamID) {
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
	
//*** Commented out until Stored Procedure in SQL is made, will push again once completed ***. 
//	public ArrayList<String> getPlayedIns(){
//		try{
//			ArrayList<String> playedIns = new ArrayList<>();
//			CallableStatement statement = this.conn.prepareCall("{? = call [Get_PlayedIn]()}");
//			statement.registerOutParameter(1, Types.INTEGER);
//			ResultSet rs = statement.executeQuery();
//			while(rs.next()) {
//	 			playedIns.add(rs.getInt("AthleteID") + rs.getInt("MatchID") + ", " + rs.getString("TeamID"));
//			}
//			for(String current: playedIns) {
//				System.out.println(current);
//			}
//			return playedIns;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}

}

	
	
	
