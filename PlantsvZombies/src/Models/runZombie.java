package Models;
/**
 * The running zombie has the same health as the normal Zombie but it moves much faster.
 * @author Kurt Burton-Rowe
 * @date November 20, 2018
 *
 */
public class RunZombie extends Zombie{

	public RunZombie(int spawnTime) {
		super(30, 1, spawnTime);
	}
}
