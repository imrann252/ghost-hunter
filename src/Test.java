import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Test extends Application {
	public static Stage mainStage;
	public void start(Stage primaryStage){
		mainStage=primaryStage;
		
		MainMenuScene m1= new MainMenuScene();
		SelectLevelScene l1=new SelectLevelScene();
		primaryStage.setScene(m1.getScene());
		primaryStage.setTitle("test");
		primaryStage.show();
		
	}
		
		/*
		Pane pane = new Pane();
		
		//Ripper rip= new} Ripper(200.0,100.0);
		//RangeToken rantoken = new RangeToken(200,250.0,3);
		
		Wisp wisp= new Wisp(200.0,100.0,true);
		pane.getChildren().addAll(wisp.getShape());
		AnimationTimer animation=new AnimationTimer() {
			public void handle (long now) {
				wisp.updateAnimation();
				wisp.move();
			}
		};
		animation.start();
		
		Scene scene=new Scene(pane,400,400);
		primaryStage.setTitle("test");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	*/

	public static void main(String[] args) {
		launch(args);
	}

}
