import javafx.application.Application;

import javafx.stage.Stage;

public class Test extends Application {
	public void start(Stage primaryStage){
		MainMenuScene m1= new MainMenuScene();
		SelectLevelScene l1=new SelectLevelScene();
		primaryStage.setScene(m1.getScene());
		primaryStage.setTitle("test");
		primaryStage.show();
		
	}
		/*
		Pane pane=new Pane();
		
		Ripper rip= new Ripper(200.0,100.0);
		pane.getChildren().add(rip.getView());
		
		
		Scene scene=new Scene(pane,400,400);
		primaryStage.setTitle("test");
		primaryStage.setScene(scene);
		primaryStage.show();
	}*/

	public static void main(String[] args) {
		launch(args);
	}

}
