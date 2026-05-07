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
	
		
		
		/*
		MainMenuScene m1= new MainMenuScene();
		mainStage=primaryStage;
		primaryStage.setScene(m1.getScene());
		primaryStage.setTitle("test");
		primaryStage.setMaximized(true);
		primaryStage.show();
		
		*/
		
		

		Pane pane = new Pane();
		Scene scene=new Scene(pane,1300,800,Color.BLUE);
		
		Ripper rip = new Ripper(150.0,80.0,true);
		//Wisp wisp= new Wisp(200.0,100.0,true);
		Hunter hunt = new Hunter(150.0,80.0,true,scene);
		Ghost ghost = new Ghost(250.0,150.0,true);
		
		
		pane.getChildren().addAll(rip.getShape(),ghost.getShape());
		
		
		primaryStage.setTitle("test");
		primaryStage.setScene(scene);
		primaryStage.show();
	
		
	
	}
	

	public static void main(String[] args) {
		launch(args);
	}

}
