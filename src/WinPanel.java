


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class WinPanel extends StackPane {

    
    public WinPanel(Runnable nextLevelAction) {
    	Font bloodyFont = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 50);
        Font bloodySmall = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 25);
       
        Rectangle rec = new Rectangle();
        rec.widthProperty().bind(this.widthProperty());
        rec.heightProperty().bind(this.heightProperty());
        rec.setFill(Color.rgb(0, 0, 0, 0.7));

        Label winup = new Label("YOU WIN!");
        Label winsub = new Label("YOU'VE VACUUMED ALL THE GHOSTS!");

        winup.setTextFill(Color.WHITE);
        winup.setFont(bloodyFont);
        winup.setStyle(winup.getStyle()+"-fx-font-weight: bold;");

        winsub.setTextFill(Color.WHITE);
        winsub.setFont(bloodySmall);
        winsub.setStyle(winup.getStyle()+"-fx-font-weight: bold;");
        

        Button nextbtn = new Button("NEXT LEVEL");
        nextbtn.setFont(bloodySmall);
        nextbtn.setStyle("-fx-background-color: purple; -fx-text-fill: white; ");

       
        nextbtn.setOnMousePressed(e -> {
            nextbtn.setStyle("-fx-background-color: red; -fx-text-fill: white; ");
        });

        nextbtn.setOnMouseReleased(e -> {
            nextbtn.setStyle("-fx-background-color: purple; -fx-text-fill: white; ");
            nextLevelAction.run(); // Bir sonraki levele geçiş fonksiyonunu tetikler burda
        });
        
        

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(winup, winsub, nextbtn);

        this.getChildren().addAll(rec, vbox);
    }
}
