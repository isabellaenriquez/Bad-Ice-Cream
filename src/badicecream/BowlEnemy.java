package badicecream;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
  enemy2 graphic
 * @author Isabella Enriquez
 */
public class BowlEnemy extends EnemyGraphic {

    private Polygon leftBrow, rightBrow;
    private QuadCurve mouth;
    private Circle leftEye, rightEye;
    private Arc outside;
    private Ellipse inside;

    public BowlEnemy(int x, int y) {
        super(x, y);
        
        // inside of bowl
        inside = new Ellipse(super.getBase().getCenterX(), super.getBase().getCenterY() - 9, 15, 3);
        inside.setFill(Color.VIOLET);
        inside.setStroke(Color.DARKORCHID);
        inside.setStrokeWidth(2);

        // outside of bowl
        outside = new Arc(super.getBase().getCenterX(), super.getBase().getCenterY() - 7.5, super.getBase().getRadius(), super.getBase().getRadius(), 180, 180);
        outside.setFill(Color.MEDIUMORCHID);
        outside.setStroke(Color.DARKORCHID);
        outside.setStrokeWidth(2);
        outside.setType(ArcType.CHORD);

        leftEye = new Circle(super.getBase().getCenterX() - 6, super.getBase().getCenterY() - 2, 2, Color.BLACK);
        rightEye = new Circle(super.getBase().getCenterX() + 6, super.getBase().getCenterY() - 2, 2, Color.BLACK);
 
        leftBrow = new Polygon(
                new double[]{
                    super.getBase().getCenterX() - 1, super.getBase().getCenterY() - 3,
                super.getBase().getCenterX() - 15, super.getBase().getCenterY() - 9,
                super.getBase().getCenterX() - 11, super.getBase().getCenterY() - 11
                }
        );
        rightBrow = new Polygon(
                new double[]{
                   super.getBase().getCenterX() + 1, super.getBase().getCenterY() - 3,
                super.getBase().getCenterX() + 15, super.getBase().getCenterY() - 9,
                super.getBase().getCenterX() + 11, super.getBase().getCenterY() - 11
                }
        );

    }

    public Circle getLeftEye() {
        return leftEye;
    }

    public void setLeftEye(Circle leftEye) {
        this.leftEye = leftEye;
    }

    public Circle getRightEye() {
        return rightEye;
    }

    public void setRightEye(Circle rightEye) {
        this.rightEye = rightEye;
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

    public QuadCurve getMouth() {
        return mouth;
    }

    public void setMouth(QuadCurve mouth) {
        this.mouth = mouth;
    }

    public Arc getOutside() {
        return outside;
    }

    public void setOutside(Arc outside) {
        this.outside = outside;
    }

    public Ellipse getInside() {
        return inside;
    }

    public void setInside(Ellipse inside) {
        this.inside = inside;
    }

    @Override
    public void moveGraphic() {

        // body
        this.inside.setCenterX(super.getBase().getCenterX());
        this.inside.setCenterY(super.getBase().getCenterY() - 9);
        this.outside.setCenterX(super.getBase().getCenterX());
        this.outside.setCenterY(super.getBase().getCenterY() - 7.5);

        //face
        this.leftEye.setCenterX(super.getBase().getCenterX() - 6);
        this.leftEye.setCenterY(super.getBase().getCenterY() - 2);
        this.rightEye.setCenterX(super.getBase().getCenterX() + 6);
        this.rightEye.setCenterY(super.getBase().getCenterY() - 2);
        this.leftBrow.getPoints().setAll(
                super.getBase().getCenterX() - 1, super.getBase().getCenterY() - 3,
                super.getBase().getCenterX() - 15, super.getBase().getCenterY() - 9,
                super.getBase().getCenterX() - 11, super.getBase().getCenterY() - 11
        );
        this.rightBrow.getPoints().setAll(
                super.getBase().getCenterX() + 1, super.getBase().getCenterY() - 3,
                super.getBase().getCenterX() + 15, super.getBase().getCenterY() - 9,
                super.getBase().getCenterX() + 11, super.getBase().getCenterY() - 11
        );
    }

}
