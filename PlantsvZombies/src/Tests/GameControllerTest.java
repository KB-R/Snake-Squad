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
import java.util.ArrayList;
import Characters.NPC;
import Controller.*;

public class GameControllerTest extends TestCase {
	GameController gc;

    public GameControllerTest() {
        super();
    }
    
	@Test
	public void testTime(){
        assertEquals("time: 0", gbv.getcurrentTime().getText());

        // go to next turn
        goc.updateTime();
        gbv.updateGameBoard(goc);
        assertEquals("time: 1", gbv.getcurrentTime().getText());
	}
}
