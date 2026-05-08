import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import javafx.animation.AnimationTimer;
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

public class GameArea {
   private Scene scene;
   private VacuumBar vb ;
   private HealthBar hb ;
   private int currentLevel;

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
 
	

   
   public GameArea(int level) {
	   
	   this.currentLevel=level;
	   ConfigLoader config=ConfigLoader.getInstance();
	
	   
	   this.area =new Pane();
	   this.currentLevel=level;
	   Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
	   this.scene = new Scene(area,visualBounds.getWidth(), visualBounds.getHeight());
	   
	   
	   this.pa = new PlayableArea(config.getAreaX(level),config.getAreaY(level),config.getWidth(level),config.getHeight(level),scene);
	   
	   double minX = pa.pArea.getLayoutX();
	   double minY = pa.pArea.getLayoutY();
	   double maxX = minX + config.getWidth(level);
	   double maxY = minY + config.getHeight(level);
	   
	   Hunter hunter=new Hunter(600,550,scene);
	   
	   vb = new VacuumBar(scene);
	   hb = new HealthBar(scene);
	   st = new Timer(config.getLevelTime(level),scene);
	   sc = new ScoreCounter(scene);
	   tokenController = new TokenController(area, pa);
	  

	   
	   ImageView areaImage = new ImageView("file:resources/level" + level + ".png");
	   areaImage.fitWidthProperty().bind(scene.widthProperty());
	   areaImage.fitHeightProperty().bind(scene.heightProperty());
	   
	   area.getChildren().addAll(areaImage,vb,hb,pa,st,sc,hunter.getShape());
	   
	   
	   //create ghosts
	   for(int i=0;i<config.getGhostCount(level);i++) {
		   double x = minX + Math.random() * (maxX-minX);
           double y = minY + Math.random() * (maxY-minY);
           
           Ghost ghost=new Ghost(x,y,false);
           entities.add(ghost);
		   area.getChildren().addAll(ghost.getShape()); 
	   }
	   
	  // create rippers
	   for(int i=0;i<config.getRipperCount(level);i++) {
		   double x = minX + Math.random() * (maxX-minX);
           double y = minY + Math.random() * (maxY-minY);
           
           Ripper ripper=new Ripper(x,y,false);
           entities.add(ripper);
		   area.getChildren().addAll(ripper.getShape()); 
	   }
	  // create wisps
	   for(int i=0;i<config.getWispCount(level);i++) {
		   double x = minX + Math.random() * (maxX-minX);
           double y = minY + Math.random() * (maxY-minY);
           
           Wisp wisp = new Wisp(x,y,false);
           entities.add(wisp);
		   area.getChildren().addAll(wisp.getShape()); 
	   }
	  
	   
	   scene.setOnKeyPressed(keyEvent -> { 
		    if (keyEvent.getCode() == KeyCode.H) {
		        for (Entity ent : entities) {
		            ent.setVisible(true);
		        }
		    }
		});

		scene.setOnKeyReleased(keyReleaseEvent -> { 
		    if (keyReleaseEvent.getCode() == KeyCode.H) {
		        for (Entity ent : entities) {
		            ent.setVisible(false);
		        }
		    }
		});
	   
	   this.animation=new AnimationTimer() {
			public void handle (long now) {
				
			    hunter.update(minX, minY, maxX, maxY,vb);
			    tokenController.update(now, hunter, hb, entities);
				
				for(int i=0;i<entities.size();i++) {
					Entity ent = entities.get(i);
					
					if(ent  instanceof Wisp) {
				    ((Wisp)(entities.get(i))).updateAnimation();
				    }
					
					ent.move(minX, minY, maxX, maxY);
					//health bar
					 if(hunter.getShape().getBoundsInParent().intersects(ent.getShape().getBoundsInParent())) {
		                	hb.setBar(-2/60.0);
		                	checkGameStatus();
		                }
					
						 
                // vakum kontrol
		            if (hunter.isVacuumActive() && vb.getCurrentAmount() > 0 && hunter.getVacuumShape().getBoundsInParent().intersects(ent.getShape().getBoundsInParent())) {
		                
		                double currentScale = ent.getShape().getScaleX(); // Mevcut büyüklüğü al
		                
		                if (currentScale < 0.15) {
		                   
		                    area.getChildren().remove(ent.getShape());
		                    entities.remove(i);
		                    
		                    if(ent instanceof Ghost) {
		                       sc.addScore(10); // Skor ekle
		                    }
		                    else if(ent instanceof Ripper) {
		                    	sc.addScore(20);
		                    }
		                    else {
		                    	sc.addScore(30);
		                    }
		                    checkGameStatus();
		                    i--; // KRİTİK: Liste kısaldığı için indexi geri çekiyoruz!
		                    continue;
		                    
		                } else {
		                    
		                    ent.getShape().setScaleX(currentScale - 0.005);
		                    ent.getShape().setScaleY(currentScale - 0.005);
		                }
		               
		              
		            } 

				}
				
			}
		};
		animation.start();
		area.setFocusTraversable(true);
		area.requestFocus();
		
}

 // win panel lose panel olabilir
 public void checkGameStatus() {
        // kazanma durumu
        if (entities.isEmpty()) {
        	animation.stop();
            WinPanel wp = new WinPanel(() -> {
                System.out.println("Sonraki seviyeye geçiliyor...");
                // Burada bir sonraki level için GameArea'yı tekrar kurabilirsin
            });
            wp.setPrefSize(450, 250);
            double centerX = pa.pArea.getLayoutX() + (pa.pArea.getWidth() / 2) - (wp.getPrefWidth() / 2);
            double centerY = pa.pArea.getLayoutY() + (pa.pArea.getHeight() / 2) - (wp.getPrefHeight() / 2);

            wp.setLayoutX(centerX);
            wp.setLayoutY(centerY);
            area.getChildren().add(wp);
        }
            //kaybetme durumu
         if(hb.getCurrentHealth()<=0) {
        	 animation.stop();
        	 LosePanel lp=new LosePanel(sc.getScore(), () -> restart(this.currentLevel), () -> goToMenu());
        	 lp.setPrefSize(450, 250);
             double centerhX = pa.pArea.getLayoutX() + (pa.pArea.getWidth() / 2) - 225;
             double centerhY = pa.pArea.getLayoutY() + (pa.pArea.getHeight() / 2) - 125;
             lp.setLayoutX(centerhX);
             lp.setLayoutY(centerhY);
        	 area.getChildren().add(lp);
        	 lp.toFront();
         }
        }
   // gameover panelini almadım diye metodu bitirdim en son kapalı parantez 
        // kaybetme
      /*  
        }
    }  */
 public void restart(int level) {
	 if (this.animation != null) {
	        this.animation.stop();
	    }
	 GameArea newLevel = new GameArea(level); 
	    Test.mainStage.setScene(newLevel.getScene());
	 
 }
 public void goToMenu() {
	 if (this.animation != null) {
	        this.animation.stop();
	    }

	    // 2. Ana menü sınıfından yeni bir nesne oluştur
	    MainMenuScene mainMenu = new MainMenuScene(); 
	    
	    // 3. Statik Stage referansını kullanarak sahneyi menü sahnesiyle değiştir
	    Test.mainStage.setScene(mainMenu.getScene());
 }
 public int getCurrentLevel() {
	 return currentLevel;
 }
   public Scene getScene() {
		return scene;
	   }
	   
   }
