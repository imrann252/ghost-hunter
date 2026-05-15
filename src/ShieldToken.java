import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class ShieldToken extends Pane {
    private boolean isCollected = false;

    public ShieldToken(double x, double y) {
        // Sarı ana daire
        Circle base = new Circle(15, Color.YELLOW);
        base.setStroke(Color.GRAY.darker());

        // 6 KÖŞELİ KALKAN LOGOSU
        Polygon shieldSign = new Polygon();
        shieldSign.getPoints().addAll(new Double[]{
        		0.0, -5.0,   // Üst orta (sivri)
                7.0, -6.0,   // Sağ üst
                7.0, 3.0,    // Sağ alt
                0.0, 9.0,   // Alt sivri
                -7.0, 3.0,   // Sol alt
                -7.0, -6.0   // Sol üst
        });
        shieldSign.setFill(Color.GRAY);
        shieldSign.setStroke(Color.BLACK);
        shieldSign.setStrokeWidth(2);

        this.getChildren().addAll(base, shieldSign);
        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public void collect(Hunter hunter, Pane root) {
        if (isCollected) return;

        // ÇÖZÜM: hunter.getShape() yerine hunter.getBody() kullanıyoruz.
        // Böylece sadece Hunter'ın gövdesine değince alınır, vakum ucuna değince değil.
        if (this.getBoundsInParent().intersects(hunter.getBody().getBoundsInParent())) {
            isCollected = true;
           
            root.getChildren().remove(this);
            hunter.setInvincibility(true);
            
            PauseTransition delay =
                    new PauseTransition(Duration.seconds(5));

            delay.setOnFinished(e -> {

            	hunter.setInvincibility(false);
                
            });

            delay.play();
            
            System.out.println("Shield Token Alındı!"); 
        }
    }
}