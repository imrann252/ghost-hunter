
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
//Imran_Akar_150124087
//Purpose: Represents the aggressive Ripper enemy type
public class Ripper extends Entity {
    
    //Constructor that defines random velocities, compiles the aggressive multi-point skin, and anchors layout positions.
    public Ripper(double x, double y,boolean isVisible) {
    	
    	this.dx = (random.nextDouble() * 4.0) - 2.0;
        this.dy = (random.nextDouble() * 4.0) - 2.0;
    	this.isVisible=isVisible;
        shape = new Group();
	     
       
        
        Polygon body = new Polygon();
        //Adds X and Y coordinates sequentially to draw the spiky border layout.
        body.getPoints().addAll(new Double[]{
                35.0,  0.0,   
                20.0,  6.0,   
                28.0, 18.0,   
                12.0, 14.0,   
                18.0, 28.0,   
                 6.0, 20.0,   
                 0.0, 35.0,  
                -6.0, 20.0,   
               -18.0, 28.0,   
               -12.0, 14.0,   
               -28.0, 18.0,   
               -20.0,  6.0,   
               -35.0,  0.0,   
               -20.0, -6.0,  
               -28.0,-18.0,   
               -12.0,-14.0,   
               -18.0,-28.0,   
                -6.0,-20.0,   
                 0.0,-35.0,  
                 6.0,-20.0,   
                18.0,-28.0,   
                12.0,-14.0,   
                28.0,-18.0,   
                20.0, -6.0    
           });
        
        body.setFill(Color.PURPLE);  
        body.setStroke(Color.BLACK);

        //Head and eye properties
        Circle head = new Circle(0, 0, 15);
        head.setFill(Color.BLACK);
        Circle leftEye = new Circle(-5, -3, 2.5);
        leftEye.setFill(Color.RED); 
        Circle rightEye = new Circle(5, -3, 2.5);
        rightEye.setFill(Color.RED);

        //Composes the multi-layered visual nodes into the main entity shape group.
        shape.getChildren().addAll(body, head, leftEye, rightEye);

       
        shape.setLayoutX(x);
        shape.setLayoutY(y);
        
        shape.setVisible(isVisible);
	     }
}