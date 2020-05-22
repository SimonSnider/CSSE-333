import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class DataGetter {
	Connection conn = null;
	DataGetter(Connection conn){
		this.conn = conn;
	}
	
	public ArrayList<String> getColumns(String arrayName, String[] viewColumns) {
		ArrayList<String> matches = new ArrayList<>();
		int i = 0;
		int max = viewColumns.length;
		while (i < max) {
			matches.add(viewColumns[i++]);
		}
		return matches;
	}
	
	//Calls have column first, asc/dsc second. "Asc" or "Desc" respectively
	public Object[][] getData(String[] colNames, String arrayName, String columnSort, String ascDesc) {
		if (ascDesc.compareTo("Ascending") == 0)
			ascDesc = "Asc";
		else if (ascDesc.compareTo("Descending") == 0) {
			ascDesc = "Desc";
		}
		return getView(colNames, arrayName, columnSort, ascDesc);
	}
	private Object[][] getView(String[] colNames, String arrayName, String columnSort, String ascDesc) {
		try{
			//ArrayList<String> matches = new ArrayList<>();
			Object[][] returner;
			CallableStatement statement = this.conn.prepareCall("{? = call [" + arrayName + "](?, ?)}");
			statement.registerOutParameter(1, Types.INTEGER);
			statement.setString(2, columnSort);
			statement.setString(3, ascDesc);
			ResultSet rs = statement.executeQuery();
			int len = colNames.length;
			//Use len to keep track of how to input into array
			int columnIndex = 0;
			int totalLength = 0;
			int rows = 0;
			ArrayList<Object> temp = new ArrayList<Object>();
			while(rs.next()) {
				columnIndex = 1;
				while(columnIndex <= len) {
					temp.add(rs.getString(columnIndex++));
					totalLength++;
				}
				rows++;
			}
			
			returner = new Object[rows][len];
			
			int currentRow = 0;
			int currentColumn = 0;
			for(int count = 0; count < totalLength; count++) {
				Object tmp = temp.get(count);
				if (tmp == null)
					returner[currentRow][currentColumn++] = "null";
				else
					returner[currentRow][currentColumn++] = temp.get(count);
				if (currentColumn == len) {
					currentColumn = 0;
					currentRow++;
				}
			}
			
			return returner;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}