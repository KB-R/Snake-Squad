package Models;

public abstract class npc {

	protected boolean isAlive;

	protected int currentHealth;
	private int maxHealth;
	protected boolean isFriendly;
	protected int x;
	protected int y;

public npc (int maxHealth, boolean isfriendly){
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean setXY(int x, int y) {
		this.x = x;
		this.y = y;
		return true;
	}

	public boolean isWhere(int x, int y) {
		return (this.x == x & this.y == y);
	}

}