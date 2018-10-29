package Controllers;
import java.util.ArrayList;
import Models.NPC;
import Models.NormalPea;
import Models.Zombie;;

/**
 * Keep track of all objects which can collide and detect whether any of them
 * are currently colliding
 */
public class CollisionDetector{
   
    /**
     * Detect collision between all collidable objects
     */
    public static void detectCollisions(GameObjectsController goc){

        // for each pea
        for(NormalPea np: goc.getPeas()){
            // check if it collised with something else
            for(Zombie zb: goc.getZombies()){
                if(np.collidesWith(zb)){
                    // some logic
                }
            }
        }
    }
}