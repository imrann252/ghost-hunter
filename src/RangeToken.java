import javafx.animation.PauseTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.util.Duration;

public class RangeToken extends Pane {

    private double x;
    private double y;

    private boolean isCollected = false;

    public RangeToken(double x, double y) {

        this.x = x;
        this.y = y;

        // Outer circle
        Circle outCircle = new Circle(15, Color.YELLOW);

        outCircle.setStroke(Color.GRAY.darker());

        // Range icon
        Polygon rangeIcon = new Polygon();

        rangeIcon.getPoints().addAll(new Double[]{
                -5.0, 5.0,
                8.0, 0.0,
                -5.0, -5.0
        });

        rangeIcon.setFill(Color.BLACK);

        this.getChildren().addAll(outCircle, rangeIcon);

        this.setLayoutX(x);
        this.setLayoutY(y);
    }

    public void collect(Hunter hunter, Pane root) {

        if (isCollected) {
            return;
        }

        if (this.getBoundsInParent().intersects(hunter.getBody().getBoundsInParent())) {

            isCollected = true;

            root.getChildren().remove(this);

            double currentX = hunter.getVacuumScaleX();
            double currentY = hunter.getVacuumScaleY();

            // Increase range
            hunter.setVacuumScale(
                    currentX + 0.5,
                    currentY + 0.5
            );

            PauseTransition delay =
                    new PauseTransition(Duration.seconds(5));

            delay.setOnFinished(e -> {

                hunter.setVacuumScale(
                        currentX,
                        currentY
                );
            });

            delay.play();
        }
    }
}