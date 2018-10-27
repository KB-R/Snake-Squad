package Controllers;
import java.util.ArrayList;
import Models.Collidable;;

/**
 * Keep track of all objects which can collide and detect whether any of them
 * are currently colliding
 */
public class CollisionDetector{
    private ArrayList<Collidable> collidables;

    public CollisionDetector(){
        collidables = new ArrayList<Collidable>();
    }

    public void addCollidable(Collidable collidable){
        collidables.add(collidable);
    }

    public void removeCollidable(Collidable collidable){
        collidables.remove(collidable);
    }

    /**
     * Detect collision between all collidable objects
     */
    public static void detectCollisions(){

    }
}