package Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import Models.NPC;
import Models.NormalPea;
import Models.Zombie;

/**
 * Handle the movement of all movable objects in the game
 */
public class MoveController{
    private static ArrayList<NPC> movables;

    public MoveController(){
        movables = new ArrayList<NPC>();
    }

    /**
     * Add a new movable object
     * @param movable
     */
    public static void addMovables(NPC movable){
        movables.add(movable);
    }

    public ArrayList<NPC> getMovables(){
        return movables;
    }

    /**
     * Move objects
     */
    public void moveObjects(int timer, Boolean collision) {
    	Iterator<NPC> movable = movables.iterator();
    	while(movable.hasNext()) {
	    	if(movable instanceof NormalPea || collision == false) { //Checks to see if the object is a NormalPea
	    		if (timer % ((NormalPea) movable).getVelocity() == 0){
	    			int i = movables.indexOf(movable);
	    			Collections.swap(movables, i, i+1); //it moves forward until it hits an enemy
	    		}
	    	}
	    	
	    	if(movable instanceof Zombie || collision == false) { //Check to see if the object is a Zombie
	    		if(timer % ((Zombie) movable).getVelocity() == 0) {
	    			int i = movables.indexOf(movable);
	    			Collections.swap(movables, i, i-1); //it moves forward until it hits an enemy
	    		}
	    	}
    	}
    }
}