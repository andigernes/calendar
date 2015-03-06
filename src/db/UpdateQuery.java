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

	public static boolean saveInDb(Appointment ap) {														//Metoden lagrer nye eventer i databasen.
		String query = String
				.format("INSERT INTO Event(Start, End, Name, Description, Location, Number_of_attendants, Username, Groupname) VALUES ('%s','%s','%s','%s','%s',1, '%s', null)",
						ap.getStartDateTime(), ap.getEndDateTime(), ap.getName(), ap.getDescription(),
						ap.getLocation(), calendar.User.getUserName());										//Oppretter en spørring som ber databasen om å lagre en Event med forskjellig data i tabeller og gir dem verdier. 
		int check;																							//En variabel check som skal fortelle om innsettingen var vellyket.
		try {
			check = db.DBConnection.getInstance().update(query);											//Oppretter en oppkobling til databasen og oppdaterer den med en ny rad i tabellen Event ved bruk av spørringen over.
			// Check = number of columns affected by query
			return check > 0;																				//Sjekker hvor mange tabeller spørringen har oppdatert/endret.
		} catch (SQLException e) {
			return false;
		}

	}

	public static boolean deleteInDb(Appointment ap) {																//Metoden sletter en avtale fra databasen.
		String deletequery = "DELETE FROM Event WHERE '"+ap.getSerialNumber().intValue()+"' = Serial_Number";		//En spørring som sletter en event fra databasen basert på serienummeret.
		int check;																									//En variabel som forteller om slettingen ble utført.
		try {
			check = db.DBConnection.getInstance().update(deletequery);												//Oppretter en oppkobling til databasen som ber den slette eventen det blir bedt om.
			return check > 0;																						//Returnerer om noe ble slettet eller ikke.
		} catch (SQLException e) {
			return false;
		}

	}

}
