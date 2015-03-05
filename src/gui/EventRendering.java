package gui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import calendar.Appointment;
import calendar.UserCalendar;

public class EventRendering extends CalendarController {

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

	public static void getAppointments(UserCalendar calendar,
			CalendarController controller) throws ParseException {
		String date;
		int eventWeek;
		String format = "yyyy-MM-dd";
		for (Appointment appointment : calendar) {
			date = appointment.getStartDate().toString();
			SimpleDateFormat df = new SimpleDateFormat(format);
			Date eventDate = df.parse(date);	
			System.out.println(eventDate);
			Calendar cal = Calendar.getInstance();
			cal.setTime(eventDate);
			eventWeek = cal.get(Calendar.WEEK_OF_YEAR);
			if (controller.week == eventWeek) {
				//sets column index
				int day = cal.get(Calendar.DAY_OF_WEEK);
				if(day == 1){
					day = 7;
				}else{
					day--;
				}
				representAppointment(appointment, controller, day);
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

	public static void representAppointment(Appointment appointment,
			CalendarController controller, int day) {
		String name = appointment.getName();
		String startTime = appointment.getStartTime().toString();
		String endTime = "" + appointment.getEndTime().toString();
		int starthalftime = 0; // 12:45
		
		// Sets grid row for startTime
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
		
		// Sets grid row for endTime
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
		
		
		
		//Sets a pane on the grid
		BorderPane pane = new BorderPane();
		appointment.setNode(pane);

		controller.eventArea.add(pane, day, startRow);
		GridPane.setRowSpan(pane, colspan);
		GridPane.setMargin(pane, new Insets(2, 2, 2, 3));
		Label text = new Label(name);
		pane.setTop(text);
		pane.setStyle("-fx-background-color:#ABE7E2");

	}

}
