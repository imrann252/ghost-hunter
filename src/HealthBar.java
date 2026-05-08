import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class HealthBar extends Pane {
	private double currentHealth = 100;
	private double maxHeight = 250; 
	private double maxHealth = 100;
	Rectangle rec2;
	
	public HealthBar(Scene scene) {
	
		Rectangle rec1 = new Rectangle(50, 250); 
        rec1.setStroke(Color.BLACK);
        rec1.setStrokeWidth(30);
        rec1.setLayoutX(0);
        rec1.setLayoutY(80);
        
        Rectangle rec2 = new Rectangle(50, 250);
        rec2.setFill(Color.RED.darker());
        rec2.setLayoutX(0);
        rec2.setLayoutY(80);
        this.rec2=rec2;
        
        Font spooky = Font.loadFont(getClass().getResourceAsStream("Spooky.ttf"), 14);
        Label helLabel = new Label("HEALTH:");
        helLabel.setFont(spooky); 
        helLabel.setTextFill(Color.WHITE); 
        helLabel.setLayoutX(-10);
        helLabel.setLayoutY(25);
        
        
        this.getChildren().addAll(rec1, rec2,helLabel);
        
        this.setLayoutX(0);
        this.setLayoutY(0);
        this.setBar(0);
        
		this.layoutXProperty().bind(scene.widthProperty().subtract(this.widthProperty().add(10)));
		this.scaleXProperty().bind(scene.widthProperty().divide(1300));//fza
	 	this.scaleYProperty().bind(scene.heightProperty().divide(800));//fza

		
	}
	
	public void setBar(double amount) {
	    // Mevcut sağlığı artır
	    currentHealth += amount;

	    // Sağlığın 100'ü aşmasını veya 0'ın altına düşmesini engelle (Sınırlandırma)
	    if (currentHealth > maxHealth) currentHealth = maxHealth;
	    if (currentHealth < 0) currentHealth = 0;

	    // Yeni yüksekliği hesapla
	    double newHeight = (currentHealth / maxHealth) * maxHeight;
	    
	    // Görsel güncellemeler
	    this.rec2.setHeight(newHeight);
	    
	    // ÖNEMLİ: Barın alttan yukarıya doğru dolması için 
	    // toplam yükseklikten mevcut yüksekliği çıkarıp başlangıç Y noktasına ekliyoruz.
	    // rec1'in Y konumu 80 olduğu için referans noktamız 80.
	    this.rec2.setLayoutY(80 + (maxHeight - newHeight)); 
	}
	
	public double getCurrentHealth() {
		return currentHealth;
	}

}

