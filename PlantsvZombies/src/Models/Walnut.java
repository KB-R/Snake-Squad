package Models;
/**The Walnut is a plant that does not attack zombies but has a higher health
 * @author Kurt Burton-Rowe
 * @since November 20, 2018
 * */
public class Walnut extends NPC{

	private final static int maxHealth = 150;
	private static int sunCost = 10;

	public Walnut(int x , int y, int spawnTime) {
		super(maxHealth, true, spawnTime);
		this.x=x;
        this.y=y;
        this.setLocation(this.x, this.y);
	}
	
}
