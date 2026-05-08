import javafx.geometry.Rectangle2D;

import javafx.stage.Screen;
import javafx.scene.shape.Rectangle;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class tokentest extends Application {
	public void start(Stage primaryStage){
		
	
		Pane pane = new Pane();

		
		Scene scene = new Scene(pane,400,400);
		
		HealthToken ht = new HealthToken(100,100);
		RangeToken rantoken = new RangeToken(200,250);
		EyeToken eye = new EyeToken(200,200);
		
		
		pane.getChildren().addAll(rantoken,eye,ht);
		
		primaryStage.setTitle("test");
		primaryStage.setScene(scene);


		primaryStage.setMaximized(true);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
