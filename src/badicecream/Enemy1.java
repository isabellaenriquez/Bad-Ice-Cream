package badicecream;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 * moves in a straight line, back and forth
 *
 * @author Isabella Enriquez
 */
public class Enemy1 extends Enemy {

    private boolean leftRight;
    private final SpoonEnemy graphic;

    public Enemy1(Color color, double speed, int x, int y, double rad, int xBounds, int yBounds, boolean leftRight) {
        super(color, speed, x, y, rad, xBounds, yBounds);
        this.leftRight = leftRight;
        graphic = new SpoonEnemy(x, y);

    }

    /**
     * if isLeftRight, enemy goes left and right
     * else, enemy goes up and down 
     * @return
     */
    public boolean isLeftRight() {
        return leftRight;
    }

    public void setLeftRight(boolean leftRight) {
        this.leftRight = leftRight;
    }

    @Override
    public void move() {
        if (isLeftRight()) {
            graphic.getBase().setCenterX(graphic.getBase().getCenterX() + super.getSpeed());
            graphic.moveGraphic();

            if (graphic.getBase().getCenterX() + graphic.getBase().getRadius() >= super.getxBounds()) {
                graphic.getBase().setCenterX(super.getxBounds() - graphic.getBase().getRadius() - 1);
                super.setSpeed(super.getSpeed() * -1);
            } else if (graphic.getBase().getCenterX() - graphic.getBase().getRadius() < 0) {
                graphic.getBase().setCenterX(graphic.getBase().getRadius()+1);
                super.setSpeed(super.getSpeed() * -1);
            }
        } else {
            graphic.getBase().setCenterY(graphic.getBase().getCenterY() + super.getSpeed());
            graphic.moveGraphic();

            if (graphic.getBase().getCenterY() + graphic.getBase().getRadius() >= super.getyBounds()) {
                graphic.getBase().setCenterY(super.getyBounds() - graphic.getBase().getRadius()-1);
                super.setSpeed(super.getSpeed() * -1);
            } else if (graphic.getBase().getCenterY() - graphic.getBase().getRadius() < 0) {
                graphic.getBase().setCenterY(graphic.getBase().getRadius()+1);
                super.setSpeed(super.getSpeed() * -1);
            }
        }
    }

    @Override
    public Circle getShape() {
        return graphic.getBase();
    }

    @Override
    public int getEnemyType() {
        return 1;
    }

    @Override
    public SpoonEnemy getGraphic() {
        return graphic;
    }

}
