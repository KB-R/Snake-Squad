package Models;
/**
 * 
 * @author Kurt Burton-Rowe
 * @version 1.0
 * @date October 26, 2018
 * The lawn mower is the indestructible get out of jail free card. It destroys all enemies in its row ONCE. 
 */
public class Lawnmower extends NPC implements Movable{
	private static int maxHealth = 10000000;
	private static int damage = 10000000;
	private static int speed = 9;
	private int x, y;
	private boolean contact = false;
	
	public Lawnmower(){
		super(maxHealth, true);
		this.setVelocity(speed);
		this.x = arry[0];
		this.y = arry[1];
		this.setLocation(x,y);
	}
	
	/*When the Lawn mower moves it goes to the end of the board only after it gets hit by a zombie*/
	public void runOver() {
		if (this.getCurrentHealth()<maxHealth) {
			this.move();
		}
	}
	@Override
	public int getVelocity() {
		// TODO Auto-generated method stub
		return speed;
	}
	@Override
	public void setVelocity(int newV) {
		// TODO Auto-generated method stub
		this.speed = newV;
	}
	
	/*When the Lawn mower moves it goes to the end of the board only after it gets hit by a zombie*/
	@Override
	public void move() {
		// TODO Auto-generated method stub
		x+=9; //
		this.setLocation(this.x, this.y);
	}
}
