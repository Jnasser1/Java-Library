package lms.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Service - Handles specifying the DAO to use, specifies and gives connection to use, 
 *         validation, business logic, business rules, exception handling, improves security, 
 *                       
 * Utility class to get connection and close it. Every transaction should use one connection 
 * object, where the connection is closed after the transaction is finished.
*/

public class Utility {
	public final String driver = "com.mysql.cj.jdbc.Driver";
	public final String url = "jdbc:mysql://localhost/library";
	public final String username = "root";
	public final String password = "root";

	private Connection conn;
	/*
	 * By default all connections commit, we want commits saved into memory, so if
	 * there is an exception, we can rollback and erase from memory, not commit to db.  
	 * setAutoCommit(false) allows us to not create a transaction until we call conn.commit(). 
	 */

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		 conn = null;
		Class.forName(driver);
		try {
			conn = (Connection) DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(false);
		} catch (SQLException sqle) {
			System.out.println("Could not connect to database.");
			sqle.printStackTrace();
		}
		return conn;
	}
	

	  public int executeQuery(String query) throws ClassNotFoundException, SQLException {
	    return conn.createStatement().executeUpdate(query);
	  }
	

}