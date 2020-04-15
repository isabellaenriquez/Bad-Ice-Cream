/*
 * moves clockwise
aka bowler
 */
package badicecream;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 *
 * @author Isabella Enriquez
 */
public class Enemy2 extends Enemy {

    private int direction;
    private final BowlEnemy graphic;

    public Enemy2(int direction, Color color, double speed, int x, int y, double rad, int xBounds, int yBounds) {
        super(color, speed, x, y, rad, xBounds, yBounds);
        this.direction = direction;
        graphic = new BowlEnemy(x, y);
    }

    /*
    legend
    1 - right
    2 - left
    3 - down
    4 - up
     */
    public void changeDirection() {
        switch (direction) {
            case 1:
                setDirection(3);
                break;
            case 2:
                setDirection(4);
                break;
            case 3:
                setDirection(2);
                break;
            case 4:
                setDirection(1);
                break;
        }
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    @Override
    public void move() {
        // when enemy hits bounds of level
        if (graphic.getBase().getCenterX() + graphic.getBase().getRadius() >= super.getxBounds()) {
            graphic.getBase().setCenterX(super.getxBounds() - 16);
            setDirection(3);

        } else if (graphic.getBase().getCenterX() - graphic.getBase().getRadius() < 0) {
            graphic.getBase().setCenterX(16);
            setDirection(4);
        } else if (graphic.getBase().getCenterY() + graphic.getBase().getRadius() >= super.getyBounds()) {
            graphic.getBase().setCenterY(super.getyBounds() - 16);
            setDirection(2);
        } else if (graphic.getBase().getCenterY() - graphic.getBase().getRadius() < 10) {
            graphic.getBase().setCenterY(26);
            setDirection(1);
        }

        switch (direction) {
            case 1:
                graphic.getBase().setCenterX(graphic.getBase().getCenterX() + getSpeed());
                break;
            case 2:
                graphic.getBase().setCenterX(graphic.getBase().getCenterX() - getSpeed());
                break;
            case 3:
                graphic.getBase().setCenterY(graphic.getBase().getCenterY() + getSpeed());
                break;
            case 4:
                graphic.getBase().setCenterY(graphic.getBase().getCenterY() - getSpeed());
                break;
        }

        graphic.moveGraphic();
    }

    @Override
    public int getEnemyType() {
        return 2;
    }

    @Override
    public Circle getShape() {
        return graphic.getBase();
    }

    @Override
    public BowlEnemy getGraphic() {
        return graphic;
    }

}
