package gui;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import calendar.Appointment;
import calendar.UserCalendar;

public class EventRendering {
	
	public EventRendering(String username){
		
	}
	
	public void getAppointmens(UserCalendar calendar){
		LocalDate date;
		String startTime;
		String endTime;
		for (Appointment appointment : calendar) {
			date = appointment.getStartDate();
			startTime = ""+appointment.getStartTime();
			endTime = ""+appointment.getEndTime();
		}
	}
	
	/**
	 * 
	 * @param date
	 * @param startTime
	 * @param endTime
	 * @param eventArea
	 */
	
	public static void representAppointment(LocalDate date, String startTime, String endTime, GridPane eventArea){
		//WeekFields weekFields = WeekFields.of(Locale.getDefault()); 
		//int weekNumber = date.get(weekFields.weekOfWeekBasedYear());
		
		int starthalftime=0;
		
		String[] startTimeArray = startTime.split(":");
		int startTimeStartRow = Integer.parseInt(startTimeArray[0]);
		int startTimeEndRow = Integer.parseInt(startTimeArray[1]) ; 
		
		if (startTimeEndRow>0 && startTimeEndRow<30) {
			startTimeEndRow=30;
			starthalftime=1;
		}
		
		if (startTimeEndRow>30) {
			startTimeStartRow++;
			startTimeEndRow=0;
		}
		
		int startRow = startTimeStartRow*2 + starthalftime;
		
		
		int endhalftime=0;

		String[] endTimeArray = endTime.split(":");
		int endTimeStartRow = Integer.parseInt(endTimeArray[0]);
		int endTimeEndRow = Integer.parseInt(endTimeArray[1]) ;
		
		if (endTimeEndRow>0 && endTimeEndRow<30) {
			endTimeEndRow=30;
			endhalftime=1;
		}
		
		if (endTimeEndRow>30) {
			endTimeStartRow++;
			endTimeEndRow=0;
		}
		
		int endRow = endTimeStartRow*2 + endhalftime;
		
		Pane pane = new Pane();
		pane.setStyle("-fx-background-color:#fff");
		
		int colspan = endRow-startRow;
		
		eventArea.add(pane, 1, startRow, 1, colspan);

		//GridPane.setRowSpan(pane, 3);
		GridPane.setMargin(pane, new Insets(3, 3, 3, 4));
		
		
	}

}
