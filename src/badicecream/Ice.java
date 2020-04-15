package badicecream;

import javafx.scene.paint.Color;
import javafx.scene.shape.*;

public class Ice extends Obstacle {

    /*
    can be broken or created by ice cream
     */
    private Rectangle iceBlock;
    private int locationX, locationY;
    private double width, height;

    public Ice() {
        locationX = 10;
        locationY = 10;
        width = 40;
        height = 40;

        iceBlock = new Rectangle(locationX, locationY, width, height);
        iceBlock.setFill(Color.ALICEBLUE);
        iceBlock.setStroke(Color.LIGHTBLUE);
    }

    public Ice(int locationX, int locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.width = 38;
        this.height = 38;

        iceBlock = new Rectangle(locationX, locationY, width, height);
        iceBlock.setFill(Color.ALICEBLUE);
        iceBlock.setStroke(Color.LIGHTBLUE);        

    }

    public Rectangle getIceBlock() {
        return iceBlock;
    }
    
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setIceBlock(Rectangle iceBlock) {
        this.iceBlock = iceBlock;
    }

    public void setX(int locationX) {
        this.locationX = locationX;
    }

    public void setY(int locationY) {
        this.locationY = locationY;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public Rectangle getShape() {
        return iceBlock;
    }

    @Override
    public double getX() {
    return iceBlock.getX();
    }

    @Override
    public double getY() {
        return iceBlock.getY();
    }

    @Override
    public String getObstacleType() {
        return "ice";
    }
    

}