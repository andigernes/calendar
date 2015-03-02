package sketch;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



public class CalendarController {
	
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML Button login;
    @FXML Button logout;
    
    public CalendarController() {
    }
    
    @FXML
    private void initialize() {
    	//TODO init
    }
    //Validering av brukernavn/Passord
    public boolean validUser(){
    	//TODO Sjekke username&password mot databasen
    	return true;
    }
    @FXML
    public void handleLogin(){
    	if(validUser()){
    		//TODO open users calendar
    		AppointmentEditController.valid(password);
    		AppointmentEditController.valid(username);
    	}else{
    		AppointmentEditController.invalid(password);
    		AppointmentEditController.invalid(username);
    	}
    }
    @FXML
    public void handleLogout(){
    	//TODO clearAll
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
            root = FXMLLoader.load(AppointmentEditController.class.getResource("Appointment.fxml"));
            Stage stage = new Stage();
            stage.setTitle("New Appointment");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
      
	}




//    public void MainCalendar(MainCalendar mainCalendar) {
//        this.mainCalendar = mainCalendar;
//        
//         appointmentTable.setItems(MainCalendar.getAppointmentData());
//
//         Add observable info to appointment box in the calendar
//    }
	
	
	

}
