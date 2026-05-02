import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class HealthToken extends Pane {
    private double x, y;
    private int restorationAmount;

    public HealthToken(double x, double y, int amount) {
        this.setX(x);
        this.restorationAmount = amount;

        // Görsel Tasarım: JavaFX geometrik şekilleri kullanılarak [cite: 132]
        // Örneğin sarı bir daire üzerine kırmızı bir artı (+) sembolü
        Circle base = new Circle(15, Color.YELLOW);
        Rectangle verticalBar = new Rectangle(-2, -8, 4, 16);
        Rectangle horizontalBar = new Rectangle(-8, -2, 16, 4);
        verticalBar.setFill(Color.RED);
        horizontalBar.setFill(Color.RED);

        this.getChildren().addAll(base, verticalBar, horizontalBar);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    // Hunter ile çarpışma olduğunda tetiklenecek metod
    /*public void applyEffect(Hunter hunter) {
        // Hunter'ın sağlığını artır ve barı güncelle [cite: 140]
        hunter.increaseHealth(restorationAmount);
    }*/

    public int getRestorationAmount() {
        return restorationAmount;
    }

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
}