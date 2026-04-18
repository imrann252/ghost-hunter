
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
public class Ripper  {
	private double x;
	private double y;
	public Ripper(double x,double y) {
		this.x=x;
		this.y=y;
	
		Polygon spikyBody= new Polygon();
		spikyBody.getPoints().addAll(new Double[] {0.0, -20.0, 
			    5.0, -5.0,    
			    20.0, -5.0,   
			    7.0, 3.0,    
			    12.0, 18.0,   
			    0.0, 8.0,     
			    -12.0, 18.0,  
			    -7.0, 3.0,    
			    -20.0, -5.0,  
			    -5.0, -5.0 });
		
		spikyBody.setFill(Color.PURPLE);
		spikyBody.setStroke(Color.BLACK);
		
		Circle circle=new Circle(this.x,this.y);
		
		
	}
}
