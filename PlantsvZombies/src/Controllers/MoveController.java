package Controllers;

import java.util.ArrayList;

import Models.*;

/**
 * Handle the movement of all movable objects in the game
 */
public class MoveController{

    private boolean undo = false;

    /**
     * Moves all the object
     * @param goc GameObjectsController the contoller containing all game objects 
     */
    public void movePeas(GameObjectsController goc){

        // move all the peas...
        for(NormalPea np: goc.getPeas()){
           np.move(goc.getTime(), undo);
        }
    }

    public void moveZombies(GameObjectsController goc) {
        //move all the zombies
        for(Zombie z: goc.getZombies()) {
        	z.move(goc.getTime(), undo);
        }
    }

    public void moveLawnmowers(GameObjectsController goc) {
        //move lawn mowers
        for(Lawnmower lm: goc.getLawnMowers()) {
        	lm.move(goc.getTime(), undo);
        }
    }

    public void setUndo(){
        this.undo = true; 
    }

    public void unsetUndo(){
        this.undo = false;
    }

}