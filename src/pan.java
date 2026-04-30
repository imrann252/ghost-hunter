package ghost;


	


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
public class pan extends Application {


    // Basılı olan tuşları saklamak için bir set (takılmayı önler)
    private final Set<KeyCode> activeKeys = new HashSet<>();

    @Override
    public void start(Stage p) {
        Pane pane = new Pane();
        
        // Hunter kurulumu
        Circle circle = new Circle(0, 0, 15);
        circle.setStroke(Color.ORANGE);
        circle.setFill(Color.ORANGE);

        // Vakum üçgeni - Başlangıçta görünmez [cite: 112]
        Polygon vacuumTriangle = new Polygon(0, 0, 80, -30, 80, 30);
        vacuumTriangle.setFill(Color.rgb(255, 0, 0, 0.4)); // Yarı şeffaf [cite: 92]
        vacuumTriangle.setVisible(false);

        Group hunter = new Group(circle, vacuumTriangle);
        // Karakteri playable_area içinde başlat (Örn: config'den gelen x:200, y:200) [cite: 206, 207]
        hunter.setTranslateX(250);
        hunter.setTranslateY(250);

        pane.getChildren().add(hunter);

        Scene scene = new Scene(pane, 800, 600);

        // Tuş takibi
        scene.setOnKeyPressed(e -> activeKeys.add(e.getCode()));
        scene.setOnKeyReleased(e -> activeKeys.remove(e.getCode()));

        // Oyun Döngüsü (AnimationTimer) - Saniyede 60 kez çalışır
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update(hunter, vacuumTriangle);
            }
        };
        gameLoop.start();

        p.setTitle("Ghost Hunter Inc. - Hunter Test");
        p.setScene(scene);
        p.show();
    }

    private void update(Group hunter, Polygon vacuum) {
        double speed = 4.0; // Akıcı hız

        // HAREKET VE YÖN (W, A, S, D) [cite: 102, 103, 104, 105]
        if (activeKeys.contains(KeyCode.W)) {
            hunter.setTranslateY(hunter.getTranslateY() - speed);
            hunter.setRotate(270);
        }
         if (activeKeys.contains(KeyCode.S)) {
            hunter.setTranslateY(hunter.getTranslateY() + speed);
            hunter.setRotate(90);
        }
         if (activeKeys.contains(KeyCode.A)) {
            hunter.setTranslateX(hunter.getTranslateX() - speed);
            hunter.setRotate(180);
        }
         if (activeKeys.contains(KeyCode.D)) {
            hunter.setTranslateX(hunter.getTranslateX() + speed);
            hunter.setRotate(0);
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
    }

    public static void main(String[] args) {
        launch(args);
    }
}