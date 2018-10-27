package Controllers;
import java.util.ArrayList;
import Models.NPC;;

/**
 * Keep track of all objects which can collide and detect whether any of them
 * are currently colliding
 */
public class CollisionDetector{
    private ArrayList<NPC> collidables;

    public CollisionDetector(){
        collidables = new ArrayList<NPC>();
    }

    public void addCollidable(NPC collidable){
        collidables.add(collidable);
    }

    public void removeCollidable(NPC collidable){
        collidables.remove(collidable);
    }

    /**
     * Detect collision between all collidable objects
     */
    public static void detectCollisions(){
    }
}