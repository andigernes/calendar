package gui;

import java.time.LocalDate;
import java.time.LocalTime;

import calendar.Appointment;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AppointmentEditController {

	@FXML
	private TextField nameField;
	@FXML
	private TextField startTimeField;
	@FXML
	private TextField endTimeField;
	@FXML
	private TextField locationField;
	@FXML
	private TextArea descriptionField;
	@FXML
	private DatePicker startDateField;
	@FXML
	private DatePicker endDateField;
	@FXML
	private Button save;

	private Appointment appointmentModel;
	private boolean okClicked = false;

	/**
	 * Initializes the appointment adds change listeners to all the fields
	 */
	@FXML
	private void initialize() {
		// Start Date Listener
		ChangeListener<LocalDate> fromDateList = new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
				if (isValidStartDate()) {
					valid(startDateField);
				} else {
					invalid(startDateField);
				}
			}
		};
		startDateField.valueProperty().addListener(fromDateList);

		ChangeListener<String> nameList = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (isValidName()) {
					valid(nameField);
				} else {
					invalid(nameField);
				}
			}
		};
		nameField.textProperty().addListener(nameList);

		// Location Field Listener
		ChangeListener<String> locationList = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (isValidLocation()) {
					valid(locationField);
				} else {
					invalid(locationField);
				}
			}
		};
		locationField.textProperty().addListener(locationList);

		// Start Time Listener
		ChangeListener<String> startTimeList = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (isValidStartTime()) {
					valid(startTimeField);
				} else {
					invalid(startTimeField);
				}
			}
		};
		startTimeField.textProperty().addListener(startTimeList);

		// End Time Listener
		ChangeListener<String> endTimeList = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (isValidEndTime()) {
					valid(endTimeField);
				} else {
					invalid(endTimeField);
				}
			}
		};
		endTimeField.textProperty().addListener(endTimeList);
	}

	/**
	 * Opens specific appointment from database saved for the user
	 * 
	 * @param appointment
	 *            An appointment object that is saved for the user
	 */
	public void openAppointment(Appointment appointment) {
		appointmentModel = appointment;
		nameField.setText("test");
		nameField.setText(appointment.getName());
		startTimeField.setText(appointment.getStartTime().toString());
		endTimeField.setText(appointment.getEndTime().toString());
		locationField.setText(appointment.getLocation());
		descriptionField.setText(appointment.getDescription());
		startDateField.setValue(appointment.getStartDate());
		endDateField.setValue(appointment.getEndDate());

	}

	/**
	 * Add a new appointment Let the user create and save new appointment
	 * 
	 * @param appointment
	 *            An appointment object that is saved for the user
	 */
	public void newAppointment(Appointment appointment) {

	}

	/**
	 * Delete an appointment in the users calendar Connect to database
	 */
	@FXML
	private void handleDeleteAppointment() {
		// TODO delete from Query
	}

	/**
	 * 
	 * @param field
	 */
	public static void invalid(Node field) {
		field.getStyleClass().add("invalid");
	}

	public static void valid(Node field) {
		field.getStyleClass().removeAll("invalid");
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	private boolean isAppointmentValid() {
		return isValidName() && isValidLocation() && isValidStartDate() && isValidStartTime() && isValidEndTime();
	}

	@FXML
	private void save() {
		if (isAppointmentValid()) {
			appointmentModel = new Appointment();
			appointmentModel.setName(nameField.getText());
			appointmentModel.setStartTime(LocalTime.parse(startTimeField.getText()));
			appointmentModel.setEndTime(LocalTime.parse(endTimeField.getText()));
			appointmentModel.setLocation(locationField.getText());
			appointmentModel.setDescription(descriptionField.getText());
			appointmentModel.setStartDate(startDateField.getValue());
			appointmentModel.setEndDate(endDateField.getValue());
			CalendarController.addAppointment(appointmentModel);
			CalendarController.closeAppointmentEditor();
		} else {
			System.out.println("not valid?");
		}
	}

	private boolean isValidName() {
		if (!(nameField.getText().isEmpty())) {
			return true;
		} else {
			return false;
		}
	}

	private boolean isValidLocation() {
		return true;
	}

	private boolean isValidStartDate() {
		try {
			return startDateField.getValue().isAfter(LocalDate.now());
		} catch (NullPointerException e) {
			return false;
		}
	}

	private boolean isValidStartTime() {
		try {
			LocalTime.parse(startTimeField.getText());
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	private boolean isValidEndTime() {
		try {
			LocalTime startTime = LocalTime.parse(startTimeField.getText());
			LocalTime endTime = LocalTime.parse(endTimeField.getText());
			if (endTime.isAfter(startTime)) {
				return true;
			}
		} catch (Exception e) {
		}
		return false;
	}

}