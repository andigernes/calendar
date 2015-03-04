package gui;

import calendar.Appointment;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class MainCalendar extends Application {

	private static Stage primaryStage;

	// For å hente avtaler fra database
	private static ObservableList<Appointment> appointmentData = FXCollections.observableArrayList();

	public MainCalendar() {

	}

	public static ObservableList<Appointment> getAppointmentData() {
		return appointmentData;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = FXMLLoader.load(CalendarController.class.getResource("Calendar.fxml"));
			Scene scene = new Scene(root, 1200, 741);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

}