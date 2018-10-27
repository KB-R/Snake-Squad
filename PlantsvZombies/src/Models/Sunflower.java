package Models;

/**
 * The SunFlower produces the sun credits that the player uses
 * @author Maxime Ndutiye
 * @author Kurt Burton-Rowe
 * @version 1.0
 * @since October 25, 2018
 * 
 */

public class Sunflower extends NPC{
    private final static int maxHealth = 50;
    private final int sunAmount = 25; // amount of sun to spawn
    private int sunProduced = 0;
    private int sunSpawnRate; // how often to spawn sun in milliseconds
    private boolean sunCollected;
    

    /**
     * @param x horizontal position
     * @param y vertical position
     */
    
    public Sunflower(int x, int y){
        super(maxHealth, true);
        this.x=x;
        this.y=y;
        this.setLocation(this.x, this.y);
        sunSpawnRate = 3000; //three seconds
        
    }
    
    /*Produce sun credits*/
    public void produceSun() {
    	if(sunCollected) {
    		sunProduced = 0;
    	}
    	for(int i =0; i < sunSpawnRate; i++);
    	this.sunProduced += this.sunAmount;
    	
    }
    
    /** 
     * @return Returns the total amount of sun credits that the sun flower produced in the game so far.
     * (to be added to the credit total)*/
    public int collectSun() {
    	this.sunCollected = true;
    	return this.sunProduced;
    	
    }
 
}
