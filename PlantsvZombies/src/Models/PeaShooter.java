package Models;

/**
 * @author Kurt Burton-Rowe
 * @version 1.0
 * @date October 26, 2018
 * PeaShooter plant that shoots at regular intervals
 */
public class PeaShooter extends NPC{
    private int shootingRate; // shooting rate in millisecs
    private int damage; // damage dealt to enemies

    public PeaShooter(int x, int y,int shootingRate){
        super(100, true);
        this.x=x;
        this.y=y; //50,50,5000,x,y,10,50);
        this.shootingRate = shootingRate;
        this.damage = 10;
    }

    /**
     * Shoots out a pea from the same coordinates as the peashooter
     */
    public void shoot(){
    		NormalPea p = new NormalPea(this.x, this.y);
    }
}