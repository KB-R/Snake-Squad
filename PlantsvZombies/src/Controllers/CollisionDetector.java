package Controllers;
import java.util.ArrayList;

import Models.Lawnmower;
import Models.NPC;
import Models.NormalPea;
import Models.PeaShooter;
import Models.Sunflower;
import Models.Zombie;

/**
 * Keep track of all objects which can collide and detect whether any of them
 * are currently colliding
 * @author Tareq
 */
public class CollisionDetector{
   
    /**
     * Detect collision between all collidable objects
     */
    public static void detectCollisions(GameObjectsController goc){

        // for each pea
        for(NormalPea np: goc.getPeas()){
            // check if it collided with a zombie
            for(Zombie zb: goc.getZombies()){
                if(np.collidesWith(zb)){
                    zb.takeDamage(np.getDamage());
                    if(!zb.isAlive()) {
                    	
                    }
                }
            }
        }
        
        // for each zombie
        for(Zombie zb: goc.getZombies()){
            // check if it collided with peaShooter
            for(PeaShooter ps: goc.getPeaShooters()){
                if(zb.collidesWith(ps)){
                    ps.takeDamage(zb.getDamage());
                    if(!ps.isAlive()) {
                    	
                    }
                }
            }
            // check if it collided with a sun flower
            for(Sunflower sf: goc.getSunflowers()){
                if(zb.collidesWith(sf)){
                    sf.takeDamage(zb.getDamage());
                    if(!sf.isAlive()) {
                    	
                    }
               }
            }
            
            // check if it collided with a lawn mower
            for(Lawnmower Lm: goc.getLawnMowers()) {
            	if(zb.collidesWith(Lm)) {
            		goc.getZombies().clear();
            	}
            }
        }
        
        
    }
}