
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.control.Label;

public class HealthBar extends Pane {
	double x,y;
	double amount;
	
	
	
	public HealthBar() {
		this.x = 200;
		this.y = 250;
		this.amount = 100;
	}


	public HealthBar(double amount) {
		
		this.amount = amount;
	
		Rectangle rec1 = new Rectangle(60, 200); 
        rec1.setStroke(Color.BLACK);
        rec1.setStrokeWidth(30);
        rec1.setFill(Color.TRANSPARENT);
        rec1.setLayoutX(700);
        rec1.setLayoutY(50);
        
        Rectangle rec2 = new Rectangle(60, 200);
        rec2.setFill(Color.RED.darker());
        rec2.setLayoutX(700);
        rec2.setLayoutY(50);

        
        Font spooky = Font.loadFont(getClass().getResourceAsStream("Spooky.ttf"), 15);
        Label helLabel = new Label("HEALTH:");
        helLabel.setFont(spooky); 
        helLabel.setTextFill(Color.BLACK); 
        helLabel.setLayoutX(690);
        helLabel.setLayoutY(-5);
        
        
        this.getChildren().addAll(rec1, rec2,helLabel);
		
	}

}