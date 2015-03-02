package sketch;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;



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
    	
    	Parent root;
        try {
        	System.out.println("Ble trykket");
            root = FXMLLoader.load(AppointmentEditController.class.getResource("Appointment.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Appointment");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
      
	}
    
    //Edit Button
    @FXML
    private void handleEditAppointment() {
    	
    	//TODO Hente fra quert
    }
    //Delete Button
    @FXML
    private void handleDeleteAppointment() {
    	//TODO slette fra Query

        
    }


//    public void MainCalendar(MainCalendar mainCalendar) {
//        this.mainCalendar = mainCalendar;
//        
//         appointmentTable.setItems(MainCalendar.getAppointmentData());
//
//         Add observable info to appointment box in the calendar
//    }
	
	
	

}
