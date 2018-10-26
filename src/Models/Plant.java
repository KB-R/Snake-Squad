package Models;

public class Plant extends npc{
    private int speed; // shooting speed or sun spawn speed
    private int refreshTime; // time until we can replant a similar plant in millis
    private int cost;
    /**
     * s
     * @param health
     * @param speed
     * @param refreshTime time untill we can spawn similar plant
     * @param x horizontal position
     * @param y virtical position 
     * @param w width
     * @param h height
     */
    Plant(int maxHealth, int speed, int refreshTime, int cost){
        super(maxHealth,true);
        this.speed = speed;
        this.refreshTime = refreshTime;        
        this.cost=cost;
    }
    
	public int getCost(){
		return cost;
	}
    
    
}