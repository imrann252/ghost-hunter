import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class SelectLevelScene {
	private Scene scene;
	public SelectLevelScene() {
   Pane selectLevel=new Pane();
	   
	   ImageView slevel= new ImageView("file:resources/select_level.png");
	   slevel.setFitHeight(500);
	   slevel.setFitWidth(1000);
	   selectLevel.getChildren().add(slevel);
	
	 this.scene=new Scene(selectLevel,1000,500);
	   
}

public Scene getScene() {
	return scene;
}
}

