import javafx.scene.layout.Pane;

public class CreateToken {

    private Pane root;

    private long lastHealthSpawn = 0;
    private long lastRandomSpawn = 0;

    public CreateToken(Pane root) {
        this.root = root;
    }

    public void update(HealthBar hb) {

        long now = System.currentTimeMillis();

        // Health token
        if (hb.currentHealth <= 80 &&
            now - lastHealthSpawn > 15000) {

            spawnHealthToken();

            lastHealthSpawn = now;
        }

        // Random token
        if (now - lastRandomSpawn > 10000) {

            int r = (int)(Math.random() * 2);

            if(r == 0) {
                spawnEyeToken();
            }
            else {
                spawnVacuumToken();
            }

            lastRandomSpawn = now;
        }
    }

    private void spawnHealthToken() {

        double x = 200 + Math.random() * 600;
        double y = 100 + Math.random() * 400;

        HealthToken token = new HealthToken(x, y);

        root.getChildren().add(token);
    }

    private void spawnEyeToken() {

        double x = 200 + Math.random() * 600;
        double y = 100 + Math.random() * 400;

        EyeToken token = new EyeToken(x, y);

        root.getChildren().add(token);
    }

    private void spawnVacuumToken() {

        double x = 200 + Math.random() * 600;
        double y = 100 + Math.random() * 400;

        RangeToken token = new RangeToken(x, y);

        root.getChildren().add(token);
    }
}