package Tests;

import Models.NPC;
import junit.framework.*;

public class TestZombie extends TestCase{
    NPC newNPC = new NPC(10,true);

    TestNPC(){

    }

    protected void setUp(){

    }

    /**
     * Test takeDamage method 
     */
    public void testTakeDamage(){
        assertEquals(1,1);
    }
}