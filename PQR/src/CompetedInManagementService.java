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
					JOptionPane.showMessageDialog(null, "Successfully updated!");
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
		
		public ArrayList<String> getCompetedIn(){
			try{
				ArrayList<String> matches = new ArrayList<>();
				CallableStatement statement = conn.prepareCall("{? = call [Get_CompetedIn]()}");
				statement.registerOutParameter(1, Types.INTEGER);
				ResultSet rs = statement.executeQuery();
				int MIDC = rs.findColumn("MatchID");
				int TIDC = rs.findColumn("TeamID");
				int TNC = rs.findColumn("TeamName");
				while(rs.next()) {
		 			matches.add(rs.getString(MIDC) + "; " + rs.getString(TNC) + "(" + rs.getString(TIDC) + ")");
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
		
		public ArrayList<String> getInfoFromID(String MID, String TID) {
			try {
				//String[] temparr = {"AthleteID", "MakeID", "TeamID", "Make", "Model"};
				CallableStatement cs = conn.prepareCall("{ ? = call [Get_CompetedIn_Data](?, ?) }");
				cs.registerOutParameter(1, Types.INTEGER);
				cs.setString(2, MID);
				cs.setString(3, TID);
				ResultSet rs =  cs.executeQuery();
				ArrayList<Integer> tempColumns = new ArrayList<Integer>();
				/*int col = 0;
				for (int z = 0; z < 7; z++) {
					col = rs.findColumn(athleteFields[z]);
					tempColumns.add(col);
				}*/
				int TIDC = rs.findColumn("TeamID");
				int TNC = rs.findColumn("TeamName");
				int MIDC = rs.findColumn("MatchID");
				int HAC = rs.findColumn("Home/Away");
				int SC = rs.findColumn("Score");
				int[] arr = {MIDC, TIDC, TNC, HAC, SC};
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
