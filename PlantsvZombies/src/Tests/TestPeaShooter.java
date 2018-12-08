package Tests;
import junit.framework.TestCase;
/**
 * Tests the Pea Shooter classes.
 * @author Kurt Burton
 * @since Novemeber 25, 2018
 * @version 1.0
 *
 */
import Characters.*;
import gameModel.*;


public class TestPeaShooter extends TestCase{

	PeaShooter normalp;
    DoublePeaShooter doublep;
    GameObjectsController goc;
	Zombie zombieOne,zombieTwo;
	
	public TestPeaShooter() {
        super();

		normalp= new PeaShooter(0,0,0,2);
        doublep= new DoublePeaShooter(1, 1, 0, 2);
        normalp.setShootingRate(2);  
        doublep.setShootingRate(2);   
        goc = new GameObjectsController();
        goc.addDoublePeaShooter(doublep);
        goc.addPeaShooter(normalp);	
	}

    /*
    @BeforeEach
    */
    protected void setUp() {
        normalp= new PeaShooter(0,0,0,2);
        doublep= new DoublePeaShooter(1, 1, 0, 2);
        normalp.setShootingRate(2);  
        doublep.setShootingRate(2);   
        goc = new GameObjectsController();
        goc.addDoublePeaShooter(doublep);
        goc.addPeaShooter(normalp);	

	}
	/*
	@Test
	*/
	public void testFriendly(){
        assertTrue(normalp.isFriendly());
        assertTrue(doublep.isFriendly());
	}
	/*
	@Test
	*/
	public void testMaxHealth() {
        assertEquals(100, normalp.getMaxHealth());
        assertEquals(100, doublep.getMaxHealth());
	}
	/*
	@Test
	*/
	public void testNameTag() {
		assertEquals(normalp.toString(),"PS");
	}
	/*
	@Test
	*/
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
	/*
	@Test
	*/
	public void testShoot(){
		
        goc.shootPeas();
		goc.shootPeas();

		
		int numberOfPeas = goc.getPeas().size();
		
		assertEquals(0, numberOfPeas);
	}
}