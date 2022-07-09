package gameCrossing.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import gameCrossing.Config;
/**
 * Use ConnectionManager to connect to your database instance.
 */
public class ConnectionManager {

	// User to connect to your database instance. By default, this is "root2".
	private final String user = Config.getdbUser();
	// Password for the user.
	private final String password = Config.getdbPassword();
	// URI to your database server. If running on the same machine, then this is "localhost".
	private final String hostName = Config.getHost();
	// Port to your database server
	private final int port= Config.getPort();
	// Name of the MySQL schema that contains your tables.
	private final String schema = Config.getSchema();

	/** Get the connection to the database instance. */
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			Properties connectionProperties = new Properties();
			connectionProperties.put("user", this.user);
			connectionProperties.put("password", this.password);
			connectionProperties.put("serverTimezone", "UTC");
			// Ensure the JDBC driver is loaded by retrieving the runtime Class descriptor.
			// Otherwise, Tomcat may have issues loading libraries in the proper order.
			// One alternative is calling this in the HttpServlet init() override.
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				throw new SQLException(e);
			}
			connection = DriverManager.getConnection(
			    "jdbc:mysql://" + this.hostName + ":" + this.port + "/" + this.schema,
			    connectionProperties);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return connection;
	}

	/** Close the connection to the database instance. */
	public void closeConnection(Connection connection) throws SQLException {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
}
