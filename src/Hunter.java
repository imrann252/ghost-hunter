import javafx.animation.PauseTransition;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.util.Duration;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

import java.util.HashSet;
import java.util.Set;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.URL;

public class Hunter {

    private Group shape;

    private Circle body;
    private Polygon vacuum;

    private Rotate vacuumRotate;
    private Scale vacuumScale;

    
    private final double radius = 15;
    private double speed = 3.0;

    private Set<KeyCode> activeKeys = new HashSet<>();

    private double mouseX;
    private double mouseY;
    
    private final AudioClip colsound;
    private MediaPlayer vacuumPlayer;
    
    private boolean isVacuumPlaying = false;

    public Hunter(double startX, double startY, Scene scene) {

        body = new Circle(radius, Color.ORANGE);
        
        String path = getClass().getResource("collision.WAV").toExternalForm();
        this.colsound = new AudioClip(path);
        
        // HİLE: Sesi bir kez 0 ses seviyesinde oynatıp durdur. 
        // Bu, JavaFX ses motorunu "ısıtır".
        colsound.setVolume(0);
        colsound.play();
        colsound.stop();
        colsound.setVolume(1); // Tekrar normal seviyeye çek
        
        
        
        URL resource = getClass().getResource("vacuum.WAV"); // Dosya yolun
        Media media = new Media(resource.toString());
        vacuumPlayer = new MediaPlayer(media);
        vacuumPlayer.setVolume(0.05);
        
        
        // Sesin bitince başa sarması için (Vakum sesi döngüye girsin)
        vacuumPlayer.setCycleCount(MediaPlayer.INDEFINITE);
      
        

        // Vacuum triangle
        vacuum = new Polygon();

        vacuum.getPoints().addAll(new Double[]{
                0.0, 0.0,
                120.0, -60.0,
                120.0, 60.0
        });

        vacuum.setFill(Color.rgb(255, 0, 0, 0.4));
        vacuum.setVisible(false);

        // Rotation
        vacuumRotate = new Rotate(0, 0, 0);

        // Scale with pivot at hunter center
        vacuumScale = new Scale(1, 1, 0, 0);

        vacuum.getTransforms().addAll(vacuumRotate, vacuumScale);

        // Vacuum follows body
        vacuum.layoutXProperty().bind(body.layoutXProperty());
        vacuum.layoutYProperty().bind(body.layoutYProperty());

        shape = new Group(vacuum, body);

        body.setLayoutX(startX);
        body.setLayoutY(startY);

        setupInput(scene);
    }

    private void setupInput(Scene scene) {

        scene.addEventFilter(
                javafx.scene.input.KeyEvent.KEY_PRESSED,
                e -> activeKeys.add(e.getCode())
        );

        scene.addEventFilter(
                javafx.scene.input.KeyEvent.KEY_RELEASED,
                e -> activeKeys.remove(e.getCode())
        );

        scene.addEventFilter(
                javafx.scene.input.MouseEvent.MOUSE_MOVED,
                e -> {
                    mouseX = e.getX();
                    mouseY = e.getY();
                }
        );
    }

    public void update(double minX, double minY,double maxX, double maxY, VacuumBar vb) {

        double nextX = body.getLayoutX();
        double nextY = body.getLayoutY();

        if (activeKeys.contains(KeyCode.W)) {
            nextY -= speed;
        }

        if (activeKeys.contains(KeyCode.S)) {
            nextY += speed;
        }

        if (activeKeys.contains(KeyCode.A)) {
            nextX -= speed;
        }

        if (activeKeys.contains(KeyCode.D)) {
            nextX += speed;
        }

        // Boundary check
        if (nextX - radius >= minX &&
                nextX + radius <= maxX) {

            body.setLayoutX(nextX);
        }

        if (nextY - radius >= minY &&
                nextY + radius <= maxY) {

            body.setLayoutY(nextY);
        }

        // Vacuum active
        vacuum.setVisible(activeKeys.contains(KeyCode.SPACE));
        boolean isSpacePressed = activeKeys.contains(KeyCode.SPACE);
        
        if (isSpacePressed && vb.getCurrentAmount() > 0) {
            vacuum.setVisible(true);
            if (!isVacuumPlaying) {
                startVacuumSound();
                isVacuumPlaying = true;
            }
            
            // Enerjiyi azalt (ConfigLoader'dan gelen değeri kullanabilirsin, örn: -0.5) [cite: 204, 231]
            vb.setBar(-0.3); 
        } else {
            vacuum.setVisible(false);
            stopVacuumSound();
            
            PauseTransition delay = new PauseTransition(Duration.seconds(0.5));

            delay.setOnFinished(e -> {
            	isVacuumPlaying = false ;
            
            });

            delay.play();
            
            // Space basılmıyorsa enerji dolar [cite: 113, 232]
            vb.setBar(0.4); 
        }

        // Rotation angle
        double dx = mouseX - body.getLayoutX();
        double dy = mouseY - body.getLayoutY();

        vacuumRotate.setAngle(
                Math.toDegrees(Math.atan2(dy, dx))
        );
    }

    // Vacuum scale methods
    public void setVacuumScale(double scaleX, double scaleY) {

        vacuumScale.setX(scaleX);
        vacuumScale.setY(scaleY);
    }

    public double getVacuumScaleX() {
        return vacuumScale.getX();
    }

    public double getVacuumScaleY() {
        return vacuumScale.getY();
    }

    public boolean isVacuumActive() {
        return vacuum.isVisible();
    }

    public javafx.scene.Node getVacuumShape() {
        return vacuum;
    }

    public Group getShape() {
        return shape;
    }
    
    public Circle getBody() {
        return body;
    }
    public void paintRed() {
    	this.body.setFill(Color.RED);;
    }
    public void paintOrange() {
    	this.body.setFill(Color.ORANGE);
    }
    
    public void playCollisionSound() {
    	    // Eğer ses zaten çalıyorsa tekrar başlatma (veya çok kısaysa direkt çal)
	    if (colsound != null && !colsound.isPlaying()) {
	        colsound.play();
	    }
	}
    
    public void startVacuumSound() {
        if (vacuumPlayer != null && vacuumPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
            vacuumPlayer.play();
        }
    }

    public void stopVacuumSound() {
    	if (vacuumPlayer != null && vacuumPlayer.getStatus() != MediaPlayer.Status.STOPPED) {
            vacuumPlayer.stop(); 
        }
    }
    
}