import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class GameArea {
   private Scene scene;
   private VacuumBar vb ;
   private HealthBar hb ;
   private Entity[] entities;
   
   public GameArea() {
	   
	   Pane area =new Pane();
	   this.scene = new Scene(area,1300,800);
	   
	   vb = new VacuumBar(0,0,100);
	   hb = new HealthBar(0,0,100,scene);
	   
	   PlayableArea pa=new PlayableArea(200,0,800,700);
	   entities= new Entity[] {
			   new Ripper(230.0,230.0,true),
			   new Ripper(460.0,560.0,true),
			   new Ripper(600.0,340.0,true),
			   new Wisp(450.0,650.0,true),
			   new Wisp(800.0,110.0,true)
	   };
	 
	   ImageView areaImage = new ImageView("file:resources/level3.png");
	   areaImage.fitWidthProperty().bind(scene.widthProperty());
	   areaImage.fitHeightProperty().bind(scene.heightProperty());
	   
	   area.getChildren().addAll(areaImage,vb,hb,pa);
	   for(int i=0;i<5;i++) {
		   area.getChildren().addAll(entities[i].getShape()); 
	   }
	   AnimationTimer animation=new AnimationTimer() {
			public void handle (long now) {
				for(int i=0;i<5;i++) {
					if(entities[i]  instanceof Wisp) {
				    ((Wisp)(entities[i])).updateAnimation();
				    entities[i].move(200.0,0.0,1000.0,700.0);}
					
				entities[i].move(200.0,0.0,1000.0,700.0);
				}
			}
		};
		animation.start();
	 
	   
	   
	   
   }
   
   public Scene getScene() {
		return scene;
	   }
	   
   }