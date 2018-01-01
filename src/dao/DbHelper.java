package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Helper class used to make a connection to the database
 * 
 * @author turalf
 *
 */
public class DbHelper {

	private Connection c;

	/**
	 * Get a connection to the database
	 * 
	 * @return the Connection object to the database
	 */
	public Connection getConnection() {
		String db = "org.sqlite.JDBC";
		String dbFileLoc = "./db/scrum";
		String url = "jdbc:sqlite:" + dbFileLoc;

		if (c == null) {
			try {
				boolean fileExist = true;
				
				if (!new File(dbFileLoc).exists()) {
					fileExist = false;
				}
				Class.forName(db);
				c = DriverManager.getConnection(url);
				
				// if db file does not exist run DDL statements
				if (!fileExist) {
					PreparedStatement ps = c.prepareStatement(DDL.CREATE_STORY.getSql());
					PreparedStatement ps1 = c.prepareStatement(DDL.CREATE_TASK.getSql());
					ps.executeUpdate();
					ps1.executeUpdate();
				}

			} catch (Exception e) {
				System.exit(1);
			}
		}
		return c;

	}

	/**
	 * Closes a connection to the database
	 */
	public void closeConnection() {
		if (c != null) {
			try {
				c.close();
			} catch (SQLException e) {
				System.exit(1);
			}
		}
	}


}
