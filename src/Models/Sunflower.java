package Models;

import javafx.beans.Observable;

public class Sunflower extends Plant{
    
	private static final int health = 999999;
	private static final int cost = 99999;
	int sunAmmount; // ammount of sun to spawn //prob not needed
    int sunSpawnRate; // how often to spawn sun in millis //prob not needed

    /**
     * 
     * @param x horizontal position
     * @param y virtical position
     */
    public Sunflower(int x, int y){
        super(50,50,5000, x, y, 10, 50);
        sunAmmount = 10;
        sunSpawnRate = 10;
    }
}