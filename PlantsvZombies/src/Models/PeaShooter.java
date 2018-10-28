package Models;
import java.util.*;
import Controllers.MoveController;

/**
 * @author Kurt Burton-Rowe
 * @version 1.0
 * @date October 26, 2018
 * PeaShooter is plant that shoots normal peas at regular intervals.
 * The PeaShooter does deal damage only its peas do.
 */
public class PeaShooter extends NPC{
    private int shootingRate; // shooting rate in millisecs
    private ArrayList<NormalPea> peas; //List of peas that the pea shooter has shot have to iterate 
    private static int sunCost = 10;
    
    public PeaShooter(int x, int y,int shootingRate){
        super(100, true);
        this.x=x;
        this.y=y; 
        this.setShootingRate(shootingRate);
    }

    /* Shoots out a pea from the same coordinates as the pea shooter*/
    public void shoot(){
    		NormalPea p = new NormalPea(this.x, this.y);
    		peas.add(p);
    		//add p to movable
    }

	public int getShootingRate() {
		return shootingRate;
	}

	public void setShootingRate(int shootingRate) {
		this.shootingRate = shootingRate;
	}
	public String toString(){
		return "PS";
	}
}
