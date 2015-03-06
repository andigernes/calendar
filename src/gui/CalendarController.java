package gui;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import calendar.Appointment;
import calendar.User;
import calendar.UserCalendar;

public class CalendarController {

	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private Button login;
	@FXML
	private Button logout;
	@FXML
	private Button add;
	@FXML
	Text usernamelogin;
	private User userModel;
	@FXML
	private Label usernamelabel;
	@FXML
	private Label passwordlabel;
	@FXML
	protected GridPane eventArea;
	@FXML
	private Label weekIndicator;
	@FXML
	private GridPane weekDays;
	public int week;
	public int year = 2015;
	private static final String[] days = { "Monday", "Tuesday", "Wednesday",
			"Thursday", "Friday", "Saturday", "Sunday" };

	private static UserCalendar appointmentList = new UserCalendar();
	private static User user;
	private static Stage appointmentEditor;

	public CalendarController() {
	}

	/**
	 * Initializes the calendar with week number
	 */
	@FXML
	private void initialize() {
		Calendar cal = Calendar.getInstance();
		Date now = new Date();
		cal.setTime(now);
		week = cal.get(Calendar.WEEK_OF_YEAR);
		weekIndicator.setText("Week: " + week);
		hide(add);
	}

	/**
	 * right-arrow; increment week by 1
	 * 
	 * @throws ParseException
	 */
	@FXML
	public void incrementWeek() {
		if (week < 52) {
			week++;
		} else {
			week = 1;
			year++;
		}
		changeWeek();
	}

	/**
	 * left-arrow; decrease week by 1
	 * 
	 * @throws ParseException
	 */
	@FXML
	public void decreaseWeek() {
		if (week > 1) {
			week--;
		} else {
			week = 52;
			year--;
		}
		changeWeek();

	}

	/**
	 * Change week and days in label weekIndicator and weekDays
	 */
	public void changeWeek() {
		Calendar cal = Calendar.getInstance();
		cal.setWeekDate(year, week, 2);

		weekIndicator.setText("Week: " + week);
		weekDays.getChildren().clear();
		for (int i = 1; i < 8; i++) {
			Label l = new Label(days[i - 1] + " " + cal.get(5) + ".");
			weekDays.add(l, i, 0);
			GridPane.setHalignment(l, HPos.CENTER);
			Separator s = new Separator(Orientation.VERTICAL);
			weekDays.add(s, i, 0);
			cal.add(5, 1);
		}
		derenderAppointments();
		try {
			EventRendering.getAppointments(appointmentList, this);
		} catch (ParseException e) {
			System.out.println(e);
		}

	}

	/**
	 * Connects with Query
	 * 
	 * @return true is user name and password is valid, false otherwise
	 * @throws SQLException
	 */
	private boolean validUser() throws SQLException {
		return db.Query.authenticate(username.getText(), password.getText());
	}

	/**
	 * Hides field
	 * 
	 * @param field
	 *            Some field, such as user name or password
	 */

	private void hide(Node field) {
		field.setDisable(true);
		field.setVisible(false);
	}

	/**
	 * Shows field
	 * 
	 * @param field
	 *            Some field, such as user name or password
	 */
	private void show(Node field) {
		field.setDisable(false);
		field.setVisible(true);
	}

	/**
	 * Checks if the user is valid shows the logged in user or messages error
	 * 
	 * @throws SQLException
	 */
	@FXML
	public void handleLogin() throws SQLException {
		if (userModel == null) {
			if (validUser()) {
				userModel = new User(username.getText());
				AppointmentEditController.valid(password);
				AppointmentEditController.valid(username);
				usernamelogin.setText("Logged in as: " + username.getText());
				user = new User(username.getText());
				username.clear();
				password.clear();
				hide(username);
				hide(password);
				hide(passwordlabel);
				hide(usernamelabel);
				show(add);
				login.setText("Log out");
				appointmentList.getCalendar(user, this);
			} else {
				AppointmentEditController.invalid(password);
				AppointmentEditController.invalid(username);
			}
		} else {
			usernamelogin.setText("");
			login.setText("Log in");
			userModel = null;
			show(username);
			show(password);
			show(passwordlabel);
			show(usernamelabel);
			hide(add);
			derenderAppointments();
			appointmentList.clear();
		}
	}

	public void derenderAppointments() {
		for (Appointment a : appointmentList) {
			eventArea.getChildren().remove(a.getNode());
		}
	}

	/**
	 * Fill in appointment slots for a logged in user
	 * 
	 * @param mainCalendar
	 * @throws SQLException
	 */
	// TODO
	public void setMainCalendar(MainCalendar mainCalendar) throws SQLException {
	}

	/**
	 * Add button actionEvent Loads a new appointment
	 */
	@FXML
	private void handleNewAppointment() {
		if (appointmentEditor == null) {
			Parent root;
			try {
				root = FXMLLoader.load(AppointmentEditController.class
						.getResource("Appointment.fxml"));
				appointmentEditor = new Stage();
				appointmentEditor.setTitle("New Appointment");
				appointmentEditor.setScene(new Scene(root));
				appointmentEditor.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Closes the new appointment window
	 */
	public static void closeAppointmentEditor() {
		if (appointmentEditor != null) {
			appointmentEditor.close();
			appointmentEditor = null;
		}
	}

	/**
	 * Saves appointment in database
	 * 
	 * @param ap
	 *            Appointment object
	 */
	public static void addAppointment(Appointment ap) {
		appointmentList.add(ap);
	}

	/**
	 * Delete appointment in database
	 * 
	 * @param ap
	 *            Appointment object
	 */
	public static void deleteAppointment(Appointment ap) {
		appointmentList.remove(ap);
	}

}
