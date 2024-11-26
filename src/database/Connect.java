package database;

import java.sql.*;

public class Connect {

	private static Connect connectionJdbc;
	private static Connection connects;
	private static Statement st;
	private final static String username = "root";
	private final static String password = "";
	private final static String host = "localhost:3306";
	private final static String databases = "chaoticwarfare";
	private final static String jdbcUrl = String.format("jdbc:mysql://%s/%s",host,databases);

	private ResultSet result;
	
	
	private Connect() {
		
		try {
			connects = DriverManager.getConnection(jdbcUrl, username, password);
			st = connects.createStatement();
		} catch (Exception e) {
		}
	}
	
	public Connection getConnects() {
		return connects;
	}

	public static Connection getConnect() {
		if(connectionJdbc == null) {
			return null;
		}
		return connectionJdbc.getConnects();
	}
	
	public static Connect getInstance() {
		if(connectionJdbc == null) {
			connectionJdbc = new Connect();
			if(connectionJdbc.getConnects() == null) {
				connectionJdbc = null;
			}
		}
		return connectionJdbc;
	}
	
	public ResultSet executeQuery(String query) {
		try {
			result = st.executeQuery(query);
		} catch (Exception e) {
			System.out.println("Failed to execute query.");
		}
		return result;
	}
}
