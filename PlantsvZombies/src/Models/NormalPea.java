package Models;
/**
 * The normal pea is the object that
 * @author Kurt Burton-Rowe
 * @date October 26,2018
 * @version 1.0
 */
public class NormalPea extends NPC implements Movable{
	private int speed;
	private final int damage = 10;
	private boolean contact = false; //boolean could be useful for the collision detector
	
	public NormalPea(int nX, int nY) {
		super(0, true);
		setVelocity(4);
		this.x = coordinates[0];
		this.y = coordinates[1];
		this.setLocation(nX, nY);
	}
	
	@Override
	public int getVelocity() {
		return speed;
	}
	/*Sets the speed of the pea's traversal through the board */
	@Override
	public void setVelocity(int newV) {
		speed = newV;
	}
	/*Moves the pea through the board only the x coordinate changes*/
	@Override
	public void move() {
		x+=4;
		this.setLocation(x, y);
	}

	public int getDamage() {
		return damage;
	}
	
}
