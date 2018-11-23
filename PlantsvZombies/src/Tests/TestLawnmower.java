package Tests;
import Models.Lawnmower;
import Models.Zombie;
//Anthony//
public class TestLawnmower extends junit.framework.TestCase{
	Lawnmower lawnmower;
	
	protected void setUp() {
	lawnmower= new Lawnmower(0,0,0); 
	}
    
	public void testFriendly(){
		assertEquals(lawnmower.isFriendly(),true);
		assertEquals(lawnmower.getMaxHealth(),10000000);
		assertEquals(lawnmower.toString(),"LM");		
    }
	
	public void testMaxHealth() {
		assertEquals(lawnmower.getMaxHealth(),10000000);
	}

	public void testNameTag() {
	assertEquals(lawnmower.toString(),"LM");
	}
    /**
     * Test takeDamage method 
     */
    public void testTakeDamage(){
        Zombie zomb = new Zombie(2, 8, 0);
        lawnmower.takeDamage(zomb.getDamage());
    	assertEquals(lawnmower.getCurrentHealth(),lawnmower.getMaxHealth()-zomb.getDamage());
    }
}