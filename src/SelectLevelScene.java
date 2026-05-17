import javafx.geometry.Rectangle2D;
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
import javafx.stage.Screen;
//Imran_Akar_150124087
//Purpose:Handles level navigation allowing the player to jump directly to specific acts.
public class SelectLevelScene {
	private Scene scene;
	
	
	public SelectLevelScene() {
		
		
		Pane selectLevel =new Pane();
		Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
		this.scene = new Scene(selectLevel,visualBounds.getWidth(), visualBounds.getHeight());
		   
	   
	   ImageView slevel= new ImageView("file:resources/select_level.png");
	   slevel.fitWidthProperty().bind(scene.widthProperty());
	   slevel.fitHeightProperty().bind(scene.heightProperty());
	   selectLevel.getChildren().add(slevel);
	   
	   VBox vbox = new VBox(60);
	   vbox.layoutXProperty().bind(scene.widthProperty().subtract(vbox.widthProperty()).divide(2));//fza
	   vbox.layoutYProperty().bind(scene.heightProperty().subtract(vbox.heightProperty()).divide(2));//fza
	   
	   Font bloody = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 40);
	   
	   //Level 1 Button Setup
	   Rectangle r1= new Rectangle(150,120);
	   r1.setFill(Color.PURPLE);
	   ImageView im1=new ImageView("file:resources/level1.png");
	   im1.fitWidthProperty().bind(r1.widthProperty().multiply(0.85));//fza
	   im1.fitHeightProperty().bind(r1.heightProperty().multiply(0.85));//fza
	   Text t1= new Text(455,115,"1");
	   t1.setFill(Color.WHITE);
	   t1.setFont(bloody);
	   StackPane sp1= new StackPane();
	   sp1.getChildren().addAll(r1,im1,t1);
	   Button b1= new Button("",sp1);
	   b1.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
	   //Highlights the level box when cursor hovers over Level 1.
	   b1.setOnMouseEntered((e) -> {
		    r1.setFill(Color.MEDIUMPURPLE); 
		});
	   //Reverts layout paint constraints back to default dark purple state.
	   b1.setOnMouseExited(e -> {
		    r1.setFill(Color.PURPLE);
		});
	   //Triggers initialization of Level 1 inside the central GameArea controller.
	   b1.setOnMouseClicked( e -> {r1.setFill(Color.WHITE);
	     GameArea game= new GameArea(1);
	     Test.mainStage.setScene(game.getScene());});
	   
	   
	   //Level 2 Button Setup
	   Rectangle r2= new Rectangle(150,120);
	   r2.setFill(Color.PURPLE);
	   ImageView im2=new ImageView("file:resources/level2.png");
	   im2.fitWidthProperty().bind(r2.widthProperty().multiply(0.85));//fza
	   im2.fitHeightProperty().bind(r2.heightProperty().multiply(0.85));//fza
	   Text t2= new Text(455,140,"2");
	   t2.setFill(Color.WHITE);
	   t2.setFont(bloody);
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
	   //Triggers initialization of Level 2 inside the central GameArea controller.
	   b2.setOnMouseClicked( e -> {r2.setFill(Color.WHITE);
	     GameArea game= new GameArea(2);
	     Test.mainStage.setScene(game.getScene());
	   });
	   
	   //Level 3 Setup
	   Rectangle r3= new Rectangle(150,120);
	   r3.setFill(Color.PURPLE);
	   ImageView im3=new ImageView("file:resources/level3.png");
	   im3.fitWidthProperty().bind(r3.widthProperty().multiply(0.85));//fza
	   im3.fitHeightProperty().bind(r3.heightProperty().multiply(0.85));//fza
	   Text t3= new Text(455,165,"3");
	   t3.setFill(Color.WHITE);
	   t3.setFont(bloody);
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
	 //Triggers initialization of Level 1 inside the central GameArea controller.
	   b3.setOnMouseClicked( e -> {r3.setFill(Color.WHITE);
	     GameArea game= new GameArea(3);
	     Test.mainStage.setScene(game.getScene());
	   
	   });
	   
	   //Binds global layout transformation variables across horizontal and vertical scale ratios.
	   vbox.scaleXProperty().bind(scene.widthProperty().divide(1300));
	   vbox.scaleYProperty().bind(scene.heightProperty().divide(800));
	   
	   vbox.getChildren().addAll(b1,b2,b3);
	   selectLevel.getChildren().add(vbox);
	   

}
//Returns the fully constructed level selector scene instance.
public Scene getScene() {
	return scene;
}
}

