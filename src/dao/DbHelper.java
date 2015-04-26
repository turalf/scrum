package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Helper class used to make a connection to the database
 * 
 * @author turalf
 *
 */
public class DbHelper {

	private  Connection c;

	/**
	 * Get a connection to the database
	 * 
	 * @return the Connection object to the database
	 */
	public  Connection getConnection() {
		String db = "org.sqlite.JDBC";
		String url = "jdbc:sqlite:./db/scrum";
		if (c == null) {
			try {
				Class.forName(db);
				c = DriverManager.getConnection(url);

			} catch (Exception e) {
				System.err.println(e.getClass().getName() + ": "
						+ e.getMessage());
				System.exit(0);
			}
		}
		return c;

	}

	/**
	 * Closes a connection to the database
	 */
	public  void closeConnection() {
		if (c != null){
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String... args) {
		new DbHelper().getConnection();
	}

}
