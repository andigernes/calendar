package gui;

import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import calendar.Appointment;
import calendar.UserCalendar;

public class EventRendering {

	public EventRendering(String username) {

	}

	public void getAppointments(UserCalendar calendar, GridPane eventArea) {
		LocalDate date;
		String startTime;
		String endTime;
		String name;
		for (Appointment appointment : calendar) {
			name = appointment.getName();
			date = appointment.getStartDate();
			startTime = appointment.getStartTime().toString();
			endTime = "" + appointment.getEndTime().toString();

			representAppointment(name, date, startTime, endTime, eventArea);
		}
	}

	/**
	 * 
	 * representAppointment() converts start and end time to integers in order to calculate 
	 * the position of event panes.
	 * 
	 * @param date
	 * @param startTime
	 * @param endTime
	 * @param eventArea
	 * 
	 */

	public static void representAppointment(String name, LocalDate date, String startTime, String endTime,
			GridPane eventArea) {
		// WeekFields weekFields = WeekFields.of(Locale.getDefault());
		// int weekNumber = date.get(weekFields.weekOfWeekBasedYear());

		int starthalftime = 0; // 12:45

		String[] startTimeArray = startTime.split(":");
		int startTimeStartRow = Integer.parseInt(startTimeArray[0]);
		int startTimeEndRow = Integer.parseInt(startTimeArray[1]);

		if (startTimeEndRow > 0 && startTimeEndRow < 30) {
			starthalftime = 1;
		}

		if (startTimeEndRow > 30) {
			startTimeStartRow++;
		}

		int startRow = startTimeStartRow * 2 + starthalftime;

		int endhalftime = 0;

		String[] endTimeArray = endTime.split(":");
		int endTimeStartRow = Integer.parseInt(endTimeArray[0]);
		int endTimeEndRow = Integer.parseInt(endTimeArray[1]);

		if (endTimeEndRow > 0 && endTimeEndRow < 30) {
			endhalftime = 1;
		}

		if (endTimeEndRow > 30) {
			endTimeStartRow++;
		}

		int endRow = endTimeStartRow * 2 + endhalftime;
		int colspan = endRow - startRow;

		BorderPane pane = new BorderPane();

		eventArea.add(pane, 1, startRow);
		GridPane.setRowSpan(pane, colspan);

		// eventArea.add(pane, 1, startRow, 1, colspan);

		GridPane.setMargin(pane, new Insets(2, 2, 2, 3));

		Label text = new Label(name);

		pane.setTop(text);

		pane.setStyle("-fx-background-color:#ABE7E2");

	}

}
