package Tests;
import Characters.*;
import junit.framework.TestCase;


/**
 * Tests the Lawnmower class.
 * @author Anthony
 * @since November 23, 2018
 */
public class TestLawnmower extends TestCase {
	Lawnmower lawnmower;
	
	/*
	@BeforeEach
	*/
	protected void setUp() {
		lawnmower= new Lawnmower(0,0,0); 
	}
	/*
    @Test
    */
	public void testFriendly(){
		assertTrue(lawnmower.isFriendly());		
	}
	/*
	@Test
	*/
	public void testMaxHealth() {
		assertEquals(lawnmower.getMaxHealth(),10000000);
	}
	/*
	@Test
	*/
	public void testNameTag() {
		assertEquals(lawnmower.toString(),"LM");
	}
	/*
    @Test
    */
    public void testTakeDamage(){
        Zombie zomb = new Zombie(10, 1, 0);
        zomb.setLocation(1, 0);
        lawnmower.takeDamage(zomb.getDamage());
		assertEquals(lawnmower.getCurrentHealth(),lawnmower.getMaxHealth()-zomb.getDamage());
		assertTrue(lawnmower.collidesWith(zomb));
	}
    
    /*
	@Test 
	*/
	public void testMove(){
		lawnmower.takeDamage(10);
		for(int i = 0; i<3; i++){
			lawnmower.move(i,false);
		}
		assertEquals(lawnmower.getLocation()[0], 3);
	}
}