import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.scene.control.Label;

//Fatma_Zehra_Aktas_150124016
//Purpose: This class creates and manages the player's health bar. it visually displays the hunter's current health and updates dynamically during the game.

public class HealthBar extends Pane {

    // Access configuration values
    private ConfigLoader config = ConfigLoader.getInstance();

    // Maximum health value from config file
    private double maxHealth = config.getMaxHealth();

    // Current health starts at maximum health
    private double currentHealth = maxHealth;

    // Maximum visual height of the health bar
    private double maxHeight = 250;

    
    // Rectangle representing the current health amount
    Rectangle rec2;

    
    public HealthBar(Scene scene) {

        // Background rectangle of the health bar
        Rectangle rec1 = new Rectangle(50, 250);

        // Border color
        rec1.setStroke(Color.BLACK);

        // Border thickness
        rec1.setStrokeWidth(30);

        // Position on screen
        rec1.setLayoutX(0);
        rec1.setLayoutY(80);

        
        // Foreground rectangle showing remaining health
        Rectangle rec2 = new Rectangle(50, 250);

        // Fill color of health bar
        rec2.setFill(Color.RED.darker());

        // Position on screen
        rec2.setLayoutX(0);
        rec2.setLayoutY(80);

        // Save rectangle reference for updates
        this.rec2 = rec2;

        
        // Load custom font
        Font bloody =
                Font.loadFont(
                        getClass().getResourceAsStream("Bloody.ttf"),
                        30);

        
        // Create HEALTH label
        Label helLabel = new Label("HEALTH:");

        // Apply font
        helLabel.setFont(bloody);

        // Text color
        helLabel.setTextFill(Color.WHITE);

        // Label position
        helLabel.setLayoutX(-10);
        helLabel.setLayoutY(25);

        
        // Add all components into pane
        this.getChildren().addAll(rec1, rec2, helLabel);

        
        // Set pane position
        this.setLayoutX(0);
        this.setLayoutY(0);

        
        // Initialize health bar
        this.setBar(0);

        
        // Keep health bar attached to right side of screen
        this.layoutXProperty().bind(
                scene.widthProperty()
                        .subtract(this.widthProperty().add(10)));

        
        // Scale health bar according to screen width
        this.scaleXProperty().bind(
                scene.widthProperty().divide(1300));

        
        // Scale health bar according to screen height
        this.scaleYProperty().bind(
                scene.heightProperty().divide(800));
    }

    
    // Updates the health bar
    // amount can be positive (heal) or negative (damage)
    public void setBar(double amount) {

        // Change current health
        currentHealth += amount;

        
        // Prevent health from exceeding maximum
        if (currentHealth > maxHealth)
            currentHealth = maxHealth;

        
        // Prevent health from going below zero
        if (currentHealth < 0)
            currentHealth = 0;

        
        // Calculate new visual height
        double newHeight =
                (currentHealth / maxHealth) * maxHeight;

        
        // Update rectangle height
        this.rec2.setHeight(newHeight);

        
        // Move rectangle upward so it fills from bottom to top
        // 80 is the original Y position
        this.rec2.setLayoutY(
                80 + (maxHeight - newHeight));
    }

    
    // Returns current health value
    public double getCurrentHealth() {
        return currentHealth;
    }
}