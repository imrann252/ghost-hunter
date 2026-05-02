
import javafx.animation.AnimationTimer;
import javafx.application.Application;
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

public class Hunter extends Entity {
	
    // Basılı olan tuşları saklamak için bir set (takılmayı önler)
    private final Set<KeyCode> activeKeys = new HashSet<>();
    
    

    public Hunter(double x, double y,boolean isVisible, Scene scene) {
    	
    	this.isVisible=isVisible;
       
  
        // Hunter kurulumu
        Circle circle = new Circle(0, 0, 15);
        circle.setStroke(Color.ORANGE);
        circle.setFill(Color.ORANGE);

        // Vakum üçgeni - Başlangıçta görünmez [cite: 112]
        Polygon vacuumTriangle = new Polygon(0, 0, 80, -30, 80, 30);
        vacuumTriangle.setFill(Color.rgb(255, 0, 0, 0.4)); // Yarı şeffaf [cite: 92]
        vacuumTriangle.setVisible(false);

        Group shape = new Group(circle, vacuumTriangle);
        // Karakteri playable_area içinde başlat (Örn: config'den gelen x:200, y:200) [cite: 206, 207]
        shape.setTranslateX(250);
        shape.setTranslateY(250);




        // Tuş takibi
        scene.setOnKeyPressed(e -> activeKeys.add(e.getCode()));
        scene.setOnKeyReleased(e -> activeKeys.remove(e.getCode()));
        
        

        // Belirlenen koordinatlara yerleştir
        shape.setLayoutX(x);
        shape.setLayoutY(y);
        
        shape.setVisible(isVisible);

        
        /*
        // Oyun Döngüsü (AnimationTimer) - Saniyede 60 kez çalışır
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(shape, vacuumTriangle);
            }
        };
        gameLoop.start();*/

       
    }
/*
    private void update(Group shape, Polygon vacuum) {
        double speed = 4.0; // Akıcı hız

        // HAREKET VE YÖN (W, A, S, D) [cite: 102, 103, 104, 105]
        if (activeKeys.contains(KeyCode.W)) {
            shape.setTranslateY(shape.getTranslateY() - speed);
            shape.setRotate(270);
        }
         if (activeKeys.contains(KeyCode.S)) {
            shape.setTranslateY(shape.getTranslateY() + speed);
            shape.setRotate(90);
        }
         if (activeKeys.contains(KeyCode.A)) {
            shape.setTranslateX(shape.getTranslateX() - speed);
            shape.setRotate(180);
        }
         if (activeKeys.contains(KeyCode.D)) {
            shape.setTranslateX(shape.getTranslateX() + speed);
            shape.setRotate(0);
        }

        // VAKUM AKTİVASYONU (SPACE) 
        if (activeKeys.contains(KeyCode.SPACE)) {
            vacuum.setVisible(true);
            // İleride buraya vakum barını düşüren kodu ekleyeceksin [cite: 112]
        } else {
            vacuum.setVisible(false);
            // İleride buraya vakum barını dolduran kodu ekleyeceksin [cite: 113]
        }
        
        // NOT: Buraya ileride config.txt'den okuduğun playable_area sınır 
        // kontrollerini (if-else) eklemen gerekecek.
    }*/
}