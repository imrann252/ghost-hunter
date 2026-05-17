import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

//Fatma_Zehra_Aktas_150124016
//Purpose: This class creates a Health Token object. When collected by the hunter,
//it restores the hunter's health and disappears from the game screen.

public class HealthToken extends Pane {

    
    private boolean isCollected = false;
    private int restorationAmount;

    
    // Constructor,creates health token icon and add in pane.
    public HealthToken(double x, double y) {


        this.restorationAmount = ConfigLoader.getInstance().getHealthTokenIncrease();

        Circle base = new Circle(15, Color.YELLOW);
        base.setStroke(Color.GRAY.darker());

        
        // Create plus (+) symbol for health design
        Rectangle verticalBar = new Rectangle(-2, -8, 4, 16);
        Rectangle horizontalBar = new Rectangle(-8, -2, 16, 4);

        verticalBar.setFill(Color.RED);
        horizontalBar.setFill(Color.RED);

  
        this.getChildren().addAll(base,verticalBar,horizontalBar);

   
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    
    // Checks if hunter collects the token
    public void collect(Hunter hunter,HealthBar hb,Pane root) {

        if (isCollected)
            return;

        
        // Check collision between hunter and token
        if (this.getBoundsInParent().intersects(hunter.getShape().getBoundsInParent())) {

            isCollected = true;
            hb.setBar(restorationAmount);
            root.getChildren().remove(this);
            
        }
    }
  
    // Returns restoration amount
    public int getRestorationAmount() {
        return restorationAmount;
    }
    
}