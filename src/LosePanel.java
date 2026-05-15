
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class LosePanel extends StackPane {
    public LosePanel(double width, double height, double finalScore, GameArea gameArea) {
        // Tüm ekranı kaplayan hafif karartılmış arka plan
        this.setPrefSize(width, height);
        this.setStyle("-fx-background-color: rgba(0, 0, 0, 0.3);"); 

        Font bloodyFont = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 50);
        Font bloodySmall = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 25);

        // Beyaz Orta Kutu (Görseldeki gibi)
        VBox whiteBox = new VBox(20);
        whiteBox.setAlignment(Pos.CENTER);
        whiteBox.setMaxSize(500, 300); // Kutunun boyutu
        whiteBox.setStyle("-fx-background-color: white; -fx-padding: 30; -fx-background-radius: 5;");

        Label gameo = new Label("GAME OVER");
        gameo.setFont(bloodyFont);
        gameo.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

        Label score = new Label("FINAL SCORE: " + finalScore);
        score.setFont(bloodySmall);
        score.setStyle("-fx-text-fill: red;");

        Button retry = new Button("RETRY");
        Button mainmenu = new Button("MAIN MENU");
        
        retry.setFont(bloodySmall);
        mainmenu.setFont(bloodySmall);
        retry.setMinWidth(180); mainmenu.setMinWidth(180);
        
        String idle = "-fx-background-color: gray; -fx-text-fill: white; -fx-cursor: hand;";
        String press = "-fx-background-color: red; -fx-text-fill: white;";
        retry.setStyle(idle);
        mainmenu.setStyle(idle);

        retry.setOnMousePressed(e -> retry.setStyle(press));
        retry.setOnMouseReleased(e -> {
            retry.setStyle(idle);
            gameArea.restart(gameArea.getCurrentLevel());
        });

        mainmenu.setOnMousePressed(e -> mainmenu.setStyle(press));
        mainmenu.setOnMouseReleased(e -> {
            mainmenu.setStyle(idle);
            gameArea.goToMenu();
        });

        HBox hbox = new HBox(15, retry, mainmenu);
        hbox.setAlignment(Pos.CENTER);

        whiteBox.getChildren().addAll(gameo, score, hbox);
        
        // Kutuyu ana panele ekle (StackPane olduğu için otomatik ortalar)
        this.getChildren().add(whiteBox);
    }
}