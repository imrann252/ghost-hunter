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

    
    private ConfigLoader config = ConfigLoader.getInstance();
    private boolean isCollected = false;
    
    //constructor, creates range token icon and add in pane.
    public RangeToken(double x, double y) {

        Circle outCircle = new Circle(15, Color.YELLOW);
        outCircle.setStroke(Color.GRAY.darker());

        Polygon rangeIcon = new Polygon();
        rangeIcon.getPoints().addAll(new Double[]{
                -5.0, 5.0,
                 8.0, 0.0,
                -5.0, -5.0
        });

        
        rangeIcon.setFill(Color.BLACK);

        this.getChildren().addAll(outCircle, rangeIcon);

        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    
    // Checks if hunter collects the token
    public void collect(Hunter hunter, Pane root) {

        // Convert percentage increase into decimal
        double percentageIncrease = config.getVacuumTokenIncrease() / 100.0;

        
        // Stop method if already collected
        if (isCollected) {
            return;
        }

        
        //Check collision between hunter and token
        if (this.getBoundsInParent().intersects(
                hunter.getBody().getBoundsInParent())) {

            isCollected = true;

            // Remove token from game screen
            root.getChildren().remove(this);

            double currentX = hunter.getVacuumScaleX();
            double currentY = hunter.getVacuumScaleY();

            hunter.setVacuumScale(
                    currentX + percentageIncrease,
                    currentY + percentageIncrease
            );

            // Create timer for temporary effect
            PauseTransition delay =
                    new PauseTransition(Duration.seconds(5));

            
            delay.setOnFinished(e -> {

                hunter.setVacuumScale(
                        currentX,
                        currentY
                );
            });

            
            delay.play();
        }
    }
}