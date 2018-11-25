package Tests;
/**
 * Tests the Walnut class.
 * @author Kurt Burton-Rowe
 * @since November 23, 2018
 */
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.*;
import Characters.*;

public class TestWalnut {
	Walnut nut;
    @BeforeEach
    protected void setUp(){
		nut = new Walnut(0, 0, 0);
    }
    @Test
    public void testFriendly(){
		assertTrue(nut.isFriendly());
    }
    
    @Test
    public void testMaxHealth() {
		assertEquals(nut.getMaxHealth(), 150);
	}
  
    @Test
    public void testTakeDamage(){
        Zombie zomb = new Zombie(2, 8, 0);
        zomb.setDamage(10);
        
        nut.takeDamage(zomb.getDamage());
    	assertEquals(nut.getCurrentHealth(),nut.getMaxHealth()-zomb.getDamage());
    }
    
    @SuppressWarnings("static-access")
    @Test
	public void testGetCost(){
        assertEquals(nut.getCost(),10);
    }
    
    @Test
    public void testLethalDamage() {
    	Zombie zomb = new Zombie(2, 8, 0);
    	zomb.setDamage(150);
    	
    	nut.takeDamage(zomb.getDamage());
    	assertFalse(nut.isAlive());    
    }
}
