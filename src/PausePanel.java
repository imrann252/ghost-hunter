
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

public class PausePanel extends StackPane {

    public PausePanel(double width, double height, GameArea gameArea) {
        // Panel Boyutlarını Sabitle
        this.setPrefSize(width, height);
        this.setMinSize(width, height);
        this.setMaxSize(width, height);
        
        // SAYDAM MOR ARKA PLAN
        this.setStyle("-fx-background-color: rgba(75, 0, 130, 0.7); -fx-background-radius: 15;");

        Font bloodyFont = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 60);
        Font bloodySmall = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 25);

        Label pauseLabel = new Label("GAME PAUSED");
        pauseLabel.setTextFill(Color.WHITE);
        pauseLabel.setFont(bloodyFont);

        // Butonlar
        Button resumeBtn = new Button("RESUME");
        Button retryBtn = new Button("RETRY");
        Button menuBtn = new Button("MAIN MENU");

        resumeBtn.setFont(bloodySmall);
        retryBtn.setFont(bloodySmall);
        menuBtn.setFont(bloodySmall);

       

        // Buton Stilleri
        String idleStyle = "-fx-background-color: #808080; -fx-text-fill: white; -fx-background-radius: 5;";
        String pressedStyle = "-fx-background-color: red; -fx-text-fill: white; -fx-background-radius: 5;";

        resumeBtn.setStyle(idleStyle);
        retryBtn.setStyle(idleStyle);
        menuBtn.setStyle(idleStyle);

        // --- Buton Olayları (Basınca Kırmızı Olma) ---

        resumeBtn.setOnMousePressed(e -> resumeBtn.setStyle(pressedStyle));
        resumeBtn.setOnMouseReleased(e -> {
            resumeBtn.setStyle(idleStyle);
            gameArea.resume(); 
        });

        retryBtn.setOnMousePressed(e -> retryBtn.setStyle(pressedStyle));
        retryBtn.setOnMouseReleased(e -> {
            retryBtn.setStyle(idleStyle);
            gameArea.restart(gameArea.getCurrentLevel());
        });

        menuBtn.setOnMousePressed(e -> menuBtn.setStyle(pressedStyle));
        menuBtn.setOnMouseReleased(e -> {
            menuBtn.setStyle(idleStyle);
            gameArea.goToMenu();
        });

        HBox hbox = new HBox(20, resumeBtn, retryBtn, menuBtn);
        hbox.setAlignment(Pos.CENTER);

        VBox vbox = new VBox(50, pauseLabel, hbox);
        vbox.setAlignment(Pos.CENTER);

        this.getChildren().add(vbox);
    }
}