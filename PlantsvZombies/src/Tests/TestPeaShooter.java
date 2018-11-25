package Tests;
/**
 * Tests the Pea Shooter classes.
 * @author Kurt Burton
 * @since Novemeber 25, 2018
 * @version 1.0
 *
 */
import Characters.*;
import gameModel.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class TestPeaShooter{

	PeaShooter normalp;
    DoublePeaShooter doublep;
    GameObjectsController goc;

    @BeforeEach
    protected void setUp() {
        normalp= new PeaShooter(0,0,1,0);
        doublep= new DoublePeaShooter(1, 1, 2, 0);
        goc = new GameObjectsController();
        goc.addDoublePeaShooter(doublep);
        goc.addPeaShooter(normalp);
	}
    @Test
	public void testFriendly(){
        assertTrue(normalp.isFriendly());
        assertTrue(doublep.isFriendly());
	}
	@Test
	public void testMaxHealth() {
        assertEquals(100, normalp.getMaxHealth());
        assertEquals(100, doublep.getMaxHealth());
	}
	@Test
	public void testNameTag() {
		assertEquals(normalp.toString(),"PS");
	}
    @Test
    public void testTakeDamage(){
    	Zombie zomb = new Zombie(10, 1, 0);
        zomb.setLocation(1, 0);
        
        normalp.takeDamage(zomb.getDamage());
        doublep.takeDamage(zomb.getDamage());
        
        assertEquals(normalp.getMaxHealth()-zomb.getDamage(),normalp.getCurrentHealth());
        assertEquals(doublep.getMaxHealth()-zomb.getDamage(),doublep.getCurrentHealth());
        
        assertTrue(normalp.collidesWith(zomb)); 
        
        zomb.setLocation(2, 1);
        assertTrue(doublep.collidesWith(zomb));
	}
	@Test 
	public void testShoot(){
		goc.shootPeas();
		goc.shootPeas();
		
		int x = goc.getPeas().size();
		
		assertEquals(3, x);
	}
}