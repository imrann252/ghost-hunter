import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class RangeToken extends Pane {
    private double x, y;
    private double increaseAmount; //vacuum_token_increase

    public RangeToken(double x, double y) {
        this.x = x;
        this.y = y;

        // token tabanı: dis daireyi ciz.
        Circle out_circle = new Circle(15, Color.YELLOW);
        out_circle.setStroke(Color.GRAY.darker());//dis kenarliklari boya.

        // Menzili temsil eden siyah bir üçgen (ikon)
        Polygon rangeIcon = new Polygon();
        rangeIcon.getPoints().addAll(new Double[]{
            -5.0, 5.0,
            8.0, 0.0,
            -5.0, -5.0
        });
        rangeIcon.setFill(Color.BLACK);//ucgenin icini doldur

        this.getChildren().addAll(out_circle, rangeIcon);
        
        // Konumlandır
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    /* Hunter ile etkileşime girdiğinde menzili artırır 
    public void applyEffect(Hunter hunter) {
        // Hunter sınıfında tanımladığın genişletme metodunu çağırır
        hunter.expandScannerRange(increaseAmount);
    }*/
}