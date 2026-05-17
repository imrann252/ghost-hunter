import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

//Fatma_Zehra_Aktas_150124016
//Purpose: This class creates a Shield Token object.
//When collected by the hunter,it temporarily gives invincibility 
//and disappears from the game screen.

public class ShieldToken extends Pane {

    
    private boolean isCollected = false;

    //constructor, creates shield token icon and add in pane.
    public ShieldToken(double x, double y) {
        
        Circle base = new Circle(15, Color.YELLOW);
        base.setStroke(Color.GRAY.darker());

        Polygon shieldSign = new Polygon();

        
        // Coordinates for 6-sided shield shape
        shieldSign.getPoints().addAll(new Double[]{

                0.0, -5.0,   // Top middle point
                7.0, -6.0,   // Top right
                7.0, 3.0,    // Bottom right
                0.0, 9.0,    // Bottom point
               -7.0, 3.0,    // Bottom left
               -7.0, -6.0    // Top left
        });

        
      
        shieldSign.setFill(Color.GRAY);
        shieldSign.setStroke(Color.BLACK);
        shieldSign.setStrokeWidth(2);

        this.getChildren().addAll(base, shieldSign);

        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    
    // Checks if hunter collects the token
    public void collect(Hunter hunter, Pane root) {

        // Stop method if already collected
        if (isCollected)
            return;

        
        // Check collision between hunter body and token
        // Using hunter body prevents collecting with vacuum area
        if (this.getBoundsInParent().intersects(hunter.getBody().getBoundsInParent())) {

            
            isCollected = true;

            
            // Remove token from game screen
            root.getChildren().remove(this);
            
            hunter.setInvincibility(true);

            
            // Create timer for temporary effect
            PauseTransition delay =
                    new PauseTransition(Duration.seconds(5));

          
            delay.setOnFinished(e -> {
                hunter.setInvincibility(false);
            });

            delay.play();
            
        }
    }
}