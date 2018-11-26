package gameModel;

import java.util.*;
import Characters.*;

/**
 * Handle the movement of all movable objects in the game
 * @author Maxime Ndutiye
 */
public class MoveController{

    private boolean undo = false;
    // private Stack<MoveController> undoMove = new Stack<>();
    // private Stack<MoveController> redoMove = new Stack<>();

    // /**
    //  * Pushes the current version of the GOC onto the stack.
    //  */
    // public void nextTurn(){
    //     undoMove.push(this);
    //     if(!redoMove.isEmpty()){
    //         redoMove.clear();
    //     }
    // }
    // /**
    //  * Returns to the previous GOC
    //  * @return The object controller from the previous turn.
    //  */
    // public MoveController prevTurn(){
    //     redoMove.push(undoMove.peek());
    //     return undoMove.pop();
    // }
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