
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class LosePanel extends StackPane {

    
    public LosePanel(int finalScore, Runnable retryAction, Runnable menuAction) {
        
      
        this.setStyle("-fx-background-color: white;");
        
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);

       
        Label gameo = new Label("GAME OVER");
        gameo.setStyle("-fx-text-fill: red; -fx-font-size: 40px; -fx-font-family: 'Chiller'; -fx-font-weight: bold;"); 

        Label score = new Label("FINAL SCORE: " + finalScore);
        score.setStyle("-fx-text-fill: red; -fx-font-size: 20px; -fx-font-family: 'Chiller';");
        
        HBox hbox = new HBox(15);
        hbox.setAlignment(Pos.CENTER);

        
        Button retry = new Button("RETRY"); 
        Button mainmenu = new Button("MAIN MENU"); 

        String idleStyle = "-fx-background-color: gray; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-padding: 10 20;";
        String pressedStyle = "-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-family: 'Courier New'; -fx-font-weight: bold; -fx-padding: 10 20;";

        retry.setStyle(idleStyle);
        mainmenu.setStyle(idleStyle);

        
        retry.setOnMousePressed(e -> retry.setStyle(pressedStyle));
        retry.setOnMouseReleased(e -> {
            retry.setStyle(idleStyle);
            retryAction.run(); // Oyunu tekrar başlatçak
        });

        mainmenu.setOnMousePressed(e -> mainmenu.setStyle(pressedStyle));
        mainmenu.setOnMouseReleased(e -> {
            mainmenu.setStyle(idleStyle);
            menuAction.run(); // Ana menüye döncek
        });

       
        hbox.getChildren().addAll(retry, mainmenu);
        vbox.getChildren().addAll(gameo, score, hbox);
        
        this.getChildren().add(vbox);
    }
}
