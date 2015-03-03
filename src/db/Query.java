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
		try{
			rs = DBConnection.getInstance().query(check);
			System.out.println(rs);
			if(rs.next()){
				return true;
				
			}
			
			
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}

		return false;
	}
	/**
	 * Checks username and password for login
	 */
	
	public boolean authenticate(String username, String password){
		String query = "SELECT * FROM User WHERE Username ='"+username+"' AND Password = '"+password+"'";
		ResultSet rs;
		try{
			rs = DBConnection.getInstance().query(query);
			System.out.println(rs);
			if(rs.next()){
				return true;
			}
		}catch (SQLException e){
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	
	public static void main(String[] args) {
		Query q = new Query();
		System.out.println(q.authenticate("Magne", "magne"));
		System.out.println(q.authenticate("Magne", "lol"));
		System.out.println(q.authenticate("Magne", "Maren"));
	}

}
