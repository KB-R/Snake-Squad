package Models;
import java.util.*;

/**
 * @author Maxime Ndutiye
 * @author Kurt Burton-Rowe
 * @version 1.0
 * @date October 25, 2018
 * 
 * The  
 */

public class Sunflower extends NPC{
	private static int maxHealth = 50;
    private static int sunAmmount = 25; // amount of sun to spawn
    private int sunSpawnRate; // how often to spawn sun in milliseconds
    private int x, y; // x y co-ordinates 
    

    /**
     * 
     * @param x horizontal position
     * @param y vertical position
     */
    
    public Sunflower(int x, int y){
        super(50, true);
        this.x=x;
        this.y=y;
        sunSpawnRate = 10;
    }
    
    /*Adds the sun credit to the credit total in the game*/
    public void collectSun() {
    	SunCredit += this.sunAmmount;
    }
 
}