import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoreCounter extends Pane {

	public double score ;
	public double point;
    public Label timerLabel;
    public Timeline timeline;
    Scene scene;

    public ScoreCounter(double point , Scene scene) {
    	
    	this.scene = scene;
    	this.point = point;
    	

    	Font bloody = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 25);
        Label scoreLabel = new Label();
        scoreLabel.setFont(bloody); 
        scoreLabel.setTextFill(Color.WHITE); 
        scoreLabel.setLayoutY(20);
        scoreLabel.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(20));
        
        timerLabel = new Label();
        timerLabel.setFont(bloody);
        timerLabel.setTextFill(Color.WHITE);
        
        
        this.getChildren().addAll(scoreLabel,timerLabel);

        
        this.updateLabel();
      
        
    }

    private void updateLabel() {
    	score+=point;
    	timerLabel.setText(String.format("SCORE: %d", score));
    	timerLabel.setLayoutY(45);
        timerLabel.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(15));
    }
    

}