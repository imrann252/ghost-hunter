


import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class WinPanel extends Application{
	
	public void start (Stage p) {
	
    StackPane stpane=new StackPane();
	Rectangle rec=new Rectangle();
	
	rec.widthProperty().bind(stpane.widthProperty());
	rec.heightProperty().bind(stpane.heightProperty());
	
	rec.setFill(Color.rgb(0, 0, 0, 0.7));
	
	Label winup =new Label("YOU WIN!");
	Label winsub=new Label("YOU'VE VACUUMED ALL THE GHOSTS!");
	
	winup.setTextFill(Color.WHITE);
	winup.setStyle("-fx-font-family: 'Chiller' ; -fx-font-size: 40px; -fx-font-weight: bold;");
	
	winsub.setTextFill(Color.WHITE);
	winsub.setStyle("-fx-font-family: 'Chiller' ; -fx-font-size: 20px;");
	
	Button nextbtn = new Button("NEXT LEVEL");
	nextbtn.setStyle("-fx-background-color: purple; -fx-text-fill: white; -fx-font-family: 'Chiller'; -fx-font-size: 18px;");
	
	nextbtn.setOnMousePressed(e -> {
	nextbtn.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-family: 'Chiller'; -fx-font-size: 18px;");
	});
	
	nextbtn.setOnMouseReleased(e -> {
    nextbtn.setStyle("-fx-background-color: purple; -fx-text-fill: white; -fx-font-family: 'Chiller'; -fx-font-size: 18px;");
    System.out.println("Next level loading...");
	});
	
	
	
	VBox vbox=new VBox(20);
	vbox.setAlignment(Pos.CENTER);
	vbox.getChildren().addAll(winup,winsub,nextbtn);
	
	stpane.getChildren().addAll(rec,vbox);
	Scene sc=new Scene(stpane,600,300);
	p.setScene(sc);
	p.show();
	
	
	
	}

	public static void main(String[] args) {
		launch(args);
	}

}
