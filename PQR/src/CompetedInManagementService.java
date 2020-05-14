import java.sql.CallableStatement;
	import java.sql.Connection;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Types;
	import java.util.ArrayList;

	import javax.swing.JOptionPane;
	
public class CompetedInManagementService {
	
		private Connection conn;
		
		public CompetedInManagementService(Connection conn) {
			this.conn = conn;
		}

		void InsertCompetedIn(String TeamID, String MatchID, String HM, String Score) {
			try {
				CallableStatement cs = conn.prepareCall("{ ? = call [Insert_CompetedIn](?, ?, ?, ?) }");
				cs.registerOutParameter(1, Types.INTEGER);
				cs.setString(2, TeamID);
				cs.setString(3, MatchID);
				cs.setString(4, HM);
				cs.setString(5, Score);
			
				cs.execute();
				int status = Integer.parseInt(cs.getString(1));
				switch(status) {
				case 0:
					JOptionPane.showMessageDialog(null, "Successfully inserted!");
					break;
				case 1:
					JOptionPane.showMessageDialog(null, "Error Code 1: Invalid Parameters.");
					break;
				case 2:
					JOptionPane.showMessageDialog(null, "Error Code 2: TeamID is not a valid Team.");
					break;
				case 3:
					JOptionPane.showMessageDialog(null, "Error Code 3: MatchID is not a valud match.");
					break;
				case 4:
					JOptionPane.showMessageDialog(null, "Error Code 4: Score cannot be a negative value.");
					break;
				case 5:
					JOptionPane.showMessageDialog(null, "Error Code 5: Must be Home or Away");
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		void UpdateCompetedIn(String TeamID, String MatchID, String HM, String Score) {
			try {
				CallableStatement cs = conn.prepareCall("{ ? = call [Update_CompetedIn](?, ?, ?, ?) }");
				cs.registerOutParameter(1, Types.INTEGER);
				cs.setString(2, TeamID);
				cs.setString(3, MatchID);
				cs.setString(4, HM);
				cs.setString(5, Score);
			
				cs.execute();
				int status = Integer.parseInt(cs.getString(1));
				switch(status) {
				case 0:
					JOptionPane.showMessageDialog(null, "Successfully inserted!");
					break;
				case 1:
					JOptionPane.showMessageDialog(null, "Error Code 1: Invalid Parameters.");
					break;
				case 2:
					JOptionPane.showMessageDialog(null, "Error Code 2: TeamID is not a valid Team.");
					break;
				case 3:
					JOptionPane.showMessageDialog(null, "Error Code 3: MatchID is not a valud match.");
					break;
				case 4:
					JOptionPane.showMessageDialog(null, "Error Code 4: Must be Home or Away");
					break;
				case 5:
					JOptionPane.showMessageDialog(null, "Error Code 5: Score cannot be a negative value.");
					break;
				
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		void DeleteCompetedIn(String TeamID, String MatchID) {
			try {
				CallableStatement cs = conn.prepareCall("{ ? = call [Delete_CompetedIn](?, ?) }");
				cs.registerOutParameter(1, Types.INTEGER);
				cs.setString(2, TeamID);
				cs.setString(3,  MatchID);
				cs.execute();
				int status = Integer.parseInt(cs.getString(1));
				switch(status) {
				case 0:
					JOptionPane.showMessageDialog(null, "Successfully deleted!");
					break;
				case 1:
					JOptionPane.showMessageDialog(null, "Error Code 1: Invalid Parameters.");
					break;
				case 2:
					JOptionPane.showMessageDialog(null, "Error Code 2: TeamID is not a valid Team.");
					break;
				case 3:
					JOptionPane.showMessageDialog(null, "Error Code 3: MatchID is not a valud match.");
					break;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
// *** Commented out until Stored Procedure in SQL is made, will push again once completed ***. 
//		public ArrayList<String> getCompetetions(){
//			try{
//				ArrayList<String> competitions = new ArrayList<>();
//				CallableStatement statement = this.conn.prepareCall("{? = call [Get_CompetedIn]()}");
//				statement.registerOutParameter(1, Types.INTEGER);
//				ResultSet rs = statement.executeQuery();
//				while(rs.next()) {
//		 			athletes.add(rs.getInt("TeamID") + ", " + rs.getString("MatchID"));
//				}
//				for(String current: competitions) {
//					System.out.println(current);
//				}
//				return competitions;
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			return null;
//		}

}
