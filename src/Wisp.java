

import java.util.Random;

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Wisp extends Entity {
 

 private Group rotatingGroup;
 private Rectangle[] recs;
 private double rotationOffset=0;


	public Wisp(double x,double y,boolean isVisible) {
		
		this.dx = (random.nextDouble() * 2.5 ) * (random.nextBoolean() ? 1 : -1);
        this.dy = (random.nextDouble() * 2.5 ) * (random.nextBoolean() ? 1 : -1);
		
		this.x=x;
		this.y=y;
		this.isVisible=isVisible;

		this.shape =new Group();
		  Circle head = new Circle(0, 0, 20);
	        head.setFill(Color.GREENYELLOW);

	        
	        Circle leftEye = new Circle(-8, -6, 5);
	        leftEye.setFill(Color.RED);
	        
	        Circle rightEye = new Circle(8, -6, 5);
	        rightEye.setFill(Color.RED);
	        rotatingGroup = new Group();
	        double r = 40;
	        this.recs= new Rectangle[]{
	        		new Rectangle(-4,-r-12.5,8,25),
	        		new Rectangle(r*Math.cos(Math.PI/6)-4,-r*Math.sin(Math.PI/6)-10,8,20),
	        		new Rectangle(r*Math.cos(Math.PI/6)-4,r*Math.sin(Math.PI/6)-10,8,20),
	        		new Rectangle(-4,r-10,8,20),
	        		new Rectangle(-r*Math.cos(Math.PI/6)-4,r*Math.sin(Math.PI/6)-10,8,20),
	        		new Rectangle(-r*Math.cos(Math.PI/6)-4,-r*Math.sin(Math.PI/6)-10,8,20),
	        	
	        };
	        
	        for (int i=0;i<6;i++) {
	        recs[i].setFill(Color.GREENYELLOW);
	        recs[i].setArcHeight(20);
	        recs[i].setArcWidth(15);
	        }
	        
	        for(int i=1;i<6;i++) {
	        recs[i].setRotate(i*60);
	        }
	        
	       
	        rotatingGroup.getChildren().addAll(recs);
	        shape.getChildren().addAll(head,leftEye,rightEye,rotatingGroup);
	        shape.setLayoutX(x);
	        shape.setLayoutY(y);
	
	        
	        shape.setVisible(isVisible);

	}
	 
	

       public void updateAnimation() {
    	   rotationOffset += 2;
    	    rotatingGroup.setRotate(rotationOffset);
       }
	
}
