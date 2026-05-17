import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.geometry.Pos;


//Fatma_Zehra_Aktas_150124016
//Purpose: This class creates an Eye Token object. When the hunter collects this token, 
//all invisible entities become visible for a limited amount of time.

public class EyeToken extends StackPane {

    // Prevents collecting the token multiple times
    private boolean isCollected = false;

    // Access configuration values
    private ConfigLoader config = ConfigLoader.getInstance();

    public EyeToken(double x, double y) {

        // Eye white (sclera)
        Ellipse ak = new Ellipse(10, 6);

        // Set eye white color
        ak.setFill(Color.YELLOW.brighter());

        // Add black outline
        ak.setStroke(Color.BLACK);

        // Outline thickness
        ak.setStrokeWidth(1);

        
        // Iris of the eye
        Circle iris = new Circle(4);

        // Iris color
        iris.setFill(Color.SKYBLUE.darker());

        // Iris outline
        iris.setStroke(Color.BLACK);

        // Iris outline thickness
        iris.setStrokeWidth(0.5);

        
        // Pupil of the eye
        Circle bebek = new Circle(2);

        // Pupil color
        bebek.setFill(Color.BLACK);

        
        // Align all children to center
        this.setAlignment(Pos.CENTER);

        
        // Main circular token background
        Circle token = new Circle(15, Color.YELLOW);

        // Border color of token
        token.setStroke(Color.GRAY.darker());

        
        // Add all eye parts into StackPane
        // StackPane places them on top of each other
        this.getChildren().addAll(token, ak, iris, bebek);

        
        // Set token position on screen
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    
    // Checks if hunter collects the token
    public void collect(Hunter hunter, ArrayList<Entity> ents, Pane root) {

        // If already collected, stop method
        if (isCollected) {
            return;
        }

        
        // Check collision between hunter and token
        if (this.getBoundsInParent().intersects(
                hunter.getBody().getBoundsInParent())) {

            // Mark token as collected
            isCollected = true;

            // Remove token from game screen
            root.getChildren().remove(this);

            
            // Make all entities visible
            for (Entity ent : ents) {

                ent.setVisible(true);

                
                // Create timer for visibility duration
                PauseTransition delay =
                        new PauseTransition(
                                Duration.seconds(config.getEyeTokenDuration()));

                
                // After timer ends, entities become invisible again
                delay.setOnFinished(e -> {
                    ent.setVisible(false);
                });

                
                // Start timer
                delay.play();
            }
        }
    }
}