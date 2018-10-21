package models;

/**
 * All movable objects must implement this interface 
 */
public interface Movable{
    public int velocity = 1;

    /**
     * get the objects velocity
     * @return int the velocity
     */
    public int getVelocity();

    /**
     * Set the velocity
     */
    public void setVelocity(int newV);

    /**
     * Move
     */
    public void move();
}