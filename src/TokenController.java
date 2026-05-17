import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Iterator;

// Fatma_Zehra_Aktas_150124016
// Purpose: This class manages token creation, spawning, and collision handling.
// It also removes collected tokens from the game and ensures spawn limits.

public class TokenController {

    private Pane area; 
    private PlayableArea pa; 
    private ArrayList<Pane> activeTokens = new ArrayList<>(); 
    private long lastCreateTime = 0; // Last token spawn time

    //constructor
    public TokenController(Pane area, PlayableArea pa) {

        this.area = area; 
        this.pa = pa; 
    }


    public void update(long now, Hunter hunter, HealthBar hb, ArrayList<Entity> entities) {

        //Create a new token every 5 seconds
        if (now - lastCreateTime >= 5_000_000_000L) {

            // Ensure maximum 2 tokens exist at the same time
            if (activeTokens.size() < 2) {
                createRandomToken();
            }

            lastCreateTime = now; 
        }

        //Check collisions and collect tokens
        Iterator<Pane> iterator = activeTokens.iterator();

        while (iterator.hasNext()) {

            Pane token = iterator.next();

            // Call corresponding collect method based on token type
            if (token instanceof HealthToken) {
                ((HealthToken) token).collect(hunter, hb, area);

            } else if (token instanceof RangeToken) {
                ((RangeToken) token).collect(hunter, area);

            } else if (token instanceof EyeToken) {
                ((EyeToken) token).collect(hunter, entities, area);

            } else if (token instanceof ShieldToken) {
                ((ShieldToken) token).collect(hunter, area);
            }

            // Remove token if it is no longer in the scene
            if (!area.getChildren().contains(token)) {
                iterator.remove();
            }
        }
    }

    // Creates a random token inside playable area
    private void createRandomToken() {

        double minX = pa.pArea.getLayoutX(); 
        double minY = pa.pArea.getLayoutY(); 
        double maxX = minX + pa.pArea.getWidth(); 
        double maxY = minY + pa.pArea.getHeight();

        double randomX = minX + Math.random() * (maxX - minX); 
        double randomY = minY + Math.random() * (maxY - minY); 

        int choice = (int) (Math.random() * 4); 
        Pane newToken;

        if (choice == 0) {
            newToken = new HealthToken(randomX, randomY);

        } else if (choice == 1) {
            newToken = new RangeToken(randomX, randomY);

        } else if (choice == 2) {
            newToken = new EyeToken(randomX, randomY);

        } else {
            newToken = new ShieldToken(randomX, randomY);
        }

        area.getChildren().add(newToken); 
        activeTokens.add(newToken); 
        
    }
}