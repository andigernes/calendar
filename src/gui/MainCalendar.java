package gui;

import application.Appointment;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class MainCalendar extends Application {
	
	private static Stage primaryStage;
	
	//For å hente avtaler fra database
	private static ObservableList<Appointment> appointmentData = FXCollections.observableArrayList();

    public MainCalendar() {

    }

    public static ObservableList<Appointment> getAppointmentData() {
        return appointmentData;
    }

	
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = FXMLLoader.load(CalendarController.class.getResource("cal2.fxml"));
			Scene scene = new Scene(root,1200,741);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
	
	
    public Stage getPrimaryStage() {
        return primaryStage;
    }

	
	
//	public static boolean showAppointmentEditDialog(Appointment appointment){
//    	try{
//    		
//    		FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainCalendar.class.getResource("Appointment.fxml"));
//            AnchorPane page = (AnchorPane) loader.load();
//
//            Stage dialogStage = new Stage();
//            dialogStage.setTitle("Edit Appointment");
//            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(primaryStage);
//            Scene scene = new Scene(page);
//            dialogStage.setScene(scene);
//
//            AppointmentEditController controller = loader.getController();
//            controller.openAppointment(appointment);
//            
//            dialogStage.showAndWait();
//
//            return controller.isOkClicked();
//    		
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
//    }


}