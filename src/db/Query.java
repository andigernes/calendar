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

	public static boolean authenticate(String username, String password) throws SQLException{				//Sjekker om en bruker som logger inn faktisk eksisterer i databasen. 
		String query = "SELECT * FROM User WHERE Username ='"+username+"' AND Password = '"+password+"'";	//Sp�rring mot databasen som henter brukernavn og passord fra databasen.
		ResultSet rs;																						//Oppretter en variabel av type ResultSet.
		rs = DBConnection.getInstance().query(query);														//Sender sp�rringen til databasen via en oppkobling og lagrer den i variabelen rs.
		if(rs.next()){																						//Sjekker om en bruker eksisterer i databasen.
			return true;																					//Om det er tilfellet at brukeren eksisterer, s� returner true.
		}
		return false;																						//Om det ikke er tilfellet returner false.


	}
	
	public ResultSet getDataFromEventTable(String username) throws SQLException		//Henter data fra Event tabellen i databasen.
	{
		DBConnection connection = DBConnection.getInstance();						//Gj�r en oppkobling til databasen.		
		String query = "SELECT * FROM Event WHERE username = '" + username + "'";	//Sp�rringen mot databasen som henter Eventer basert p� et brukernavn.
		return connection.query(query);												//Returnerer resultatet fra sp�rringen.
	}

	public static void main(String[] args) {

	}

}
