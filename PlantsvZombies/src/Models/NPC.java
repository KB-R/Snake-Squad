package Models;
import java.util.Arrays;

import Controllers.CollisionDetector;
/**
 * @author Athony Maevskipopov
 * @author Kurt Burton-Rowe
 * @author Maxime N
 * @date October 26, 2018
 * @version 2.0
 *
 */
public abstract class NPC {
	protected boolean isAlive;
	protected boolean collision;
	protected int damage;
	protected int currentHealth;
	private int maxHealth;
	protected boolean isFriendly;
	protected int x;
	protected int y;
	protected int[] coordinates;
	protected static int sunCost;
	protected int timeSpawned;

	/** 
	 * @param maxHealth the max health of the object
	 * @param isFriendly boolean for whether object is friendly for the user
	 */
	public NPC (int maxHealth, boolean isFriendly, int timeSpawned){
		this.isAlive = true;
		this.currentHealth = maxHealth;
		this.maxHealth = maxHealth;
		this.isFriendly = isFriendly;
		this.coordinates = new int[2];
		this.damage = 0;
		this.collision = false;
		this.timeSpawned = timeSpawned;
	}
	
	/**
	 * @return The current health of the NPC
	 */
	public int getCurrentHealth() {
		return currentHealth;
	}
	/**
	 * @return The max Health of the NPC
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
	
	/**
	 * @param damage The amount of health taken from NPC when it collides with opposite type of NPC 
	 * @return The current health of the NPC
	 */
	public int takeDamage(int damage) {
		currentHealth = currentHealth - damage;

		if (currentHealth <= 0) {
			dies();
		}
		return currentHealth;
	}
	
	/**
	 * @param o The second NPC will getting hit and will take the amount of damage from this NPC 
	 */
	public void dealDamage(NPC o) {
		o.takeDamage(damage);
	}
	
	/*The NPC dies since its current health is 0*/
	private void dies() {
		isAlive = false;
		currentHealth = 0;
	}
	
	/**
	 * @return Boolean to check if NPC is still alive
	 */
	public boolean isAlive() {
		return isAlive;
	}

	/**
	 * @return Checks if their friend
	 */
	public boolean isFriendly() {
		return isFriendly;
	}
	
	/**
	 * Get the cost of friendly buyable items
	 * @return int the cost of the item
	 */
    public static int getCost(){
        return sunCost;
	}

	/**
	 * @return An int array that holds the [x, y] coordinates
	 */
	public int[] getLocation() {
	    	this.coordinates[0] = this.x;
	    	this.coordinates[1] = this.y;
	    	return coordinates;
	}
	
	/**
	* @return An int array that holds the [x, y] coordinates
	*/
	public int getY() {
		    return this.y;
	}

	/**
	 * @param nX The new x coordinate of the NPC
	* @param nY The new y coordinate of the NPC
	*/
	public void setLocation(int nX, int nY) {
		this.x=nX;
		this.y=nY;
		this.coordinates[0] = this.x;
		this.coordinates[1] = this.y;
	}
	
	 /**
	  * Checks if 2 NPCs are the same team
	  * @param o NPC that might collide 
	  * @return True if they will collide
	  */
	 public boolean isOpposite(NPC o) {
		 if((this.isFriendly() && !(o.isFriendly()))  || (!(this.isFriendly()) && (o.isFriendly())) ) {
			 return true;
		 }
		 return false;
	 }
	 
	 /*set collision to true or false*/
	 public void collided() {
		 this.collision= !(this.collision);
	 }

	/**
	 * Checks if the NPCs collide with one another
	* @param o An NPC in the same coordinate as this NPC object
	* @return True if they collide with each other
	*/
	public boolean collidesWith(NPC o){
		//both of them have the same location and are a friendly v unfriendly they collide
		if((Arrays.equals(this.getLocation(), o.getLocation()) ) && (  this.isOpposite(o) )&&this.isAlive()&&o.isAlive()){
			this.collided();
			o.collided();
			return true;
		}
		return false;
	 }
	 
	public String toString(){
		return "NPC";
	}

	public int getTimeSpawned(){
		return timeSpawned;
	}
}
