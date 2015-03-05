package db;

import java.sql.SQLException;

import calendar.Appointment;

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
						ap.getLocation(), "Maren");
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
		// TODO Auto-generated method stub
		return false;
	}

}
