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
   public Timer st;
   public ScoreCounter sc;
	
   
   public GameArea() {
	   
	   Pane area =new Pane();
	   this.scene = new Scene(area,1300,800);
	   
	   vb = new VacuumBar(0,10,100,scene);
	   hb = new HealthBar(0,10,100,scene);
	   st = new Timer(120,scene);
	   sc = new ScoreCounter(scene);
	  sc.addScore(100);
	   ImageView areaImage= new ImageView("file:resources/level1.png");
	   areaImage.fitWidthProperty().bind(scene.widthProperty());
	   areaImage.fitHeightProperty().bind(scene.heightProperty());
	   
	   
	   
	   ConfigLoader config = ConfigLoader.getInstance();

	   // 1. Level'ın süresini çekelim
	   int initialTime = config.getLevelTime(1); // 60 dönecektir 
	   st = new Timer(initialTime, scene);

	   // Karakter sağlığını belirleyelim
	   int health = config.getMaxHealth(); // 100 dönecektir
	   area.getChildren().addAll(areaImage,vb,hb,st,sc);
   }
   
   public Scene getScene() {
		return scene;
	   }
	   
   }