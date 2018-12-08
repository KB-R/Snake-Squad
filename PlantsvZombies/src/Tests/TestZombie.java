package Tests;
import junit.framework.TestCase;
import Characters.*;

/**
 * Tests the Zombie classes.
 * @author Anthony
 * @author Kurt Burton-Rowe
 * @since November 23, 2018
 */


public class TestZombie extends TestCase{
    Zombie zomb;
    NormalZombie normal;
    BucketHeadZombie bucket;
    PylonZombie pylon;
    RunZombie run;
    
	public TestZombie() {
        super();

		zomb = new Zombie(20, 0, 0);
    	zomb.setLocation(3, 0);
    	
    	bucket = new BucketHeadZombie(0);
    	bucket.setLocation(3, 1);
    	
    	pylon = new PylonZombie(0);
    	pylon.setLocation(3, 2);
    	
    	run = new RunZombie(0);
    	run.setLocation(3, 3);
    	
    	normal = new NormalZombie(0);
    	normal.setLocation(3, 4);
	}

    /*
    @BeforeEach
    */
    protected void setUp(){
    	zomb = new Zombie(20, 0, 0);
    	zomb.setLocation(3, 0);
    	
    	bucket = new BucketHeadZombie(0);
    	bucket.setLocation(3, 1);
    	
    	pylon = new PylonZombie(0);
    	pylon.setLocation(3, 2);
    	
    	run = new RunZombie(0);
    	run.setLocation(3, 3);
    	
    	normal = new NormalZombie(0);
    	normal.setLocation(3, 4);
    }
	/*
	@Test
	*/
    public void testFriendly(){
		assertFalse(zomb.isFriendly());
		assertFalse(bucket.isFriendly());
		assertFalse(pylon.isFriendly());
		assertFalse(run.isFriendly());
		assertFalse(normal.isFriendly());
    }
	/*
	@Test
	*/
    public void testNameTag() {
    	assertEquals(zomb.toString(),"ZB");
    }
	/*
	@Test
	*/
    public void testMaxHealth() {
		assertEquals(zomb.getMaxHealth(),20);
		assertEquals(bucket.getMaxHealth(),60);
		assertEquals(pylon.getMaxHealth(),60);
		assertEquals(run.getMaxHealth(),30);
		assertEquals(normal.getMaxHealth(),30);
	}
	/*
	@Test
	*/
    public void testTakeDamage(){
    	NormalPea Npea= new NormalPea(0, 0, 0);
    	zomb.takeDamage(Npea.getDamage());  
    	bucket.takeDamage(10);
    	pylon.takeDamage(10);
    	run.takeDamage(10);
    	normal.takeDamage(10);
    	
        assertEquals(zomb.getCurrentHealth(),10);
        assertEquals(bucket.getCurrentHealth(),50);
        assertEquals(pylon.getCurrentHealth(),50);
        assertEquals(run.getCurrentHealth(),20);
        assertEquals(normal.getCurrentHealth(),20);
    }
	/*
	@Test
	*/
    public void testLethalDamage() {
    	NormalPea Npea= new NormalPea(0, 0, 0);
    	zomb.takeDamage(Npea.getDamage()); 
    	zomb.takeDamage(Npea.getDamage());
    	bucket.takeDamage(60);
    	pylon.takeDamage(60);
    	run.takeDamage(30);
    	normal.takeDamage(30);
    	
    	assertEquals(zomb.getCurrentHealth(),0);
        assertEquals(bucket.getCurrentHealth(),0);
        assertEquals(pylon.getCurrentHealth(),0);
        assertEquals(run.getCurrentHealth(),0);
        assertEquals(normal.getCurrentHealth(),0);
    	
        assertFalse(zomb.isAlive()); 
        assertFalse(bucket.isAlive());
        assertFalse(pylon.isAlive()); 
        assertFalse(run.isAlive()); 
        assertFalse(normal.isAlive()); 
    }
	/*
	@Test
	*/
    public void testMove() {
		zomb.move(0,false);
		zomb.move(0,false);
		
		bucket.move(0, false);
		bucket.move(0, false);
		
		pylon.move(0,false);
		pylon.move(0,false);
		
		normal.move(0, false);
		normal.move(0, false);
		
		run.move(0, false);
		run.move(0, false);
        
        assertEquals(zomb.getLocation()[0], 3);
		assertEquals(bucket.getLocation()[0], 3);
		assertEquals(pylon.getLocation()[0], 3);
		assertEquals(run.getLocation()[0], 3);
		assertEquals(normal.getLocation()[0], 3);
    }
}