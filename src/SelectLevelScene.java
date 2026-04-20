import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class SelectLevelScene {
	private Scene scene;
	public SelectLevelScene() {
   Pane selectLevel=new Pane();
	   
	   ImageView slevel= new ImageView("file:resources/select_level.png");
	   slevel.setFitHeight(500);
	   slevel.setFitWidth(1000);
	   selectLevel.getChildren().add(slevel);
	   
	   VBox vbox= new VBox(20);
	   vbox.setLayoutX(445);
	   vbox.setLayoutY(110);
	   
	   
	   Rectangle r1= new Rectangle(80,60);
	   r1.setFill(Color.PURPLE);
	   ImageView im1=new ImageView("file:resources/level1.png");
	   im1.setFitHeight(45);
	   im1.setFitWidth(65);
	   Text t1= new Text(455,115,"1");
	   t1.setFill(Color.WHITE);
	   t1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
	   StackPane sp1= new StackPane();
	   sp1.getChildren().addAll(r1,im1,t1);
	   Button b1= new Button("",sp1);
	   b1.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
	   b1.setOnMouseEntered((e) -> {
		    r1.setFill(Color.MEDIUMPURPLE); 
		});
	   b1.setOnMouseExited(e -> {
		    r1.setFill(Color.PURPLE);
		});
	   b1.setOnMouseClicked( e -> {r1.setFill(Color.WHITE);});
	   
	   Rectangle r2= new Rectangle(80,60);
	   r2.setFill(Color.PURPLE);
	   ImageView im2=new ImageView("file:resources/level2.png");
	   im2.setFitHeight(45);
	   im2.setFitWidth(65);
	   Text t2= new Text(455,140,"2");
	   t2.setFill(Color.WHITE);
	   t2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
	   StackPane sp2= new StackPane();
	   sp2.getChildren().addAll(r2,im2,t2);
	   Button b2= new Button("",sp2);
	   b2.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
	   
	   b2.setOnMouseEntered((e) -> {
		    r2.setFill(Color.MEDIUMPURPLE); 
		});
	   b2.setOnMouseExited(e -> {
		    r2.setFill(Color.PURPLE);
		});
	   b2.setOnMouseClicked( e -> {r2.setFill(Color.WHITE);});
	   
	   
	   Rectangle r3= new Rectangle(80,60);
	   r3.setFill(Color.PURPLE);
	   ImageView im3=new ImageView("file:resources/level3.png");
	   im3.setFitHeight(45);
	   im3.setFitWidth(65);
	   Text t3= new Text(455,165,"3");
	   t3.setFill(Color.WHITE);
	   t3.setFont(Font.font("Arial", FontWeight.BOLD, 20));
	   StackPane sp3= new StackPane();
	   sp3.getChildren().addAll(r3,im3,t3);
	   Button b3= new Button("",sp3);
	   b3.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
	   
	   b3.setOnMouseEntered((e) -> {
		    r3.setFill(Color.MEDIUMPURPLE); 
		});
	   b3.setOnMouseExited(e -> {
		    r3.setFill(Color.PURPLE);
		});
	   b3.setOnMouseClicked( e -> {r3.setFill(Color.WHITE);});
	   
	   
	   vbox.getChildren().addAll(b1,b2,b3);
	   selectLevel.getChildren().add(vbox);
	   
	
	 this.scene=new Scene(selectLevel,1000,500);
	   
}

public Scene getScene() {
	return scene;
}
}

