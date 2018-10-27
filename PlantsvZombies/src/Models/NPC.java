package Models;
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

	protected int currentHealth;
	private int maxHealth;
	protected boolean isFriendly;
	protected int x;
	protected int y;
	protected int[] coordinates;
	
	/** 
	 * @param maxHealth the max health of the object
	 * @param isFriendly boolean for whether object is friendly for the user
	 */
	public NPC (int maxHealth, boolean isFriendly){
		this.isAlive = true;
		this.currentHealth = maxHealth;
		this.maxHealth = maxHealth;
		this.isFriendly = isFriendly;
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
	 * @return An int array that holds the [x, y] coordinates
	 */
	public int[] getLocation() {
	    	this.coordinates[0] = this.x;
	    	this.coordinates[1] = this.y;
	    	return coordinates;
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
	  * @param o An NPC in the same coordinate as this NPC object
	  * @return True if they collide with each other
	  * Checks if the NPCs collide with one another
	  */
	 public boolean collidesWith(NPC o){
		 //if both are friendly or if both are not friendly then return false
	     if ( (this.isFriendly() && o.isFriendly())  || (!(this.isFriendly()) && !(o.isFriendly())) ) {
	    	 return false;
	     }  
	     return true;
	 }

}
