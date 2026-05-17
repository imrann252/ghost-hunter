import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

// Fatma_Zehra_Aktas_150124016
// Purpose: This class creates and manages an in-game countdown timer.
// It updates the remaining time every second and displays it on the screen.

public class Timer extends Pane {

    public int second; // Remaining time in seconds
    public Label timerLabel; // Label used to display the timer
    public Timeline timeline; // Timeline for countdown logic
    Scene scene; // Reference to the game scene

    public Timer(int second, Scene scene) {

        this.scene = scene; // Store scene reference
        this.second = second; // Initialize remaining time

        Font bloody = Font.loadFont(
                getClass().getResourceAsStream("Bloody.ttf"),
                25
        ); // Load custom font

        timerLabel = new Label(); // Create timer label
        timerLabel.setFont(bloody); // Set font style
        timerLabel.setTextFill(Color.WHITE); // Set text color

        this.getChildren().add(timerLabel); // Add label to pane

        setupTimer(second); // Initialize timer logic

        this.updateLabel(); // Update label initially
        this.start(); // Start timer automatically
    }

    // Sets up the countdown logic
    private void setupTimer(int second) {

        this.second = second; // Set initial time

        KeyFrame kf = new KeyFrame(Duration.seconds(1), event -> {

            if (this.second > 0) {
                this.second--; // Decrease time by 1 second
                updateLabel(); // Update UI
            } else {
                timeline.stop(); // Stop timer when it reaches zero
            }
        });

        timeline = new Timeline(kf); // Create timeline
        timeline.setCycleCount(Timeline.INDEFINITE); // Run indefinitely
    }

    // Starts the timer
    public void start() {
        timeline.play();
    }

    // Pauses the timer
    public void pause() {
        timeline.pause();
    }

    // Stops the timer and resets it
    public void stop() {
        timeline.stop();
        second = 0;
        updateLabel();
    }

    // Updates the timer label UI
    private void updateLabel() {

        int min = second / 60; // Convert seconds to minutes
        int sec = second % 60; // Remaining seconds

        timerLabel.setText(String.format("%02d:%02d", min, sec)); // Format time

        timerLabel.setLayoutY(45); // Set vertical position

        timerLabel.layoutXProperty().bind(
                scene.widthProperty().divide(2).subtract(15)
        ); // Center horizontally
    }

    // Returns remaining time
    public int getRemainingTime() {
        return this.second;
    }
}