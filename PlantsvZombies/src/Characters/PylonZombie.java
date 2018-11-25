package Characters;
/*The Pylon Zombie has more health than normal zombies but it moves at the same pace
 * @author Kurt Burton-Rowe
 * @date November 20, 2018*/
public class PylonZombie extends Zombie{
	private int damage=15;

	public PylonZombie(int spawnTime) {
		super(60, 1, spawnTime);
		setDamage(damage);
	}
}
