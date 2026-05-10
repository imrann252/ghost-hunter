import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class HealthToken extends Pane {
    private double x, y;
    private boolean isCollected = false;
    private int restorationAmount ;

    public HealthToken(double x, double y) {
        this.x = x;
        this.y = y;
        this.restorationAmount = ConfigLoader.getInstance().getHealthTokenIncrease();

        Circle base = new Circle(15, Color.YELLOW);
        base.setStroke(Color.GRAY.darker());
        
        // Artı (+) işareti tasarımı
        Rectangle verticalBar = new Rectangle(-2, -8, 4, 16);
        Rectangle horizontalBar = new Rectangle(-8, -2, 16, 4);
        verticalBar.setFill(Color.RED);
        horizontalBar.setFill(Color.RED);

        this.getChildren().addAll(base, verticalBar, horizontalBar);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public void collect(Hunter hunter, HealthBar hb, Pane root) {
        if (isCollected) return;

        // Hunter'ın gövdesi ile Token çarpışıyor mu?
       
        if (this.getBoundsInParent().intersects(hunter.getShape().getBoundsInParent())) {
                isCollected = true;
                hb.setBar(restorationAmount);
                root.getChildren().remove(this);
        
    }
    }

    
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