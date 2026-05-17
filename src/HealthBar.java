import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.scene.control.Label;

//Fatma_Zehra_Aktas_150124016
//Purpose: This class creates and manages the player's health bar. it visually displays the hunter's current health and updates dynamically during the game.

public class HealthBar extends Pane {

    private ConfigLoader config = ConfigLoader.getInstance();
    private double maxHealth = config.getMaxHealth();
    private double currentHealth = maxHealth;
    private double maxHeight = 250;

    Rectangle rec2;

    //constructor, creates health bar and add in pane.
    public HealthBar(Scene scene) {

        // Background rectangle of the health bar
        Rectangle rec1 = new Rectangle(50, 250);

        rec1.setStroke(Color.BLACK);
        rec1.setStrokeWidth(30);
        rec1.setLayoutX(0);
        rec1.setLayoutY(80);

        Rectangle rec2 = new Rectangle(50, 250);
        rec2.setFill(Color.RED.darker());
        rec2.setLayoutX(0);
        rec2.setLayoutY(80);
        this.rec2 = rec2;

        Font bloody = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"),30);

        Label helLabel = new Label("HEALTH:");
        helLabel.setFont(bloody);
        helLabel.setTextFill(Color.WHITE);
        helLabel.setLayoutX(-10);
        helLabel.setLayoutY(25);

        
        this.getChildren().addAll(rec1, rec2, helLabel);
        this.setLayoutX(0);
        this.setLayoutY(0);
        this.setBar(0);

      
        this.layoutXProperty().bind(scene.widthProperty().subtract(this.widthProperty().add(10)));
        
        this.scaleXProperty().bind(scene.widthProperty().divide(1300));
        this.scaleYProperty().bind(scene.heightProperty().divide(800));
    }

    
    // Updates the health bar
    public void setBar(double amount) {

        currentHealth += amount;

        // Prevent health from exceeding maximum
        if (currentHealth > maxHealth)
            currentHealth = maxHealth;

        // Prevent health from going below zero
        if (currentHealth < 0)
            currentHealth = 0;
        
        double newHeight = (currentHealth / maxHealth) * maxHeight;

        this.rec2.setHeight(newHeight);
        this.rec2.setLayoutY(80 + (maxHeight - newHeight));
        
    }

    
    // Returns current health value
    public double getCurrentHealth() {
        return currentHealth;
    }
}