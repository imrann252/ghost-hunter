import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class HealthToken extends Pane {
    private double x, y;
    private boolean isCollected = false;
    private int restorationAmount = 20; // 20 artsın istediğin için varsayılan değer

    public HealthToken(double x, double y) {
        this.x = x;
        this.y = y;

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
        if (this.getBoundsInParent().intersects(hunter.getBody().getBoundsInParent())) {
            isCollected = true;

            // 1. Sağlık barını güncelle (+20)
            hb.setBar(restorationAmount);

            // 2. Token'ı ekrandan kaldır
            root.getChildren().remove(this);
            
            System.out.println("Sağlık toplandı! Yeni sağlık: " + hb.currentHealth);
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