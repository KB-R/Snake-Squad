package models;

public class Plant{
    private int health;
    private int speed; // shooting speed or sun spawn speed
    private int refreshTime; // time until we can replant a similar plant in millis
    public int x;
    public int y;
    public int w;
    public int h;

    /**
     * 
     * @param health
     * @param speed
     * @param refreshTime time untill we can spawn similar plant
     * @param x horizontal position
     * @param y virtical position 
     * @param w width
     * @param h height
     */
    Plant(int health, int speed, int refreshTime, int x, int y, int w, int h){
        this.health = health; 
        this.speed = speed;
        this.refreshTime = refreshTime;        
        this.x = x;
        this.y = y; 
        this.w = w;
        this.h = h;
    }
}