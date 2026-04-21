

import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Wisp {
 private Group shape;
	public Wisp(double x,double y) {

		this.shape =new Group();
		  Circle head = new Circle(0, 0, 40);
	        head.setFill(Color.RED);

	        
	        Circle leftEye = new Circle(-10, -6, 5);
	        leftEye.setFill(Color.YELLOW);
	        
	        Circle rightEye = new Circle(10, -6, 5);
	        rightEye.setFill(Color.YELLOW);
	        
	        Rectangle[] recs= {new Rectangle(0,-60,8,25),
	        		new Rectangle(0,60,8,25),
	        		new Rectangle(60,0,8,25),
	        		new Rectangle(-60,0,8,25),
	        		new Rectangle(-40,50,8,25),
	        		new Rectangle(40,50,8,25),
	        };
	        
	        for (int i=0;i<6;i++) {
	        recs[i].setFill(Color.RED);
	        recs[i].setArcHeight(20);
	        recs[i].setArcWidth(15);
	        }
	        for(int i=0;i<6;i++) {
	        recs[i].setRotate((i+1)*60);
	        }
	       
	        
	        shape.getChildren().addAll(head,leftEye,rightEye,recs[0],recs[1],recs[2],recs[3],recs[4],recs[5]);
	        shape.setLayoutX(200);
	        shape.setLayoutY(200);
	       

	}
	
	public Group getShape() {
		return shape;
	}
}
