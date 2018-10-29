package Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

import Models.Lawnmower;
import Models.NPC;
import Models.NormalPea;
import Models.NormalZombie;
import Models.PeaShooter;
import Models.Sunflower;
import Models.Zombie;

/**
 * Handle the movement of all movable objects in the game
 * @author Tareq 
 */
public class MoveController{
    /**
     * Move objects
     */
    public void moveObjects(int timer, GameObjectsController goc){
        // move all the peas
        for(NormalPea np: goc.getPeas()){
            for(Zombie zb: goc.getZombies()){
	    		for(int a=0; a< 3;a++) {
	    			if(!np.collidesWith(zb)){
	    				int i = goc.getPeas().indexOf(np);
	    				Collections.swap(goc.getPeas(), i, i+1); //it moves forward until it hits an enemy
	    			}
	    		}
            }
        }
        
     // moves all zombies
        for(Zombie zb: goc.getZombies()){
        	//if it collides with a pea shooter
        	for(PeaShooter ps: goc.getPeaShooters()){
                if(!zb.collidesWith(ps)){
                    if(timer % 5 == 0) {
                    	int i = goc.getZombies().indexOf(zb);
            			Collections.swap(goc.getZombies(), i, i-1); //it moves forward until it hits an enemy
                    }
                }
            }
        	
            // if it collides with a sun flower
            for(Sunflower sf: goc.getSunflowers()){
                if(!zb.collidesWith(sf)){
                    if(timer % 5 == 0) {
                    	int i = goc.getZombies().indexOf(zb);
            			Collections.swap(goc.getZombies(), i, i-1); //it moves forward until it hits an enemy
                    }
               }           
    		}
        }
    }
}