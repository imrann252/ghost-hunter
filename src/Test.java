import javafx.scene.paint.*;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Test extends Application {
	public static Stage mainStage;
	public void start(Stage primaryStage){
	
		
		
		
		MainMenuScene m1= new MainMenuScene();
		mainStage=primaryStage;
		primaryStage.setScene(m1.getScene());
		primaryStage.setTitle("test");
		primaryStage.setMaximized(true);
		primaryStage.show();
		

	
	}
	

	public static void main(String[] args) {
		launch(args);
	}

}
