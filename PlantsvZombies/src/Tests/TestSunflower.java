package Tests;
/**
 * Tests the Sunflower classes.
 * @author Kurt Burton
 * @author Anthony
 * @since Novemeber 25, 2018
 * @version 1.0
 *
 */
import Characters.*;
import junit.framework.TestCase;



public class TestSunflower extends TestCase{
	Sunflower sunf;
	
	public TestSunflower() {
        super();

		sunf = new Sunflower(0, 0, 0);
    }

    /*
    @BeforeEach
    */
    protected void setUp(){
    	sunf = new Sunflower(0, 0, 0);
    }
	/*
	@Test
	*/
    public void testFriendly(){
		assertTrue(sunf.isFriendly());
    }
    
	/*
	@Test
	*/
    public void testMaxHealth() {
		assertEquals(sunf.getMaxHealth(),50);
	}
    

	/*
	@Test
	*/
    public void testNameTag() {
    	assertEquals(sunf.toString(),"SF");
    }
  
	/*
	@Test
	*/
    public void testTakeDamage(){
        Zombie zomb = new Zombie(2, 8, 0);
        zomb.setDamage(10);
        
        sunf.takeDamage(zomb.getDamage());
    	assertEquals(sunf.getCurrentHealth(),sunf.getMaxHealth()-zomb.getDamage());
    }
    
    @SuppressWarnings("static-access")
	/*
	@Test
	*/
	public void testGetCost(){
        assertEquals(sunf.getCost(),10);
    }
    
	/*
	@Test
	*/
    public void testLethalDamage() {
    	Zombie zomb = new Zombie(2, 8, 0);
    	zomb.setDamage(50);
    	
    	sunf.takeDamage(zomb.getDamage());
    	assertFalse(sunf.isAlive());    	
    }   
}
