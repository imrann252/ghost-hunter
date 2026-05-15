


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class WinPanel extends StackPane {
    public WinPanel(double width, double height, GameArea gameArea) {
        this.setPrefSize(width, height);
        
        // Karartma katmanı
        Rectangle backgroundOverlay = new Rectangle(width, height);
        backgroundOverlay.setFill(Color.rgb(0, 0, 0, 0.7));

        Font bloodyFont = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 60);
        Font bloodySmall = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 30);

        Label winup = new Label("YOU WIN!");
        winup.setTextFill(Color.WHITE); // Kazandığında daha görkemli olması için
        winup.setFont(bloodyFont);

        Label winsub = new Label("YOU'VE VACUUMED ALL THE GHOSTS!");
        winsub.setTextFill(Color.WHITE);
        winsub.setFont(bloodySmall);

        Button nextbtn = new Button("NEXT LEVEL");
        nextbtn.setFont(bloodySmall);
        nextbtn.setMinWidth(250);
        nextbtn.setStyle("-fx-background-color: purple; -fx-text-fill: white; -fx-cursor: hand;");

        nextbtn.setOnMousePressed(e -> nextbtn.setStyle("-fx-background-color: red; -fx-text-fill: white;"));
        nextbtn.setOnMouseReleased(e -> {
            nextbtn.setStyle("-fx-background-color: purple; -fx-text-fill: white;");
            int nextLvl = (gameArea.getCurrentLevel() < 3) ? (gameArea.getCurrentLevel() + 1) : 1;
            gameArea.restart(nextLvl);
        });

        VBox vbox = new VBox(25, winup, winsub, nextbtn);
        vbox.setAlignment(Pos.CENTER);

        this.getChildren().addAll(backgroundOverlay, vbox);
    }
}