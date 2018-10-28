package Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

import Models.NPC;
import Models.NormalPea;
import Models.NormalZombie;
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
    public void moveObjects(int timer) {
    	ListIterator<NPC> movable = movables.listIterator();
    	while(movable.hasNext()) {
	    	if(movable instanceof NormalPea ) {
	    		int i = movables.indexOf(movable);
	    		for(i++; i< 3;) {
	    			if(((NPC) movable).collidesWith(movable.next())) { //Checks to see if the next object is an enemey
	    				Collections.swap(movables, i, i+1); //it moves forward until it hits an enemy
	    			}
	    		}
	    	}
	    	
	    	if(movable instanceof NormalZombie && movable.previous() == null) { //Check to see if the object is a Zombie
	    		if(((NPC) movable).isFriendly() == movable.previous().isFriendly()){
		    		if(timer % 5 == 0) {
		    			int i = movables.indexOf(movable);
		    			Collections.swap(movables, i, i-1); //it moves forward until it hits an enemy
		    		}
	    	}
    	}
    }
}
}