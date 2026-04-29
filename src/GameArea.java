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
   public ScoreTimer st;
	
   
   public GameArea() {
	   
	   Pane area =new Pane();
	   this.scene = new Scene(area,1300,800);
	   
	   vb = new VacuumBar(0,0,100,scene);
	   hb = new HealthBar(0,0,100,scene);
	   st = new ScoreTimer(120,scene);
	   vb.setBar(50);
	   
	   
	   
	   ImageView areaImage= new ImageView("file:resources/level1.png");
	   areaImage.fitWidthProperty().bind(scene.widthProperty());
	   areaImage.fitHeightProperty().bind(scene.heightProperty());
	   
	   area.getChildren().addAll(areaImage,vb,hb,st);
	   
	   
	   
   }
   
   public Scene getScene() {
		return scene;
	   }
	   
   }