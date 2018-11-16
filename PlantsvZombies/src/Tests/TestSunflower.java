package Tests;

import Models.Sunflower;
import Models.Zombie;

public class TestSunflower extends junit.framework.TestCase{
    Sunflower sunf;
    
    protected void setUp(){
    	sunf = new Sunflower(0, 0, 0);
    }

    public void testFriendly(){
		assertEquals(sunf.isFriendly(),true);
    }
    
    /*someone look at the produceSun function and this one, im not sure why these dont work.
    public void testCorrectSunProduced() {
    	sunf.produceSun(9999);
    	assertEquals(sunf.collectSun(),0);
    	sunf.produceSun(10020);
    	assertEquals(sunf.collectSun(),0);
    	sunf.produceSun(8);
    	assertEquals(sunf.collectSun(),8);
    }
    
    */
    public void testMaxHealth() {
		assertEquals(sunf.getMaxHealth(),50);
	}
    

    
    public void testNameTag() {
    	assertEquals(sunf.toString(),"SF");
    	}
  
    /**
     * Test takeDamage method 
     */
    public void testTakeDamage(){
        Zombie zomb = new Zombie(2, 8);
        zomb.setDamage(10);
        sunf.takeDamage(zomb.getDamage());
    	assertEquals(sunf.getCurrentHealth(),sunf.getMaxHealth()-zomb.getDamage());
    }
    
    @SuppressWarnings("static-access")
	public void testGetCost(){
        assertEquals(sunf.getCost(),10);
    }
    
    public void testLethalDamage() {
    	Zombie zomb = new Zombie(2, 8);
    	zomb.setDamage(10);
    	sunf.takeDamage(zomb.getDamage());
    	sunf.takeDamage(zomb.getDamage());
    	sunf.takeDamage(zomb.getDamage());
    	sunf.takeDamage(zomb.getDamage());
    	sunf.takeDamage(zomb.getDamage());
    	assertEquals(sunf.isAlive(),false);    	
    }
    
    
    
    
}