package sketch;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


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


    private Appointment appointmentModel;
    private boolean okClicked = false;

    @FXML
    private void initialize() {
    	//Start Date Listener
    	ChangeListener<LocalDate> fromDateList = new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable,
					LocalDate oldValue, LocalDate newValue) {
					if(isValidStartDate()){
						valid(startDateField);
					}else{
						invalid(startDateField);
					}
			}
		};
		startDateField.valueProperty().addListener(fromDateList);
		//End date Listener
		ChangeListener<LocalDate> toDateList = new ChangeListener<LocalDate>() {
			@Override
			public void changed(ObservableValue<? extends LocalDate> observable,
					LocalDate oldValue, LocalDate newValue) {
					if(isValidEndDate()){
						valid(endDateField);
					}else{
						invalid(endDateField);
					}
			}
		};
		endDateField.valueProperty().addListener(toDateList);
		//Name Field Listener
		ChangeListener<String> nameList = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
					if(isValidName()){
						valid(nameField);
					}else{
						invalid(nameField);
					}
			}
		};
		nameField.textProperty().addListener(nameList);
		//Location Field Listener
		ChangeListener<String> locationList = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
					if(isValidLocation()){
						valid(locationField);
					}else{
						invalid(locationField);
					}
			}
		};
		locationField.textProperty().addListener(locationList);
		
		//Start Time Listener
		ChangeListener<String> startTimeList = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
					if(isValidStartTime()){
						valid(startTimeField);
					}else{
						invalid(startTimeField);
					}
			}
		};
		startTimeField.textProperty().addListener(startTimeList);
		
		//End Time Listener
		ChangeListener<String> endTimeList = new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
					if(isValidEndTime()){
						valid(endTimeField);
					}else{
						invalid(endTimeField);
					}
			}
		};
		endTimeField.textProperty().addListener(endTimeList);
    }
    
 // To open an already saved appointment
  public void openAppointment(Appointment appointment) {
      appointmentModel = appointment;
      nameField.setText(appointment.getName());
      startTimeField.setText(appointment.getStartTime().toString());
      endTimeField.setText(appointment.getEndTime().toString());
      locationField.setText(appointment.getLocation());
      descriptionField.setText(appointment.getDescription());
      startDateField.setValue(appointment.getStartDate());
      endDateField.setValue(appointment.getEndDate());

  }
  // To open a new Appointment
  public void setAppointment(Appointment appointment){

  }
    public static void invalid(Node field){
    	field.getStyleClass().add("invalid");
    }
    public static void valid(Node field){
    	field.getStyleClass().removeAll("invalid");
    }

    

    public boolean isOkClicked() {
        return okClicked;
    }
    private boolean isAppointmentValid(){
    	if (isValidName()&&isValidLocation()&&isValidStartDate()&&isValidEndDate()&&isValidStartTime()&&isValidEndTime()) {
    		return true;
    	}else{
    		return false;
    	}
    }

    @FXML
    private void save() {
        if (isAppointmentValid()) {
            appointmentModel.setName(nameField.getText());
            appointmentModel.setStartTime(LocalTime.parse(startTimeField.getText()));
            appointmentModel.setEndTime(LocalTime.parse(endTimeField.getText()));
            appointmentModel.setLocation(locationField.getText());
            appointmentModel.setDescription(descriptionField.getText());
            appointmentModel.setStartDate(startDateField.getValue());
            appointmentModel.setEndDate(endDateField.getValue());

            okClicked = true;
        }
    }
    


    private boolean isValidName(){
    	if (!(nameField.getText().isEmpty())) {
			return true;
		}else{
			return false;	
		}
    }
    
    private boolean isValidLocation(){
    	return true;
    }
    
    private boolean isValidStartDate(){
    	if(startDateField.getValue().isAfter(LocalDate.now())){
			return true;
		}else{
			return false;
		}
    }
    private boolean isValidEndDate(){
    	if(endDateField.getValue().isAfter(startDateField.getValue())){
    		return true;
		}else{
			return false;
		}
    }
    private boolean isValidStartTime(){
    	try{
    		LocalTime.parse(startTimeField.getText());
    	}catch (Exception e){
    		return false;
    	} 
    	return true;
    }
    private boolean isValidEndTime(){
    	try{
    		LocalTime startTime = LocalTime.parse(startTimeField.getText());
    		LocalTime endTime = LocalTime.parse(endTimeField.getText());
    		if(endTime.isAfter(startTime)){
    			return true;
    		}
    	}catch (Exception e){}    	
    	return false;
    }


}