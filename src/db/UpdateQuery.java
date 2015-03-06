package db;

import java.sql.SQLException;

import calendar.Appointment;
import calendar.User;

/**
 * This class is for updating the database
 *
 * @author Gruppe9
 *
 */
public class UpdateQuery {
	/**
	 * Saves a specific event to the database's event table
	 */

	public static boolean saveInDb(Appointment ap) {
		String query = String
				.format("INSERT INTO Event(Start, End, Name, Description, Location, Number_of_attendants, Username, Groupname) VALUES ('%s','%s','%s','%s','%s',1, '%s', null)",
						ap.getStartDateTime(), ap.getEndDateTime(), ap.getName(), ap.getDescription(),
						ap.getLocation(), calendar.User.getUserName());
		int check;
		try {
			check = db.DBConnection.getInstance().update(query);
			// Check = number of columns affected by query
			return check > 0;
		} catch (SQLException e) {
			return false;
		}

	}

	public static boolean deleteInDb(Appointment ap) {
		String deletequery = "DELETE FROM Event WHERE '"+ap.getSerialNumber().intValue()+"' = Serial_Number";
		System.out.println(deletequery);
		int check;
		try {
			System.out.println("her?");
			check = db.DBConnection.getInstance().update(deletequery);
			System.out.println("Chseck " + check);
			return check > 0;
		} catch (SQLException e) {
			return false;
		}

	}

}
