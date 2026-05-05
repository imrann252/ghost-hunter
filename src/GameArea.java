import java.util.ArrayList;
import java.util.List;

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
	

   
   public GameArea(int level) {
	   
	   
	   ConfigLoader config=ConfigLoader.getInstance();
	
	   
	   Pane area =new Pane();
	   
	   Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
	   this.scene = new Scene(area,visualBounds.getWidth(), visualBounds.getHeight());
	   
	   Hunter hunter=new Hunter(300,250,true,scene);
	   
	   vb = new VacuumBar(0,0,100,scene);
	   hb = new HealthBar(0,0,100,scene);
	   st = new Timer(config.getLevelTime(level),scene);
	   sc = new ScoreCounter(scene);
	  
	   
	   PlayableArea pa = new PlayableArea(config.getAreaX(level),config.getAreaY(level),config.getWidth(level),config.getHeight(level),scene);
	 
	   
	   ImageView areaImage = new ImageView("file:resources/level" + level + ".png");
	   areaImage.fitWidthProperty().bind(scene.widthProperty());
	   areaImage.fitHeightProperty().bind(scene.heightProperty());
	   
	   area.getChildren().addAll(areaImage,vb,hb,pa,st,sc,hunter.getShape());
	   //create ghosts
	   for(int i=0;i<config.getGhostCount(level);i++) {
		   double x = 200 + Math.random() * 600;
           double y = 100 + Math.random() * 400;
           
           Ghost ghost=new Ghost(x,y,false);
           entities.add(ghost);
		   area.getChildren().addAll(ghost.getShape()); 
	   }
	   
	  // create rippers
	   for(int i=0;i<config.getRipperCount(level);i++) {
		   double x = 200 + Math.random() * 600;
           double y = 100 + Math.random() * 400;
           
           Ripper ripper=new Ripper(x,y,false);
           entities.add(ripper);
		   area.getChildren().addAll(ripper.getShape()); 
	   }
	  // create wisps
	   for(int i=0;i<config.getWispCount(level);i++) {
		   double x = 200 + Math.random() * 600;
           double y = 100 + Math.random() * 400;
           
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
	   
	   AnimationTimer animation=new AnimationTimer() {
			public void handle (long now) {
			 hunter.update(pa.pArea.getLayoutX(),pa.pArea.getLayoutY(),pa.pArea.getLayoutX()+config.getWidth(level),pa.pArea.getLayoutY()+config.getHeight(level));
				for(int i=0;i<entities.size();i++) {
					
					if(entities.get(i)  instanceof Wisp) {
				    ((Wisp)(entities.get(i))).updateAnimation();
				    }
					
				hunter.update(pa.pArea.getLayoutX(),pa.pArea.getLayoutY(),pa.pArea.getLayoutX()+config.getWidth(level),pa.pArea.getLayoutY()+config.getHeight(level));
				entities.get(i).move(pa.pArea.getLayoutX(),pa.pArea.getLayoutY(),pa.pArea.getLayoutX()+config.getWidth(level),pa.pArea.getLayoutY()+config.getHeight(level));
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
  /* private void checkGameStatus() {
        // kazanma durumu
        if (entities.isEmpty()) {
            animation.stop();
            WinPanel wp = new WinPanel(() -> {
                System.out.println("Sonraki seviyeye geçiliyor...");
                // Burada bir sonraki level için GameArea'yı tekrar kurabilirsin
            });
            root.getChildren().add(wp);
        }

        // kaybetme
        if (st.getTime() <= 0) { //buraya canda gelcek
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
