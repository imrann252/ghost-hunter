import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ScoreCounter extends Pane {

	public double score = 0 ;
	public double point = 0;
    public Label scoreLabel;
    Scene scene;

    public ScoreCounter(Scene scene) {
    	
    	this.scene = scene;

    	Font bloody = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 25);
        scoreLabel = new Label();
        scoreLabel.setFont(bloody); 
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setLayoutY(20);
        scoreLabel.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(35));


        
        this.getChildren().add(scoreLabel);
        this.addScore(point);

    }

    public void addScore(double point) {
    	this.point = point;
    	this.score+=this.point;
    	scoreLabel.setText(String.format("SCORE: %02.0f", this.score));
    	
    }
    

}