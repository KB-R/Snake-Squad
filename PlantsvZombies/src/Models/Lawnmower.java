package Models;
/**
 * The lawn mower is the indestructible get out of jail free card. It destroys all enemies in its row ONCE.
 * @author Kurt Burton-Rowe
 * @version 1.0
 * @date October 26, 2018
 */
public class Lawnmower extends NPC implements Movable{
	private final static int maxHealth = 10000000;
	private final int damage = 10000000;
	private final int speed = 9;
	private int x, y;
	
	public Lawnmower(int x, int y){
		super(maxHealth, true);
		this.setVelocity(speed);
		this.x = x;
		this.y = y;
		this.setLocation(this.x,this.y);
	}
	
	/**
	 * @return The speed that the lawn mower traverses the board
	 */
	@Override
	public int getVelocity() {
		return speed;
	}
	
	/*Cannot change the speed of the lawn mower*/ 
	@Override
	public void setVelocity(int newV) {}
	
	public int getDamage() {
		return damage;
	}
	
	/*When the Lawn mower moves it goes to the end of the board only after it gets hit by a zombie*/
	@Override
	public void move() {
		if(!(collision)&&this.currentHealth<maxHealth){
			x++;
			this.setLocation(this.x, this.y);
		}
	}

	public String toString(){
		return "LM";
	}
}
