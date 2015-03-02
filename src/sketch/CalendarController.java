package sketch;

import javafx.fxml.FXML;
import javafx.scene.control.Label;



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

    private AppointmentMain am;

    public CalendarController() {
    }
    
    @FXML
    private void initialize() {
    	
    	//showAppointmentDetails(null); //clears appointment details
    	//ikke ferdig
    }
    
  //TODO 
    public void setMainCalendar(MainCalendar mainCalendar){
		//Hente ut brukers kalender
	}	
    
    // AddButton
    @FXML
    private void handleNewAppointment() {
    	System.out.println("Ble trykket");
    	am = new AppointmentMain();
       
	}
    

    @FXML
    private void handleEditAppointment() {
    	
//        Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();
//        if (selectedAppointment != null) {
//            boolean okClicked = MainCalendar.showAppointmentEditDialog(selectedAppointment);
//            if (okClicked) {
//                showAppointmentDetails(selectedAppointment);
//            }
//
//        } else {
//            // Nothing selected.
//
//        }
    }
    
    @FXML
    private void handleDeleteAppointment() {
    	//TODO hente ferdig avtale fra Query
    	
//    	int selectedIndex = appointmentTable.getSelectionModel().getSelectedIndex();
//        if (selectedIndex >= 0) {
//            appointmentTable.getItems().remove(selectedIndex);
//        } else {
//        	//Nothing selected.
//        }
        
    }


//    public void MainCalendar(MainCalendar mainCalendar) {
//        this.mainCalendar = mainCalendar;
//        
//         appointmentTable.setItems(MainCalendar.getAppointmentData());
//
//         Add observable info to appointment box in the calendar
//    }
	
	
	

}
