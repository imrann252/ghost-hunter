import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
//Imran_Akar_150124087
//Purpose: Manages the interactive pause/play button
public class Pause extends Pane {
	private StackPane icon;
	private boolean isPaused=false;

  //Constructor that sets up the background button frame, screen centering binds, and click listeners.
  public Pause(Scene scene,GameArea ga) {
	
	
	 Rectangle r1= new Rectangle(35,35);
	   r1.setFill(Color.WHITE);
	   r1.setArcWidth(10); 
       r1.setArcHeight(10);
	   r1.setX(0);
	   r1.setY(0);
	   
	   icon=new StackPane();
	   icon.setPrefSize(35, 35);
	   setPauseIcon();
	   
	   this.getChildren().addAll(r1,icon);
       this.layoutYProperty().set(91);
       this.layoutXProperty().bind(scene.widthProperty().divide(2).subtract(10)); 
       
       //Informs the core GameArea controller to toggle engine execution states.
       this.setOnMouseClicked(e -> {
    	   ga.togglePause();
       });
	   
  }
  //Switches the internal state and clears old graphics to load the corresponding active icon.
  private void State() {
	  icon.getChildren().clear();
	  
	  if(isPaused)
		  setPauseIcon();
	  else
		  setPlayIcon();
	  isPaused=!isPaused;
  }
  
  //Builds two vertical rectangles inside an HBox to render the standard pause symbol.
  private void setPauseIcon() {
       Rectangle r2=new Rectangle(7,21);
       r2.setFill(Color.BLACK);
       Rectangle r3=new Rectangle(7,21);
       r3.setFill(Color.BLACK);
       
       HBox hbox=new HBox(7);
       hbox.setAlignment(Pos.CENTER);
       hbox.getChildren().addAll(r2,r3);
       icon.getChildren().add(hbox);
       
  }
 //Builds a single 3-point polygon shape to render the standard play triangle.
  private void setPlayIcon() {
       
       Polygon triangle = new Polygon(
               0.0, 0.0, 
               0.0, 24.0, 
               18.0, 12.0
           );
       
       triangle.setFill(Color.BLACK);
       icon.getChildren().add(triangle);
       
      
       }
  //Returns the current boolean tracking if the game environment is paused.
  public boolean isPaused() {
	  return isPaused;
  }
  //Forcefully synchronizes the button state with external engine parameters and replaces icons.
  public void setPausedState(boolean state) {
	  this.isPaused=state;
	  icon.getChildren().clear();
	  if(isPaused) {
		  setPlayIcon();
	  }
	  else {
		  setPauseIcon();
	  }
  }

  
}

