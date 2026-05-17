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
//Seyma_Nur_Hacimurtazaoglu_150124057
//Represents the playable Hunter character in the game. It manages the hunter's visual appearance (body, scanning triangle, and shield), 
//process movement logic, directional rotation based on the mouse pointer, and handles active audio elements for interactions like vacuuming and collisions
public class Hunter {

    private Group shape;
    private ConfigLoader config=ConfigLoader.getInstance();
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
    
    private Circle shieldCircle;
    private boolean isInvincible = false;
//Constructor that positions the Hunter at the designated spawn points, configures JavaFX geometric layers (body circle, vacuum scanner, and shield outline),
    //caches environmental audio resources, and initiates keyboard/mouse state listeners.
    public Hunter(double startX, double startY, Scene scene) {
      
        body = new Circle(radius, Color.ORANGE);
        
        shieldCircle = new Circle(25); 
        shieldCircle.setFill(Color.TRANSPARENT); 
        shieldCircle.setStroke(Color.RED);
        shieldCircle.setStrokeWidth(3);
        
      
        shieldCircle.getStrokeDashArray().addAll(10.0, 10.0);
        
        shieldCircle.setVisible(false);
        
     // Purpose: Binds the shield's layout center to the hunter's body position so it moves synchronized with the player.
        shieldCircle.layoutXProperty().bind(body.layoutXProperty());
        shieldCircle.layoutYProperty().bind(body.layoutYProperty());
        
     
     // Purpose: Resolves the local path of the collision audio resource file and converts it into an external string URL format.
        String path = getClass().getResource("collision.WAV").toExternalForm();
        this.colsound = new AudioClip(path);
        
       
        colsound.setVolume(0);
        colsound.play();
        colsound.stop();
        colsound.setVolume(1); 
        
        
        // Purpose: Locates the vacuum sound resource and initializes the media playback constraints with a low ambient background volume.
        URL resource = getClass().getResource("vacuum.WAV"); 
        Media media = new Media(resource.toString());
        vacuumPlayer = new MediaPlayer(media);
        vacuumPlayer.setVolume(0.05);
        
        
        
        vacuumPlayer.setCycleCount(MediaPlayer.INDEFINITE);
      
        

       
        vacuum = new Polygon();

        vacuum.getPoints().addAll(new Double[]{
                0.0, 0.0,
                120.0, -60.0,
                120.0, 60.0
        });

        vacuum.setFill(Color.rgb(255, 0, 0, 0.4));
        vacuum.setVisible(false);

        
        vacuumRotate = new Rotate(0, 0, 0);

        // Scale with pivot at hunter center
        vacuumScale = new Scale(1, 1, 0, 0);

        vacuum.getTransforms().addAll(vacuumRotate, vacuumScale);

      
        vacuum.layoutXProperty().bind(body.layoutXProperty());
        vacuum.layoutYProperty().bind(body.layoutYProperty());

        shape = new Group(vacuum, body, shieldCircle);

        body.setLayoutX(startX);
        body.setLayoutY(startY);

        setupInput(scene);
    }
 // Purpose: Assigns low-level input tracking filtering logic to the shared active Scene container to update keys being held down and store precise cursor positions.
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
 //Calculates the hunter's positional offsets using modern WASD states, respects bounding coordinates, 
 // drains or regenerates the dynamic scanning metric bar, and handles vacuum rotation orientation
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

       
        vacuum.setVisible(activeKeys.contains(KeyCode.SPACE));
        boolean isSpacePressed = activeKeys.contains(KeyCode.SPACE);
     // Purpose: Drains vacuum energy dynamically based on configuration file stats while space key is held down.
        if (isSpacePressed && vb.getCurrentAmount() > 0) {
            vacuum.setVisible(true);
            if (!isVacuumPlaying) {
                startVacuumSound();
                isVacuumPlaying = true;
            }
            
            
            vb.setBar(-(config.getVacuumDecrease()/4.0)); 
        } else {
        	// Purpose: Automatically recharges vacuum fuel meters at standard idle intervals when input keys are released.
            vacuum.setVisible(false);
            vacuum.setVisible(false);
            stopVacuumSound();
            
            PauseTransition delay = new PauseTransition(Duration.seconds(0.5));

            delay.setOnFinished(e -> {
            	isVacuumPlaying = false ;
            
            });

            delay.play();
            
          
            vb.setBar(config.getVacuumIncrease()/4.0); 
        }

     // Purpose: Calculates trigonometric arc tangent variables between user positions and mouse bounds to update tracking rotation values.
        double dx = mouseX - body.getLayoutX();
        double dy = mouseY - body.getLayoutY();

        vacuumRotate.setAngle(
                Math.toDegrees(Math.atan2(dy, dx))
        );
    }

//Purpose: Dictates the behavioral status of the power-up invincibility window and controls 
    //the visibility of the perimeter shield ring component.
    public void setInvincibility(boolean active) {
        this.isInvincible = active;
        shieldCircle.setVisible(active);
    }
//Accesses the boolean flag representing the current vulnerability or invincibility state.
    public boolean isInvincible() {
        return isInvincible;
    }
//Returns the Circle geometry node that renders the interactive barrier shield perimeter.
    public Circle getShieldCircle() {
        return shieldCircle;
    }
    
    //Dynamically adjust X and Y axis scale transformations to modify the outer bounds of the active scanning zone
    public void setVacuumScale(double scaleX, double scaleY) {

        vacuumScale.setX(scaleX);
        vacuumScale.setY(scaleY);
    }
//Accesses the current horizontal scale multiplier factor of the vacuum cone.
    public double getVacuumScaleX() {
        return vacuumScale.getX();
    }
//Accesses the current vertical scale multiplier factor of the vacuum cone.
    public double getVacuumScaleY() {
        return vacuumScale.getY();
    }
//Verifies if the vacuum rendering region is currently visible on screen.
    public boolean isVacuumActive() {
        return vacuum.isVisible();
    }
//Exposes the raw underlying JavaFX Polygon Node used for intersection tests and detection
    public javafx.scene.Node getVacuumShape() {
        return vacuum;
    }
//Exposes the combined top-level layout Group node representing the compiled visual hunter stack.
    
    public Group getShape() {
        return shape;
    }
    //Exposes the core base Circle geometry matching the precise coordinate origin of the user
    public Circle getBody() {
        return body;
    }
    //Re-colors the character node fill to vibrant red to communicate active damage instances
    public void paintRed() {
    	this.body.setFill(Color.RED);;
    }
    //Resets the character node fill back to its default orange appearance.
    public void paintOrange() {
    	this.body.setFill(Color.ORANGE);
    }
 //Applies a blue paint finish to modify the center circle component decoration.
    public void paintblue() {
    	this.body.setFill(Color.BLUE);
    }
    //Fires a standalone single-shot collision warning audio clip if no instance is playing.
    public void playCollisionSound() {
    	    
	    if (colsound != null && !colsound.isPlaying()) {
	        colsound.play();
	    }
	}
    //Initiates looping playback tracking of the ongoing ambient operational vacuum sound effect.
    public void startVacuumSound() {
        if (vacuumPlayer != null && vacuumPlayer.getStatus() != MediaPlayer.Status.PLAYING) {
            vacuumPlayer.play();
        }
    }
  //Halts media pipeline execution to silence operational vacuum noise instances immediately.
    public void stopVacuumSound() {
    	if (vacuumPlayer != null && vacuumPlayer.getStatus() != MediaPlayer.Status.STOPPED) {
            vacuumPlayer.stop(); 
        }
    }
    
}