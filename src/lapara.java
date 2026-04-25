
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import java.util.Random;

public class lapara extends Application{


	
	

	

	    // Seviye 1 için örnek oynanabilir alan sınırları [cite: 206, 207, 208]
	    private double areaX = 50, areaY = 50, areaWidth = 700, areaHeight = 500;

	    @Override
	    public void start(Stage stage) {
	        Pane root = new Pane();
	        root.setStyle("-fx-background-color: #1a1a2e;"); // Karanlık arka plan

	        // Oynanabilir alanı temsil eden kırmızı sınır (Cheat Key görseli gibi) [cite: 87]
	        Rectangle boundary = new Rectangle(areaX, areaY, areaWidth, areaHeight);
	        boundary.setFill(Color.TRANSPARENT);
	        boundary.setStroke(Color.RED);
	        root.getChildren().add(boundary);

	        // Hayaleti oluştur ve ekle [cite: 122]
	        Ghost ghost = new Ghost(areaX + 100, areaY + 100);
	        root.getChildren().add(ghost);

	        // Oyun Döngüsü: Saniyede 60 kez çalışır
	        AnimationTimer timer = new AnimationTimer() {
	            @Override
	            public void handle(long now) {
	                // Hayaleti sınırlar içinde hareket ettir 
	                ghost.move(areaX, areaY, areaX + areaWidth, areaY + areaHeight);
	            }
	        };
	        timer.start();

	        stage.setScene(new Scene(root, 800, 600));
	        stage.setTitle("Ghost Movement Test");
	        stage.show();
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
	}
	
	


 class Ghost extends Group {
    private double dx, dy; // Hareket hızı
    private Random random = new Random();

    public Ghost(double startX, double startY) {
        // --- GÖRÜNÜM TASARIMI ---
        // Üst kısım: Yarım daire (Arc) [cite: 90, 96]
        Arc head = new Arc(0, 0, 15, 15, 0, 180);
        head.setType(ArcType.ROUND);
        head.setFill(Color.WHITE);

        // Gövde: Dikdörtgen (Rectangle) 
        Rectangle body = new Rectangle(-15, 0, 30, 15);
        body.setFill(Color.WHITE);

        // Gözler: Küçük daireler (Circle) 
        Circle leftEye = new Circle(-5, -2, 2, Color.BLACK);
        Circle rightEye = new Circle(5, -2, 2, Color.BLACK);

        // Şekilleri gruba ekle ve şeffaflığı ayarla [cite: 96]
        this.getChildren().addAll(head, body, leftEye, rightEye);
        this.setOpacity(0.6); // Klasik yarı şeffaf hayalet görünümü [cite: 96]

        // --- BAŞLANGIÇ KONUMU VE HIZI ---
        this.setLayoutX(startX);
        this.setLayoutY(startY);

        // Rastgele yön ve hız belirleme [cite: 123]
        this.dx = (random.nextDouble() * 2 + 1) * (random.nextBoolean() ? 1 : -1);
        this.dy = (random.nextDouble() * 2 + 1) * (random.nextBoolean() ? 1 : -1);
    }

    // Hareket ve Sekme Mantığı
    public void move(double minX, double minY, double maxX, double maxY) {
        // Mevcut konumu hız kadar artır [cite: 121]
        this.setLayoutX(this.getLayoutX() + dx);
        this.setLayoutY(this.getLayoutY() + dy);

        // Kenarlara çarpınca simetrik olarak sek (Bounce Back) 
        if (this.getLayoutX() - 15 <= minX || this.getLayoutX() + 15 >= maxX) {
            dx *= -1; // Yatayda yön değiştir
        }
        if (this.getLayoutY() - 15 <= minY || this.getLayoutY() + 15 >= maxY) {
            dy *= -1; // Dikeyde yön değiştir
        }
    }
}

