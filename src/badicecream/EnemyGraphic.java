
package badicecream;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 * parent class to all enemy graphics
 * @author Isabella Enriquez
 */
public abstract class EnemyGraphic {
    private Circle base;
    private final int startX, startY; // starting positions

    public EnemyGraphic(int x, int y) {
        base = new Circle(x, y, 15, Color.TRANSPARENT);
        startX = x;
        startY = y;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }
    
    public Circle getBase() {
        return base;
    }

    public void setBase(Circle base) {
        this.base = base;
    }

   public abstract void moveGraphic();
   
    /**
     * resets the enemy positions to their starting positions
     */
    public void resetPositions(){
        base.setCenterX(startX);
        base.setCenterY(startY);
    }
   
    
}