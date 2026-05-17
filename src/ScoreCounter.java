import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


//Fatma_Zehra_Aktas_150124016
//Purpose: This class manages and displays the player's score.
//it updates the score dynamically during the game.

public class ScoreCounter extends Pane {

    // Total score value
    public double score = 0;

    // Points to be added
    public double point = 0;

    // Label used to display score
    public Label scoreLabel;

    // Reference to scene
    Scene scene;

    
    public ScoreCounter(Scene scene) {

    	
        this.scene = scene;

        
        // Load custom font
        Font bloody =
                Font.loadFont(
                        getClass().getResourceAsStream("Bloody.ttf"),
                        25
                );

        
        // Create score label
        scoreLabel = new Label();

        
        // Apply font
        scoreLabel.setFont(bloody);

        
        // Set text color
        scoreLabel.setTextFill(Color.WHITE);

        
        // Set vertical position
        scoreLabel.setLayoutY(20);

        
        // Center label horizontally on screen
        scoreLabel.layoutXProperty().bind(
                scene.widthProperty()
                        .divide(2)
                        .subtract(35)
        );

        
        // Add label into pane
        this.getChildren().add(scoreLabel);

        
        // Initialize score display
        this.addScore(point);
    }

    
    // Adds points to current score
    public void addScore(double point) {

        // Store added point
        this.point = point;

        
        // increase total score
        this.score += this.point;

        
        // Update score text
        scoreLabel.setText(
                String.format("SCORE: %02.0f", this.score)
        );
    }

    
    // Returns current score
    public double getScore() {
        return score;
    }
}