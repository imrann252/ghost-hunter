import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class testfatma extends Application {
	public void start(Stage primaryStage){
		
		/*
		MainMenuScene m1= new MainMenuScene();
		SelectLevelScene l1=new SelectLevelScene();
		primaryStage.setScene(m1.getScene());
		primaryStage.setTitle("test");
		primaryStage.show();
		
		
		*/
		//Pane pane = new Pane();
		GameArea game = new GameArea();
		
		//Scene scene = new Scene(pane,400,400);
		
		/*RangeToken rantoken = new RangeToken(200,250,3);
		VacuumBar vacvac = new VacuumBar(0,0,100);
		HealthBar helhel = new HealthBar(0,0,100,scene);
		
		vacvac.setBar(50);
		helhel.setBar(70);
		
		
		//pane.getChildren().addAll(helhel,vacvac);*/
		
		primaryStage.setTitle("test");
		primaryStage.setScene(game.getScene());
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
