package calendar;

import gui.CalendarController;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class UserCalendar implements Iterable<Appointment> {							//Klassen h�ndterer en brukers avtale liste.
	private ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();		//En liste som inneholder en brukers avtaler.

	public boolean add(Appointment ap) {												//Metoden legger til en avtale i listen.
		if (db.UpdateQuery.saveInDb(ap))												//Hvis avtalen blir lagt til i databasen...
			return appointmentList.add(ap);												//s� skal man legge til avtalen i listen og returnere den.
		return false;																	//hvis ikke, s� returnerer vi false.
	}

	public boolean remove(Object ap) {													//Metoden fjerner en avtale fra listen.
		if (db.UpdateQuery.deleteInDb((Appointment) ap))								//Hvis avtalen blir fjernet fra databasen...
			return appointmentList.remove(ap);											//s� fjerner vi avtalen fra listen og returnerer den.
		return false;																	//hvis ikke kunne den ikke fjernes, og vi returnerer false.
	}

	public void getCalendar(User user, CalendarController controller){					//Metoden henter en kalender basert p� parameterene bruker og en kalender kontroller.
		try {																			//Fordi man kan f� en SQLException, s� m� man pr�ve om koden ikke feiler...
			appointmentList = db.Query.getDataFromEventTable(user.getUserName());		//Henter ut alle avtalene til en bruker ved � kalle p� getDataFromEventTable() som er en metode i Query-klassen.
			gui.EventRendering.getAppointments(this, controller);						//Koden tegner hele kalenderen p� nytt, med alle avtalene til en bruker.
		} catch (SQLException | ParseException e) {										//Om koden feiler m� vi fange feilen og kaste et unntak. 
			System.out.println(e);														//Printer ut feilen som oppstod.
		}
	}

	@Override
	public Iterator<Appointment> iterator() {											//Metoden itererer over avtale-objekter.
		Collections.sort(appointmentList);												//Sorterer alle objektene i appointmentList.
		return appointmentList.iterator();												//Returnerer appointmentList som sortert.
	}

	public void clear() {
		appointmentList.clear();
	}
}
