/*
 * player's character
 */
package badicecream;

public class IceCream {

    /*
    Has-a:  graphic
            weapon
    life
    current location
     */
    private int locationX, locationY;
    private IceCreamGraphic graphic;
    private int life;

    public IceCream() {
        this.locationX = 10;
        this.locationY = 10;
        this.graphic = new IceCreamGraphic();
        this.life = 3;
    }

    public IceCream(int locationX, int locationY, IceCreamGraphic graphic, int life) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.graphic = graphic;
        this.life = life;
    }

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public IceCreamGraphic getGraphic() {
        return graphic;
    }

    public int getLife() {
        return life;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public void setGraphic(IceCreamGraphic graphic) {
        this.graphic = graphic;
    }

    public void setLife(int life) {
        this.life = life;
    }

}