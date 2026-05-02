
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class VacuumBar extends Pane {
	double x,y;
	double currentAmount;
	double maxHeight = 250; 
	double maxAmount = 100;
	Scene scene;
	Rectangle rec2;
	 
	
	public VacuumBar() {
		this.x = 200;
		this.y = 250;
		this.currentAmount = 100;
	}


	public VacuumBar(double x, double y, double currentAmount,Scene scene) {
		
		this.x=x;
		this.y=y;
		this.currentAmount = currentAmount;

		
	
		Rectangle rec1 = new Rectangle(50, 250); 
        rec1.setStroke(Color.BLACK);
        rec1.setStrokeWidth(30);
        rec1.setLayoutX(40);
        rec1.setLayoutY(80);
        
        
        
        Rectangle rec2 = new Rectangle(50, 250);
        rec2.setFill(Color.PURPLE.darker());
        rec2.setLayoutX(40);
        rec2.setLayoutY(80);
        this.rec2=rec2;

        
        Font spooky = Font.loadFont(getClass().getResourceAsStream("Spooky.ttf"), 15);
        Label vacLabel = new Label("VACUUM:");
        vacLabel.setFont(spooky); 
        vacLabel.setTextFill(Color.WHITE); 
        vacLabel.setLayoutX(30);
        vacLabel.setLayoutY(25);
        
        
        this.getChildren().addAll(rec1, rec2,vacLabel);
        
        this.setLayoutX(x);
        this.setLayoutY(y);
        this.scaleXProperty().bind(scene.widthProperty().divide(1300));//fza
 	   	this.scaleYProperty().bind(scene.heightProperty().divide(800));//fza
		
	}
	
	public void setBar(double currentAmount) {
	     
	    double newHeight = (currentAmount / this.maxAmount) * this.maxHeight;
	    this.rec2.setHeight(newHeight);
	    this.rec2.setLayoutY(50 + (this.maxHeight - newHeight)); 
	}

}
