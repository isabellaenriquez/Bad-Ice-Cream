/*
 * To change this license header, choose License Headers in Project Properties.
 * 
 */
package badicecream;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Fruit {

    private int locationX, locationY, radius;
    private String type;
    private Circle fruit;
    private FruitGraphic graphic;

    public Fruit() {
        this.locationX = 10;
        this.locationY = 10;
        this.radius = 10;
        this.type = "cherry";
        if (type.equalsIgnoreCase("cherry")) {
            this.fruit = new Circle(locationX, locationY, radius, Color.CRIMSON);
        } else if (type.equalsIgnoreCase("strawberry")) {
            this.fruit = new Circle(locationX, locationY, radius, Color.PINK);
        } else if (type.equalsIgnoreCase("banana")) {
            this.fruit = new Circle(locationX, locationY, radius, Color.LEMONCHIFFON);
        } else if (type.equalsIgnoreCase("watermelon")){
        this.fruit = new Circle(locationX, locationY, radius, Color.GREEN);
        }else {
            this.fruit = new Circle(locationX, locationY, radius, Color.ORANGE);
        }
        graphic = new FruitGraphic(type, locationX, locationY);
    }

    public Fruit(int locationX, int locationY, String type) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.radius = 10;
        this.type = type;
        if (type.equalsIgnoreCase("cherry")) {
            this.fruit = new Circle(locationX, locationY, radius, Color.CRIMSON);
        } else if (type.equalsIgnoreCase("strawberry")) {
            this.fruit = new Circle(locationX, locationY, radius, Color.PINK);
        } else if (type.equalsIgnoreCase("banana")) {
            this.fruit = new Circle(locationX, locationY, radius, Color.LEMONCHIFFON);
        } else if (type.equalsIgnoreCase("watermelon")) {
            this.fruit = new Circle(locationX, locationY, radius, Color.GREEN);
        } else {
            this.fruit = new Circle(locationX, locationY, radius, Color.ORANGE);
        }
        graphic = new FruitGraphic(type, locationX, locationY);
    }

    public FruitGraphic getGraphic() {
        return graphic;
    }

    public void setGraphic(FruitGraphic graphic) {
        this.graphic = graphic;
    }
    
    

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public int getRadius() {
        return radius;
    }

    public Circle getFruit() {
        return fruit;
    }

    public String getType() {
        return type;
    }

    

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setFruit(Circle fruit) {
        this.fruit = fruit;
    }

    public void setType(String type) {
        this.type = type;
    }

    

}