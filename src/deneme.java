AnimationTimer animation = new AnimationTimer() {
	    public void handle(long now) {
	        pan.update(); // 

	       //vacuum.getBoundsInParent().intersects(ent.getShape().getBoundsInParent())
	        for (int i = 0; i < entities.size(); i++) {
	            Entity ent = entities.get(i);
	            
	        
	            ent.move(pa.pArea.getLayoutX(), pa.pArea.getLayoutY(), 
	                     pa.pArea.getLayoutX() + config.getWidth(level), 
	                     pa.pArea.getLayoutY() + config.getHeight(level));

	            
	         

	            if (vacuum.isVisible() && ) {
	                
	                double currentScale = ent.getShape().getScaleX();
	                ent.getShape().setScaleX(currentScale - 0.01); // %1 küçült
	                ent.getShape().setScaleY(currentScale - 0.01);

	                
	                if (currentScale < 0.2) {
	                    area.getChildren().remove(ent.getShape()); 
	                    entities.remove(i);
	                    sc.addScore(100); 
	                }
	            }
	        }

	        //en son bu
	        if (entities.isEmpty()) {
	            this.stop(); 
	            handleWin(); 
	        }
	    
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

import javafx.animation.AnimationTimer;

}}
