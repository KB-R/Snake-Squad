package Tests;

import Models.NormalPea;
import Models.Zombie;

public class TestZombie extends junit.framework.TestCase{
    Zombie zomb;

  

    protected void setUp(){
    	zomb = new Zombie(20, 0);
    	zomb.setLocation(3, 3);
    }
    
    public void testFriendly(){
		assertEquals(zomb.isFriendly(),false);
    }
    
    public void testNameTag() {
    	assertEquals(zomb.toString(),"ZB");
    }
    
    
    public void testMaxHealth() {
		assertEquals(zomb.getMaxHealth(),20);
	}
    
    
    
    /**
     * Test takeDamage method 
     */
    public void testTakeDamage(){
    	NormalPea Npea= new NormalPea(0, 0);
    	zomb.takeDamage(Npea.getDamage());        	
        assertEquals(zomb.getCurrentHealth(),10);
    }
    
    public void testLethalDamage() {
    	NormalPea Npea= new NormalPea(0, 0);
    	zomb.takeDamage(Npea.getDamage()); 
    	zomb.takeDamage(Npea.getDamage()); 
    	assertEquals(zomb.isAlive(),false);  
    }
}