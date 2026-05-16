
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;


public class Ripper extends Entity {
    

    public Ripper(double x, double y,boolean isVisible) {
    	
    	this.dx = (random.nextDouble() * 2 ) * (random.nextBoolean() ? 1 : -1);
        this.dy = (random.nextDouble() * 2 ) * (random.nextBoolean() ? 1 : -1);
        
    	this.isVisible=isVisible;
        shape = new Group();
	     
       
        // 1. 12 Çıkıntılı (24 Noktalı) Geniş Gövde
        Polygon body = new Polygon();
     
        body.getPoints().addAll(new Double[]{
                35.0,  0.0,   // 1. Diken ucu (En sağ)
                20.0,  6.0,   // İç girinti
                28.0, 18.0,   // 2. Diken ucu
                12.0, 14.0,   // İç girinti
                18.0, 28.0,   // 3. Diken ucu
                 6.0, 20.0,   // İç girinti
                 0.0, 35.0,   // 4. Diken ucu (En alt)
                -6.0, 20.0,   // İç girinti
               -18.0, 28.0,   // 5. Diken ucu
               -12.0, 14.0,   // İç girinti
               -28.0, 18.0,   // 6. Diken ucu
               -20.0,  6.0,   // İç girinti
               -35.0,  0.0,   // 7. Diken ucu (En sol)
               -20.0, -6.0,   // İç girinti
               -28.0,-18.0,   // 8. Diken ucu
               -12.0,-14.0,   // İç girinti
               -18.0,-28.0,   // 9. Diken ucu
                -6.0,-20.0,   // İç girinti
                 0.0,-35.0,   // 10. Diken ucu (En üst)
                 6.0,-20.0,   // İç girinti
                18.0,-28.0,   // 11. Diken ucu
                12.0,-14.0,   // İç girinti
                28.0,-18.0,   // 12. Diken ucu
                20.0, -6.0    // İç girinti
           });
        
        body.setFill(Color.PURPLE); // Ripper mor gövdelidir 
        body.setStroke(Color.BLACK);

        // 2. Siyah Kafa (Merkezi Alan)
        Circle head = new Circle(0, 0, 15);
        head.setFill(Color.BLACK);

        // 3. Tehditkar Kırmızı Gözler
        Circle leftEye = new Circle(-5, -3, 2.5);
        leftEye.setFill(Color.RED);
        
        Circle rightEye = new Circle(5, -3, 2.5);
        rightEye.setFill(Color.RED);

        // Katmanları gruba ekle
        shape.getChildren().addAll(body, head, leftEye, rightEye);

        // Belirlenen koordinatlara yerleştir
        shape.setLayoutX(x);
        shape.setLayoutY(y);
        
        shape.setVisible(isVisible);
	     }
}