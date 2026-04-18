import com.sun.glass.ui.Application;

public class Test extends Application {
	public void start(Stage primaryStage) {
		Pane pane=new Pane();
		
		Ripper rip= new Ripper(200.0,100.0);
		
	}

	public static void main(String[] args) {
		launch(args);
	}

}
