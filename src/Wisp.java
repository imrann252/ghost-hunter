

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Wisp extends Entity {
 

 private Group shape;
 private Group rotatingGroup;
 private Rectangle[] recs;
 private double rotationOffset=0;
	public Wisp(double x,double y,boolean isVisible) {
		
		this.x=x;
		this.y=y;
		this.isVisible=isVisible;

		this.shape =new Group();
		  Circle head = new Circle(0, 0, 40);
	        head.setFill(Color.RED);

	        
	        Circle leftEye = new Circle(-10, -6, 5);
	        leftEye.setFill(Color.YELLOW);
	        
	        Circle rightEye = new Circle(10, -6, 5);
	        rightEye.setFill(Color.YELLOW);
	        rotatingGroup = new Group();
	        double r = 70;
	        this.recs= new Rectangle[]{
	        		new Rectangle(-4,-r-12.5,8,25),
	        		new Rectangle(r*Math.cos(Math.PI/6)-4,-r*Math.sin(Math.PI/6)-12.5,8,25),
	        		new Rectangle(r*Math.cos(Math.PI/6)-4,r*Math.sin(Math.PI/6)-12.5,8,25),
	        		new Rectangle(-4,r-12.5,8,25),
	        		new Rectangle(-r*Math.cos(Math.PI/6)-4,r*Math.sin(Math.PI/6)-12.5,8,25),
	        		new Rectangle(-r*Math.cos(Math.PI/6)-4,-r*Math.sin(Math.PI/6)-12.5,8,25),
	        	
	        };
	        
	        for (int i=0;i<6;i++) {
	        recs[i].setFill(Color.RED);
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
	       

	}
	 
	 @Override
		void move() {
		 double dx = (Math.random() - 0.5) * 2; 
		 double dy = (Math.random() - 0.5) * 2;
		
		     this.x += dx; // Her karede 1 piksel sağa git
	        this.y +=dy; // Her karede yarım piksel aşağı git
	     
	        this.shape.setLayoutX(this.x);
	        this.shape.setLayoutY(this.y);
	        
		}

		@Override
		public void setVisible(boolean visibility) {
			// TODO Auto-generated method stub
			super.setVisible(visibility);
		}

       public void updateAnimation() {
    	   rotationOffset += 2;
    	    rotatingGroup.setRotate(rotationOffset);
       }
	public Group getShape() {
		return shape;
	}
}
