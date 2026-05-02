import java.util.Random;

import javafx.scene.Group;

public abstract class Entity {

	double x, y,z;
    Group shape;
	boolean isVisible;
	public double dx,dy;
	Random random = new Random();
	
	
   public Entity() {
		
	}
	public Entity(double x, double y,boolean isVisible) {
		this.x=x;
		this.y=y;
	    this.isVisible=isVisible;
	  
	}
	
	
	
	void checkBounds(double width, double height) {};
	
	 public void setVisible(boolean visibility) {
		 this.isVisible = visibility; 
		 if (this.shape != null) {
		        this.shape.setVisible(visibility); // Group sınıfındaki setVisible() methodunu çağırır görünürlüğü düzenler		   
		        }
	 }
	 
	 public Group getShape() {
		 return shape;
	 }
	 
	 void move(double minX, double minY, double maxX, double maxY) {
		 
		    this.shape.setLayoutX(this.shape.getLayoutX() + dx);
		    this.shape.setLayoutY(this.shape.getLayoutY() + dy);

		    double radius = 15; 

		 
		    if (this.shape.getLayoutX() - radius <= minX) {
		        dx = Math.abs(dx); //sag
		        this.shape.setLayoutX(minX + radius); 
		        
		    } else if (this.shape.getLayoutX() + radius >= maxX) {
		        dx = -Math.abs(dx); // sol
		        this.shape.setLayoutX(maxX - radius);
		    }

		    
		    if (this.shape.getLayoutY() - radius <= minY) {
		        dy = Math.abs(dy); // asagi
		        this.shape.setLayoutY(minY + radius);
		        
		    } else if (this.shape.getLayoutY() + radius >= maxY) {
		        dy = -Math.abs(dy); // yukari
		        this.shape.setLayoutY(maxY - radius);
		    }
		}
}
