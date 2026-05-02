
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Ghost extends Entity {

    public Ghost(double x, double y, boolean isVisible) {
       
        super(x, y, isVisible);

        this.dx = (random.nextDouble() * 2 + 1) * (random.nextBoolean() ? 1 : -1);
        this.dy = (random.nextDouble() * 2 + 1) * (random.nextBoolean() ? 1 : -1);
        
        this.isVisible = isVisible;
        shape = new Group();

       
        Arc head = new Arc(0, 0, 15, 15, 0, 180);
        head.setType(ArcType.ROUND);
        head.setFill(Color.WHITE);

       
        Rectangle bodyBase = new Rectangle(-15, -1, 30, 16);
        bodyBase.setFill(Color.WHITE);

        
        Circle leftEye = new Circle(-5, -2, 2.5, Color.BLACK.darker());
        Circle rightEye = new Circle(5, -2, 2.5, Color.BLACK.darker());

       
        shape.getChildren().addAll(head, bodyBase, leftEye, rightEye);

       
        shape.setOpacity(0.6);

        
        shape.setLayoutX(x);
        shape.setLayoutY(y);

       
        shape.setVisible(isVisible);
        
 
    }
}
