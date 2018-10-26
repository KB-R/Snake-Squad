package Models;

/**
 * PeaShooter plant that shoots at regular intervals
 */
public class PeaShooter extends Plant{
	private static final int cost = 9999;
    private static final int shootingRate; // shooting rate in millis (/turn?)
    private static final int damage; // damage dealt to enemies
 

    public PeaShooter(int x, int y,int shootingRate){ //inc max hp
       // super(50,50,5000,x,y,10,50);
        this.shootingRate = shootingRate;
        this.damage = 10;
        this.cost=9999;
    }

    /**
     * Shoot at a pea
     * 
     */
    public void shoot(){
    	int x=this.x; //current plant loc
    	
    	while (array grid logic(x, this.y))
    		if //zombie is at x,y && @zombie
    			//return "damage" to zombie
    		
    		else
    			
    			//incress x++; for each turn
    }

    }
}