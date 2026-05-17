
import java.util.Random;


import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

//Seyma_Nur_Hacimurtazaoglu_150124057
// Purpose: Represents the standard Ghost entity in the game, handling its random initial movement and graphical shape creation.
public class Ghost extends Entity {
//Purpose : Constructor that initializes the Ghost's position, sets a random movement velocity (dx, dy), and constructs its semi-transparent visual body components.
    public Ghost(double x, double y, boolean isVisible) {
       //Calls the constructor of the superclass (Entity) to initialize base coordinates and visibility status.
        super(x, y, isVisible);
        this.dx = (random.nextDouble() * 4.0) - 2.0;
        this.dy = (random.nextDouble() * 4.0) - 2.0;
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
