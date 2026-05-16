import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PlayableArea extends Pane{
private double width ,height;
public Rectangle pArea;

 public PlayableArea(double width,double height,Scene scene) {
	 
	 this.width=width;
	 this.height=height;
	 
	 pArea=new Rectangle(0,0,width,height);
	 //pArea.setTranslateX(-width/2);
	 //pArea.setTranslateY(-height/2);
	 pArea.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(width/2));//fza
	 pArea.layoutYProperty().bind(scene.heightProperty().divide(2).add(25).subtract(height/2));//fza
	 pArea.setFill(Color.TRANSPARENT);
	 pArea.setStroke(Color.RED);
	 this.getChildren().add(pArea);
	 this.pArea = pArea;
	 
 }
 
 







}
