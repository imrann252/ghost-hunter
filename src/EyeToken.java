import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Circle;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.geometry.Pos;


//Fatma_Zehra_Aktas_150124016
//Purpose: This class creates an Eye Token object. When the hunter collects this token, 
//all invisible entities become visible for a limited amount of time.

public class EyeToken extends StackPane {

    private boolean isCollected = false;
    private ConfigLoader config = ConfigLoader.getInstance();

    
    //constructor,creates eye token icon and add in stack pane.
    public EyeToken(double x, double y) {

        // Eye white (sclera)
        Ellipse ak = new Ellipse(10, 6);
        ak.setFill(Color.YELLOW.brighter());
        ak.setStroke(Color.BLACK);
        ak.setStrokeWidth(1);

        
        // Iris of the eye
        Circle iris = new Circle(4);
        iris.setFill(Color.SKYBLUE.darker());
        iris.setStroke(Color.BLACK);
        iris.setStrokeWidth(0.5);
        
        // Pupil of the eye
        Circle bebek = new Circle(2);
        bebek.setFill(Color.BLACK);

        this.setAlignment(Pos.CENTER);

        Circle token = new Circle(15, Color.YELLOW);
        token.setStroke(Color.GRAY.darker());

        this.getChildren().addAll(token, ak, iris, bebek);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    
    // Checks if hunter collects the token
    public void collect(Hunter hunter, ArrayList<Entity> ents, Pane root) {

        
        if (isCollected) {
            return;
        }

        
        // Check collision between hunter and token
        if (this.getBoundsInParent().intersects(
                hunter.getBody().getBoundsInParent())) {

            
            isCollected = true;

            
            root.getChildren().remove(this);

            for (Entity ent : ents) {

                ent.setVisible(true);

                // Create timer for visibility duration
                PauseTransition delay =
                        new PauseTransition(
                                Duration.seconds(config.getEyeTokenDuration()));

                delay.setOnFinished(e -> {
                    ent.setVisible(false);
                });

                delay.play();
            }
        }
    }
}