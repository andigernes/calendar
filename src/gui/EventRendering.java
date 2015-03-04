package gui;

import java.awt.Color;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.WeekFields;
import java.util.Locale;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import calendar.Appointment;
import calendar.Calendar;

public class EventRendering {
	
	public EventRendering(String username){
		
	}
	
	public void getAppointmens(Calendar calendar){
		LocalDate date;
		LocalTime startTime;
		LocalTime endTime;
		for (Appointment appointment : calendar) {
			date = appointment.getStartDate();
			startTime = appointment.getStartTime();
			endTime = appointment.getEndTime();
		}
	}
	
	public static void representAppointment(LocalDate date, LocalTime startTime, LocalTime endTime, GridPane eventArea){
		//WeekFields weekFields = WeekFields.of(Locale.getDefault()); 
		//int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
		
		Pane pane = new Pane();
		pane.setStyle("-fx-background-color:#fff");
		
		eventArea.add(pane, 1, 20);
		GridPane.setRowSpan(pane, 3);

		
		
		
		
		
	}

}
