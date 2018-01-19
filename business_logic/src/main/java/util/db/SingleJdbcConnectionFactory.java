package util.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This will get the H2 DB connection on a thread local.
 * 
 * @author Tony Boarman
 * @date Jan 6, 2018
 */
public class SingleJdbcConnectionFactory implements ConnectionFactory {

	private final ThreadLocal<Connection> connection = new ThreadLocal<Connection>();
	
	@Override
	public Connection getConnection() throws SQLException {
		
		//Assign a Connection to the Thread, if not done so already
		if (connection.get() == null) {
			
			try {
				Class.forName("org.h2.Driver");
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
			
			Connection threadConnection = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;AUTO_SERVER=TRUE", "", "");
			
			connection.set( threadConnection );
		}
		
		return connection.get();
	}

	/**
	 * Release the connection from the associated Thread.  If the connection is opened,
	 * it is closed.
	 */
	@Override
	public void releaseConnection() throws SQLException {
		
		Connection threadConnection = connection.get();
		
		try {
			if (threadConnection != null && !(threadConnection.isClosed()) ) {
	
				try {
					//If not in auto-commit mode, commit transactions
					if ( !(threadConnection.getAutoCommit())) {
						threadConnection.commit();
					}
				} finally {
					threadConnection.close();
				}
			}
		} finally {
			// Always remove the connection from the ThreadLocal, even if an error occurs above.
			// Clear connection from ThreadLocal Map (does no harm if called & has no assigned threadConnection)
			connection.remove();
		}
	}

}
