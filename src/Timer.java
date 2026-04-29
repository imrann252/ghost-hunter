import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Timer extends Pane {

    public int second ;
    public Label timerLabel;
    public Timeline timeline;
    Scene scene;

    public Timer(int second, Scene scene) {
    	
    	this.scene = scene;
    	this.second = second ;
    	
    	Font bloody = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 25);
        Label scoreLabel = new Label("SCORE:");
        scoreLabel.setFont(bloody); 
        scoreLabel.setTextFill(Color.WHITE); 
        scoreLabel.setLayoutY(20);
        scoreLabel.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(20));
        
        timerLabel = new Label();
        timerLabel.setFont(bloody);
        timerLabel.setTextFill(Color.WHITE);
        
        
        this.getChildren().addAll(scoreLabel,timerLabel);

        setupTimer(second); 
        
        this.updateLabel();
        this.start();
        
        
    }

    private void setupTimer(int second) {

    	this.second = second;
   
        KeyFrame kf = new KeyFrame(Duration.seconds(1), event -> {
            this.second--;
            
            if (this.second > 0) {
                this.second--;
                updateLabel();  
            } 
            else {
                timeline.stop();
            }
        });

        timeline = new Timeline(kf);
        timeline.setCycleCount(Timeline.INDEFINITE);
    }

    public void start() {
        timeline.play();
    }

    public void pause() {
        timeline.pause();
    }

    public void stop() {
        timeline.stop();
        second = 0;
        updateLabel();
    }

    private void updateLabel() {
    	int min = second / 60;
    	int sec = second % 60;
    	timerLabel.setText(String.format("%02d:%02d", min, sec));
    	timerLabel.setLayoutY(45);
        timerLabel.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(15));
    }
    

}