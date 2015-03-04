package db;

import java.sql.*;


import calendar.Appointment;
/**
 * This class is for updating the database
 *
 * @author Gruppe9
 *
 */
public class UpdateQuery {
	/**
	 * save event in the database
	 */

	public static boolean saveInDb(Appointment ap){
		String query = String.format("INSERT INTO Event(Start, End, Name, Description, Location, Number_of_attendants, Username, Groupname) VALUES ('%s','%s','%s','%s','%s',1, '%s', null)", 
				ap.getStartDateTime(), ap.getEndDateTime(), ap.getName(), ap.getDescription(), ap.getLocation(), "Maren");
		int check;
		try{
			check = db.DBConnection.getInstance().update(query);
			if(check > 0){
				return true;
			}
		}catch(Exception e){

		}

		return true;
	}

	public static boolean deleteInDb(Appointment ap) {
		// TODO Auto-generated method stub
		return false;
	}

}
