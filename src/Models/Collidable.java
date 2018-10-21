package models;

/**
 * Objects that can collide with one another
 */
public class Collidable{
    // x, y position, with & height
    int x;
    int y;
    int w; 
    int h;

    public Collidable(int x, int y, int w, int h){
        this.x = x;
        this.y = y; 
        this.w = w;
        this.h = h;
    }

    public boolean collidesWith(Collidable o){
        return false;
    }
}