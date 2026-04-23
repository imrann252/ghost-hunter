
public abstract class Entity {


	double x, y,z;

	boolean isVisible;
	
	
   public Entity() {
		
	}
	public Entity(double x, double y,boolean isVisible) {
		this.x=x;
		this.y=y;
	    this.isVisible=isVisible;
	}
	
	
	void move() {};
	void checkBounds(double width, double height) {};
	
	 public void setVisible(boolean visibility) {
		 this.isVisible = visibility;
	 }

}
