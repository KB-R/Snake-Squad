package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import javax.swing.JButton;
import Views.*;
import gameModel.GOCManager;
import gameModel.GameObjectsController;
import gameModel.MoveController;
import junit.framework.TestCase;
import java.util.ArrayList;
import Characters.NPC;

public class TestGOCManager extends TestCase {
	GOCManager gmg;
    public TestGOCManager() {
        super();

        gmg = new GOCManager();
    }
    
	@Test
	public void testInitStack(){
        assertNotNull(gmg.getNextStack());
        assertNotNull(gmg.getPrevStack());

        assertEquals(0,gmg.getNextStack().size());
        assertEquals(0,gmg.getPrevStack().size());

        // init next stack
        gmg.initStack(new GameObjectsController());
        
        // assert size increase
        assertEquals(1,gmg.getNextStack().size());

        // assert prev receives prev goc
        gmg.updateStack(false); 
        gmg.updateStack(true); // undoing turn
        assertEquals(1,gmg.getPrevStack().size());

        // assert size doesn't go below zero
        gmg.updateStack(true); // undoing turn
        gmg.updateStack(true); // undoing turn
        assertEquals(1,gmg.getNextStack().size());
	}   

	@Test
	public void testUndoRedo(){
        gmg.initStack(new GameObjectsController());
        gmg.updateStack(false);
        gmg.updateStack(false);
        gmg.updateStack(false);

        GameObjectsController top = gmg.getCurrentGOC();
        
        gmg.updateStack(false); // next turn
        assertNotSame(top, gmg.getCurrentGOC());

        // undo 
        gmg.updateStack(true); // next turn
        assertEquals(top, gmg.getCurrentGOC());
	}

}
