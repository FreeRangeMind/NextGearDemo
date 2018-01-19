package util.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionFactory {

	/**
	 * Assign and return a JDBC Connection that is associated with the current running Thread.
	 * 
     * @throws SQLException - if a database access error occurs
	 */
	public Connection getConnection() throws SQLException;

	/**
	 * Release the connection associated with the current running Thread
	 */
	public void releaseConnection() throws SQLException;
}
