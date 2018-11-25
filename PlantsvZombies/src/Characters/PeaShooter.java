package Characters;
import java.util.*;
import gameModel.MoveController;

/**
 * @author Kurt Burton-Rowe
 * @version 1.0
 * @date October 26, 2018
 * PeaShooter is plant that shoots normal peas at regular intervals.
 * The PeaShooter does deal damage only its peas do.
 */
public class PeaShooter extends NPC{
    private int shootingRate; // shooting rate in millisecs 
    private static int sunCost = 10;
    
    /**
     * Normal Pea Shooter Constructor
     * @param x the x co-oridante where it will be placed.
     * @param y the y co-ordinate where it will be placed.
     * @param shootingRate The rate that the peashooter will shoot out peas.
     * @param timeSpawned When the object was created.
     */
    public PeaShooter(int x, int y,int shootingRate, int timeSpawned){
        super(100, true, timeSpawned);
        this.x=x;
        this.y=y; 
        this.setShootingRate(shootingRate);
        this.currentHealth=100;
    }

    /* Shoots out a pea from the same coordinates as the pea shooter*/
    public NormalPea shoot(int time){
        NormalPea p = new NormalPea(this.x, this.y, time);
        return p;
    }

	public int getShootingRate() {
		return shootingRate++;
	}

	public void setShootingRate(int timer) {
		this.shootingRate = timer;
	}
	public String toString(){
		return "PS";
	}
}
