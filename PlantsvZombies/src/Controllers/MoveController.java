package Controllers;

import java.util.ArrayList;

import Models.NormalPea;

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
            // do stuff
        }
    }
}