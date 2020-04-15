package badicecream;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 * follows player
 * aka ghost
 * @author Isabella Enriquez
 */
public class Enemy3 extends Enemy {

    private int direction;
    private final IceCreamGhost graphic;

    public Enemy3(int direction, Color color, double speed, int x, int y, double rad, int xBounds, int yBounds) {
        super(color, speed, x, y, rad, xBounds, yBounds);
        this.direction = direction;
        graphic = new IceCreamGhost(x, y);
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void move() {
        // when bounds of level are hit
        if (graphic.getBase().getCenterX() + graphic.getBase().getRadius() >= super.getxBounds()) {
             direction = 2;
        } else if (graphic.getBase().getCenterX() - graphic.getBase().getRadius() < 0) {
            direction = 1;
        } else if (graphic.getBase().getCenterY() + graphic.getBase().getRadius() >= super.getyBounds()) {
            direction = 4;
        } else if (graphic.getBase().getCenterY() - graphic.getBase().getRadius() < 0) {
            direction = 3;
        }

        switch (direction) {
            case 1:
                graphic.getBase().setCenterX(graphic.getBase().getCenterX() + super.getSpeed());
                break; // right
            case 2:
                graphic.getBase().setCenterX(graphic.getBase().getCenterX() - super.getSpeed());
                break;//left
            case 3:
                graphic.getBase().setCenterY(graphic.getBase().getCenterY() + super.getSpeed());
                break;// down
            case 4:
                graphic.getBase().setCenterY(graphic.getBase().getCenterY() - super.getSpeed());
                break;//up
        }
        graphic.moveGraphic();

    }
    
    /**
     * creates the delay after the enemy breaks ice blocks
     */
    public void pause(){
        graphic.getBase().setCenterX(graphic.getBase().getCenterX());
        graphic.getBase().setCenterY(graphic.getBase().getCenterY());
        
        graphic.moveGraphic();
    }

    @Override
    public int getEnemyType() {
        return 3;
    }

    @Override
    public Circle getShape() {
        return graphic.getBase();
    }

    @Override
    public IceCreamGhost getGraphic() {
        return graphic;
    }

}
