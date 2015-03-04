package db;

import java.sql.*;

/**
 * This class only contains static methods for getting information from the
 * database
 *
 * @author Gruppe9
 *
 */
public class Query {

	/**
	 * Checks whether the username and password matches the user DB
	 */

	public static boolean authenticate(String username, String password) throws SQLException {
		String query = "SELECT * FROM User WHERE Username ='" + username + "' AND Password = '" + password + "'";
		ResultSet rs;
		rs = DBConnection.getInstance().query(query);
		return rs.next(); // Returns whether a non-empty result was returned

	}

	/**
	 * Retrieves all events owned by a specific user
	 */
	public ResultSet getDataFromEventTable(String username) throws SQLException {
		DBConnection connection = DBConnection.getInstance();
		String query = "SELECT * FROM Event WHERE username = '" + username + "'";
		ResultSet resultSet = connection.query(query);
		return resultSet;
	}

	public static void main(String[] args) {
		Query q = new Query();
		try {
			q.getDataFromEventTable("Andreas");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
