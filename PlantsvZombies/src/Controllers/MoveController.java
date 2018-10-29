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
    	for( int i=0;  i < movables.size(); i++ ){
	    	if(movables.get(i) instanceof NormalPea ) {
	    		for(int a=0; a< 3;a++) {
	    			if(movables.get(i).collidesWith(movables.get(i+1))) { //Checks to see if the next object is an enemey
	    				Collections.swap(movables, a, a+1); //it moves forward until it hits an enemy
	    			}
	    		}
	    	}
	    	
	    	if(movables.get(i) instanceof NormalZombie && movables.get(i-1) == null) { //Check to see if the object is a Zombie
	    		if((movables.get(i).isFriendly() == movables.get(i-1).isFriendly())){
		    		if(timer % 5 == 0) {
		    			Collections.swap(movables, i, i-1); //it moves forward until it hits an enemy
		    		}
	    	}
    	}
    }
}
}