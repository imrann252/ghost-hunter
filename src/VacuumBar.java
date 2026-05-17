import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.scene.control.Label;

// Fatma_Zehra_Aktas_150124016
// Purpose: This class creates and manages the Vacuum Bar UI.
// It visually represents the hunter's vacuum energy and updates dynamically.

public class VacuumBar extends Pane {

    private ConfigLoader config = ConfigLoader.getInstance(); 
    private double maxAmount = config.getMaxVacuum(); 
    private double currentAmount = maxAmount; 
    private double maxHeight = 250; 
    private Rectangle rec2; 

    //constructor, creates vacuum bar and add in pane.
    public VacuumBar(Scene scene) {

        Rectangle rec1 = new Rectangle(50, maxHeight); 
        rec1.setStroke(Color.BLACK); 
        rec1.setStrokeWidth(30); 
        rec1.setLayoutX(40); 
        rec1.setLayoutY(80); 

        rec2 = new Rectangle(50, maxHeight);
        rec2.setFill(Color.PURPLE.darker()); 
        rec2.setLayoutX(40); 
        rec2.setLayoutY(80); 
   

        Font bloody = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"),30); 
        
        Label vacLabel = new Label("VACUUM:"); 
        vacLabel.setFont(bloody); 
        vacLabel.setTextFill(Color.WHITE); 
        vacLabel.setLayoutX(30); 
        vacLabel.setLayoutY(25); 

        this.getChildren().addAll(rec1, rec2, vacLabel);
        this.setLayoutX(0); 
        this.setLayoutY(0); 

        this.scaleXProperty().bind(scene.widthProperty().divide(1300)); 
        this.scaleYProperty().bind(scene.heightProperty().divide(800)); 
        
    }

    
    // Updates the vacuum bar value
    public void setBar(double amount) {

        currentAmount += amount;

        // Clamp value to maximum
        if (currentAmount > maxAmount)
            currentAmount = maxAmount;

        // Clamp value to minimum
        if (currentAmount < 0)
            currentAmount = 0;

      
        double newHeight = (currentAmount / maxAmount) * maxHeight;
        this.rec2.setHeight(newHeight); 
        this.rec2.setLayoutY(80 + (maxHeight - newHeight));
        
    }

    
    // Returns current vacuum amount
    public double getCurrentAmount() {
        return currentAmount;
    }
}