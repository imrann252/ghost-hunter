import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlayableArea extends Pane{
private double x,y;
private double width ,height;

 public PlayableArea(double x,double y,double width,double height) {
	 this.x=x;
	 this.y=y;
	 this.width=width;
	 this.height=height;
	 
	 Rectangle pArea=new Rectangle(x,y,width,height);
	 pArea.setFill(Color.TRANSPARENT);
	 pArea.setStroke(Color.RED);
	 this.getChildren().add(pArea);
 }







}
