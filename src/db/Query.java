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
	 * 
	 * @param username
	 * @return true id user exists, false otherwise
	 */

	public boolean userExists(String username){
		String check = "SELECT * FROM User WHERE Username ='"+username+"'";
		ResultSet rs;
//		try{
//			rs = DBConnection.setValues(check, null);
//			if (rs.next()) {
//				return true;
//			}
//		}catch (SQLException e){
//
//		}

		return false;
	}

}
