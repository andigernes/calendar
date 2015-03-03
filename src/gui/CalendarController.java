package gui;

import java.io.IOException;
import java.util.ArrayList;

import calendar.Appointment;
import calendar.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class CalendarController {
	
    @FXML private TextField username;
    @FXML private PasswordField password;
    @FXML Button login;
    @FXML Button logout;
    @FXML Text usernamelogin;
    private User userModel; 
    private static ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
    public CalendarController() {
    }
   
    public static void addAppointment(Appointment ap){
    	appointmentList.add(ap);
    	System.out.println(ap);
    }
    
    
    @FXML
    private void initialize() {
    	//TODO init
    	logout.setDisable(true);
    }
    
    /**
     * Connects with Query
     * @return true is user name and password is valid, false otherwise
     */
    private boolean validUser(){
    	return db.Query.authenticate(username.getText(), password.getText());
    }
    
    /**
     * Checks if username and password is valid
     */
    @FXML
    public void handleLogin(){
    	if(validUser()){
    		userModel = new User(username.getText());
    		AppointmentEditController.valid(password);
    		AppointmentEditController.valid(username);
    		System.out.println("login succsess");
    		usernamelogin.setText("Logged in as: "+ username.getText());
    		username.clear();
    		password.clear();
    		username.setDisable(true);
    		password.setDisable(true);
    		login.setDisable(true);
    		logout.setDisable(false);
    		
    	}else{
    		AppointmentEditController.invalid(password);
    		AppointmentEditController.invalid(username);
    	}
    }
    @FXML
    public void handleLogout(){
    	username.setDisable(false);
		password.setDisable(false);
		login.setDisable(false);
		logout.setDisable(true);
		usernamelogin.setText("");
    	userModel.setUserName(null);
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
