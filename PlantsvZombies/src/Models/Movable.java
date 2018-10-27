package Models;

/**
 * @author Maxime Ndutiye 
 * @version 1.0
 * @date October 25, 2018
 * All movable objects must implement this interface (Peas, Zombies and Lawn mowers)
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