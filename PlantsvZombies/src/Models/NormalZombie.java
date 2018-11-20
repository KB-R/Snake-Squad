package Models;
/**
 * @author Kurt Burton-Rowe
 * @version 1.0
 * @date October 26, 2018
 */

public class NormalZombie extends Zombie{
	private int damage=10;

	public NormalZombie(int timeSpawned) {
		super(30, 1, timeSpawned);
		setDamage(damage);
	}
	public String toString(){
		return "Z";
	}
	
}
