// Şeyma nur Hacımurtazaoğlu 150124057
//Purpose: Creates the panel that appears when the player wins the level, containing the next level, main menu, and retry buttons.

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WinPanel extends StackPane {
	
	//Purpose: Constructor that initializes the panel dimensions, applies specific semi-transparent 
   //theme styles, configures typography containers, and binds mouse interactions 
    // to calculate and trigger the subsequent level progression sequence.

    public WinPanel(double width, double height, GameArea gameArea) {
  
    	  this.setPrefSize(width, height);
          this.setMinSize(width, height);
          this.setMaxSize(width, height);
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.7); -fx-background-radius: 15;");

       //Loads the custom thematic "Bloody" font styles from resources for labels and buttons.
        Font bloodyFont = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 60);
        Font bloodySmall = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 25);

        Label winup = new Label("YOU WIN!");
        winup.setTextFill(Color.WHITE);
        winup.setFont(bloodyFont);
        winup.setStyle("-fx-font-weight: bold;");

        Label winsub = new Label("YOU'VE VACUUMED ALL THE GHOSTS!");
        winsub.setTextFill(Color.WHITE);
        winsub.setFont(bloodySmall);

      
        Button nextbtn = new Button("NEXT LEVEL");
        nextbtn.setFont(bloodySmall);
      //    Purpose: Defines CSS styles for default and pressed states to provide clear visual feedback and enhance user navigation.  
        String idleStyle = "-fx-background-color: purple; -fx-text-fill: white; -fx-padding: 10 30; -fx-background-radius: 5;";
        String pressedStyle = "-fx-background-color: red; -fx-text-fill: white; -fx-padding: 10 30; -fx-background-radius: 5;";
        
        nextbtn.setStyle(idleStyle);

        nextbtn.setOnMousePressed(e -> nextbtn.setStyle(pressedStyle));
        nextbtn.setOnMouseReleased(e -> {
            nextbtn.setStyle(idleStyle);
            
            int nextLvl = (gameArea.getCurrentLevel() < 3) ? (gameArea.getCurrentLevel() + 1) : 1;
            gameArea.restart(nextLvl);
        });

        VBox vbox = new VBox(25, winup, winsub, nextbtn);
        vbox.setAlignment(Pos.CENTER);

        this.getChildren().add(vbox);
    }
}