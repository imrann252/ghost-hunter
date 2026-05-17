import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
//Imran_Akar_150124087
//Manages the bounded red gameplay box and binds its position to keep it perfectly centered on screen.
public class PlayableArea extends Pane{
private double width ,height;
public Rectangle pArea;


//Sets the red frame shape and dynamically aligns it with the screen size.
 public PlayableArea(double width,double height,Scene scene) {
	 
	 this.width=width;
	 this.height=height;
	 
	 //Binds the layout X and Y properties to lock the container dead-center relative to window monitors.
	 pArea=new Rectangle(0,0,width,height);
	 pArea.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(width/2));
	 pArea.layoutYProperty().bind(scene.heightProperty().divide(2).add(25).subtract(height/2));
	 pArea.setFill(Color.TRANSPARENT);
	 pArea.setStroke(Color.RED);
	 this.getChildren().add(pArea);
	 this.pArea = pArea;
	 
 }
 
 







}
