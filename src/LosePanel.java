import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class LosePanel extends StackPane {

    public LosePanel(double width, double height, double finalScore, GameArea gameArea) {
        // Panel Boyutları ve Arka Plan (Tamamen Beyaz)
        this.setPrefSize(width, height);
        this.setMinSize(width, height);
        this.setMaxSize(width, height);
        this.setStyle("-fx-background-color: white; -fx-background-radius: 15;");

        // Fontlar
        Font bloodyFont = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 50);
        Font bloodySmall = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 22);

        // Etiketler
        Label gameo = new Label("GAME OVER");
        gameo.setFont(bloodyFont);
        gameo.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");

        Label score = new Label("FINAL SCORE: " + finalScore);
        score.setFont(bloodySmall);
        score.setStyle("-fx-text-fill: red;");

        // Butonlar
        Button retry = new Button("RETRY");
        Button mainmenu = new Button("MAIN MENU");

        retry.setFont(bloodySmall);
        mainmenu.setFont(bloodySmall);
        retry.setPrefSize(160, 50);
        mainmenu.setPrefSize(160, 50);

        String idleStyle = "-fx-background-color: #808080; -fx-text-fill: white; -fx-background-radius: 5;";
        String pressedStyle = "-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 5;";

        retry.setStyle(idleStyle);
        mainmenu.setStyle(idleStyle);

        // Retry Buton Efektleri
        retry.setOnMousePressed(e -> retry.setStyle(pressedStyle));
        retry.setOnMouseReleased(e -> {
            retry.setStyle(idleStyle);
            gameArea.restart(gameArea.getCurrentLevel());
        });

        // Main Menu Buton Efektleri
        mainmenu.setOnMousePressed(e -> mainmenu.setStyle(pressedStyle));
        mainmenu.setOnMouseReleased(e -> {
            mainmenu.setStyle(idleStyle);
            gameArea.goToMenu();
        });

        HBox hbox = new HBox(20, retry, mainmenu);
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(25, gameo, score, hbox);
        vbox.setAlignment(Pos.CENTER);

        this.getChildren().add(vbox);
    }
}