
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class VacuumBar extends Pane {
    private double maxHeight = 250; 
    private double maxAmount = 100;
    private double currentAmount =100;
    private Rectangle rec2;

    public VacuumBar(Scene scene) {

        // Arka Plan (Sınır)
        Rectangle rec1 = new Rectangle(50, maxHeight); 
        rec1.setStroke(Color.BLACK);
        rec1.setStrokeWidth(30); // Dökümandaki gibi kalın kenarlık [cite: 36]
        rec1.setLayoutX(40);
        rec1.setLayoutY(80);
        
        // Enerji Çubuğu
        rec2 = new Rectangle(50, maxHeight);
        rec2.setFill(Color.PURPLE.darker()); 
        rec2.setLayoutX(40);
        rec2.setLayoutY(80);
        this.rec2 = rec2;


        
        Font bloody = Font.loadFont(getClass().getResourceAsStream("Bloody.ttf"), 30);

        Label vacLabel = new Label("VACUUM:");
        vacLabel.setFont(bloody); 
        vacLabel.setTextFill(Color.WHITE); 
        vacLabel.setLayoutX(30);
        vacLabel.setLayoutY(25);
        
        this.getChildren().addAll(rec1, rec2, vacLabel);
        this.setLayoutX(0);
        this.setLayoutY(0);

        this.scaleXProperty().bind(scene.widthProperty().divide(1300));
        this.scaleYProperty().bind(scene.heightProperty().divide(800));
    }

    // Enerjiyi güncellemek için yardımcı metod
    public void setBar(double amount) {
        currentAmount += amount;

        // Sınırları koru (0 ile 100 arası) [cite: 204]
        if (currentAmount > maxAmount) currentAmount = maxAmount;
        if (currentAmount < 0) currentAmount = 0;

        // Görsel güncelleme
        double newHeight = (currentAmount / maxAmount) * maxHeight;
        this.rec2.setHeight(newHeight);
        
        // Alttan yukarı dolması için: Başlangıç Y (80) + Boş kalan kısım
        this.rec2.setLayoutY(80 + (maxHeight - newHeight)); 
    }

    public double getCurrentAmount() {
        return currentAmount;
    }
}