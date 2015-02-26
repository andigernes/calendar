package sketch;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.controlsfx.dialog.Dialogs;

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
    private TextField descriptionField;
    @FXML
    private TextField startDateField;
    @FXML
    private TextField endDateField;


    private Stage dialogStage;
    private Appointment appointment;
    private boolean okClicked = false;


    private void initialize() {
    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;

        nameField.setText(appointment.getName());
        startTimeField.setText(appointment.getStartTime());
        endTimeField.setText(appointment.getEndTime());
        locationField.setText(appointment.getLocation());
        descriptionField.setText(appointment.getDescription());
        startDateField.setText(DateUtil.format(appointment.getStartDate()));
        endDateField.setText(DateUtil.format(appointment.getEndDate()));
       // startDateField.setPromptText("dd.mm.yyyy");
    }


    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            appointment.setName(nameField.getText());
            appointment.setStartTime(startTimeField.getText());
            appointment.setEndTime(endTimeField.getText());
            appointment.setLocation(locationField.getText());
            appointment.setDescription(descriptionField.getText());
            appointment.setStartDate(DateUtil.parse(startDateField.getText()));
            appointment.setEndDate(DateUtil.parse(endDateField.getText()));

            okClicked = true;
            dialogStage.close();
        }
    }


    @FXML
    private void handleCancel() {
        dialogStage.close();
    }


    private boolean isInputValid() {
        String errorMessage = "";
        
        
        String regex = ".+";

        if (nameField.getText().equals("") ) {
			errorMessage += "Must enter a name! \n";
		}
        
        if ((!nameField.getText().equals("")) && (! nameField.getText().matches(regex))) {
        	errorMessage += "The name has an invalid format! \n";
		}
        
        if (locationField.getText().equals("")) {
        	errorMessage += "Must enter a location! \n";
        }
        
        if ((!locationField.getText().equals("")) && (! locationField.getText().matches(regex))) {
        	errorMessage += "The location has an invalid format! \n";
		}
        
        if (descriptionField.getText().equals("")) {
        	errorMessage += "Must enter a description! \n";	
		}

        //mangler validering for startTime og endTime, antar at startDate og 
        //endDate vil være korrekte etterom datepicker brukes, men kan validere for å se om endDate er etter startDate
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Dialogs.create()
                .title("Invalid Fields")
                .masthead("Please correct invalid fields")
                .message(errorMessage)
                .showError();
            return false;
        }
    }
}