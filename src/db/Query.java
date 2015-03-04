package db;

import java.sql.*;

/**
 * This class only contains static methods for getting information from the database
 *
 * @author Gruppe9
 *
 */
public class Query {


	/**
	 * Checks username and password for login
	 */

	public static boolean authenticate(String username, String password) throws SQLException{
		String query = "SELECT * FROM User WHERE Username ='"+username+"' AND Password = '"+password+"'";
		ResultSet rs;
		rs = DBConnection.getInstance().query(query);
		if(rs.next()){
			return true;
		}
		return false;


	}
	
	public ResultSet getDataFromEventTable(String username) throws SQLException
	{
		DBConnection connection = DBConnection.getInstance();
		String query = "SELECT * FROM Event WHERE username = '" + username + "'";
		return connection.query(query);
	}

	public static void main(String[] args) {

	}

}
