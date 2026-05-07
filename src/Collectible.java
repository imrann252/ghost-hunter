import java.util.ArrayList;

import javafx.scene.layout.Pane;

public interface Collectible {

	
	//EyeToken için visibility true olacak.
	//RangeToken için vacuumTriangle alanı büyüyecek.
	//HealthToken için HealthBar seviyesi 10 yükselecek.

	void collect(Hunter hunter, ArrayList<Entity> ents, Pane root);
	
	
}