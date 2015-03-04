package gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import calendar.Appointment;
import calendar.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML Label usernamelabel;
    @FXML Label passwordlabel;
    
    
    private static ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();
    public CalendarController() {
    }
   
    public static void addAppointment(Appointment ap){
    	appointmentList.add(ap);
    	db.UpdateQuery.saveInDb(ap);
    }
    
    
    @FXML
    private void initialize() {
    	//TODO init

    }
    
    /**
     * Connects with Query
     * @return true is user name and password is valid, false otherwise
     * @throws SQLException 
     */
    private boolean validUser() throws SQLException{
    	return db.Query.authenticate(username.getText(), password.getText());
    }
    
    /**
     * Checks if username and password is valid
     */
    
    private void hide(Node field){
    	field.setDisable(true);
    	field.setVisible(false);
    }
    
    private void show(Node field){
    	field.setDisable(false);
    	field.setVisible(true);
    }
    
    
    @FXML
    public void handleLogin() throws SQLException{
    	if(userModel == null) {
	    	
	    	if(validUser()){
	    		userModel = new User(username.getText());
	    		AppointmentEditController.valid(password);
	    		AppointmentEditController.valid(username);
	    		System.out.println("login success");
	    		usernamelogin.setText("Logged in as: "+ username.getText());
	    		username.clear();
	    		password.clear();
	    		hide(username);
	    		hide(password);
	    		hide(passwordlabel);
	    		hide(usernamelabel);
	    		login.setText("Log out");	
	    	}else{
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
    	}
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
