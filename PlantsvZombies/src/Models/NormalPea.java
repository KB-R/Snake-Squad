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
	private static int sunCost = 10;

	public NormalPea(int nX, int nY, int timeSpawned) {
		super(1, true, timeSpawned);
		setVelocity(4);
		this.x = coordinates[0];
		this.y = coordinates[1];
		this.setLocation(nX, nY);
	}
	
	@Override
	public int getVelocity() {
		return speed;
	}

	/**
	 * Sets the speed of the pea's traversal through the board 
	 * 
	 **/
	@Override
	public void setVelocity(int newV) {
		speed = newV;
	}

	/**
	 * Moves the pea through the board only if there's no collision and not outside the board. 
	 * Only the x coordinate changes
	 * @param int time the current time
	 * @param boolean undo wether a turn is being undone
	 **/
	@Override
	public void move(int time, boolean undo) {
		if(!(collision)&&this.coordinates[0]<10) {
			if(undo){
				x--;
			}else{
				x++;
			}
			this.setLocation(x, y);
		}
		else if(collision) {
			this.takeDamage(1);
		}
	}

	/**
	 * Get the cost of friendly buyable items
	 * @return int the cost of the item
	 */
    public static int getCost(){
        return sunCost;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public String toString(){
		return "NP";
	}
}
