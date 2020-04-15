package badicecream;

import javafx.scene.shape.*;

/**
 * things you can't pass
 * @author Isabella Enriquez
 */
public abstract class Obstacle {

    public abstract Shape getShape();

    public abstract double getX();

    public abstract double getY();
    
    public abstract String getObstacleType();

}
