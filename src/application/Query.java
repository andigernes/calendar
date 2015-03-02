package application;

import java.sql.*;

import db.DBConnection;


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
		Statement st;
		ResultSet rs;
		try{
			st = DBConnection).createStatement();
			rs = st.executeQuery(queryCheck);
			if (rs.next()) {
				st.close();
				return true;
			}
		}catch (SQLException e){

		}

		return true;
	}

}
