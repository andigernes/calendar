package gui;

import calendar.Appointment;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MainCalendar extends Application {															//Klassen starter kalender applikasjonen 

	private static Stage primaryStage;

	// For å hente avtaler fra database
	private static ObservableList<Appointment> appointmentData = FXCollections.observableArrayList();	//En liste med avtale-objekter som lytter på endringer.

	public MainCalendar() {

	}

	public static ObservableList<Appointment> getAppointmentData() {									//Metoden henter ut avtaledata fra listen.
		return appointmentData;																			//Returnerer avtaledata fra listen. 
	}

	@Override
	public void start(Stage primaryStage) {																//Metoden oppretter en scene som er utgangspunktet for applikasjonen.
		try {																							//Må prøve om man kan laste inn filen...
			Pane root = FXMLLoader.load(CalendarController.class.getResource("Calendar.fxml"));			//Oppretter en pane som laster inn fxmlfilen Calendar.
			Scene scene = new Scene(root, 1200, 741);													//Lager en ny scene på størrelse 1200x741
			primaryStage.setScene(scene);																//Spesifiserer hvilken scene som skal brukes som hovedscene.
			primaryStage.show();																		//Viser frem scenen på skjermen.
		} catch (Exception e) {																			//Hvis koden feiler på noe vis må vi fange og kaste et unntak.
			e.printStackTrace();																		//Printer ut hvor feilen oppstod og hva den var.
		}
	}

	public static void main(String[] args) {															//Starter hele applikasjonen.
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

}