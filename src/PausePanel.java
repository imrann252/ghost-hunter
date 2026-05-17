// Şeyma nur Hacımurtazaoğlu 150124057
//Purpose: Creates the panel that appears when the game is paused, containing the resume, main menu, and retry buttons.
 
//paaaaaaaa

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

public class PausePanel extends StackPane {
//Purpose: Constructor that initializes panel dimensions, configures text styles, and manages mouse click listeners for game controls.
    public PausePanel(double width, double height, GameArea gameArea) {
        
        this.setPrefSize(width, height);
        this.setMinSize(width, height);
        this.setMaxSize(width, height);
        
        
        this.setStyle("-fx-background-color: rgba(75, 0, 130, 0.7); -fx-background-radius: 15;");
 //Loads the custom thematic "Bloody" font styles from resources for labels and buttons.
        Font bloodyFont = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 60);
        Font bloodySmall = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 25);

        Label pl = new Label("GAME PAUSED");
        pl.setTextFill(Color.WHITE);
        pl.setFont(bloodyFont);

        Button resumebtn = new Button("RESUME");
        Button retrybtn = new Button("RETRY");
        Button menubtn = new Button("MAIN MENU");

        resumebtn.setFont(bloodySmall);
        retrybtn.setFont(bloodySmall);
        menubtn.setFont(bloodySmall);
        
        resumebtn.setPrefSize(140, 47);
        retrybtn.setPrefSize(140, 47);
        menubtn.setPrefSize(140, 47);

       
//    Purpose: Defines CSS styles for default and pressed states to provide clear visual feedback and enhance user navigation.
        String idleStyle = "-fx-background-color: #808080; -fx-text-fill: white; -fx-background-radius: 6;";
        String pressedStyle = "-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 6;";
//Purpose: Sets uniform dimensions for all menu buttons to maintain layout symmetry and provide better user accessibility.
        resumebtn.setPrefSize(140, 47);
        resumebtn.setStyle(idleStyle);
        retrybtn.setStyle(idleStyle);
        menubtn.setStyle(idleStyle);

        
   
        resumebtn.setOnMousePressed(e -> resumebtn.setStyle(pressedStyle));
        resumebtn.setOnMouseReleased(e -> {
        	resumebtn.setStyle(idleStyle);
            gameArea.resume(); 
        });

        retrybtn.setOnMousePressed(e -> retrybtn.setStyle(pressedStyle));
        retrybtn.setOnMouseReleased(e -> {
        	retrybtn.setStyle(idleStyle);
            gameArea.restart(gameArea.getCurrentLevel());
        });

        menubtn.setOnMousePressed(e -> menubtn.setStyle(pressedStyle));
        menubtn.setOnMouseReleased(e -> {
        	menubtn.setStyle(idleStyle);
            gameArea.goToMenu();
        });

        HBox hbox = new HBox(20, resumebtn, retrybtn, menubtn);
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(61, pl, hbox);
        vbox.setAlignment(Pos.CENTER);

        this.getChildren().add(vbox);
    }
}