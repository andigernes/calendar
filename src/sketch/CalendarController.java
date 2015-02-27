package sketch;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CalendarController {
	
    @FXML
    private Label nameLabel;
    @FXML
    private Label startTimeLabel;
    @FXML
    private Label endTimeLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label startDateLabel;
    @FXML
    private Label endDateLabel;

    private MainCalendar mainCalendar;

    public CalendarController() {
    }
    
    @FXML
    private void initialize() {
    	//showAppointmentDetails(null); //clears appointment details
    	//ikke ferdig
    }
    
    //ikke ferdig!
    
    public void setMainCalendar(MainCalendar mainCalendar){
		this.mainCalendar = mainCalendar;
		appointmentTable.setItems(mainCalendar.getApplicationData());	
	}	
    
    @FXML
    private void handleNewAppointment() {
        Appointment tempAppointment = new Appointment();
        boolean okClicked = MainCalendar.showAppointmentEditDialog(tempAppointment);
        if (okClicked) {
            MainCalendar.getAppointmentData().add(tempAppointment);
        }
    }

    @FXML
    private void handleEditAppointment() {
        Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
        if (selectedAppointment != null) {
            boolean okClicked = MainCalendar.showAppointmentEditDialog(selectedAppointment);
            if (okClicked) {
                showAppointmentDetails(selectedAppointment);
            }

        } else {
            // Nothing selected.
            Dialogs.create()
                .title("No Selection")
                .masthead("No Appointment Selected")
                .message("Please select an appointment in the table.")
                .showWarning();
        }
    }
    
    @FXML
    private void handleDeleteAppointment() {
    	/*
    	int selectedIndex = appointmentTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            appointmentTable.getItems().remove(selectedIndex);
        } else {
            Dialogs.create()
                .title("No Selection")
                .masthead("No Appointment Selected")
                .message("Please select an appointment in the table.")
                .showWarning();
        }
        */
    }


    public void MainCalendar(MainCalendar mainCalendar) {
        this.mainCalendar = mainCalendar;
        
        // appointmentTable.setItems(MainCalendar.getAppointmentData());

        // Add observable info to appointment box in the calendar
    }
	
    
    
		// må lage pop-up vindu som viser en avtale
	
	private void showAppointmentDetails(Appointment appointment) {
	    if (appointment != null) {
	        nameLabel.setText(appointment.getName());
	        startTimeLabel.setText(appointment.getStartTime().toString());
	        endTimeLabel.setText(appointment.getEndTime().toString());
	        locationLabel.setText(appointment.getLocation());
	        descriptionLabel.setText(appointment.getDescription());
	        startDateLabel.setText(appointment.getStartDate().toString());
	        endDateLabel.setText(appointment.getEndDate().toString());
	    } else {
	        nameLabel.setText("");
	        startTimeLabel.setText("");
	        endTimeLabel.setText("");
	        locationLabel.setText("");
	        descriptionLabel.setText("");
	        startDateLabel.setText("");
	        endDateLabel.setText("");
	    }
	}
	

}
