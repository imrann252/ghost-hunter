import java.util.ArrayList;

import java.util.List;
import java.util.HashSet;
import java.util.Set;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

//Imran_Akar_150124087
//Coordinates the main game loop, level setups, UI widgets, and entity updates.
public class GameArea {
   private Scene scene;
   private VacuumBar vb ;
   private HealthBar hb ;
   private int currentLevel=1;
   private Pause pause;
   private ConfigLoader config;

   private TokenController tokenController;

   private ArrayList<Entity> entities = new ArrayList<>();

   public Timer st;
   public ScoreCounter sc;
   public boolean visibility;

   private Set<KeyCode> activeKeys = new HashSet<>();
   private double currentMouseX;
   private double currentMouseY;

   private AnimationTimer animation;
   private Pane area;
   private PlayableArea pa ;
   private Hunter hunter;
   private  boolean isGameOver=false;
   private boolean isGamePaused;
   private PausePanel pausePanel;
	

   //Constructor that initializes game configs, sets up scene dimensions, and spawns UI elements for the current level.
   public GameArea(int level) {
	   
	  
	   
	   this.config=ConfigLoader.getInstance();
	   this.currentLevel=level;
	   ConfigLoader config=ConfigLoader.getInstance();
	
	   
	   this.area =new Pane();
	   
	   //Sets up the visual window constraints to match the full-screen scale.
	   Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
	   this.scene = new Scene(area,visualBounds.getWidth(), visualBounds.getHeight());
	   
	   //Spawns the red boundary box based on configuration sizes for the specific level.
	   this.pa = new PlayableArea(config.getWidth(level),config.getHeight(level),scene);
	   pa.setVisible(false);
	   //Calculates absolute coordinate limits for the movement bounding box.
	   double minX = (scene.getWidth() / 2) - (config.getWidth(level) / 2);
	   double minY = (scene.getHeight() / 2) + 25 - (config.getHeight(level) / 2);
	   double maxX = minX + config.getWidth(level);
	   double maxY = minY + config.getHeight(level);
	   
	   //Spawns the player character (Hunter) at the default startup coordinates.
	   hunter=new Hunter(600,550,scene);
	   
	   //Instantiates side gauge bars, game clocks, scoreboards, and support package handlers
	   vb = new VacuumBar(scene);
	   hb = new HealthBar(scene);
	   st = new Timer(config.getLevelTime(level),scene);
	   sc = new ScoreCounter(scene);
	   pause=new Pause(scene,this);
	   tokenController = new TokenController(area, pa);
	  

	   //Loads and binds the level's specific theme background to match screen size dynamically.
	   ImageView areaImage = new ImageView("file:resources/level" + level + ".png");
	   areaImage.fitWidthProperty().bind(scene.widthProperty());
	   areaImage.fitHeightProperty().bind(scene.heightProperty());
	   
	   area.getChildren().addAll(areaImage,vb,hb,pa,st,sc,hunter.getShape(),pause);
	   
	   
	   //Spawns standard Ghost units at random coordinates inside the map limits and registers them.
	   for(int i=0;i<config.getGhostCount(level);i++) {
		   double x = minX + Math.random() * (maxX-minX);
           double y = minY + Math.random() * (maxY-minY);
           
           Ghost ghost=new Ghost(x,y,false);
           entities.add(ghost);
		   area.getChildren().addAll(ghost.getShape()); 
	   }
	   
	  //Spawns standard Ripper units at random coordinates inside the map limits and registers them.
	   for(int i=0;i<config.getRipperCount(level);i++) {
		   double x = minX + Math.random() * (maxX-minX);
           double y = minY + Math.random() * (maxY-minY);
           
           Ripper ripper=new Ripper(x,y,false);
           entities.add(ripper);
		   area.getChildren().addAll(ripper.getShape()); 
	   }
	  //Spawns standard Wisp units at random coordinates inside the map limits and registers them.
	   for(int i=0;i<config.getWispCount(level);i++) {
		   double x = minX + Math.random() * (maxX-minX);
           double y = minY + Math.random() * (maxY-minY);
           
           Wisp wisp = new Wisp(x,y,false);
           entities.add(wisp);
		   area.getChildren().addAll(wisp.getShape()); 
	   }
	  
	   //Forces all hidden entities and the red border framework to be fully visible when holding down the H key.
	   scene.setOnKeyPressed(keyEvent -> { 
		    if (keyEvent.getCode() == KeyCode.H) {
		        for (Entity ent : entities) {
		            ent.setVisible(true);
		            pa.setVisible(true);
		        }
		    }
		});
	   //Hides the ghosts and the red boundary framework back to default hidden states upon releasing the H key.
		scene.setOnKeyReleased(keyReleaseEvent -> { 
		    if (keyReleaseEvent.getCode() == KeyCode.H) {
		        for (Entity ent : entities) {
		            ent.setVisible(false);
		            pa.setVisible(false);
		        }
		    }
		});
	   
		//Sets up the main game loop running at 60 FPS to calculate real-time physics, inputs, and collisions.
	   this.animation=new AnimationTimer() {
			public void handle (long now) {
				checkGameStatus(); 
		        
				//Freezes the update cycle immediately if the match state is paused or over.
		        if (isGameOver || isGamePaused) return; 
			    hunter.update(minX, minY, maxX, maxY,vb);
			    tokenController.update(now, hunter, hb, entities);
				
			    //Iterates through all alive targets to process movement patterns and state transformations.
				for(int i=0;i<entities.size();i++) {
					Entity ent = entities.get(i);
					
				//Specific cast to trigger orbital tail rotation if the current entity is a Wisp
					if(ent  instanceof Wisp) {
				    ((Wisp)(entities.get(i))).updateAnimation();
				    }
					
					ent.move(minX, minY, maxX, maxY);
					
					
					 
					//Evaluates rigid geometry collisions between the player body and monster hitboxes.
					if(hunter.getBody().getBoundsInParent().intersects(ent.getShape().getBoundsInParent())) {
						
						if (!hunter.isInvincible()) {
							hb.setBar(-(config.getEntityDamage())/60.0);
							hunter.playCollisionSound();
		                	hunter.paintRed();
		                	
		                	PauseTransition delay = new PauseTransition(Duration.seconds(0.1));

		                    delay.setOnFinished(e -> {
		                    	hunter.paintOrange();
		                    
		                    });

		                    delay.play();
						}
						//Grants damage immunity and applies a flashing blue visual effect when shield is active.
						else {
							hunter.paintblue();
		                	
		                	PauseTransition delay = new PauseTransition(Duration.seconds(0.1));

		                    delay.setOnFinished(e -> {
		                    	hunter.paintOrange();
		                    
		                    });
		                    
		                    delay.play();
						}
						
							
	                	
	                	checkGameStatus();
	                }
					 
					  
					
						 
                //Monitors active weapon interaction constraints to shrink or eliminate targets inside the vacuum cone.
		            if (hunter.isVacuumActive() && vb.getCurrentAmount() > 0 && hunter.getVacuumShape().getBoundsInParent().intersects(ent.getShape().getBoundsInParent())) {
		                
		                double currentScale = ent.getShape().getScaleX(); 
		       
		                //Removes entity from layout and tracks score increments when scale reaches down below 50%.
		                if (currentScale < 0.50) {
		     
		                   
		                    area.getChildren().remove(ent.getShape());
		                    entities.remove(i);
		                    
		                    if(ent instanceof Ghost) {
		                       sc.addScore(10); 
		                    }
		                    else if(ent instanceof Ripper) {
		                    	sc.addScore(20);
		                    }
		                    else {
		                    	sc.addScore(30);
		                    }
		                    checkGameStatus();
		                    i--; // Decrements the loop index to prevent pointer skipping after removing an entity from array list.
		                    continue;
		                    
		                } else {
		                    //Gradually scales down the monster vector matrix per frame under vacuum pressure.
		                    ent.getShape().setScaleX(currentScale - 0.005);
		                    ent.getShape().setScaleY(currentScale - 0.005);
		                }
		               
		              
		            } 

				}
				
			}
			
		};
		//Fires up the central animation sub-clock and requests operational input focus for the layout pane.
		animation.start();
		area.setFocusTraversable(true);
		area.requestFocus();
		
}


//Halts the game clock, freezes animations, and displays the centered PausePanel overlay.
public void pause() {
    if (isGameOver || isGamePaused) return;
    this.isGamePaused=true;
    // Stop the game
    if (animation != null) animation.stop();
    if (st != null) st.pause();

    pause.setPausedState(true);
    //Sets panel scale constants dynamically to occupy 30% of the active display layout.
    double pW = area.getWidth() * 0.3;
    double pH = area.getHeight() * 0.3;

    
    pausePanel = new PausePanel(pW, pH, this);
    
    //Computes alignment layouts to anchor the pause dialogue dead-center on screen.
    pausePanel.setLayoutX((area.getWidth() - pW) / 2);
    pausePanel.setLayoutY((area.getHeight() - pH) / 2);

    
    area.getChildren().add(pausePanel);
    pausePanel.toFront();
}
//Clears the PausePanel overlay, synchronizes indicators, and resumes active engine tasks.
public void resume() {
	this.isGamePaused=false;
    if (pausePanel != null) {
        area.getChildren().remove(pausePanel);
    }
    
    pause.setPausedState(false);
    if (animation != null) animation.start();
    if (st != null) st.start();
}
 //Monitors match telemetry to trigger and center concrete WinPanel or LosePanel overlay menus.
   public void checkGameStatus() {
	    if (isGameOver) return;

	    
	    double panelW = area.getWidth() * 0.3; 
	    double panelH = area.getHeight() * 0.3;
        
	    //Triggers WinPanel display if all target nodes are neutralized within the valid session duration.
	    if (entities.isEmpty() && st.getRemainingTime() > 0 && hb.getCurrentHealth() > 0) {
	       isGameOver = true;
	       hunter.stopVacuumSound();
	       animation.stop();
	       if(st != null) st.stop();

	        WinPanel wp = new WinPanel(panelW, panelH, this);
	        
	    
	        wp.setLayoutX((area.getWidth() - panelW) / 2);
	        wp.setLayoutY((area.getHeight() - panelH) / 2); // Burada currentW yazmıştın, düzelttik

	        area.getChildren().add(wp);
	        wp.toFront();
	    }
	    
	   // Triggers LosePanel display with scores if player health drops to zero or timer expires.
	    else if (hb.getCurrentHealth() <= 0 || st.getRemainingTime() <= 0) {
	        isGameOver = true;
	        hunter.stopVacuumSound();
	        animation.stop();
	        if(st != null) st.stop();

	        LosePanel lp = new LosePanel(panelW, panelH, sc.getScore(), this);
	        
	        lp.setLayoutX((area.getWidth() - panelW) / 2);
	        lp.setLayoutY((area.getHeight() - panelH) / 2);

	        area.getChildren().add(lp);
	        lp.toFront();
	    }
	}
   //Toggles between active gameplay and the paused state depending on the current engine flag.
   public void togglePause() {
	   if(isGamePaused) {
		   resume();
	   }
	   else {
		   pause();
	   }
   }
	//Flushes the active timers, resets flags, and reloads the current level from scratch.
 public void restart(int level) {
	 if (this.animation != null) {
	        this.animation.stop();
	        hunter.stopVacuumSound();
	    }
	 if (this.st != null) {
	        this.st.stop();
	    }
	 isGameOver=false;
	 GameArea newLevel = new GameArea(level); 
	    Test.mainStage.setScene(newLevel.getScene());
	 
 }
 //Safely teardowns engine loops and sound channels before switching the stage back to the root MainMenuScene.
 public void goToMenu() {
	 if (this.animation != null) {
	        this.animation.stop();
	        hunter.stopVacuumSound();
	        
	    }
        isGameOver=false;
	    
	    MainMenuScene mainMenu = new MainMenuScene(); 
	    Test.mainStage.setScene(mainMenu.getScene());
 }
 //Returns the current integer identifier tracking the active stage difficulty.
 public int getCurrentLevel() {
	 return currentLevel;
 }
 //Returns the fully operational and layered layout container for this session.
   public Scene getScene() {
		return scene;
	   }
	   
   }
