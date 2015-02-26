package sketch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class AppointmentMain extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
	        Pane root = FXMLLoader.load(AppointmentEditController.class.getResource("Appointment.fxml"));
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
}