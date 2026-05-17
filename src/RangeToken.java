import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;


//Fatma_Zehra_Aktas_150124016
//Purpose: This class creates a Range Token object.When collected by the hunter,
//it temporarily increases the vacuum range and disappears from the game screen.

public class RangeToken extends Pane {

    // Access configuration values
    private ConfigLoader config = ConfigLoader.getInstance();

    // Prevents collecting token multiple times
    private boolean isCollected = false;

    
    public RangeToken(double x, double y) {

        // Main circular token background
        Circle outCircle = new Circle(15, Color.YELLOW);

        // Border color
        outCircle.setStroke(Color.GRAY.darker());

        
        // Create range icon using polygon
        Polygon rangeIcon = new Polygon();

        
        // Triangle coordinates
        rangeIcon.getPoints().addAll(new Double[]{
                -5.0, 5.0,
                 8.0, 0.0,
                -5.0, -5.0
        });

        
        // icon color
        rangeIcon.setFill(Color.BLACK);

        
        // Add shapes into pane
        this.getChildren().addAll(outCircle, rangeIcon);

        
        // Set token position on screen
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    
    // Checks if hunter collects the token
    public void collect(Hunter hunter, Pane root) {

        // Convert percentage increase into decimal
        double percentageIncrease =
                config.getVacuumTokenIncrease() / 100.0;

        
        // Stop method if already collected
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

            
            // Save current vacuum scale values
            double currentX = hunter.getVacuumScaleX();
            double currentY = hunter.getVacuumScaleY();

            
            //increase vacuum range
            hunter.setVacuumScale(
                    currentX + percentageIncrease,
                    currentY + percentageIncrease
            );

            
            // Create timer for temporary effect
            PauseTransition delay =
                    new PauseTransition(Duration.seconds(5));

            
            // Restore old vacuum range after timer ends
            delay.setOnFinished(e -> {

                hunter.setVacuumScale(
                        currentX,
                        currentY
                );
            });

            
            // Start timer
            delay.play();
        }
    }
}