
public abstract class Entity {

	double x, y;
	double speedX, speedY;
	boolean isVisible;
	
	void move() {}
	void draw() {}
	void checkBounds(double width, double height) {}

}
