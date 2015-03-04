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
	
	public static boolean saveInDb(Appointment ap){																			//Sp�rring mot databasen som setter inn en tabell med data i Event tabellen.																	
		String query = String.format("INSERT INTO Event(Start, End, Name, Description, Location, Number_of_attendants, Username, Groupname) VALUES ('%s','%s','%s','%s','%s',1, '%s', null)", 
				ap.getStartDateTime(), ap.getEndDateTime(), ap.getName(), ap.getDescription(), ap.getLocation(), "Maren");	//Sp�rring mot databasen som setter inn en tabell med data i Event tabellen.
		int check;																											//Oppretter en variabel check.
		try{																												//Fordi vi kan f� en exception s� m� vi pr�ve om koden vil gi en exception.
			check = db.DBConnection.getInstance().update(query);															//Setter check til � v�re en ny rad i tabellen i databasen og utf�rer innsettingen ved en oppkobling.  
			return check > 0;																								//Sjekker om variabelen check er st�rre enn 0, dvs. at informasjon er satt.	
																															//Er det tilfellet s� returneres true.
		}catch(Exception e){																								//Om koden f�r feil, s� fanger vi den for � h�ndtere den.
			return false;																									//S� returnerer vi true 
		}

	}

	public static boolean deleteInDb(Appointment ap) {
		// TODO Auto-generated method stub
		return false;
	}

}
