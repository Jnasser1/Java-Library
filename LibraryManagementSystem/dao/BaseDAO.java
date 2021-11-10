package lms.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/*
 * DAO is the the layer that communicates with the database, contains functionality for db. Does not give connection.
 * Isolating all database interaction within DAO and hiding queries from outside viewer.
 * SQLException for database-access error occurs, ClassNotFoundException, .forName(Driver)
 * 
 * Abstract class works well here as we want non abstract methods that each non-abstract subclass will use.
 * The subclasses will give their own implementation for the abstract methods which extract data as a list or single object.
 * 
 *  For generic type T, generic entity.
*/

public abstract class BaseDAO<T> {
	protected Connection con = null;

	/*
	 * As DAO is not creating connection, the constructors specify that they will
	 * use the same connection
	 */

	// Create the connection in service, and send it to the base dao.
	
	public BaseDAO(Connection con) {
		this.con = con;
	}

	protected Integer savePK(String sql, Object[] values) throws SQLException, ClassNotFoundException {
		// SQL string is precompiled and stored in pstmt.
		PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		if (values != null) {
			int j = 1; // index
			for (Object o : values) {
				// Given object is converted to SQL type and sent to database.
				pstmt.setObject(j, o);
				j++;
			}
		}
		// Execute the sql insert, update, or delete statement.
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.next()) {
			// Mapping keys back to java objects.
			return rs.getInt(1);
		}
		return null;
	}

	// i.e sql = "insert into table values (?,?)". ->
	// -> "insert into table values(values[0],values[1])"

	protected void save(String sql, Object[] values) throws SQLException, ClassNotFoundException {

		PreparedStatement pstmt = con.prepareStatement(sql);
		if (values != null) {
			int j = 1;
			for (Object o : values) {
				pstmt.setObject(j, o);
				j++;
			}
		}
		pstmt.executeUpdate();
	}

	protected List<T> read(String sql, Object[] values) throws SQLException, ClassNotFoundException {
		PreparedStatement pstmt = con.prepareStatement(sql);
		if (values != null) {
			int j = 1;
			for (Object o : values) {
				pstmt.setObject(j, o);
				j++;
			}
		}

		return extractData(pstmt.executeQuery());

	}

	protected T readSingle(String sql, Object[] values) throws SQLException, ClassNotFoundException {
		PreparedStatement pstmt = con.prepareStatement(sql);
		if (values != null) {
			int j = 1;
			for (Object o : values) {
				pstmt.setObject(j, o);
				j++;
			}
		}
		return extractSingleData(pstmt.executeQuery());
	}

	abstract List<T> extractData(ResultSet rs) throws SQLException, ClassNotFoundException;

	abstract T extractSingleData(ResultSet rs) throws SQLException, ClassNotFoundException;

}
