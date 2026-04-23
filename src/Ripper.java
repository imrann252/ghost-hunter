import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;

public class Ripper extends Entity {
    private Group view;
   

    public Ripper(double x, double y,boolean isVisible) {
        view = new Group();
       isVisible=false;
        // 1. 12 Çıkıntılı (24 Noktalı) Geniş Gövde
        Polygon body = new Polygon();
        Double[] points = new Double[48]; // 24 nokta * (x,y) = 48 değer
        
        double outerRadius = 35.0; // Dikenlerin uç noktası
        double innerRadius = 22.0; // Gövdenin geniş kalması için iç yarıçap
        
        for (int i = 0; i < 24; i++) {
            double angle = Math.toRadians(i * (360.0 / 24.0));
            double r = (i % 2 == 0) ? outerRadius : innerRadius;
            points[i * 2] = r * Math.cos(angle);     // x koordinatı
            points[i * 2 + 1] = r * Math.sin(angle); // y koordinatı
        }
        
        body.getPoints().addAll(points);
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
        view.getChildren().addAll(body, head, leftEye, rightEye);

        // Belirlenen koordinatlara yerleştir
        view.setLayoutX(x);
        view.setLayoutY(y);
    }

    @Override
	void move() {
		// TODO Auto-generated method stub
		this.x=this.x+0.01;
		this.y=this.y+0.01;
	}

	@Override
	public void setVisible(boolean visibility) {
		// TODO Auto-generated method stub
		super.setVisible(visibility);
	}

	public Group getView() {
        return view;
    }
}