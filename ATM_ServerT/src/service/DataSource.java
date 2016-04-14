package service;

import java.sql.Connection;

import org.apache.commons.dbcp.BasicDataSource;

public class DataSource {
	Connection connection = null;
	BasicDataSource bdSource = new BasicDataSource();

	public DataSource() {
		bdSource.setDriverClassName("com.mysql.jdbc.Driver");
		bdSource.setUrl("jdbc:mysql://localhost/atm");
		bdSource.setUsername("Solomka");
		bdSource.setPassword("solomka77");
	}

	public Connection createConnection() {
		Connection con = null;
		try {
			if (connection != null) {
				System.out.println("Cant create a New Connection");
			} else {
				con = bdSource.getConnection();
			}
		} catch (Exception e) {
			System.out.println("Error Occured " + e.toString());
		}
		return con;
	}
}
