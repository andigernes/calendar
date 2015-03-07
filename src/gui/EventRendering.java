package gui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import calendar.Appointment;
import calendar.UserCalendar;

public class EventRendering{

	public EventRendering() {

	}

	/**
	 * getAppointments takes in both the appointments and the eventArea in which
	 * the events will be displayed.
	 * 
	 * @param calendar
	 * @param controller
	 * @param eventArea
	 * @throws ParseException
	 */

	public static void getAppointments(UserCalendar calendar, CalendarController controller) throws ParseException {	//Metoden viser frem avtaler i en brukers kalender ved å ta inn en brukers kalender og en controller.
		String date;													//Oppretter en variabel date som tar inn en string.
		int eventWeek;													//Oppretter en variabel eventWeek som tar inn en int.
		String format = "yyyy-MM-dd";									//Oppretter en variabel format som skal formatere datoen og er av typen string.
		for (Appointment appointment : calendar) {						//Løper igjennom alle avtaler i en kalender for å kunne plassere avtalene i kalenderen hvor de hører hjemme.
			date = appointment.getStartDate().toString();				//Setter dato variabelen til å være startdatoen for avtalen.
			SimpleDateFormat df = new SimpleDateFormat(format);			//Oppretter et nytt SimpleDateFormat-objekt som skal formatere datoen til formatet som er spesifisert i format-variabelen.
			Date eventDate = df.parse(date);							//Omformer en dato på streng-format til å bli et dato-objekt.
			Calendar cal = Calendar.getInstance();						//Henter et kalender-objekt.
			cal.setTime(eventDate);										//Setter kalender-objektets dato til å være startdatoen for når en avtale skal starte. 
			eventWeek = cal.get(Calendar.WEEK_OF_YEAR);					//Henter ut et tall for uken vi er i og setter variabelen eventWeek til denne verdien.
			if (controller.week == eventWeek) {							//Sjekker hvis uken controlleren har nå er lik uken kalenderen har...
				// sets column index
				int day = cal.get(Calendar.DAY_OF_WEEK);				//da henter vi et tall for dagen i uken.
				if (day == 1) {											//Hvis dagen er lik 1, dvs en sødag...
					day = 7;											//så skal søndagen settes til å være den syvende dagen i uken.
				} else {												//Hvis det er en annen dag enn søndag... 
					day--;												//så må verdien til dagen minkes med 1. Dette er fordi mandag har verdi 2, tirsdag 3 osv. For at Mandag skal være første dag i uken og ikke Søndag så må verdiene trekkes fra med 1.
				}
				representAppointment(appointment, controller, day);		//Kaller metoden som tegner opp avtalen i kalenderen.
			}
		}
	}

	/**
	 * 
	 * representAppointment() converts start and end time to integers in order
	 * to calculate the position of event panes.
	 * 
	 * @param date
	 * @param startTime
	 * @param endTime
	 * @param eventArea
	 * 
	 */

	public static void representAppointment(Appointment appointment, CalendarController controller, int day) {	//Metoden tegner opp en avtale i kalenderen ved å ta inn en avtale og en controller.
		String name = appointment.getName();								//Henter ut avtalenavnet fra et avtale-objekt.
		String startTime = appointment.getStartTime().toString();			//Henter ut starttiden på avtalen i streng-format.												
		String endTime = "" + appointment.getEndTime().toString();			//Henter ut sluttiden på avtalen i streng-format.
		int starthalftime = 0;												//Oppretter en variabel av typen int som settes til 0. 															

		// Sets grid row for startTime
		String[] startTimeArray = startTime.split(":");						//Oppretter en tabell med timer, minutter og sekunder i hver sin rad ved å splitte variabelen startTime på ":". Har vi tiden 17:45:13, får vi listen [17, 45, 13]. 
		int startTimeStartRow = Integer.parseInt(startTimeArray[0]);		//Konverterer timen som er første element i listen over til en type int. 
		int startTimeEndRow = Integer.parseInt(startTimeArray[1]);			//Konverterer minuttene som er andre element i listen til en type int.
		if (startTimeEndRow > 0 && startTimeEndRow < 30) {					//Sjekker om minuttene er mellom 0 og 30...
			starthalftime = 1;												//da sier vi at møtet varer i en halvtime, derfor settes startHalfTime til å være 1.
		}
		if (startTimeEndRow > 30) {											//Sjekker om minuttene er større enn 30...
			startTimeStartRow++;											//da sier vi at møtet like gjerne varer 1 time.
		}
		int startRow = startTimeStartRow * 2 + starthalftime;				//?
		int endhalftime = 0;												//Setter endhalftime tilbake til 0 fordi

		// Sets grid row for endTime
		String[] endTimeArray = endTime.split(":");							//Oppretter en liste med sluttidene for arrangementet ved å splitte på ":"
		int endTimeStartRow = Integer.parseInt(endTimeArray[0]);			//Konverterer timen som er første element i listen over til en type int. 
		int endTimeEndRow = Integer.parseInt(endTimeArray[1]);				//Konverterer minuttene som er andre element i listen til en type int.
		if (endTimeEndRow > 0 && endTimeEndRow < 30) {						//Sjekker om minuttene er mellom 0 og 30...
			endhalftime = 1;												//da sier vi at møtet varer i en halvtime, derfor settes startHalfTime til å være 1.
		}
		if (endTimeEndRow > 30) {											//Sjekker om minuttene er større enn 30...
			endTimeStartRow++;												//da sier vi at møtet like gjerne varer 1 time.
		}
		int endRow = endTimeStartRow * 2 + endhalftime;						//?
		int colspan = endRow - startRow;									//En variabel av typen int som skal fortelle kalenderen hvor avtalen spenner seg over.

		// Sets a pane on the grid
		BorderPane pane = (BorderPane) appointment.getNode();				//Lagrer et border pane av en avtalenode.
		if (pane == null) {													//Hvis pane er lik null...
			pane = new BorderPane();										//så oppretter vi et nytt BorderPane-objekt
			appointment.setNode(pane);										//så setter vi en avtalenode til å være bli representert ved BorderPanet som er opprettet. 
		}
		controller.eventArea.add(pane, day, startRow);						//Kaller på kontrolleren og får den til å legge oppå en visning av avtalen i rutenettet på kalenderen.
		GridPane.setRowSpan(pane, colspan);									//Gjør at avtaler kan gå over flere rader, tar inn en avtale og dets varighet.
		GridPane.setMargin(pane, new Insets(2, 2, 2, 3));					//Skrumper inn avtalen slik at den er innenfor de rammer som er satt for den.
		Label text = new Label(name);										//Lager et nytt Label-objekt som blir satt til å være navnet på avtalen. 
		pane.setTop(text);													//Setter navnet til avtalen oppe i venstre hjørne.
		pane.setStyle("-fx-background-color:#ABE7E2");						//Setter bakgrunnsfargen til avtalen.
		pane.addEventHandler(MouseEvent.MOUSE_CLICKED,						//Legger til en lytter som registrerer museklikk på avtalen.
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {							//Metoden kjøres når noen klikker på avtalen og åpner den.
				controller.openAppointment(appointment);					//Kaller på metoden som åpner en avtale.
			}
		});

	}

}
