package Models;

import javafx.beans.Observable;

public class Sunflower extends Plant{
    int sunAmmount; // ammount of sun to spawn
    int sunSpawnRate; // how often to spawn sun in millis

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