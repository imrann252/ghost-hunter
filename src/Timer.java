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

    public int second; 
    public Label timerLabel; 
    public Timeline timeline; 
    Scene scene; 

    //constructor,creates new timer label and add in pane.
    public Timer(int second, Scene scene) {

        this.scene = scene; 
        this.second = second; 

        Font bloody = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"),25); 

        timerLabel = new Label(); 
        timerLabel.setFont(bloody); 
        timerLabel.setTextFill(Color.WHITE);

        this.getChildren().add(timerLabel); 

        setupTimer(second); 

        this.updateLabel(); 
        this.start(); 
    }

    // Sets up the countdown logic
    private void setupTimer(int second) {

        this.second = second; 

        KeyFrame kf = new KeyFrame(Duration.seconds(1), event -> {

            if (this.second > 0) {
                this.second--; 
                updateLabel(); 
            } else {
                timeline.stop(); 
            }
        });

        timeline = new Timeline(kf); 
        timeline.setCycleCount(Timeline.INDEFINITE); 
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

        int min = second / 60;
        int sec = second % 60; 
        
        timerLabel.setText(String.format("%02d:%02d", min, sec)); 
        timerLabel.setLayoutY(45);
        timerLabel.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(15)); 
    }

    // Returns remaining time
    public int getRemainingTime() {
        return this.second;
    }
}