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

public class GameArea {
   private Scene scene;
   private VacuumBar vb ;
   private HealthBar hb ;

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
	   
	   
	   ConfigLoader config=ConfigLoader.getInstance();
	
	   
	   this.area =new Pane();
	   
	   Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
	   this.scene = new Scene(area,visualBounds.getWidth(), visualBounds.getHeight());
	   
	   
	   this.pa = new PlayableArea(config.getAreaX(level),config.getAreaY(level),config.getWidth(level),config.getHeight(level),scene);
	   
	   double minX = pa.pArea.getLayoutX();
	   double minY = pa.pArea.getLayoutY();
	   double maxX = minX + config.getWidth(level);
	   double maxY = minY + config.getHeight(level);
	   
	   Hunter hunter=new Hunter(600,550,scene);
	   
	   vb = new VacuumBar(0,0,100,scene);
	   hb = new HealthBar(0,0,100,scene);
	   st = new Timer(config.getLevelTime(level),scene);
	   sc = new ScoreCounter(scene);
	  
	   double a = minX + 15 + Math.random() * ((maxX - minX) - 30);
       double b = minY + 15 + Math.random() * ((maxY-minY)- 30);
	   EyeToken eye = new EyeToken(a,b);
	   
	   
	 
	   
	   ImageView areaImage = new ImageView("file:resources/level" + level + ".png");
	   areaImage.fitWidthProperty().bind(scene.widthProperty());
	   areaImage.fitHeightProperty().bind(scene.heightProperty());
	   
	   area.getChildren().addAll(areaImage,vb,hb,pa,st,sc,hunter.getShape(),eye);
	   
	   
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
	  
	   int initialTime = config.getLevelTime(level); 
	   st = new Timer(initialTime, scene);
	   
	   scene.setOnKeyPressed(e-> {
		   if(e.getCode()==KeyCode.H) {
			   for(Entity ent: entities) {
				   ent.setVisible(true);
			   }
		   }
	   });
	   scene.setOnKeyReleased(e-> {
		   if(e.getCode()==KeyCode.H) {
			   for(Entity ent: entities) {
				   ent.setVisible(false);
			   }
		   }
	   });
	   
	   this.animation=new AnimationTimer() {
			public void handle (long now) {
				// hunter.update(pa.pArea.getLayoutX(),pa.pArea.getLayoutY(),pa.pArea.getLayoutX()+config.getWidth(level),pa.pArea.getLayoutY()+config.getHeight(level));
                double minX = pa.pArea.getLayoutX();
		        double minY = pa.pArea.getLayoutY();
		        double maxX = minX + config.getWidth(level);
		        double maxY = minY + config.getHeight(level);

			    hunter.update(minX, minY, maxX, maxY);
				
				eye.collect(hunter, entities,area);
				for(int i=0;i<entities.size();i++) {

					Entity ent = entities.get(i);
					
					if(entities.get(i)  instanceof Wisp) {
				    ((Wisp)(entities.get(i))).updateAnimation();
				    }
					
				
				entities.get(i).move(minX, minY, maxX, maxY);
                // vakum kontrol
		            if (hunter.isVacuumActive() && hunter.getVacuumShape().getBoundsInParent().intersects(ent.getShape().getBoundsInParent())) {
		                
		                double currentScale = ent.getShape().getScaleX(); // Mevcut büyüklüğü al
		                
		                if (currentScale < 0.15) {
		                   
		                    area.getChildren().remove(ent.getShape());
		                    entities.remove(i);
		                    sc.addScore(10); // Skor ekle
		                    checkGameStatus();
		                } else {
		                    
		                    ent.getShape().setScaleX(currentScale - 0.01);
		                    ent.getShape().setScaleY(currentScale - 0.01);
		                }
		            } else {
		                
		                if (ent.getShape().getScaleX() < 1.0) {
		                    ent.getShape().setScaleX(ent.getShape().getScaleX() + 0.01);
		                    ent.getShape().setScaleY(ent.getShape().getScaleY() + 0.01);
		                }
		            }


					
				}
				
			}
		};
		animation.start();
		//System.out.println(pa.pArea.getLayoutX());

		
		/*int max_health = config.getMaxHealth();
		   int max_vacuum = config.getMaxVacuum();
		   int entity_damage = config.getEntityDamage();
		   
		   int health_token_increase = config.getHealthTokenIncrease();
		   int vacuum_token_increase = config.getVacuumTokenIncrease();
		   int eye_token_duration = config.getEyeTokenDuration();*/
		
	   /*

	 
	   sc = new ScoreCounter(scene);
	  sc.addScore(100);
	  
	   // Karakter sağlığını belirleyelim
	   int health = config.getMaxHealth(); // 100 dönecektir
	   area.getChildren().addAll(areaImage,vb,hb,st,sc);
	   */
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
        }}
   // gameover panelini almadım diye metodu bitirdim en son kapalı parantez 
        // kaybetme
      /*  if (st.getTime() <= 0) { //buraya canda gelcek
            animation.stop();
            LosePanel lp = new LosePanel(sc.getScore(), () -> {
                // Retry Aksiyonu: Mevcut seviyeyi yeniden başlat
            }, () -> {
                // Menu Aksiyonu
            });
            root.getChildren().add(lp);
        }
    }  */
   public Scene getScene() {
		return scene;
	   }
	   
   }
