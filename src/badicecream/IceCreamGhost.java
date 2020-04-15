/*
 * enemy3 graphic
 */
package badicecream;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 *
 * @author Isabella Enriquez
 */
public class IceCreamGhost extends EnemyGraphic {

    private Polygon cone;
    private Ellipse leftEye, rightEye;
    private Circle iceCream, mouth;
    private ArrayList<Ellipse> melted = new ArrayList<>(), meltedOutline = new ArrayList<>();

    public IceCreamGhost(int x, int y) {
        super(x, y);

        cone = new Polygon(
                new double[]{
                   super.getBase().getCenterX() - 6, super.getBase().getCenterY() - 14,
                super.getBase().getCenterX() + 12, super.getBase().getCenterY() - 38, // top
                super.getBase().getCenterX() + 16, super.getBase().getCenterY()
                }
        );
        
        cone.setFill(Color.BURLYWOOD);
        cone.setStroke(Color.BROWN);
        cone.setStrokeWidth(2);

        meltedOutline.add(new Ellipse(super.getBase().getCenterX() + 14, super.getBase().getCenterY() + 5, 5, 5));
        meltedOutline.add(new Ellipse(super.getBase().getCenterX() + 11, super.getBase().getCenterY() + 8, 5, 5));
        meltedOutline.add(new Ellipse(super.getBase().getCenterX() - 14, super.getBase().getCenterY() + 5, 5, 5));
        meltedOutline.add(new Ellipse(super.getBase().getCenterX() - 11, super.getBase().getCenterY() + 8, 5, 5));

        for (int i = 0; i < meltedOutline.size(); i++) {
            meltedOutline.get(i).setStroke(Color.LIGHTGREY);
            meltedOutline.get(i).setStrokeWidth(4);

        }

        iceCream = new Circle(super.getBase().getCenterX(), super.getBase().getCenterY(), super.getBase().getRadius(), Color.GHOSTWHITE);
        iceCream.setStroke(Color.LIGHTGREY);
        iceCream.setStrokeWidth(2);

        melted.add(new Ellipse(super.getBase().getCenterX() + 14, super.getBase().getCenterY() + 5, 5, 5));
        melted.add(new Ellipse(super.getBase().getCenterX() + 11, super.getBase().getCenterY() + 8, 5, 5));
        melted.add(new Ellipse(super.getBase().getCenterX() - 14, super.getBase().getCenterY() + 5, 5, 5));
        melted.add(new Ellipse(super.getBase().getCenterX() - 11, super.getBase().getCenterY() + 8, 5, 5));

        for (int i = 0; i < melted.size(); i++) {
            melted.get(i).setFill(Color.GHOSTWHITE);
        }
        
        leftEye = new Ellipse(super.getBase().getCenterX() - 8, super.getBase().getCenterY()-3, 3.5, 5);
        leftEye.setStroke(Color.LIGHTGREY);
        leftEye.setStrokeWidth(2);
        rightEye = new Ellipse(super.getBase().getCenterX() +4, super.getBase().getCenterY()-3, 3.5, 5);
        rightEye.setStroke(Color.LIGHTGREY);
        rightEye.setStrokeWidth(2);
        
        mouth = new Circle(super.getBase().getCenterX() -2, super.getBase().getCenterY() + 7, 2);
        mouth.setStroke(Color.LIGHTGREY);
        mouth.setStrokeWidth(1);
    }

    public ArrayList<Ellipse> getMeltedOutline() {
        return meltedOutline;
    }

    public void setMeltedOutline(ArrayList<Ellipse> meltedOutline) {
        this.meltedOutline = meltedOutline;
    }

    public Polygon getCone() {
        return cone;
    }

    public void setCone(Polygon cone) {
        this.cone = cone;
    }

    public Ellipse getLeftEye() {
        return leftEye;
    }

    public void setLeftEye(Ellipse leftEye) {
        this.leftEye = leftEye;
    }

    public Ellipse getRightEye() {
        return rightEye;
    }

    public void setRightEye(Ellipse rightEye) {
        this.rightEye = rightEye;
    }

    public Circle getIceCream() {
        return iceCream;
    }

    public void setIceCream(Circle iceCream) {
        this.iceCream = iceCream;
    }

    public ArrayList<Ellipse> getMelted() {
        return melted;
    }

    public void setMelted(ArrayList<Ellipse> melted) {
        this.melted = melted;
    }

    public Circle getMouth() {
        return mouth;
    }

    public void setMouth(Circle mouth) {
        this.mouth = mouth;
    }

    @Override
    public void moveGraphic() {

        this.cone.getPoints().setAll(
                super.getBase().getCenterX() - 6, super.getBase().getCenterY() - 14,
                super.getBase().getCenterX() + 12, super.getBase().getCenterY() - 38, // top
                super.getBase().getCenterX() + 16, super.getBase().getCenterY()
        );

        this.meltedOutline.get(0).setCenterX(super.getBase().getCenterX() + 14);
        this.meltedOutline.get(0).setCenterY(super.getBase().getCenterY() + 5);
        this.meltedOutline.get(1).setCenterX(super.getBase().getCenterX() + 11);
        this.meltedOutline.get(1).setCenterY(super.getBase().getCenterY() + 8);
        this.meltedOutline.get(2).setCenterX(super.getBase().getCenterX() - 14);
        this.meltedOutline.get(2).setCenterY(super.getBase().getCenterY() + 5);
        this.meltedOutline.get(3).setCenterX(super.getBase().getCenterX() - 11);
        this.meltedOutline.get(3).setCenterY(super.getBase().getCenterY() + 8);

        this.iceCream.setCenterX(super.getBase().getCenterX());
        this.iceCream.setCenterY(super.getBase().getCenterY());

        this.melted.get(0).setCenterX(super.getBase().getCenterX() + 14);
        this.melted.get(0).setCenterY(super.getBase().getCenterY() + 5);
        this.melted.get(1).setCenterX(super.getBase().getCenterX() + 11);
        this.melted.get(1).setCenterY(super.getBase().getCenterY() + 8);
        this.melted.get(2).setCenterX(super.getBase().getCenterX() - 14);
        this.melted.get(2).setCenterY(super.getBase().getCenterY() + 5);
        this.melted.get(3).setCenterX(super.getBase().getCenterX() - 11);
        this.melted.get(3).setCenterY(super.getBase().getCenterY() + 8);
        
        this.leftEye.setCenterX(super.getBase().getCenterX() - 8);
        this.leftEye.setCenterY(super.getBase().getCenterY()-3);
        this.rightEye.setCenterX(super.getBase().getCenterX() +4);
        this.rightEye.setCenterY(super.getBase().getCenterY()-3);
        
        this.mouth.setCenterX(super.getBase().getCenterX() -2);
        this.mouth.setCenterY(super.getBase().getCenterY() +7);
       

    }

}
