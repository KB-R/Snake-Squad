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
    	ListIterator<NPC> collide = collidables.listIterator();
    	while(collide.hasNext()) {
    		NPC next = collide.next();
    		NPC pres = (NPC) collide;
    		NPC prev = collide.previous();
    		if(pres.collidesWith(next) || pres.collidesWith(prev)) {
    			if(pres instanceof NormalPea && ((NormalPea) pres).getContact()) { //Checks to see if it's a pea and if it already hit or not
    				next.takeDamage(((NormalPea) pres).getDamage());
    				((NormalPea) pres).setContact(true);
    			}
    			if(pres instanceof NormalZombie) {
    				prev.takeDamage(((NormalPea) pres).getDamage());
    			}
    		}
    		
    	}
		return null;
    	
    }
}