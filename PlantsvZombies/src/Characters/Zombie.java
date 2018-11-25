package Characters;
import java.util.Random;
/**
 * 
 * @author Kurt Burton-Rowe
 * @author Maxime Ndutiye
 * @version 1.0
 * @date October 25, 2018
 * 
 * The base model for all Zombies that will appear in the game
 */

public class Zombie extends NPC implements Movable{

    protected int maxHealth;
    protected int speed;
    protected int damage;
    protected int moveTick=0;
    protected int x;
    protected int y;
    protected int[] coordinate;
    
    /**
     * Zombie constructor to set where it spawns on the board and how many tiles it takes up
     * @param health The amount of health that a Zombie gets
     * @param speed  The speed that the Zombie will traverse the board
     * @param timeSpawned The time that the zombie spawned
     */
    public Zombie(int health, int speed, int timeSpawned) {
    	super(health, false, timeSpawned);
    	this.maxHealth = health;
    	this.speed = speed;
    	this.x = 9;
    	this.y = random();
    	setLocation(this.x,this.y);
    }
   
    /**
     * Move the NPC
     * @param int time the current time
     * @param boolean undo whether or not we are undoing a turn
     */
    public void move(int time, boolean undo){
    	if(!(collision)&&(time%5==0)&&(this.coordinates[0]>0)) {
            if(undo){
                this.x++;
            }else{
                this.x--;
            }
    		setLocation(this.x, this.y);
    	}
    }

    public int random() {
    	Random rand = new Random();
    	return rand.nextInt(6);
    }
	
    /**
     * @return The speed of the Zombie
     */
    @Override
    public int getVelocity() {
	    return speed;
    }
	
    @Override
    public void setVelocity(int newV) {
	    this.speed = newV;
    }
	
    public void setDamage(int damage) {
	    this.damage=damage;
    }
	
    /**
     * @return The damage that the Zombie inflicts 
     */
    public int getDamage() {
        return damage;
    }

    public String toString(){
        return "ZB";
    }
}
