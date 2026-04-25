

import java.util.Random;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class deneemegörünt extends Application{
	private double dx, dy;
    private Random random = new Random();
    private double startX = 100, startY = 100;
	public void start (Stage p) {
		
	
	Arc head = new Arc(0, 0, 15, 15, 0, 180);
    head.setType(ArcType.ROUND);
    head.setFill(Color.WHITE);

    
    Rectangle body = new Rectangle(-15, 0, 30, 15);
    body.setFill(Color.WHITE);

    
    Circle leftEye = new Circle(-5, -2, 2, Color.BLACK);
    Circle rightEye = new Circle(5, -2, 2, Color.BLACK);
    Pane pane=new Pane();
    Scene scene = new Scene(pane,800,500);
    scene.setFill(Color.BLACK);
    

    
    pane.getChildren().addAll(head, body, leftEye, rightEye);
    pane.setOpacity(0.6); 

    head.setTranslateX(startX); head.setTranslateY(startY);
    body.setTranslateX(startX); body.setTranslateY(startY);

    
    this.dx = (random.nextDouble() * 2 + 1) * (random.nextBoolean() ? 1 : -1);
    this.dy = (random.nextDouble() * 2 + 1) * (random.nextBoolean() ? 1 : -1);
    p.setTitle("KeyEventDemo");
    p.setScene(scene); 
    p.show(); 
}

	public static void main(String[] args) {
		  launch(args);
		  
	}

}
