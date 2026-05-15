import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Iterator;

public class TokenController {
    private Pane area;
    private PlayableArea pa;
    private ArrayList<Pane> activeTokens = new ArrayList<>();
    private long lastCreateTime = 0;

    public TokenController(Pane area, PlayableArea pa) {
        this.area = area;
        this.pa = pa;
    }

    public void update(long now, Hunter hunter, HealthBar hb, ArrayList<Entity> entities) {
        // 1. Dökümana göre 5 saniyede bir Create kontrolü 
        if (now - lastCreateTime >= 5_000_000_000L) {
            // En fazla 2 token olabilir kuralı 
            if (activeTokens.size() < 2) {
                createRandomToken();
            }
            lastCreateTime = now;
        }

        // 2. Token toplama (collision) ve listeden temizleme kontrolü
        Iterator<Pane> iterator = activeTokens.iterator();
        while (iterator.hasNext()) {
            Pane token = iterator.next();

            // Tipine göre ilgili collect metodunu çağırıyoruz
            if (token instanceof HealthToken) {
                ((HealthToken) token).collect(hunter, hb, area);
            } else if (token instanceof RangeToken) {
                ((RangeToken) token).collect(hunter, area);
            } else if (token instanceof EyeToken) {
                ((EyeToken) token).collect(hunter, entities, area);
            }else if (token instanceof ShieldToken) {
                ((ShieldToken) token).collect(hunter, area);
            }
            // Eğer token Pane'den kaldırılmışsa (collect edilmişse) listeden sil [cite: 140]
            if (!area.getChildren().contains(token)) {
                iterator.remove();
            }
        }
    }

    private void createRandomToken() {
        // PlayableArea sınırları içinde rastgele yer belirle [cite: 122, 136]
    	double minX = pa.pArea.getLayoutX();
 	   	double minY = pa.pArea.getLayoutY();
 	   	double maxX = minX + pa.pArea.getWidth();
 	   	double maxY = minY + pa.pArea.getHeight();

        double randomX = minX + Math.random() * (maxX - minX);
        double randomY = minY + Math.random() * (maxY - minY);

        // Rastgele tip seçimi [cite: 131, 136]
        int choice = (int) (Math.random() * 4);
        Pane newToken;

        if (choice == 0) {
            newToken = new HealthToken(randomX, randomY);
        } else if (choice == 1) {
            newToken = new RangeToken(randomX, randomY);
        } else if (choice == 2) {
            newToken = new EyeToken(randomX, randomY);
        }else {
        	newToken = new ShieldToken(randomX, randomY);
        }

        area.getChildren().add(newToken);
        activeTokens.add(newToken);
    }
}