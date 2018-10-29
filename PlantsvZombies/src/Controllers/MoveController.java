package Controllers;

import java.util.ArrayList;

import Models.Lawnmower;
import Models.NormalPea;
import Models.Zombie;

/**
 * Handle the movement of all movable objects in the game
 */
public class MoveController{
    /**
     * Moves all the object
     * @param goc GameObjectsController the contoller containing all game objects 
     */
    public void moveObjects(GameObjectsController goc){

        // move all the peas...
        for(NormalPea np: goc.getPeas()){
           np.move();
        }
        goc.updatePeas(goc.getPeas());
        //move all the zombies
        for(Zombie z: goc.getZombies()) {
        	z.move();
        }
        goc.updateZombies(goc.getZombies());
        //move lawn mowers
        for(Lawnmower lm: goc.getLawnMowers()) {
        	lm.move();
        }
        goc.updateLawnMowers(goc.getLawnMowers());
    }
}