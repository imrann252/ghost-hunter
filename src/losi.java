
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class losi extends Application{
	public void start (Stage p) {
		
	StackPane stpane = new StackPane();
	stpane.setStyle("-fx-background-color: white;");
	
	VBox vbox = new VBox(20);
	vbox.setAlignment(Pos.CENTER);

	
	Label gameo = new Label("GAME OVER");
	gameo.setStyle("-fx-text-fill: red; -fx-font-size: 40px; -fx-font-family: 'Chiller'; -fx-font-weight: bold;"); 
	

	int finalScore = 0; 
	Label score = new Label("FINAL SCORE: " + finalScore);
	score.setStyle("-fx-text-fill: red; -fx-font-size: 20px; -fx-font-family: 'Chiller';");
	
	HBox hbox = new HBox(15);
	hbox.setAlignment(Pos.CENTER);

	Button retry = new Button("RETRY"); 
	Button mainmenu = new Button("MAIN MENU"); 

	
	retry.setStyle("-fx-background-color: gray; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-padding: 10 20;");
	mainmenu.setStyle("-fx-background-color: gray; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-padding: 10 20;");
	
	retry.setOnMousePressed(e -> {
	    retry.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-padding: 10 20;");
	});

	mainmenu.setOnMousePressed(e -> {
	    mainmenu.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-padding: 10 20;");
	});

	retry.setOnMouseReleased(e -> {
	    retry.setStyle("-fx-background-color: gray; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-padding: 10 20;");
	});

	mainmenu.setOnMouseReleased(e -> {
	    mainmenu.setStyle("-fx-background-color: gray; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-padding: 10 20;");
	});

	hbox.getChildren().addAll(retry, mainmenu);
	vbox.getChildren().addAll(gameo, score, hbox);
	

	
	
	stpane.getChildren().addAll(vbox);
	Scene sc=new Scene(stpane,600,300);
	p.setScene(sc);
	p.show();
	
	
	
	

	}
	    
	
	public static void main(String[] args) {
		launch(args);

	}

}
