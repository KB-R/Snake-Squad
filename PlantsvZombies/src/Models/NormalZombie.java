package Models;
/**
 * @author Kurt Burton-Rowe
 * @version 1.0
 * @date October 26, 2018
 */

public class NormalZombie extends Zombie{
	private static int maxHealth=30;
	private int damage=10;
	
	public NormalZombie(int height) {
		super(maxHealth, 1, height);
		setDamage(damage);
	}
}
