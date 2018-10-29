package Controllers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

import Models.NPC;
import Models.NormalPea;
import Models.NormalZombie;

/**
 * Keep track of all objects which can collide and detect whether any of them
 * are currently colliding
 * @author Tareq Hanafi
 */
public class CollisionDetector{
    private ArrayList<NPC> collidables;

    public CollisionDetector(){
        collidables = new ArrayList<NPC>();
    }

    public void addCollidable(NPC collidable){
        collidables.add(collidable);
    }

    public void removeCollidable(NPC collidable){
        collidables.remove(collidable);
    }

    /**
     * Detect collision between all collidable objects
     */
    public void detectCollisions(){
    	for( int i=0;  i < collidables.size(); i++ ){
    		NPC pres = collidables.get(i);
    		if(pres.collidesWith(collidables.get(i+1))) {
    			if(pres instanceof NormalPea) { //Checks to see if it's a pea and if it already hit or not
    				collidables.get(i+1).takeDamage(((NormalPea) pres).getDamage());
    			}
    			
    			if(pres instanceof NormalZombie) {
    				if(i<0) {
    					System.out.println("Game Over");
    				}
    				else {
    					collidables.get(i-1).takeDamage(((NormalZombie) pres).getDamage());
    				}
    			}
    			
    		}
    		
    	}
    	
    }
}