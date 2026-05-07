import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Circle;
import javafx.geometry.Pos;

public class EyeToken extends StackPane {
    private boolean isCollected = false;

    // Range Token genelde 40-50 px civarı olur, varsayılanı 50 yapalım
    public EyeToken(double x, double y) {
        this.isCollected = false;

        // Göz Akı 
        Ellipse ak = new Ellipse(10, 6);
        ak.setFill(Color.YELLOW.brighter());
        ak.setStroke(Color.BLACK);
        ak.setStrokeWidth(1);

        // İris
        Circle iris = new Circle(4);
        iris.setFill(Color.SKYBLUE.darker());
        iris.setStroke(Color.BLACK);
        iris.setStrokeWidth(0.5);

        // Göz Bebeği 
        Circle bebek = new Circle(2);
        bebek.setFill(Color.BLACK);
        
        this.setAlignment(Pos.CENTER);
        
        Circle token = new Circle(15, Color.YELLOW);
        token.setStroke(Color.GRAY.darker());

        this.getChildren().addAll(token,ak, iris, bebek);
        
       
        this.setLayoutX(x);
        this.setLayoutY(y);
        
    }

    public void collect() {
        this.isCollected = true;
        this.setVisible(false);
    }

    public boolean isCollected() {
        return isCollected;
    }
}