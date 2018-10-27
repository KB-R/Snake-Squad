package Models;
/**
 * 
 * @author Kurt Burton-Rowe
 * @date October 26,2018
 * @version 1.0
 *
 * The normal pea is the object that 
 */
public class NormalPea extends NPC implements Movable{
	private int x, y;
	private int speed;
	private boolean contact = false;
	
	public NormalPea(int nX, int nY) {
		super(0, true);
		setVelocity(4);
		this.x = arry[0];
		this.y = arry[1];
		this.setLocation(nX, nY);
	}
	
	@Override
	public int getVelocity() {
		// TODO Auto-generated method stub
		return speed;
	}
	/*Sets the speed of the pea's traversal through the board */
	@Override
	public void setVelocity(int newV) {
		// TODO Auto-generated method stub
		speed = newV;
	}
	/*Moves the pea through the board only the x coordinate changes*/
	@Override
	public void move() {
		// TODO Auto-generated method stub
		x-=2;
		this.setLocation(x, y);
	}
	
}
