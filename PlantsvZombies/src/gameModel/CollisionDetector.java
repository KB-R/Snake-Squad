package gameModel;

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
                	//if the zombie is still alive it can move again
                	if(zb.isAlive()) {
						zb.takeDamage(10);
						zb.collided();
						np.collided();
						np.takeDamage(1);
                	}
                	

                }
            }  
        }  
        
        // for each lawn mower
        for(Lawnmower lm: goc.getLawnMowers()){
            // check if it collided with something else
            for(Zombie zb: goc.getZombies()){
                if(lm.collidesWith(zb)){
                	lm.takeDamage(zb.getDamage());
					zb.takeDamage(lm.getDamage());   	
					 
					lm.collided();
					zb.collided();
                }
            }
        }        
        
        // for each zombie
        for(Zombie z: goc.getZombies()) {
        	for(Sunflower sf: goc.getSunflowers()) {
        		if(z.collidesWith(sf)) {
					//after collision zombie eats sunflower
        			sf.takeDamage(z.getDamage());
        			if(sf.isAlive()) {
						z.collided();
						sf.collided();
        			}
        		}
        	}
        	
        	for(PeaShooter p: goc.getPeaShooters()) {
        		if(z.collidesWith(p)) {
        			//after collision zombie eats pea shooter
        			p.takeDamage(z.getDamage());
        			if(p.isAlive()) {
						z.collided();
						p.collided();
        			}
        		}
        	}
        }
	}
	
	/**
	 * Remove collided flag from objects that can collide
	 * @param GameObjectsController goc The GOC to obtain game objects from
	 */
	public static void clearCollisions(GameObjectsController goc){
        for(Zombie z: goc.getZombies()) {
			z.clearCollided();
		}
		
		for(Lawnmower lm: goc.getLawnMowers()) {
			lm.clearCollided();
        }
	}
    
}
