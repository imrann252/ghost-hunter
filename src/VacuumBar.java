
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.control.Label;

public class VacuumBar extends Pane {
	double x,y;
	double amount;
	
	
	
	public VacuumBar() {
		this.x = 200;
		this.y = 250;
		this.amount = 100;
	}


	public VacuumBar(double x, double y, double amount) {
		
		this.x=x;
		this.y=y;
		this.amount = amount;
	
		Rectangle rec1 = new Rectangle(60, 200); 
        rec1.setStroke(Color.BLACK);
        rec1.setStrokeWidth(30);
        rec1.setFill(Color.TRANSPARENT);
        rec1.setLayoutX(50);
        rec1.setLayoutY(50);
        
        Rectangle rec2 = new Rectangle(60, 200);
        rec2.setFill(Color.PURPLE.darker());
        rec2.setLayoutX(50);
        rec2.setLayoutY(50);

        
        Font spooky = Font.loadFont(getClass().getResourceAsStream("Spooky.ttf"), 15);
        Label vacLabel = new Label("VACUUM:");
        vacLabel.setFont(spooky); 
        vacLabel.setTextFill(Color.BLACK); 
        vacLabel.setLayoutX(40);
        vacLabel.setLayoutY(-5);
        
        
        this.getChildren().addAll(rec1, rec2,vacLabel);
        
        this.setLayoutX(x);
        this.setLayoutY(y);
		
	}

}
