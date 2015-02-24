package sketch;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Appointment extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader();
	        fxmlLoader.setController(this);
	        fxmlLoader.setLocation(getClass().getResource("Appointment.fxml"));
	        Parent root = (Parent) fxmlLoader.load(this.getClass().getResourceAsStream("appointment.fxml"));
	        primaryStage.setScene(new Scene(root));
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}