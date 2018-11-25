package Characters;
/**
 * The running zombie has the same health as the normal Zombie but it moves much faster.
 * @author Kurt Burton-Rowe
 * @date November 20, 2018
 *
 */
public class RunZombie extends Zombie{
    private int damage=8;

	public RunZombie(int spawnTime) {
        super(30, 1, spawnTime);
        setDamage(damage);
	}

	/**
     * Move the NPC
     * @param int time the current time
     * @param boolean undo whether or not we are undoing a turn
     */
	@Override
    public void move(int time, boolean undo){
		// Run zombies are a faster
    	if(!(collision)&&(time%3==0)&&(this.coordinates[0]>0)) {
            if(undo){
                this.x++;
            }else{
                this.x--;
            }
    		setLocation(this.x, this.y);
    	}
    }
}
