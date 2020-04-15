/*
 * parent class to all specific enemy classes
 */
package badicecream;

import javafx.scene.paint.Color;

/**
 *
 * @author Isabella Enriquez
 */
public abstract class Enemy extends Obstacle {
    
    private Color color;
    private double speed, rad;
    private int x, y, xBounds, yBounds; // x bounds = width of level, y bounds = height of level

    public Enemy(Color color, double speed, int x, int y, double rad, int xBounds, int yBounds) {
        this.color = color;
        this.speed = speed;
        this.x = x;
        this.y = y;
        this.rad = rad;
        this.xBounds = xBounds;
        this.yBounds = yBounds;
    }

    public int getxBounds() {
        return xBounds;
    }

    public void setxBounds(int xBounds) {
        this.xBounds = xBounds;
    }

    public void setyBounds(int yBounds) {
        this.yBounds = yBounds;
    }

    public int getyBounds() {
        return yBounds;
    }    
    
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public double getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getRad() {
        return rad;
    }

    public void setRad(double rad) {
        this.rad = rad;
    }

    public abstract EnemyGraphic getGraphic();
        
    public abstract void move();
    
    public abstract int getEnemyType();
    
    @Override
    public String getObstacleType(){
        return "enemy";
    }
    
    
    
    
}
