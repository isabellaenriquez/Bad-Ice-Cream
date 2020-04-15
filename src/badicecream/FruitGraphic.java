package badicecream;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

/**
 *
 * @author Isabella Enriquez
 */
public class FruitGraphic {

    private String type;
    private int x, y;
    private Circle base;
    private ArrayList<Shape> image = new ArrayList<>(); // image = the graphic of the fruit

    public FruitGraphic(String type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
        base = new Circle(x, y, 10, Color.TRANSPARENT);

        // sets the fruit's graphic based on the type
        if (type.equalsIgnoreCase("cherry")) {

            // stem
            // startx, startY, controlX, controlY, endX, endY
            image.add(new QuadCurve(base.getCenterX() + 5, base.getCenterY() - 6, base.getCenterX() - 3, base.getCenterY() - 20, base.getCenterX() - 10, base.getCenterY() - 15));
            image.get(image.size() - 1).setFill(Color.TRANSPARENT);
            image.get(image.size() - 1).setStroke(Color.GREEN);
            image.get(image.size() - 1).setStrokeWidth(2);
            image.add(new QuadCurve(base.getCenterX() - 5, base.getCenterY() - 6, base.getCenterX(), base.getCenterY() - 6, base.getCenterX(), base.getCenterY() - 12));
            image.get(image.size() - 1).setFill(Color.TRANSPARENT);
            image.get(image.size() - 1).setStroke(Color.GREEN);
            image.get(image.size() - 1).setStrokeWidth(2);

            // leaf
            image.add(new QuadCurve(base.getCenterX() - 3, base.getCenterY() - 15, base.getCenterX() + 3, base.getCenterY() - 20, base.getCenterX() + 13, base.getCenterY() - 15)); // top half
            image.get(image.size() - 1).setFill(Color.LIGHTGREEN);
            image.get(image.size() - 1).setStroke(Color.GREEN);
            image.get(image.size() - 1).setStrokeWidth(2);
            image.add(new QuadCurve(base.getCenterX() - 3, base.getCenterY() - 15, base.getCenterX() + 5, base.getCenterY() - 7, base.getCenterX() + 13, base.getCenterY() - 15)); // bottom half
            image.get(image.size() - 1).setFill(Color.LIGHTGREEN);
            image.get(image.size() - 1).setStroke(Color.GREEN);
            image.get(image.size() - 1).setStrokeWidth(2);

            // cherry 1
            image.add(new Circle(base.getCenterX() + 5, base.getCenterY(), 6, Color.CORAL));
            image.get(image.size() - 1).setStroke(Color.CRIMSON);
            image.get(image.size() - 1).setStrokeWidth(2);
            image.add(new Circle(base.getCenterX() + 8, base.getCenterY() - 1, 2, Color.PEACHPUFF)); // shiny part

            //cherry 2
            image.add(new Circle(base.getCenterX() - 6, base.getCenterY(), 6, Color.CORAL));
            image.get(image.size() - 1).setStroke(Color.CRIMSON);
            image.get(image.size() - 1).setStrokeWidth(2);
            image.add(new Circle(base.getCenterX() - 3, base.getCenterY() - 1, 2, Color.PEACHPUFF));
        } else if (type.equalsIgnoreCase("banana")) {
            
            // back banana outline
            image.add(new QuadCurve(base.getCenterX() + 1, base.getCenterY() - 8, base.getCenterX(), base.getCenterY() + 5, base.getCenterX() - 14, base.getCenterY() + 3));
            image.get(image.size() - 1).setStroke(Color.DARKGOLDENROD);
            image.get(image.size() - 1).setStrokeWidth(9);
            image.get(image.size() - 1).setFill(Color.TRANSPARENT);
            
            // back banana
            image.add(new QuadCurve(base.getCenterX() + 1, base.getCenterY() - 8, base.getCenterX(), base.getCenterY() + 5, base.getCenterX() - 14, base.getCenterY() + 3));
            image.get(image.size() - 1).setStroke(Color.GOLDENROD);
            image.get(image.size() - 1).setStrokeWidth(6);
            image.get(image.size() - 1).setFill(Color.TRANSPARENT);
            
            // front banana outline
            image.add(new QuadCurve(base.getCenterX() - 12, base.getCenterY() + 10, base.getCenterX() + 5, base.getCenterY() + 6, base.getCenterX() + 1, base.getCenterY() - 10));
            image.get(image.size() - 1).setStroke(Color.DARKGOLDENROD);
            image.get(image.size() - 1).setStrokeWidth(9);
            image.get(image.size() - 1).setFill(Color.TRANSPARENT);
            
            // front banana
            image.add(new QuadCurve(base.getCenterX() - 12, base.getCenterY() + 10, base.getCenterX() + 5, base.getCenterY() + 6, base.getCenterX() + 1, base.getCenterY() - 10));
            image.get(image.size() - 1).setStroke(Color.GOLD);
            image.get(image.size() - 1).setStrokeWidth(6);
            image.get(image.size() - 1).setFill(Color.TRANSPARENT);

            // that little black part (i think it's a stem lol)
            image.add(new Rectangle(base.getCenterX()-2, base.getCenterY() - 17, 6, 8));
           image.get(image.size() - 1).setFill(Color.SIENNA);
           image.get(image.size() - 1).setStroke(Color.MAROON);
           image.get(image.size() - 1).setStrokeWidth(2);

        } else if (type.equalsIgnoreCase("strawberry")) {
            // leaves w outline
            Polygon leftLeafLine = new Polygon(
                    new double[]{
                        base.getCenterX(), base.getCenterY() - 10,
                        base.getCenterX() - 5, base.getCenterY() - 12,
                        base.getCenterX() - 7.5, base.getCenterY() - 18,
                        base.getCenterX() - 3, base.getCenterY() - 16
                    }
            );
            Polygon centerLeafLine = new Polygon(
                    new double[]{
                        base.getCenterX(), base.getCenterY() - 10,
                        base.getCenterX() - 3, base.getCenterY() - 16,
                        base.getCenterX(), base.getCenterY() - 20,
                        base.getCenterX() + 3, base.getCenterY() - 16
                    }
            );
            Polygon rightLeafLine = new Polygon(
                    new double[]{
                        base.getCenterX(), base.getCenterY() - 10,
                        base.getCenterX() + 5, base.getCenterY() - 12,
                        base.getCenterX() + 7.5, base.getCenterY() - 18,
                        base.getCenterX() - 3, base.getCenterY() - 16
                    }
            );
            leftLeafLine.setFill(Color.LIGHTGREEN);
            leftLeafLine.setStroke(Color.GREEN);
            leftLeafLine.setStrokeWidth(4);
            image.add(leftLeafLine);
            centerLeafLine.setFill(Color.LIGHTGREEN);
            centerLeafLine.setStroke(Color.GREEN);
            centerLeafLine.setStrokeWidth(4);
            image.add(centerLeafLine);
            rightLeafLine.setFill(Color.LIGHTGREEN);
            rightLeafLine.setStroke(Color.GREEN);
            rightLeafLine.setStrokeWidth(4);
            image.add(rightLeafLine);

            // leaves
            Polygon leftLeaf = new Polygon(
                    new double[]{
                        base.getCenterX(), base.getCenterY() - 10,
                        base.getCenterX() - 5, base.getCenterY() - 12,
                        base.getCenterX() - 7.5, base.getCenterY() - 18,
                        base.getCenterX() - 3, base.getCenterY() - 16
                    }
            );
            Polygon centerLeaf = new Polygon(
                    new double[]{
                        base.getCenterX(), base.getCenterY() - 10,
                        base.getCenterX() - 3, base.getCenterY() - 16,
                        base.getCenterX(), base.getCenterY() - 20,
                        base.getCenterX() + 3, base.getCenterY() - 16
                    }
            );
            Polygon rightLeaf = new Polygon(
                    new double[]{
                        base.getCenterX(), base.getCenterY() - 10,
                        base.getCenterX() + 5, base.getCenterY() - 12,
                        base.getCenterX() + 7.5, base.getCenterY() - 18,
                        base.getCenterX() - 3, base.getCenterY() - 16
                    }
            );

            leftLeaf.setFill(Color.LIGHTGREEN);
            image.add(leftLeaf);
            centerLeaf.setFill(Color.LIGHTGREEN);
            image.add(centerLeaf);
            rightLeaf.setFill(Color.LIGHTGREEN);
            image.add(rightLeaf);

            // diamond = bottom part of strawberry
            Polygon diamondOutline = new Polygon(new double[]{
                base.getCenterX() - 8, base.getCenterY(), base.getCenterX(), base.getCenterY() - 8, base.getCenterX() + 8, base.getCenterY(), base.getCenterX(), base.getCenterY() + 10
            });
            Polygon diamond = new Polygon(new double[]{
                base.getCenterX() - 8, base.getCenterY(), base.getCenterX(), base.getCenterY() - 8, base.getCenterX() + 8, base.getCenterY(), base.getCenterX(), base.getCenterY() + 10
            });

            diamondOutline.setFill(Color.RED);
            diamondOutline.setStroke(Color.FIREBRICK);
            diamondOutline.setStrokeWidth(4);
            diamond.setFill(Color.RED);
            image.add(diamondOutline);

            // top part of strawbery - outline
            image.add(new Circle(base.getCenterX() - base.getRadius() / 2, base.getCenterY() - base.getRadius() / 2, base.getRadius() / 2, Color.RED));
            image.get(image.size() - 1).setStroke(Color.FIREBRICK);
            image.get(image.size() - 1).setStrokeWidth(4);
            image.add(new Circle(base.getCenterX() + base.getRadius() / 2, base.getCenterY() - base.getRadius() / 2, base.getRadius() / 2, Color.RED));
            image.get(image.size() - 1).setStroke(Color.FIREBRICK);
            image.get(image.size() - 1).setStrokeWidth(4);

            image.add(diamond);

            // top part of strawberry
            image.add(new Circle(base.getCenterX() - base.getRadius() / 2, base.getCenterY() - base.getRadius() / 2, base.getRadius() / 2, Color.RED));
            image.add(new Circle(base.getCenterX() + base.getRadius() / 2, base.getCenterY() - base.getRadius() / 2, base.getRadius() / 2, Color.RED));

            // seeds
            image.add(new Ellipse(base.getCenterX() - 4, base.getCenterY() - 6, 0.5, 1));
            image.get(image.size() - 1).setFill(Color.YELLOW);
            image.add(new Ellipse(base.getCenterX() + 4, base.getCenterY() - 6, 0.5, 1));
            image.get(image.size() - 1).setFill(Color.YELLOW);
            image.add(new Ellipse(base.getCenterX(), base.getCenterY() - 3, 0.5, 1));
            image.get(image.size() - 1).setFill(Color.YELLOW);
            image.add(new Ellipse(base.getCenterX() + 2, base.getCenterY() + 3, 0.5, 1));
            image.get(image.size() - 1).setFill(Color.YELLOW);
            image.add(new Ellipse(base.getCenterX() - 2, base.getCenterY() + 3, 0.5, 1));
            image.get(image.size() - 1).setFill(Color.YELLOW);
            image.add(new Ellipse(base.getCenterX() - 6, base.getCenterY() - 2, 0.5, 1));
            image.get(image.size() - 1).setFill(Color.YELLOW);
            image.add(new Ellipse(base.getCenterX() + 6, base.getCenterY() - 2, 0.5, 1));
            image.get(image.size() - 1).setFill(Color.YELLOW);
        }else if (type.equalsIgnoreCase("watermelon")){
            // skin
            Arc skin = new Arc(base.getCenterX(), base.getCenterY(), base.getRadius()*1.5, base.getRadius()*1.5, 215, 180);
            // arc - x, y, radiusX, radiusY, starting angle, terminal arm
            skin.setType(ArcType.CHORD);
            skin.setFill(Color.GREEN);
            skin.setStroke(Color.DARKGREEN);
           skin.setStrokeWidth(3);
            image.add(skin);
            
            // white part
            Arc whiteThing = new Arc(base.getCenterX(), base.getCenterY(), base.getRadius()*1.25, base.getRadius()*1.25, 215, 180);
            whiteThing.setType(ArcType.CHORD);
            whiteThing.setFill(Color.WHITE);
           whiteThing.setStrokeWidth(3);
            image.add(whiteThing);
            
            // edible part
            Arc melon = new Arc(base.getCenterX(), base.getCenterY(), base.getRadius(), base.getRadius(), 215, 180);
            melon.setType(ArcType.CHORD);
            melon.setFill(Color.CORAL);
            melon.setStroke(Color.RED);
           melon.setStrokeWidth(3);
            image.add(melon);
            
            // seeds
            image.add(new Ellipse(base.getCenterX() -4, base.getCenterY()+6, 1, 1));
            image.add(new Ellipse(base.getCenterX()+2, base.getCenterY()+4, 1, 1));
            image.add(new Ellipse(base.getCenterX()+6, base.getCenterY()-1, 1, 1));
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Circle getBase() {
        return base;
    }

    public void setBase(Circle base) {
        this.base = base;
    }

    public ArrayList<Shape> getImage() {
        return image;
    }

    public void setImage(ArrayList<Shape> image) {
        this.image = image;
    }

}
