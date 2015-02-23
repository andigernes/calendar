package db;

import java.sql.*;
import java.util.*;

public class DBConnection {
	private final String hostname = "mysql.stud.ntnu.no";
	private final String username = "magneskj_felles";
	private final String password = "gruppe9";
	private final String database = "magneskj_felles";

	private final String CONNECTION = "jdbc:mysql://" + hostname + "/" + database;
	private final String dbClassName = "com.mysql.jdbc.Driver";

	private final Connection connection;
	private final static DBConnection instance = new DBConnection();

	/**
	 * Open up a database connection with specified host etc.
	 * This constructor is private to ensure that no new database connections are opened
	 */
	private DBConnection() {
		try {
			Class.forName(dbClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Properties p = new Properties();
		p.put("user", username);
		p.put("password", password);
		Connection c;
		try {
			c = DriverManager.getConnection(CONNECTION, p);
		} catch (SQLException e) {
			c = null;
			e.printStackTrace();
		}
		connection = c;
	}

	/**
	 * Create a singleton of DBConnection
	 *
	 * This will be used to get to current open database connection
	 * @return instance of database connection
	 */
	public static DBConnection getInstance() {
		return instance;
	}

	/**
	 * Close connection
	 */
	public void close(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Simple SQL query
	 * @param sql SQL query
	 * @return results
	 * @throws SQLException SQL exception
	 */
	public ResultSet query(String sql) throws SQLException {
		return connection.createStatement().executeQuery(sql);
	}

	/**
	 * Simple SQL update. Use prepareUpdate instead to avoid SQL injections.
	 * @param sql SQL query
	 * @throws SQLException SQL exception
	 */
	public void update(String sql) throws SQLException {
		connection.createStatement().executeUpdate(sql);
	}

	/**
	 * Create a PreparedStatement with specified SQL
	 * @param sql SQL query
	 * @return prepared statement
	 * @throws SQLException SQL exception
	 */
	public PreparedStatement prepare(String sql) throws SQLException {
		return connection.prepareStatement(sql);
	}

	/**
	 * Run a prepared statement based SQL query based on a collection of objects
	 * @param sql SQL query
	 * @param objects objects to use in the prepared statement
	 * @return results
	 * @throws SQLException SQL exception
	 */
	public ResultSet prepareQuery(String sql, Collection<String> objects) throws SQLException {
		PreparedStatement preparedStatement = this.prepare(sql);
		DBConnection.setValues(preparedStatement, objects);
		return preparedStatement.executeQuery();
	}

	/**
	 * Run a prepared statement based SQL query based on object parameters
	 * @param sql SQL query
	 * @param objects objects to use in the prepared statement
	 * @return results
	 * @throws SQLException SQL exception
	 */
	public ResultSet prepareQuery(String sql, String... objects) throws SQLException {
		return this.prepareQuery(sql, Arrays.asList(objects));
	}

	/**
	 * Do an SQL update with a prepared statement using a collection of objects
	 * @param sql SQL query
	 * @param objects object values to update
	 * @throws SQLException SQL exception
	 */
	public void prepareUpdate(String sql, Collection<String> objects) throws SQLException {
		PreparedStatement preparedStatement = this.prepare(sql);
		setValues(preparedStatement, objects);
		preparedStatement.executeUpdate();
	}

	/**
	 * Do an SQL update with a prepared statement using a object parameters
	 * @param sql SQL query
	 * @param objects object values to update
	 * @throws SQLException SQL exception
	 */
	public void prepareUpdate(String sql, String... objects) throws SQLException {
		prepareUpdate(sql, new ArrayList<>(Arrays.asList(objects)));
	}

	/**
	 * PreparedStatement helper method to set multiple values at once
	 * @param preparedStatement preparedstatement to set values for
	 * @param objects values
	 * @throws SQLException SQL exception
	 */
	public static void setValues(PreparedStatement preparedStatement, Collection<String> objects) throws SQLException {
		int i = 1;
		for(Object object: objects) {
			preparedStatement.setObject(i, object);
			i++;
		}
	}
}