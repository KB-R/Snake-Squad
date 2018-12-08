package Tests;

import static org.junit.jupiter.api.Assertions.*;

import gameModel.*;
import Characters.*;
import Controller.GameController;
import Views.GameBoardView;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * 
 * @author KBR
 *
 */
public class TestGameController {
	GameObjectsController goc;
	MoveController mov;
	GameController controller;
	GameBoardView view;
	
	@BeforeEach
	void setup() {
		controller = new GameController();
		mov = new MoveController();
		view = new GameBoardView(goc, mov);
		
	}
	
	@Test
	void testEndgame() {
		assertFalse(controller.checkEndGame());
	}
	@Test
	void testEndWave() {
		assertFalse(controller.checkEndWave());
	}

}
