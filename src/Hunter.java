
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Hunter {
    private final Set<KeyCode> activeKeys = new HashSet<>();
    private Group shape;
    private Polygon vacuumTriangle;
    private Scene scene;
    public double x, y;
    public boolean isVisible;

    public Hunter(double startX, double startY, boolean isVisible, Scene scene) {
        this.scene = scene;
        this.isVisible = isVisible;

        Circle circle = new Circle(0, 0, 15);
        circle.setStroke(Color.ORANGE);
        circle.setFill(Color.ORANGE);

        vacuumTriangle = new Polygon(0, 0, 80, -30, 80, 30);
        vacuumTriangle.setFill(Color.rgb(255, 0, 0, 0.4));
        vacuumTriangle.setVisible(isVisible);

        shape = new Group(circle, vacuumTriangle);
        
        
        // Konumlandırma: Layout koordinatlarını sabit tutup Translate ile hareket edelim
        shape.setLayoutX(startX);
        shape.setLayoutY(startY);
       
        // Dinleyicileri ekle
        addKeyListeners();
        this.update(startY, startY, startX, startY);
    }

    private void addKeyListeners() {
        // Scene null değilse dinle
        if (scene != null) {
            scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> activeKeys.add(e.getCode()));
            scene.addEventFilter(KeyEvent.KEY_RELEASED, e -> activeKeys.remove(e.getCode()));
        }
    }

    public void update(double minX, double minY , double maxX, double maxY) {
        double speed = 3.0;
        
        // Mevcut konumları al
        double dx = shape.getTranslateX();
        double dy = shape.getTranslateY();
        x = shape.getLayoutX()+shape.getTranslateX();
        y = shape.getLayoutY()+shape.getTranslateY();
        
        double radius = 15; 

	
	  
	    if (activeKeys.contains(KeyCode.W)) {
            shape.setTranslateY(dy - speed);
            shape.setRotate(270);
        }
	    else if (activeKeys.contains(KeyCode.S)) {
            shape.setTranslateY(dy + speed);
            shape.setRotate(90);
        }
	    else if (activeKeys.contains(KeyCode.A)) {
            shape.setTranslateX(dx - speed);
            shape.setRotate(180);
        }
	    else if (activeKeys.contains(KeyCode.D)) {
            shape.setTranslateX(dx + speed);
            shape.setRotate(0);
        }
	    
	    if (isVisible) {
	    	vacuumTriangle.setVisible(isVisible);
	    }
	    else {
	    	vacuumTriangle.setVisible(activeKeys.contains(KeyCode.SPACE));
	    }
	   
        
        System.out.println(this.shape.getTranslateX() + " " + this.shape.getTranslateY());
    }

    public Group getShape() { return shape; }

}