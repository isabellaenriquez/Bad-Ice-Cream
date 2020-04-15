/*
 * ice cream graphic
 */
package badicecream;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class IceCreamGraphic {

    /*
    states: coordinates
    methods: create 
             update 
     */
    private Circle cir;
    private ArrayList<Ellipse> swirl, swirlOutline;
    private Rectangle cone;
    private Arc topOutline;
    private QuadCurve topRight, bottomMouth, topMouth;

    public IceCreamGraphic() {
        cir = new Circle();
        cir.setFill(Color.LIGHTCORAL);
        cir.setRadius(10);
        cir.setCenterX(200);
        cir.setCenterY(200);

        swirl = new ArrayList<>();
        swirlOutline = new ArrayList<>();
        //body
        swirlOutline.add(new Ellipse(cir.getCenterX(), cir.getCenterY() + 10, 20, 10));
        swirlOutline.add(new Ellipse(cir.getCenterX(), cir.getCenterY(), 16, 10));
        swirlOutline.add(new Ellipse(cir.getCenterX() - 6, cir.getCenterY() - 10, 6, 8));
        swirlOutline.add(new Ellipse(cir.getCenterX() + 6, cir.getCenterY() - 10, 6, 8));
        swirlOutline.add(new Ellipse(cir.getCenterX(), cir.getCenterY() - 14, 6, 8));

        topOutline = new Arc(cir.getCenterX() + 9, cir.getCenterY() - 19, 14, 8, 90, 100);
        topOutline.setStrokeWidth(3);
        topOutline.setStroke(Color.CRIMSON);

        for (int i = 0; i < swirlOutline.size(); i++) {
            swirlOutline.get(i).setStroke(Color.CRIMSON);
            swirlOutline.get(i).setStrokeWidth(3);
            swirlOutline.get(i).setFill(Color.TRANSPARENT);

        }

        //body
        swirl.add(new Ellipse(cir.getCenterX() + 4, cir.getCenterY() - 20, 8, 6));
        swirl.add(new Ellipse(cir.getCenterX() + 9, cir.getCenterY() - 22, 5, 8));
        swirl.add(new Ellipse(cir.getCenterX(), cir.getCenterY() + 10, 20, 10));
        swirl.add(new Ellipse(cir.getCenterX(), cir.getCenterY(), 16, 10));
        swirl.add(new Ellipse(cir.getCenterX() - 6, cir.getCenterY() - 10, 6, 8));
        swirl.add(new Ellipse(cir.getCenterX() + 6, cir.getCenterY() - 10, 6, 8));
        swirl.add(new Ellipse(cir.getCenterX(), cir.getCenterY() - 14, 6, 10));
        //mouth
        swirl.add(new Ellipse(cir.getCenterX(), cir.getCenterY() + 5, 6, 6));
        swirl.add(new Ellipse(cir.getCenterX(), cir.getCenterY() + 2, 8, 3));
        //eyes
        swirl.add(new Ellipse(cir.getCenterX() - 6, cir.getCenterY() - 6, 4, 5));
        swirl.get(swirl.size() - 1).setStroke(Color.BLACK);
        swirl.get(swirl.size() - 1).setStrokeWidth(0.5);
        swirl.add(new Ellipse(cir.getCenterX() + 6, cir.getCenterY() - 6, 4, 5));
        swirl.get(swirl.size() - 1).setStroke(Color.BLACK);
        swirl.get(swirl.size() - 1).setStrokeWidth(0.5);

        swirl.add(new Ellipse(cir.getCenterX() - 6, cir.getCenterY() - 5, 2, 3));
        swirl.add(new Ellipse(cir.getCenterX() + 6, cir.getCenterY() - 5, 2, 3));

        for (int i = 0; i < swirl.size(); i++) {
            swirl.get(i).setFill(Color.LIGHTCORAL);
        }

        swirl.get(1).setFill(Color.GAINSBORO);
        swirl.get(7).setFill(Color.RED);
        swirl.get(9).setFill(Color.WHITE);
        swirl.get(10).setFill(Color.WHITE);
        swirl.get(11).setFill(Color.BLUEVIOLET);
        swirl.get(12).setFill(Color.BLUEVIOLET);

        cone = new Rectangle(cir.getCenterX() - 10, cir.getCenterY() + 8, 20, 20);
        cone.setFill(Color.KHAKI);
        cone.setStroke(Color.CHOCOLATE);

        // top right of swirl outline
        topRight = new QuadCurve(cir.getCenterX() + 8, cir.getCenterY() - 19, cir.getCenterX() +3, cir.getCenterY() - 18, cir.getCenterX() + 4, cir.getCenterY() - 27);
        topRight.setFill(Color.TRANSPARENT);
        topRight.setStroke(Color.CRIMSON);
        topRight.setStrokeWidth(1.25);
        
        // mouth outline
        topMouth = new QuadCurve(cir.getCenterX()-7, cir.getCenterY()+4, cir.getCenterX(), cir.getCenterY()+6, cir.getCenterX() +7, cir.getCenterY() +4);
        topMouth.setFill(Color.TRANSPARENT);
        topMouth.setStroke(Color.FIREBRICK);
        topMouth.setStrokeWidth(1.5);
        
        bottomMouth = new QuadCurve(cir.getCenterX()-7, cir.getCenterY()+5, cir.getCenterX(), cir.getCenterY() + 18, cir.getCenterX()+7, cir.getCenterY() +5);
        bottomMouth.setFill(Color.TRANSPARENT);
        bottomMouth.setStroke(Color.FIREBRICK);
        bottomMouth.setStrokeWidth(1.5);
    }

    public void moveGraphic() {
        cone.setX(cir.getCenterX() - 10);

        this.swirlOutline.get(0).setCenterX(cir.getCenterX());
        this.swirlOutline.get(1).setCenterX(cir.getCenterX());
        this.swirlOutline.get(2).setCenterX(cir.getCenterX() - 6);
        this.swirlOutline.get(3).setCenterX(cir.getCenterX() + 6);
        this.swirlOutline.get(4).setCenterX(cir.getCenterX());

        this.swirlOutline.get(0).setCenterY(cir.getCenterY() + 10);
        this.swirlOutline.get(1).setCenterY(cir.getCenterY());
        this.swirlOutline.get(2).setCenterY(cir.getCenterY() - 10);
        this.swirlOutline.get(3).setCenterY(cir.getCenterY() - 10);
        this.swirlOutline.get(4).setCenterY(cir.getCenterY() - 14);

        this.topOutline.setCenterX(cir.getCenterX() + 9);
        this.topOutline.setCenterY(cir.getCenterY() - 19);

        this.swirl.get(0).setCenterX(cir.getCenterX() + 4);
        this.swirl.get(1).setCenterX(cir.getCenterX() + 9);
        this.swirl.get(2).setCenterX(cir.getCenterX());
        this.swirl.get(3).setCenterX(cir.getCenterX());
        this.swirl.get(4).setCenterX(cir.getCenterX() - 6);
        this.swirl.get(5).setCenterX(cir.getCenterX() + 6);
        this.swirl.get(6).setCenterX(cir.getCenterX());
        this.swirl.get(7).setCenterX(cir.getCenterX());
        this.swirl.get(8).setCenterX(cir.getCenterX());
        this.swirl.get(9).setCenterX(cir.getCenterX() - 6);
        this.swirl.get(10).setCenterX(cir.getCenterX() + 6);
        this.swirl.get(11).setCenterX(cir.getCenterX() - 6);
        this.swirl.get(12).setCenterX(cir.getCenterX() + 6);

        cone.setY(cir.getCenterY() + 8);
        this.swirl.get(0).setCenterY(cir.getCenterY() - 20);
        this.swirl.get(1).setCenterY(cir.getCenterY() - 22);
        this.swirl.get(2).setCenterY(cir.getCenterY() + 10);
        this.swirl.get(3).setCenterY(cir.getCenterY());
        this.swirl.get(4).setCenterY(cir.getCenterY() - 10);
        this.swirl.get(5).setCenterY(cir.getCenterY() - 10);
        this.swirl.get(6).setCenterY(cir.getCenterY() - 14);
        this.swirl.get(7).setCenterY(cir.getCenterY() + 5);
        this.swirl.get(8).setCenterY(cir.getCenterY() + 2);
        this.swirl.get(9).setCenterY(cir.getCenterY() - 6);
        this.swirl.get(10).setCenterY(cir.getCenterY() - 6);
        this.swirl.get(11).setCenterY(cir.getCenterY() - 5);
        this.swirl.get(12).setCenterY(cir.getCenterY() - 5);


        this.topRight.setStartX(cir.getCenterX() + 8);
        this.topRight.setStartY(cir.getCenterY() - 19);
        this.topRight.setControlX(cir.getCenterX()+3);
        this.topRight.setControlY(cir.getCenterY()-18);
        this.topRight.setEndX(cir.getCenterX()+4);
        this.topRight.setEndY(cir.getCenterY()-27);
        
        this.topMouth.setStartX(cir.getCenterX() - 7);
        this.topMouth.setStartY(cir.getCenterY() +4);
        this.topMouth.setControlX(cir.getCenterX());
        this.topMouth.setControlY(cir.getCenterY()+6);
        this.topMouth.setEndX(cir.getCenterX()+7);
        this.topMouth.setEndY(cir.getCenterY()+4);
        
     
      this.bottomMouth.setStartX(cir.getCenterX() - 7);
        this.bottomMouth.setStartY(cir.getCenterY() +5);
        this.bottomMouth.setControlX(cir.getCenterX());
        this.bottomMouth.setControlY(cir.getCenterY()+18);
        this.bottomMouth.setEndX(cir.getCenterX()+7);
        this.bottomMouth.setEndY(cir.getCenterY()+5);


    }

    public Arc getTopOutline() {
        return topOutline;
    }

    public void setTopOutline(Arc topOutline) {
        this.topOutline = topOutline;
    }

    public ArrayList<Ellipse> getSwirlOutline() {
        return swirlOutline;
    }

    public void setSwirlOutline(ArrayList<Ellipse> swirlOutline) {
        this.swirlOutline = swirlOutline;
    }

    public QuadCurve getTopRight() {
        return topRight;
    }

    public void setTopRight(QuadCurve topRight) {
        this.topRight = topRight;
    }

    public Circle getCir() {
        return cir;
    }

    public void setCir(Circle cir) {
        this.cir = cir;

        cone.setX(cir.getCenterX() - 10);

        this.swirlOutline.get(0).setCenterX(cir.getCenterX() + 4);
        this.swirlOutline.get(1).setCenterX(cir.getCenterX() + 9);
        this.swirlOutline.get(2).setCenterX(cir.getCenterX());
        this.swirlOutline.get(3).setCenterX(cir.getCenterX());
        this.swirlOutline.get(4).setCenterX(cir.getCenterX() - 6);
        this.swirlOutline.get(5).setCenterX(cir.getCenterX() + 6);
        this.swirlOutline.get(6).setCenterX(cir.getCenterX());

        this.swirlOutline.get(0).setCenterY(cir.getCenterY() + 4);
        this.swirlOutline.get(1).setCenterY(cir.getCenterY() + 9);
        this.swirlOutline.get(2).setCenterY(cir.getCenterY());
        this.swirlOutline.get(3).setCenterY(cir.getCenterY());
        this.swirlOutline.get(4).setCenterY(cir.getCenterY() - 6);
        this.swirlOutline.get(5).setCenterY(cir.getCenterY() + 6);
        this.swirlOutline.get(6).setCenterY(cir.getCenterY());

        this.swirl.get(0).setCenterX(cir.getCenterX() + 4);
        this.swirl.get(1).setCenterX(cir.getCenterX() + 9);
        this.swirl.get(2).setCenterX(cir.getCenterX());
        this.swirl.get(3).setCenterX(cir.getCenterX());
        this.swirl.get(4).setCenterX(cir.getCenterX() - 6);
        this.swirl.get(5).setCenterX(cir.getCenterX() + 6);
        this.swirl.get(6).setCenterX(cir.getCenterX());
        this.swirl.get(7).setCenterX(cir.getCenterX());
        this.swirl.get(8).setCenterX(cir.getCenterX());
        this.swirl.get(9).setCenterX(cir.getCenterX() - 6);
        this.swirl.get(10).setCenterX(cir.getCenterX() + 6);
        this.swirl.get(11).setCenterX(cir.getCenterX() - 6);
        this.swirl.get(12).setCenterX(cir.getCenterX() + 6);

        cone.setY(cir.getCenterY() + 8);
        this.swirl.get(0).setCenterY(cir.getCenterY() - 20);
        this.swirl.get(1).setCenterY(cir.getCenterY() - 22);
        this.swirl.get(2).setCenterY(cir.getCenterY() + 10);
        this.swirl.get(3).setCenterY(cir.getCenterY());
        this.swirl.get(4).setCenterY(cir.getCenterY() - 10);
        this.swirl.get(5).setCenterY(cir.getCenterY() - 10);
        this.swirl.get(6).setCenterY(cir.getCenterY() - 14);
        this.swirl.get(7).setCenterY(cir.getCenterY() + 5);
        this.swirl.get(8).setCenterY(cir.getCenterY() + 2);
        this.swirl.get(9).setCenterY(cir.getCenterY() - 6);
        this.swirl.get(10).setCenterY(cir.getCenterY() - 6);
        this.swirl.get(11).setCenterY(cir.getCenterY() - 5);
        this.swirl.get(12).setCenterY(cir.getCenterY() - 5);

    }

    public ArrayList<Ellipse> getSwirl() {
        return swirl;
    }

    public void setSwirl(ArrayList<Ellipse> swirl) {
        this.swirl = swirl;
    }

    public Rectangle getCone() {
        return cone;
    }

    public void setCone(Rectangle cone) {
        this.cone = cone;
    }

    public QuadCurve getBottomMouth() {
        return bottomMouth;
    }

    public void setBottomMouth(QuadCurve bottomMouth) {
        this.bottomMouth = bottomMouth;
    }

    public QuadCurve getTopMouth() {
        return topMouth;
    }

    public void setTopMouth(QuadCurve topMouth) {
        this.topMouth = topMouth;
    }
    
    
}
