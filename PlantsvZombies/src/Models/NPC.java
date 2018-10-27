package Models;
/**
 * @author Athony Maevskipopov
 * @author Kurt Burton-Rowe
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
	protected int[] arry;
	
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

	public int getCurrentHealth() {
		return currentHealth;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int takeDamage(int damage) {
		currentHealth = currentHealth - damage;

		if (currentHealth <= 0) {
			dies();
		}
		return currentHealth;
	}

	private void dies() {
		isAlive = false;
		currentHealth = 0;

	}

	public boolean isAlive() {
		return isAlive;
	}

	public boolean isFriendly() {
		return isFriendly;
	}

	public int[] getLocation() {
	    	this.arry[0] = this.x;
	    	this.arry[1] = this.y;
	    	return arry;
	 }
	    
	 public void setLocation(int nX, int nY) {
	    	this.x=nX;
	    	this.y=nY;
	    	this.arry[0] = this.x;
	    	this.arry[1] = this.y;
	}

}