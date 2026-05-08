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

public class EyeToken extends StackPane implements Collectible {
    private boolean isCollected = false;

    // Range Token genelde 40-50 px civarı olur, varsayılanı 50 yapalım
    public EyeToken(double x, double y) {

        // Göz Akı 
        Ellipse ak = new Ellipse(10, 6);
        ak.setFill(Color.YELLOW.brighter());
        ak.setStroke(Color.BLACK);
        ak.setStrokeWidth(1);

        // İris
        Circle iris = new Circle(4);
        iris.setFill(Color.SKYBLUE.darker());
        iris.setStroke(Color.BLACK);
        iris.setStrokeWidth(0.5);

        // Göz Bebeği 
        Circle bebek = new Circle(2);
        bebek.setFill(Color.BLACK);
        
        this.setAlignment(Pos.CENTER);
        
        Circle token = new Circle(15, Color.YELLOW);
        token.setStroke(Color.GRAY.darker());

        this.getChildren().addAll(token,ak, iris, bebek);
        
       
        this.setLayoutX(x);
        this.setLayoutY(y);
        
    }
    
    @Override
    public void collect(Hunter hunter, ArrayList<Entity> ents, Pane root) {

        if (isCollected) {
            return;
        }

        if (this.getBoundsInParent().intersects(hunter.getShape().getBoundsInParent())) {

            isCollected = true;
            root.getChildren().remove(this);

            for (Entity ent : ents) {

                ent.setVisible(true);

                PauseTransition delay =
                        new PauseTransition(Duration.seconds(5));

                delay.setOnFinished(e -> {
                    ent.setVisible(false);
                });

                delay.play();
            }
        }
    }


    
}
        



