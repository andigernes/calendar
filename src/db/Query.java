package db;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import calendar.Appointment;
import calendar.Room;

/**
 * This class only contains static methods for getting information from the
 * database
 *
 * @author Gruppe9
 *
 */
public class Query {

	/**
	 * Checks whether the username and password matches the user DB
	 */

	public static boolean authenticate(String username, String password) throws SQLException {						//Metoden sjekker om brukeren eksisterer i databasen og er boolean fordi den skal svare true om brukeren eksisterer eller false hvis ikke.
		String query = "SELECT * FROM User WHERE Username ='" + username + "' AND Password = '" + password + "'";	//Lager en sp�rring som ber om brukernavn og passord fra tabellen User i databasen.
		ResultSet rs = DBConnection.getInstance().query(query);														//Henter en oppkobling til databasen og sender sp�rringen til den.
		return rs.next();																							//Returnerer resultatet fra sp�rringen og g�r igjennom den.
	}

	/**
	 * Retrieves all events owned by a specific user
	 */
	public static ArrayList<Appointment> getDataFromEventTable(String username) throws SQLException {	//Metoden foretar en sp�rring mot databasen for � hente ut alle avtaler til en bruker og lagrer dem som avtaler i en liste. 
		DBConnection connection = DBConnection.getInstance();											//Oppretter en oppkobling til databasen.
		String query = "SELECT * FROM Event WHERE username = '" + username + "'";						//Lager en sp�rring som skal hente ut alle avtaler til en bruker ut i fra brukernavnet. 
		ResultSet resultSet = connection.query(query);													//Resultatet av sp�rringen er et ResultSet som m� utf�res metoder p� for � f� formatet og informasjonen vi vil ha. Bruker oppkoblingen fra andre kodelinje til � sende inn sp�rringen.
		ArrayList<Appointment> cal = new ArrayList<Appointment>();										//M� opprette en ArrayList som lagrer avtale-objekter i seg og som vi skal returnere fra denne metoden. 	
		while(resultSet.next()){																		//Benytter en while-l�kke for � l�pe igjennom alle radene som vi har f�tt fra kodelinje 4, slik at vi kan utf�re metodene vi trenger for � f� informasjonen vi vil ha tak i kun en gang.
			Appointment a = new Appointment();															//Starter s� � opprette nytt avtale-objekt som vi vil lagre informasjonen i, denne skal inn i listen v�r fra kodelinje 3. 
			//Add serial number
			int sn = resultSet.getInt(1);																//Henter ut serienummeret til en avtale fra resultatet vi fikk fra sp�rringen.
			a.setSerialNumber(sn);																		//Setter s� feltet SerialNumber i avtale-objektet vi opprettet i kodelinje 7 til verdien vi fikk fra kodelinje 6.
			//Add start date, time and end time															//Vi fortsetter � plotte inn data fra databasen og inn i avatale-objektet v�rt
			String[] dateTime = resultSet.getString(2).split(" ");										//Vi m� ha dato og tid til avtale-objektet v�rt, p� grunn av formatet DATETIME (2015-03-20 16:30:00) m� vi bruke getString() for � gj�re DATETIME om til en String. Vi splitter s� strengen p� mellomrom (" ") for � skille dato fra tid.
			String sD = dateTime[0];																	//Henter ut startdato fra kodelinjen over. 
			String sT = dateTime[1];																	//Henter ut starttid for en avtale fra kodelinjen to kodelinjer over. 
			dateTime =  resultSet.getString(3).split(" ");												//Samme begrunnelse som 3 kodelinjer over bare at n� henter vi slutdato og slutttid.
			String eT = dateTime[1];																	//Henter ut slutttid fra listen i kodelinjen ett hakk over.
			LocalDate startDate = LocalDate.parse(sD);													//Gj�r om startdatoen som er en streng til formatet LocalDate (2007-03-06).
			LocalTime startTime = LocalTime.parse(sT);													//Gj�r om starttiden fra en streng til formatet LocalTime (10:15)
			LocalTime endTime = LocalTime.parse(eT);													//Samme omformatering som for startdatoen.	
			a.setStartDate(startDate);																	//Ettersom startdatoen i avtale-objektet er LocalDate, s� har vi f�tt det formatet 3 kodelinjer over. Setter s� feltet startDate til verdien fra 3 kodelinjer over.  
			a.setStartTime(startTime);																	//Samme forklaring som over, bare at startTime er p� format LocalTime. 
			a.setEndTime(endTime);																		//Samme forklaring som rett over.
			//Add name
			String name = resultSet.getString(4);														//Lagrer navnet p� avatalen fra sp�rringen.
			a.setName(name);																			//Setter s� feltet name i avtale-objektet til � v�re verdien i variabelen over. 
			//Add description
			String desc = resultSet.getString(5);														//Henter ut beskrivelsen fra resultatet fra sp�rringen.
			a.setDescription(desc);																		//Setter feltet description i avtale-objektet til � v�re verdien i variabelen over.
			//Add location
			String location = resultSet.getString(6);													//Henter ut lokasjonen avtalen befinner seg p�.
			a.setLocation(location);																	//Setter feltet location i avtale-objektet.
			cal.add(a);																					//Legger til avtale-objektet i listen som vi lagde.
		}

		return cal;																						//Returnerer listen med alle avtalene til en bruker i.
	}

	public static ArrayList<Room> retrieveRoomsFromDB() throws SQLException {		//Metoden foretar en sp�rring mot databasen for � hente ut alle rom som er tilgjengelig og lagrer dem som rom i en liste. 
		DBConnection connection = DBConnection.getInstance();						//Oppretter en oppkobling til databasen.
		String query = "SELECT * FROM Room";										//Lager en sp�rring som skal hente ut alle rom som er tilgjengelig.
		ResultSet resultSet = connection.query(query);								//Resultatet av sp�rringen er et ResultSet som m� utf�res metoder p� for � f� formatet og informasjonen vi vil ha. Bruker oppkoblingen fra andre kodelinje til � sende inn sp�rringen.
		ArrayList<Room> rooms = new ArrayList<>();									//M� opprette en ArrayList som lagrer avtale-objekter i seg og som vi skal returnere fra denne metoden.
		while(resultSet.next()) {													//Benytter en while-l�kke for � l�pe igjennom alle radene som vi har f�tt fra kodelinje 4, slik at vi kan utf�re metodene vi trenger for � f� informasjonen vi vil ha tak i kun en gang.
			String nameRoom = resultSet.getString("Name_Room");						//Henter ut navnet p� rommet fra resultatet ved hjelp av navnet p� tabellen iformasjonen ligger i.
			int capacity = resultSet.getInt("capacity");							//Henter ut kapasiteten til et rom p� samme m�te som over. 
			Room room = new Room(nameRoom, capacity);								//Oppretter et nytt rom-objekt med et navn og en kapasitet som er fra rommene hentet fra databasen.
			rooms.add(room);														//Legger til rommet som er laget, inn i rom-tabellen. 
		}
		
		return rooms;																//Returnerer rom-tabellen med alle rommene som eksisterer.
	}

}
