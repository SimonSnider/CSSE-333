import java.sql.Connection;

public class ConnectionManagementDevice {
	private Connection conn = null;
	
	public ConnectionManagementDevice(Connection connection) {
		this.conn = connection;
	}

	public Connection getConnection() {
		return this.conn;
	}
}