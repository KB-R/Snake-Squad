package Models;

/**
 * All movable objects must implement this interface (Peas, Zombies and Lawn mowers)
 * @author Maxime Ndutiye 
 * @version 1.0
 * @date October 25, 2018
 */
public interface Movable{
    public int velocity = 1;

    /**
     * @return get the objects velocity
     */
    public int getVelocity();

    /* Set the velocity */
    public void setVelocity(int newV);

    /* Move the object */
    public void move(int time, boolean undo);
}
