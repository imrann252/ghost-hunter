import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

//Fatma_Zehra_Aktas_150124016
//Purpose: This class creates a Health Token object. When collected by the hunter,
//it restores the hunter's health and disappears from the game screen.

public class HealthToken extends Pane {

    // Token position
    private double x, y;

    // Prevents collecting the token multiple times
    private boolean isCollected = false;

    // Amount of health restored by this token
    private int restorationAmount;

    
    // Constructor
    // x and y determine token position
    public HealthToken(double x, double y) {

        // Store coordinates
        this.x = x;
        this.y = y;

        // Get restoration amount from config file
        this.restorationAmount =
                ConfigLoader.getInstance()
                            .getHealthTokenIncrease();

        
        // Main circular token background
        Circle base = new Circle(15, Color.YELLOW);

        // Border color
        base.setStroke(Color.GRAY.darker());

        
        // Create plus (+) symbol for health design

        // Vertical rectangle of plus sign
        Rectangle verticalBar =
                new Rectangle(-2, -8, 4, 16);

        // Horizontal rectangle of plus sign
        Rectangle horizontalBar =
                new Rectangle(-8, -2, 16, 4);

        
        // Set plus sign color
        verticalBar.setFill(Color.RED);
        horizontalBar.setFill(Color.RED);

        
        // Add all shapes into pane
        this.getChildren().addAll(
                base,
                verticalBar,
                horizontalBar
        );

        
        // Set token position on screen
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    
    // Checks if hunter collects the token
    public void collect(Hunter hunter,
                        HealthBar hb,
                        Pane root) {

        // Stop method if token already collected
        if (isCollected)
            return;

        
        // Check collision between hunter and token
        if (this.getBoundsInParent().intersects(
                hunter.getShape()
                      .getBoundsInParent())) {

            // Mark token as collected
            isCollected = true;

            
            // Restore hunter health
            hb.setBar(restorationAmount);

            
            // Remove token from game screen
            root.getChildren().remove(this);
        }
    }

    
    // Returns restoration amount
    public int getRestorationAmount() {
        return restorationAmount;
    }

    
    // Returns Y coordinate
    public double getY() {
        return y;
    }

    
    // Updates Y coordinate
    public void setY(double y) {
        this.y = y;
    }

    
    // Returns X coordinate
    public double getX() {
        return x;
    }

    
    // Updates X coordinate
    public void setX(double x) {
        this.x = x;
    }
}