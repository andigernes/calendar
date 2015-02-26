package sketch;

import java.io.IOException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class MainCalendar extends Application {
	
	private Stage primaryStage;
	
	private ObservableList<Appointment> appointmentData = FXCollections.observableArrayList();

    public MainCalendar() {

    }


    public ObservableList<Appointment> getAppointmentData() {
        return appointmentData;
    }

/*	
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = FXMLLoader.load(LoginController.class.getResource("cal2.fxml"));
			Scene scene = new Scene(root,1200,741);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
*/
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void showAppointmentOverview() {
	    try {
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainCalendar.class.getResource("cal2.fxml"));
	        AnchorPane appointmentOverview = (AnchorPane) loader.load();


	        // Give the controller access to the main app.
	        AppointmentOverviewController controller = loader.getController();
	        controller.setMainCalendar(this);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
    public Stage getPrimaryStage() {
        return primaryStage;
    }

	
	
	public boolean showAppointmentEditDialog(Appointment appointment){
    	try{
    		
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainCalendar.class.getResource("Appointment.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Appointment");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            AppointmentEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setAppointment(appointment);

            dialogStage.showAndWait();

            return controller.isOkClicked();
    		
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
	}
}