
package badicecream;

import java.util.ArrayList;
import javafx.scene.layout.BorderPane;

/**
 * creates a field and has all the field's characteristics
 * @author Isabella Enriquez
 */
public class Field {

    private ArrayList<Obstacle> list = new ArrayList<>();
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<Fruit> fruits = new ArrayList<>();
    private BorderPane root = new BorderPane();
    

    public Field() {
    }

    public BorderPane getRoot() {
        return root;
    }

    public void setRoot(BorderPane root) {
        this.root = root;
    }    

    public ArrayList<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(ArrayList<Fruit> fruits) {
        this.fruits = fruits;
    }
    
    /**
     * add fruits to the list of fruit
     * @param fruit
     */
    public void addFruits(Fruit fruit){
        fruits.add(fruit);
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }

    /**
     * add enemies to the list of enemies
     * @param enemy
     */
    public void addEnemies(Enemy enemy) {
        enemies.add(enemy);
    }

    public ArrayList<Obstacle> getList() {
        return list;
    }

    public void setList(ArrayList<Obstacle> list) {
        this.list = list;
    }

    /**
     * add obstacles to the list of obstacles
     * @param obstacle
     */
    public void addObstacle(Obstacle obstacle) {
        list.add(obstacle);
    }

}
