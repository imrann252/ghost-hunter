import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class HealthBar extends Pane {
	double x,y;
	double currentAmount;
	double maxHeight = 250; 
	double maxAmount = 100;
	Rectangle rec2;
	
	
	public HealthBar() {
		this.x = 0;
		this.y = 0;
		this.currentAmount = 100;
	}

	public HealthBar(double x, double y, double currentAmount, Scene scene) {
		
		this.x=x;
		this.y=y;
		this.currentAmount = currentAmount;
	
		Rectangle rec1 = new Rectangle(50, 250); 
        rec1.setStroke(Color.BLACK);
        rec1.setStrokeWidth(30);
        rec1.setLayoutX(0);
        rec1.setLayoutY(80);
        
        Rectangle rec2 = new Rectangle(50, 250);
        rec2.setFill(Color.RED.darker());
        rec2.setLayoutX(0);
        rec2.setLayoutY(80);
        this.rec2=rec2;
        
        Font spooky = Font.loadFont(getClass().getResourceAsStream("Spooky.ttf"), 14);
        Label helLabel = new Label("HEALTH:");
        helLabel.setFont(spooky); 
        helLabel.setTextFill(Color.WHITE); 
        helLabel.setLayoutX(-10);
        helLabel.setLayoutY(25);
        
        
        this.getChildren().addAll(rec1, rec2,helLabel);
        
        this.setLayoutX(x);
        this.setLayoutY(y);
        
		this.layoutXProperty().bind(scene.widthProperty().subtract(this.widthProperty().add(10)));
		this.scaleXProperty().bind(scene.widthProperty().divide(1300));//fza
	 	this.scaleYProperty().bind(scene.heightProperty().divide(800));//fza

		
	}
	
	public void setBar(double currentAmount) {
	     
	    double newHeight = (currentAmount / this.maxAmount) * this.maxHeight;
	    this.rec2.setHeight(newHeight);
	    this.rec2.setLayoutY(50 + (this.maxHeight - newHeight)); 
	}

}

