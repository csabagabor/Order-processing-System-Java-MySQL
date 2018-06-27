package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A factory for creating Connection objects.
 */
public class ConnectionFactory {

	/** The LOGGER of this class. */
	private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class.getName());

	/** The DRIVER. */
	private static final String DRIVER = "com.mysql.jdbc.Driver";

	/** The  DBURL. */
	private static final String DBURL = "jdbc:mysql://localhost:3306/orderman";

	/** The  USER. */
	private static final String USER = "root";

	/** The  PASSWORD. */
	private static final String PASS = "";// for this general purpose
											// application, it doesn't matter if
											// it is empty...

	/** The single instance. */
	private static ConnectionFactory singleInstance = new ConnectionFactory();

	/**
	 * Instantiates a new connection factory.
	 */
	private ConnectionFactory() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new Connection object.
	 *
	 * @return the connection
	 */
	private Connection createConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DBURL, USER, PASS);
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "An error occured while trying to connect to the database");
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public static Connection getConnection() {
		return singleInstance.createConnection();
	}

	/**
	 * Close the connection.
	 *
	 * @param connection
	 *            the connection to close
	 */
	public static void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the connection");
			}
		}
	}

	/**
	 * Closes the statement.
	 *
	 * @param statement
	 *            the statement to close
	 */
	public static void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the statement");
			}
		}
	}

	/**
	 * Closes the resultSet.
	 *
	 * @param resultSet
	 *            the resultSet to be closed
	 */
	public static void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				LOGGER.log(Level.WARNING, "An error occured while trying to close the ResultSet");
			}
		}
	}
}
