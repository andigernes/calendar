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
	//	Collection<String> values = new ArrayList<String>();

		String query = "INSERT INTO Event (Start, End, Name, Description, Location, Number Of Attendants, Username, Groupname) VALUES "+ "('"+ap.getStartTime()+"','"+ ap.getEndTime()+"','"+ap.getName()+"','"+ap.getDescription()+"','"+ap.getLocation()+"'1, Maren, null";
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
	
}
