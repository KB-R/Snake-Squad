package models;

/**
 * PeaShooter plant that shoots at regular intervals
 */
public class PeaShooter extends Plant{
    private int shootingRate; // shooting rate in millis
    private int damage; // damage dealt to enemies

    public PeaShooter(int x, int y,int shootingRate){
        super(50,50,5000,x,y,10,50);
        this.shootingRate = shootingRate;
        this.damage = 10;
    }

    /**
     * Shoot at a pea
     * 
     */
    public void shoot(){

    }
}