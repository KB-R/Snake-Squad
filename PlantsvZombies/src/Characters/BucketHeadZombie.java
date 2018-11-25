package Characters;
/*The Pylon Zombie has more health than normal zombies but it moves at the same pace
 * @author Kurt Burton-Rowe
 * @date November 20, 2018*/
public class BucketHeadZombie extends Zombie{
	private int damage=10;

	/**
	 * The Bucket Head Zombie constructor
	 * @param spawnTime When the zombie was spawned.
	 */
	public BucketHeadZombie(int spawnTime) {
		super(60, 1, spawnTime);
		setDamage(damage);
	}
}
