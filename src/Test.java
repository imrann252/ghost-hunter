import javafx.scene.paint.*;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
//Seyma_Nur_Hacimurtazaoglu_150124057
//Purpose: Serves as the main entry point of the application, initializing the primary stage and launching the game.
public class Test extends Application {
	public static Stage mainStage;
	//Purpose: Configures the primary window, displays the main menu scene, and maximizes the game view upon startup.
	public void start(Stage primaryStage){
	
	
		MainMenuScene m1= new MainMenuScene();
		mainStage=primaryStage;
		
		primaryStage.setScene(m1.getScene());
		primaryStage.setTitle("test");
		primaryStage.setMaximized(true);
		
		primaryStage.show();
		

	
	}
	
//Purpose:Main method that executes standard JavaFX application launch sequence.
	public static void main(String[] args) {
		launch(args);
	}

}
