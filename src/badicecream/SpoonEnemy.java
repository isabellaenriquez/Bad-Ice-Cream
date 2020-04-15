/*
 * enemy1 graphic
 */
package badicecream;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 *
 * @author Isabella Enriquez
 */
public class SpoonEnemy extends EnemyGraphic {

    private ArrayList<Ellipse> spoon = new ArrayList<>();
    private Polygon leftBrow, rightBrow, mouth;

    public SpoonEnemy(int x, int y) {
        super(x, y);

        spoon.add(new Ellipse(super.getBase().getCenterX(), super.getBase().getCenterY() - 5, 3, 15)); // body w outline
        spoon.get(spoon.size() - 1).setFill(Color.GRAY);
        spoon.get(spoon.size() - 1).setStroke(Color.BLACK);
        spoon.get(spoon.size() - 1).setStrokeWidth(2);

        spoon.add(new Ellipse(super.getBase().getCenterX(), super.getBase().getCenterY() - 11, 9, 9)); // head
        spoon.get(spoon.size() - 1).setFill(Color.GRAY);
        spoon.get(spoon.size() - 1).setStroke(Color.BLACK);

        spoon.add(new Ellipse(super.getBase().getCenterX(), super.getBase().getCenterY() - 5, 3, 15)); // body
        spoon.get(spoon.size() - 1).setFill(Color.GRAY);

        spoon.add(new Ellipse(super.getBase().getCenterX() - 3, super.getBase().getCenterY() - 12, 1, 1)); // left eye
        spoon.get(spoon.size() - 1).setFill(Color.BLACK);

        spoon.add(new Ellipse(super.getBase().getCenterX() + 3, super.getBase().getCenterY() - 12, 1, 1)); // right eye
        spoon.get(spoon.size() - 1).setFill(Color.BLACK);

        leftBrow = new Polygon(
                new double[]{
                    super.getBase().getCenterX() - 8, super.getBase().getCenterY() - 17, super.getBase().getCenterX() - 5, super.getBase().getCenterY() - 15, super.getBase().getCenterX() - 2, super.getBase().getCenterY() - 13
                }
        );
        leftBrow.setFill(Color.BLACK);
        leftBrow.setStroke(Color.BLACK);
        leftBrow.setStrokeWidth(1);

        rightBrow = new Polygon(
                new double[]{
                    super.getBase().getCenterX() + 8, super.getBase().getCenterY() - 17, super.getBase().getCenterX() + 5, super.getBase().getCenterY() - 15, super.getBase().getCenterX() + 2, super.getBase().getCenterY() - 13
                }
        );
        rightBrow.setFill(Color.BLACK);
        rightBrow.setStroke(Color.BLACK);
        rightBrow.setStrokeWidth(1);

        mouth = new Polygon(new double[]{
            super.getBase().getCenterX() - 4, super.getBase().getCenterY() - 8, super.getBase().getCenterX(), super.getBase().getCenterY() - 5, super.getBase().getCenterX() + 4, super.getBase().getCenterY() - 8
        });
        mouth.setFill(Color.RED);
        mouth.setStroke(Color.BLACK);
        mouth.setStrokeWidth(1);
        mouth.setStrokeLineCap(StrokeLineCap.ROUND);
        mouth.setStrokeLineJoin(StrokeLineJoin.ROUND);

    }

    public Polygon getLeftBrow() {
        return leftBrow;
    }

    public void setLeftBrow(Polygon leftBrow) {
        this.leftBrow = leftBrow;
    }

    public Polygon getRightBrow() {
        return rightBrow;
    }

    public void setRightBrow(Polygon rightBrow) {
        this.rightBrow = rightBrow;
    }

    public ArrayList<Ellipse> getSpoon() {
        return spoon;
    }

    public void setSpoon(ArrayList<Ellipse> spoon) {
        this.spoon = spoon;
    }

    @Override
    public void moveGraphic() {

        this.spoon.get(0).setCenterX(super.getBase().getCenterX()); // body
        this.spoon.get(0).setCenterY(super.getBase().getCenterY() - 5);

        this.spoon.get(1).setCenterX(super.getBase().getCenterX()); // head
        this.spoon.get(1).setCenterY(super.getBase().getCenterY() - 11);

        this.spoon.get(2).setCenterX(super.getBase().getCenterX()); // body
        this.spoon.get(2).setCenterY(super.getBase().getCenterY() - 5);

        //face
        this.spoon.get(3).setCenterX(super.getBase().getCenterX() - 3); // left eye
        this.spoon.get(3).setCenterY(super.getBase().getCenterY() - 12);
        this.spoon.get(4).setCenterX(super.getBase().getCenterX() + 3); // right eye
        this.spoon.get(4).setCenterY(super.getBase().getCenterY() - 12);

        this.leftBrow.getPoints().set(0, super.getBase().getCenterX() - 8);
        this.leftBrow.getPoints().set(1, super.getBase().getCenterY() - 17);
        this.leftBrow.getPoints().set(2, super.getBase().getCenterX() - 5);
        this.leftBrow.getPoints().set(3, super.getBase().getCenterY() - 15);
        this.leftBrow.getPoints().set(4, super.getBase().getCenterX() - 2);
        this.leftBrow.getPoints().set(5, super.getBase().getCenterY() - 13);
        this.rightBrow.getPoints().set(0, super.getBase().getCenterX() + 8);
        this.rightBrow.getPoints().set(1, super.getBase().getCenterY() - 17);
        this.rightBrow.getPoints().set(2, super.getBase().getCenterX() + 5);
        this.rightBrow.getPoints().set(3, super.getBase().getCenterY() - 15);
        this.rightBrow.getPoints().set(4, super.getBase().getCenterX() + 2);
        this.rightBrow.getPoints().set(5, super.getBase().getCenterY() - 13);

        this.mouth.getPoints().set(0, super.getBase().getCenterX() - 4);
        this.mouth.getPoints().set(1, super.getBase().getCenterY() - 8);
        this.mouth.getPoints().set(2, super.getBase().getCenterX());
        this.mouth.getPoints().set(3, super.getBase().getCenterY() - 5);
        this.mouth.getPoints().set(4, super.getBase().getCenterX() + 4);
        this.mouth.getPoints().set(5, super.getBase().getCenterY() - 8);

    }

    public Polygon getMouth() {
        return mouth;
    }

    public void setMouth(Polygon mouth) {
        this.mouth = mouth;
    }

}
