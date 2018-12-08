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

public class TestGameView extends TestCase {
	GameBoardView gbv;
    GameObjectsController goc;
    MoveController mc;
    ArrayList<NPC>[][] gameBoard;

    public TestGameView() {
        super();

        goc = new GameObjectsController();
        mc = new MoveController();
        gbv = new GameBoardView(goc, mc);
        gameBoard = new ArrayList[6][10];
        for(int i=0;i<6;i++){
            for(int j=0; j<10; j++){
                gameBoard[i][j] = new ArrayList<NPC>();
            }
        }
        goc.setGameBoard(gameBoard); 
    }
    
	@Test
	public void testTime(){
        assertEquals("time: 0", gbv.getcurrentTime().getText());

        // go to next turn
        goc.updateTime();
        gbv.updateGameBoard(goc);
        assertEquals("time: 1", gbv.getcurrentTime().getText());
	}

	@Test
	public void testSfCoolDown(){
        assertEquals("sf cooldown: 0", gbv.getsfCoolDown().getText());

        // go to next turn
        goc.updateCoolDowns();
        goc.updateCoolDowns();
        goc.updateCoolDowns();
        goc.updateCoolDowns();
        goc.updateCoolDowns();

        // cool down should not go lower than 0
        gbv.updateGameBoard(goc);
        assertEquals("sf cooldown: 0", gbv.getsfCoolDown().getText());
	}

	@Test
	public void testPsCoolDown(){
        assertEquals("ps cooldown: 0", gbv.getpsCoolDown().getText());

        // go to next turn
        goc.updateCoolDowns();
        goc.updateCoolDowns();
        goc.updateCoolDowns();
        goc.updateCoolDowns();
        goc.updateCoolDowns();

        // cool down should not go lower than 0
        gbv.updateGameBoard(goc);
        assertEquals("ps cooldown: 0", gbv.getpsCoolDown().getText());
    
        // buy item
        String[] buyStr = new String("buy ps " + 2 + " " + 2).split("\\s");
        goc.buyItem(buyStr);

        // should update
        gbv.updateGameBoard(goc);
        assertEquals("ps cooldown: 3", gbv.getpsCoolDown().getText());

        goc.updateCoolDowns();
        goc.updateCoolDowns();

        // should go down to 1
        gbv.updateGameBoard(goc);
        assertEquals("ps cooldown: 1", gbv.getpsCoolDown().getText());
        
	}

}
