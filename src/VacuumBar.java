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

    ConfigLoader config = ConfigLoader.getInstance(); // Access configuration values

    private double maxAmount = config.getMaxVacuum(); // Maximum vacuum value

    private double currentAmount = maxAmount; // Current vacuum amount

    private double maxHeight = 250; // Maximum visual height of the bar

    private Rectangle rec2; // Foreground bar (updated energy bar)

  
    public VacuumBar(Scene scene) {

        Rectangle rec1 = new Rectangle(50, maxHeight); // Background bar

        rec1.setStroke(Color.BLACK); // Border color

        rec1.setStrokeWidth(30); // Thick border

        rec1.setLayoutX(40); // X position

        rec1.setLayoutY(80); // Y position

        rec2 = new Rectangle(50, maxHeight); // Foreground vacuum bar

        rec2.setFill(Color.PURPLE.darker()); // Bar color

        rec2.setLayoutX(40); // X position

        rec2.setLayoutY(80); // Y position

        this.rec2 = rec2; // Store reference for updates

        Font bloody =
                Font.loadFont(
                        getClass().getResourceAsStream("Bloody.ttf"),
                        30
                ); // Load custom font

        Label vacLabel = new Label("VACUUM:"); // Label text

        vacLabel.setFont(bloody); // Set font

        vacLabel.setTextFill(Color.WHITE); // Set text color

        vacLabel.setLayoutX(30); // Label X position

        vacLabel.setLayoutY(25); // Label Y position

        this.getChildren().addAll(rec1, rec2, vacLabel); // Add UI elements to pane

        this.setLayoutX(0); // Pane X position

        this.setLayoutY(0); // Pane Y position

        this.scaleXProperty().bind(scene.widthProperty().divide(1300)); // Scale X with screen size

        this.scaleYProperty().bind(scene.heightProperty().divide(800)); // Scale Y with screen size
    }

    
    // Updates the vacuum bar value
    public void setBar(double amount) {

        currentAmount += amount; // Update current value

        // Clamp value to maximum
        if (currentAmount > maxAmount)
            currentAmount = maxAmount;

        // Clamp value to minimum
        if (currentAmount < 0)
            currentAmount = 0;

        // Calculate new visual height
        double newHeight =
                (currentAmount / maxAmount) * maxHeight;

        this.rec2.setHeight(newHeight); // Update bar height

        // Adjust position so it fills from bottom to top
        this.rec2.setLayoutY(
                80 + (maxHeight - newHeight)
        );
    }

    
    // Returns current vacuum amount
    public double getCurrentAmount() {
        return currentAmount;
    }
}