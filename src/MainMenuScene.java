
import javafx.beans.property.Property;
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
	   this.scene=new Scene(main,1300,800);
	  
	   
	   ImageView mainImage= new ImageView("file:resources/main_menu.png");
	   mainImage.fitWidthProperty().bind(scene.widthProperty());//fza
	   mainImage.fitHeightProperty().bind(scene.heightProperty());//fza
	   main.getChildren().add(mainImage);
	   
	   HBox hbox= new HBox(35);
	   hbox.layoutXProperty().bind(scene.widthProperty().subtract(hbox.widthProperty()).divide(2));//fza
	   hbox.layoutYProperty().bind(scene.heightProperty().subtract(hbox.heightProperty()).divide(1.25));//fza
	   
	   Font spooky = Font.loadFont(getClass().getResourceAsStream("Spooky.ttf"), 18);
	   
	   Rectangle r1= new Rectangle(175,75);
	   r1.setArcHeight(20);
	   r1.setArcWidth(20);
	   r1.setFill(Color.MEDIUMPURPLE);
	   Text t1= new Text("START GAME");
	   t1.setFill(Color.WHITE);
	   t1.setFont(spooky);
	   
	   StackPane stack= new StackPane(r1,t1);
	   Button b1=new Button("",stack);
	   b1.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
	   
	   b1.setOnMouseEntered( e -> {
		   r1.setFill(Color.WHITE);
		   t1.setFill(Color.RED);});
	   
	   b1.setOnMouseExited(e -> {
		   r1.setFill(Color.MEDIUMPURPLE);
		   t1.setFill(Color.WHITE);
		});
	   

	   
	   Rectangle r2= new Rectangle(175,75);
	   r2.setArcHeight(20);
	   r2.setArcWidth(20);
	   r2.setFill(Color.MEDIUMPURPLE);
	   Text t2= new Text("SELECT LEVEL");
	   t2.setFill(Color.WHITE);
	   t2.setFont(spooky);
	   
	   StackPane stack2= new StackPane(r2,t2);
	   Button b2=new Button("",stack2);
	   b2.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
	   b2.setOnMouseClicked(e -> {
			  SelectLevelScene sls= new SelectLevelScene();
			  Test.mainStage.setScene(sls.getScene());
			  
		  });
		   
	   b2.setOnMouseEntered( e -> {
		   r2.setFill(Color.WHITE);
		   t2.setFill(Color.RED);});
	   
	   b2.setOnMouseExited(e -> {
		   r2.setFill(Color.MEDIUMPURPLE);
		   t2.setFill(Color.WHITE);
		});
	   
	  
	   
	   Rectangle r3= new Rectangle(175,75);
	   r3.setArcHeight(20);
	   r3.setArcWidth(20);
	   r3.setFill(Color.MEDIUMPURPLE);
	   Text t3= new Text("EXIT");
	   t3.setFill(Color.WHITE);
	   t3.setFont(spooky);
	   
	   
	   StackPane stack3= new StackPane(r3,t3);
	   Button b3=new Button("",stack3);
	   b3.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
	   
	   b3.setOnMouseEntered( e -> {
		   r3.setFill(Color.WHITE);
		   t3.setFill(Color.RED);});
	   
	   b3.setOnMouseExited(e -> {
		   r3.setFill(Color.MEDIUMPURPLE);
		   t3.setFill(Color.WHITE);
		});
	   
	   
	   hbox.scaleXProperty().bind(scene.widthProperty().divide(1300));//fza
	   hbox.scaleYProperty().bind(scene.heightProperty().divide(800));//fza
	   
	   
	   hbox.getChildren().addAll(b1,b2,b3);
	   main.getChildren().add(hbox);
	   
	   
	   
	   
	   
   }

   public Scene getScene() {
	return scene;
   }
}
