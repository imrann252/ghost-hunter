import javafx.geometry.Pos;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
//Seyma_Nur_Hacimurtazaoglu_150124057
// Purpose: Creates the panel that appears when the player loses or the time is up, displaying the white "Game Over" screen with the final score, retry button, and main menu navigation options.

public class LosePanel extends StackPane {
//Constructor that constructs the user interface components for the loss screen, sets up thematic fonts and layout constraints, and handles the event triggers for restarting the current level or returning to the main menu.
    public LosePanel(double width, double height, double finalScore, GameArea gameArea) {
        
        this.setPrefSize(width, height);
        this.setMinSize(width, height);
        this.setMaxSize(width, height);
        this.setStyle("-fx-background-color: white; -fx-background-radius: 15;");

     // //Loads the custom thematic "Bloody" font styles from resources for labels and buttons.
        Font bloodyFont = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 50);
        Font bloodySmall = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 22);

      
        Label gameo = new Label("GAME OVER");
        gameo.setFont(bloodyFont);
        gameo.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

        Label score = new Label("FINAL SCORE: " + finalScore);
        score.setFont(bloodySmall);
        score.setStyle("-fx-text-fill: red;");

     
        Button retry = new Button("RETRY");
        Button mainmenu = new Button("MAIN MENU");

        retry.setFont(bloodySmall);
        mainmenu.setFont(bloodySmall);
//Purpose: Sets uniform dimensions for all menu buttons to maintain layout symmetry and provide better user accessibility.
        retry.setPrefSize(160, 50);
        mainmenu.setPrefSize(160, 50);
    //    Purpose: Defines CSS styles for default and pressed states to provide clear visual feedback and enhance user navigation.
        String idleStyle = "-fx-background-color: #808080; -fx-text-fill: white; -fx-background-radius: 5;";
        String pressedStyle = "-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 5;";

        retry.setStyle(idleStyle);
        mainmenu.setStyle(idleStyle);

        
        retry.setOnMousePressed(e -> retry.setStyle(pressedStyle));
        retry.setOnMouseReleased(e -> {
            retry.setStyle(idleStyle);
            gameArea.restart(gameArea.getCurrentLevel());
        });

     
        mainmenu.setOnMousePressed(e -> mainmenu.setStyle(pressedStyle));
        mainmenu.setOnMouseReleased(e -> {
            mainmenu.setStyle(idleStyle);
            gameArea.goToMenu();
        });

        HBox hbox = new HBox(20, retry, mainmenu);
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(25, gameo, score, hbox);
        vbox.setAlignment(Pos.CENTER);

        this.getChildren().add(vbox);
    }
}