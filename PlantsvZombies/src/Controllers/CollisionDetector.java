package Controllers;
import java.util.ArrayList;
import java.util.Iterator;

import Models.NPC;
import Models.NormalPea;;

/**
 * Keep track of all objects which can collide and detect whether any of them
 * are currently colliding
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
    public Boolean detectCollisions(){
    	Iterator<NPC> collide = collidables.iterator();
    	while(collide.hasNext()) {
    		NPC next = collide.next();
    		NPC pres = (NPC) collide;
    		if(pres.collidesWith(next)) {
    			if(pres instanceof NormalPea) {
    				next.takeDamage(((NormalPea) pres).getDamage());
    				collidables.remove(collide);
    			}
    		}
    		
    	}
		return null;
    	
    }
}