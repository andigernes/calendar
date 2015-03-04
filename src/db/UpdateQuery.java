package db;

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
	
	public static boolean saveInDb(Appointment ap){																			//Spørring mot databasen som setter inn en tabell med data i Event tabellen.																	
		String query = String.format("INSERT INTO Event(Start, End, Name, Description, Location, Number_of_attendants, Username, Groupname) VALUES ('%s','%s','%s','%s','%s',1, '%s', null)", 
				ap.getStartDateTime(), ap.getEndDateTime(), ap.getName(), ap.getDescription(), ap.getLocation(), "Maren");	//Spørring mot databasen som setter inn en tabell med data i Event tabellen.
		int check;																											//Oppretter en variabel check.
		try{																												//Fordi vi kan få en exception så må vi prøve om koden vil gi en exception.
			check = db.DBConnection.getInstance().update(query);															//Setter check til å være en ny rad i tabellen i databasen og utfører innsettingen ved en oppkobling.  
			return check > 0;																								//Sjekker om variabelen check er større enn 0, dvs. at informasjon er satt.	
																															//Er det tilfellet så returneres true.
		}catch(Exception e){																								//Om koden får feil, så fanger vi den for å håndtere den.
			return false;																									//Så returnerer vi true 
		}

	}

	public static boolean deleteInDb(Appointment ap) {
		// TODO Auto-generated method stub
		return false;
	}

}
