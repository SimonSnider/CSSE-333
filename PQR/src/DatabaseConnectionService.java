import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import javax.swing.*;
public class DatabaseConnectionService {
	
	//DO NOT EDIT THIS STRING, YOU WILL RECEIVE NO CREDIT FOR THIS TASK IF THIS STRING IS EDITED
	private final String SampleURL = "jdbc:sqlserver://${dbServer};databaseName=${dbName};user=${user};password={${pass}}";

	private Connection connection = null;

	private String databaseName;
	private String serverName;

	public DatabaseConnectionService(String serverName, String databaseName) {
		//DO NOT CHANGE THIS METHOD
		this.serverName = serverName;
		this.databaseName = databaseName;
	}

	public boolean connect(String user, String pass) throws ClassNotFoundException {
		JPanel panel = new JPanel();
		panel.setBounds(650, 350, 300, 200);
		JLabel label = new JLabel();
		panel.add(label);
		//String connectionString = "jdbc:sqlserver://" + this.serverName + ";databaseName=" + this.databaseName + ";user=" + user + ";password={" + pass + "}";
		
		//Load from properties file and encrypt password
		
		//TEMPORARY
		String connectionString = "jdbc:sqlserver://golem.csse.rose-hulman.edu;databaseName=PrepQuidditchReport;user=PQRUser30;password=S2G2Password";
		
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:sqlserver://golem.csse.rose-hulman.edu;databaseName=PrepQuidditchReport;user=PQRUser30;password={S2G2Password}");
			this.connection = con;
			label.setText("Successfully connected!");
			panel.setVisible(true);
            return true;
        }
        catch (SQLException e) {
        	label.setText("Connection failed!");
        	e.printStackTrace();
        	panel.setVisible(true);
            return false;
        }
	}
	

	public Connection getConnection() {
		return this.connection;
	}
	
	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connection = null;
	}
	
	public String getDBName() {
		return this.databaseName;
	}

}
