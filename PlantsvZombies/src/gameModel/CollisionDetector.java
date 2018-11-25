package gameModel;
import java.util.ArrayList;

import Characters.*;
/**
 * @author Maxime N
 * @author Kurt Burton-Rowe
 * @date October 28, 2018
 * 
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
            // check if it collided with something else
            for(Zombie zb: goc.getZombies()){
                if(np.collidesWith(zb)){
                	zb.takeDamage(10);
                	//if the zombie is still alive it can move again
                	if(zb.isAlive()) {
                		zb.collided();
                	}
                	
                	//pea disappears after collision 
            		np.takeDamage(1);
                }
            }
            goc.updateZombies(goc.getZombies());
  
        }
        goc.updatePeas(goc.getPeas());
  
        
        // for each lawn mower
        for(Lawnmower lm: goc.getLawnMowers()){
            // check if it collided with something else
            for(Zombie zb: goc.getZombies()){
                if(lm.collidesWith(zb)){

                	lm.takeDamage(zb.getDamage());
                	
                	//all zombies die against lawn mower
                	zb.takeDamage(lm.getDamage());   	
                }
            }
            goc.updateZombies(goc.getZombies());
           
            //lawn mower can move after it collides with all zombies at the same location
            lm.collided();
        }
        goc.updateLawnMowers(goc.getLawnMowers());
        
        
        // for each zombie
        for(Zombie z: goc.getZombies()) {
        	for(Sunflower sf: goc.getSunflowers()) {
        		if(z.collidesWith(sf)) {
					//after collision zombie eats sunflower
        			sf.takeDamage(z.getDamage());
        			if(sf.isAlive()) {
        				//sun flower dies zombie keeps moving
        				z.collided();
        			}
        		}
        	}
        	goc.updateSunflower(goc.getSunflowers());
        	
        	for(PeaShooter p: goc.getPeaShooters()) {
        		if(z.collidesWith(p)) {
        			//after collision zombie eats pea shooter
        			p.takeDamage(z.getDamage());
        			if(p.isAlive()) {
        				//pea shooter dies zombie keeps moving
        				z.collided();
        			}
        		}
        	}
        	goc.updatePeaShooters(goc.getPeaShooters());
        }
        goc.updateZombies(goc.getZombies()); 
	}
	
	public static void clearCollisions(GameObjectsController goc){
        for(Zombie z: goc.getZombies()) {
			z.clearCollided();
        }
	}
    
}
