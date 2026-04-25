import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ScoreTime extends Pane {
    int counter = 0;
    
    public ScoreTime() {
    	
    	Label label = new Label("10");
        Timeline timeline = new Timeline();
        
        // 1 saniyede bir çalışacak KeyFrame
        KeyFrame kf = new KeyFrame(Duration.seconds(1), event -> {
            counter--;
            label.setText(String.valueOf(counter));
            if (counter <= 0) {
                timeline.stop();
                label.setText("Bitti!");
            }
        });
        
        timeline.getKeyFrames().add(kf);
        timeline.setCycleCount(10); // 10 kez çalış
        timeline.play();
    	
    }
    
 
}
