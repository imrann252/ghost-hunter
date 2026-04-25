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
	 }
	 public Group getShape() {
		 return shape;
	 }
		void move(double minX, double minY, double maxX, double maxY) {
			 
			 this.shape.setLayoutX(this.shape.getLayoutX() + dx);
		        this.shape.setLayoutY(this.shape.getLayoutY() + dy);

		        // Kenarlara çarpınca simetrik olarak sek (Bounce Back) 
		        if (this.shape.getLayoutX() - 15 <= minX || this.shape.getLayoutX() + 15 >= maxX) {
		            dx *= -1; // Yatayda yön değiştir
		        }
		        if (this.shape.getLayoutY() - 15 <= minY || this.shape.getLayoutY() + 15 >= maxY) {
		            dy *= -1; // Dikeyde yön değiştir
		        }
		        
			}

}
