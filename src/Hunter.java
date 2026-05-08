
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import java.util.HashSet;
import java.util.Set;

public class Hunter {
    private Group shape;
    private Circle body;
    private Polygon vacuum;
    private Rotate vacuumRotate;
    
    private final double radius = 15; 
    private double speed = 4.0;
    
    // hunter bi tek başına yapsın bunu
    private Set<KeyCode> activeKeys = new HashSet<>();
    private double mouseX, mouseY;

    public Hunter(double startX, double startY, Scene scene) {
      
        body = new Circle(radius, Color.ORANGE);
        
        vacuum = new Polygon();
        vacuum.getPoints().addAll(new Double[]{
            0.0, 0.0,           // Tepe noktası (Pivot)
            180.0, -60.0,       
            180.0, 60.0
        });
        vacuum.setFill(Color.rgb(255, 0, 0, 0.4)); 
        vacuum.setVisible(false);

        vacuumRotate = new Rotate(0, 0, 0);
        vacuum.getTransforms().add(vacuumRotate);

        vacuum.layoutXProperty().bind(body.layoutXProperty());
        vacuum.layoutYProperty().bind(body.layoutYProperty());

        shape = new Group(vacuum, body);
        body.setLayoutX(startX);
        body.setLayoutY(startY);

        // 2. Dinleyicileri Sahneye Bağla 
        setupInput(scene);
    }

    private void setupInput(Scene scene) {
        scene.addEventFilter(javafx.scene.input.KeyEvent.KEY_PRESSED, e -> activeKeys.add(e.getCode()));
        scene.addEventFilter(javafx.scene.input.KeyEvent.KEY_RELEASED, e -> activeKeys.remove(e.getCode()));
        scene.addEventFilter(javafx.scene.input.MouseEvent.MOUSE_MOVED, e -> {
            mouseX = e.getX();
            mouseY = e.getY();
        });
    }

    public void update(double minX, double minY, double maxX, double maxY) {
        double nextX = body.getLayoutX();
        double nextY = body.getLayoutY();

        if (activeKeys.contains(KeyCode.W)) nextY -= speed;
        if (activeKeys.contains(KeyCode.S)) nextY += speed;
        if (activeKeys.contains(KeyCode.A)) nextX -= speed;
        if (activeKeys.contains(KeyCode.D)) nextX += speed;

       
        if (nextX - radius >= minX && nextX + radius <= maxX) {
            body.setLayoutX(nextX);
        }
        if (nextY - radius >= minY && nextY + radius <= maxY) {
            body.setLayoutY(nextY);
        }

        vacuum.setVisible(activeKeys.contains(KeyCode.SPACE));

      //tanjan hesaplanıyo
        double dx = mouseX - body.getLayoutX();
        double dy = mouseY - body.getLayoutY();
        // burda da radyan almadığı için dereec
        vacuumRotate.setAngle(Math.toDegrees(Math.atan2(dy, dx)));
    }

    public Group getShape() { return shape; }
}
