package Models;
/*The Walnut is a plant that does not attack zombies but has a higher health
 * @author Kurt Burton-Rowe
 * @date November 20, 2018
 * */
public class Walnut extends NPC{

	private final static int maxHealth = 150;
	
	public Walnut(int x , int y, int spawnTime) {
		super(maxHealth, true, spawnTime);
		this.coordinates[0] = x;
		this.coordinates[1] = y;
	}
	
}
