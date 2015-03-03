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
	
	public static boolean authenticate(String username, String password){
		String query = "SELECT * FROM User WHERE Username ='"+username+"' AND Password = '"+password+"'";
		ResultSet rs;
		try{
			rs = DBConnection.getInstance().query(query);
			System.out.println(rs);
			if(rs.next()){
				return true;
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	
	
	public static void main(String[] args) {
		
	}

}
