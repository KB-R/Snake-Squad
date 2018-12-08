package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import javax.swing.JButton;
import Views.*;
import gameModel.GameObjectsController;
import gameModel.MoveController;
import junit.framework.TestCase;

class TestGameView {
	GameBoardView gbv;
    GameObjectsController goc;
    MoveController mc;

	@BeforeEach
	void setUp() {
        goc = new GameObjectsController();
        mc = new MoveController();
		gbv = new GameBoardView(goc, mc);
	}

	@Test
	void testTime(){
        assertEquals("time: 0", gbv.getcurrentTime().getText());

        // go to next turn
        goc.updateTime();
        assertEquals("time: 1", gbv.getcurrentTime().getText());
	}

	@Test
	void testSfCoolDown(){
        assertEquals("sf cooldown: 0", gbv.getsfCoolDown().getText());

        // go to next turn
        goc.updateTime();
        goc.updateTime();
        goc.updateTime();
        goc.updateTime();
        goc.updateTime();

        // cool down should not go lower than 0
        gbv.updateGameBoard(goc);
        assertEquals("sf cooldown: 0", gbv.getsfCoolDown().getText());
    
        // buy item
        String[] buyStr = new String("buy sf " + 3 + " " + 3).split("\\s");
        goc.buyItem(buyStr);

        gbv.updateGameBoard(goc);
        assertEquals("sf cooldown: 3", gbv.getcurrentTime().getText());

        goc.updateTime();
        goc.updateTime();
        goc.updateTime();

        // cool down should be zero now
        assertEquals("sf cooldown: 0", gbv.getcurrentTime().getText());
	}

	@Test
	void testPsCoolDown(){
        assertEquals("ps cooldown: 0", gbv.getsfCoolDown().getText());

        // go to next turn
        goc.updateTime();
        goc.updateTime();
        goc.updateTime();
        goc.updateTime();
        goc.updateTime();

        // cool down should not go lower than 0
        gbv.updateGameBoard(goc);
        assertEquals("ps cooldown: 0", gbv.getsfCoolDown().getText());
    
        // buy item
        String[] buyStr = new String("buy ps " + 2 + " " + 2).split("\\s");
        goc.buyItem(buyStr);

        gbv.updateGameBoard(goc);
        assertEquals("ps cooldown: 3", gbv.getcurrentTime().getText());

        goc.updateTime();
        goc.updateTime();
        goc.updateTime();

        // cool down should be zero now
        assertEquals("ps cooldown: 0", gbv.getcurrentTime().getText());
	}

}
