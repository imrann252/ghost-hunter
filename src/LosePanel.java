
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LosePanel extends StackPane {

    
    public LosePanel(double finalScore, Runnable retryAction, Runnable menuAction) {
        
      
        this.setStyle("-fx-background-color: white;");
        
        Font bloodyFont = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 50);
        Font bloodySmall = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 25);
        
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);

       
        Label gameo = new Label("GAME OVER");
        gameo.setFont(bloodyFont);
        gameo.setStyle(gameo.getStyle()+"-fx-text-fill: red;  -fx-font-weight: bold;"); 

        Label score = new Label("FINAL SCORE: " + finalScore);
        score.setFont(bloodySmall);
        score.setStyle(score.getStyle()+"-fx-text-fill: red");
        
        HBox hbox = new HBox(15);
        hbox.setAlignment(Pos.CENTER);

        
        Button retry = new Button("RETRY"); 
        retry.setFont(bloodySmall);
        Button mainmenu = new Button("MAIN MENU"); 
        mainmenu.setFont(bloodySmall);
        
        retry.setMinWidth(180); 
        mainmenu.setMinWidth(180);
        retry.setPrefHeight(60);
        mainmenu.setPrefHeight(60);

        String idleStyle = "-fx-background-color: gray; -fx-text-fill: white; -fx-font-weight: bold;";
        String pressedStyle = "-fx-background-color: red; -fx-text-fill: white;  -fx-font-weight: bold;";

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
