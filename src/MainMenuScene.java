
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

public class MainMenuScene {
   private Scene scene;
   
   public MainMenuScene() {
	   
	   Pane main=new Pane();
	   
	   ImageView mainImage= new ImageView("file:resources/main_menu.png.png");
	   mainImage.setFitHeight(500);
	   mainImage.setFitWidth(1000);
	   main.getChildren().add(mainImage);
	   
	   HBox hbox= new HBox(35);
	   hbox.setLayoutX(250); // HBox'ı ekranın ortasına doğru kaydırır
	   hbox.setLayoutY(360);
	   
	   Rectangle r1= new Rectangle(122,50);
	   r1.setFill(Color.MEDIUMPURPLE);
	   Text t1= new Text(155,425,"START GAME");
	   t1.setFill(Color.WHITE);
	   t1.setFont(Font.font("Courier",FontWeight.EXTRA_BOLD,FontPosture.REGULAR,15));
	   
	   StackPane stack= new StackPane(r1,t1);
	   Button b1=new Button("",stack);
	   b1.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
	   
	   
	   Rectangle r2= new Rectangle(122,50);
	   r2.setFill(Color.MEDIUMPURPLE);
	   Text t2= new Text(155,425,"SELECT LEVEL");
	   t2.setFill(Color.WHITE);
	   t2.setFont(Font.font("Courier",FontWeight.EXTRA_BOLD,FontPosture.REGULAR,15));
	   
	   StackPane stack2= new StackPane(r2,t2);
	   Button b2=new Button("",stack2);
	   b2.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
	   
	   
	   
	   Rectangle r3= new Rectangle(122,50);
	   r3.setFill(Color.MEDIUMPURPLE);
	   Text t3= new Text(155,425,"EXIT");
	   t3.setFill(Color.WHITE);
	   t3.setFont(Font.font("Courier",FontWeight.EXTRA_BOLD,FontPosture.REGULAR,15));
	   
	   StackPane stack3= new StackPane(r3,t3);
	   Button b3=new Button("",stack3);
	   b3.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
	   
	 
	   hbox.getChildren().addAll(b1,b2,b3);
	   main.getChildren().add(hbox);
	   
	   
	   
	   this.scene=new Scene(main,1000,500);
	   
   }

   public Scene getScene() {
	return scene;
   }
}
