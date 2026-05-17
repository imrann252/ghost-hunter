import java.util.Random;

import javafx.scene.Group;
//Imran_Akar_150124087
//Purpose:It is the abstract base class that contains the common properties of all moving elements in the game (Ghost, Ripper, Wisp) and forms the foundation for a polymorphic structure.
public abstract class Entity {
    
	// Cartesian coordinates for tracking position
	double x, y,z;
	
	// Graphical container holding JavaFX primitive shapes
    Group shape;
    
   // Visibility state under player perception triggers
	boolean isVisible;
	
	// Velocity vectors applied on every frame cycle
	public double dx,dy;
	Random random = new Random();
	
	
   public Entity() {
		
	}
	public Entity(double x, double y,boolean isVisible) {
		this.x=x;
		this.y=y;
	    this.isVisible=isVisible;
	  
	}
	
	
	
	//Toggles visibility state for both logic and JavaFX graphics tree.
	 public void setVisible(boolean visibility) {
		 this.isVisible = visibility; 
		 if (this.shape != null) {
		        this.shape.setVisible(visibility); 	   
		        }
	 }
	 
	 //Returns the graphical group to render and manipulate the monster's appearance.
	 public Group getShape() {
		 return shape;
	 }
	 
	 void move(double minX, double minY, double maxX, double maxY) {
		// Updates entity position and handles border collisions with a 15px radius.
	     //Inverts velocity vectors upon boundary intersection to keep entities inside.
		    this.shape.setLayoutX(this.shape.getLayoutX() + dx);
		    this.shape.setLayoutY(this.shape.getLayoutY() + dy);

		    double radius = 15; 

		    // Left boundary collision
		    if (this.shape.getLayoutX() - radius <= minX) {
		        dx = Math.abs(dx); 
		        this.shape.setLayoutX(minX + radius); 
		        
		    } 
		   // Right boundary collision
		    else if (this.shape.getLayoutX() + radius >= maxX) {
		        dx = -Math.abs(dx); 
		        this.shape.setLayoutX(maxX - radius);
		    }

		 // Top boundary collision
		    if (this.shape.getLayoutY() - radius <= minY) {
		        dy = Math.abs(dy); 
		        this.shape.setLayoutY(minY + radius);
		        
		    } 
		 // Bottom boundary collision
		    else if (this.shape.getLayoutY() + radius >= maxY) {
		        dy = -Math.abs(dy); 
		        this.shape.setLayoutY(maxY - radius);
		    }
		}
}
