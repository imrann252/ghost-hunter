
import javafx.beans.property.Property;
import javafx.geometry.Rectangle2D;
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
import javafx.stage.Screen;
//Imran_Akar_150124087
//Purpose:Represents the primary entrance screen of the game.
// Sets up background assets and core navigation buttons.
public class MainMenuScene {
   private Scene scene;
   
   
   public MainMenuScene() {
	   
	   Pane main=new Pane();
	   
	   // Fetch primary screen boundaries for true full-screen scaling
	   Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
	   this.scene = new Scene(main,visualBounds.getWidth(), visualBounds.getHeight());

	  
	   // Bind background image dimensions directly to the active scene properties
	   ImageView mainImage= new ImageView("file:resources/main_menu.png");
	   mainImage.fitWidthProperty().bind(scene.widthProperty());
	   mainImage.fitHeightProperty().bind(scene.heightProperty());
	   main.getChildren().add(mainImage);
	   
	   HBox hbox= new HBox(35);
	   hbox.layoutXProperty().bind(scene.widthProperty().subtract(hbox.widthProperty()).divide(2));//fza
	   hbox.layoutYProperty().bind(scene.heightProperty().subtract(hbox.heightProperty()).divide(1.25));//fza
	   
	   Font bloody = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 30);
	   
	   //Start Game Button Setup
	   Rectangle r1= new Rectangle(175,75);
	   r1.setArcHeight(20);
	   r1.setArcWidth(20);
	   r1.setFill(Color.MEDIUMPURPLE);
	   Text t1= new Text("START GAME");
	   t1.setFill(Color.WHITE);
	   t1.setFont(bloody);
	   t1.setLayoutY(-10);
	   
	   StackPane stack= new StackPane(r1,t1);
	   Button b1=new Button("",stack);
	   b1.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
	   
	// Updates the background and text color when mouse hovers over Start Game.
	   b1.setOnMouseEntered( e -> {
		   r1.setFill(Color.WHITE);
		   t1.setFill(Color.RED);});
	   
	 // Reverts the button colors back to default when mouse exits Start Game.
	   b1.setOnMouseExited(e -> {
		   r1.setFill(Color.MEDIUMPURPLE);
		   t1.setFill(Color.WHITE);
		});
	   
	   //Instantiates GameArea for Level 1 and updates the main stage scene upon click.
	   b1.setOnMouseClicked(e->{
		   GameArea game= new GameArea(1);
		   Test.mainStage.setScene(game.getScene());
	   });
	   

	 //Select Level Button Setup
	   Rectangle r2= new Rectangle(175,75);
	   r2.setArcHeight(20);
	   r2.setArcWidth(20);
	   r2.setFill(Color.MEDIUMPURPLE);
	   Text t2= new Text("SELECT LEVEL");
	   t2.setFill(Color.WHITE);
	   t2.setFont(bloody);
	   
	   StackPane stack2= new StackPane(r2,t2);
	   Button b2=new Button("",stack2);
	   b2.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
	   
	// Routes the player to the dedicated level selection panel
	   b2.setOnMouseClicked(e -> {
			  SelectLevelScene sls= new SelectLevelScene();
			  Test.mainStage.setScene(sls.getScene());
			  
		  });
	//Provides immediate visual feedback when hovering over Select Level button.   
	   b2.setOnMouseEntered( e -> {
		   r2.setFill(Color.WHITE);
		   t2.setFill(Color.RED);});
	  //Resets the level button appearance when the mouse leaves.
	   b2.setOnMouseExited(e -> {
		   r2.setFill(Color.MEDIUMPURPLE);
		   t2.setFill(Color.WHITE);
		});
	   
	  
	   //Exıt Button Setup
	   Rectangle r3= new Rectangle(175,75);
	   r3.setArcHeight(20);
	   r3.setArcWidth(20);
	   r3.setFill(Color.MEDIUMPURPLE);
	   Text t3= new Text("EXIT");
	   t3.setFill(Color.WHITE);
	   t3.setFont(bloody);
	   
	   
	   StackPane stack3= new StackPane(r3,t3);
	   Button b3=new Button("",stack3);
	   b3.setStyle("-fx-background-color: transparent; -fx-cursor: hand;");
	   //Adjusts layout cosmetics for the exit widget during active mouse enter events.
	   b3.setOnMouseEntered( e -> {
		   r3.setFill(Color.WHITE);
		   t3.setFill(Color.RED);});
	   
	   //Restores default purple style to exit button when mouse leaves.
	   b3.setOnMouseExited(e -> {
		   r3.setFill(Color.MEDIUMPURPLE);
		   t3.setFill(Color.WHITE);
		});
	   
	// Shuts down the runtime application cleanly
	   b3.setOnMouseClicked(e -> {
		   System.exit(0);
	   });
	   
	   //Binds horizontal and vertical scale coefficient dynamically to monitor width resolution properties.
	   hbox.scaleXProperty().bind(scene.widthProperty().divide(1300));//fza
	   hbox.scaleYProperty().bind(scene.heightProperty().divide(800));//fza
	   
	   
	   hbox.getChildren().addAll(b1,b2,b3);
	   main.getChildren().add(hbox);
	   
	   
   }
  //Returns the fully constructed main menu scene container.
   public Scene getScene() {
	return scene;
   }
}
