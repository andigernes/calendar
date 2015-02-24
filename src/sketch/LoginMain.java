package sketch;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;


public class LoginMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = FXMLLoader.load(LoginController.class.getResource("Loginv3.fxml"));
			Scene scene = new Scene(root,500,700);
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
