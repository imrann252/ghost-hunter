
public abstract class Entity {


	double x, y;
	double speedX, speedY;
	boolean isVisible;
	
   public Entity() {
		
	}
	public Entity(double x, double y,double speedX,double speedY,boolean isVisible) {
		this.x=x;
		this.y=y;
		this.speedX=speedX;
	    this.speedY=speedY;
	    this.isVisible=isVisible;
	}
	
	
	void move() {}
	void draw() {}
	void checkBounds(double width, double height) {}

}
