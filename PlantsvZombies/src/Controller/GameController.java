package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import gameModel.CollisionDetector;
import gameModel.MoveController;
import Characters.*;
import Views.*;
import gameModel.*;

/**
 * @author Maxime Ndutiye
 * @version 1.0
 * @since October 27, 2018
 * The GameController class is responsible for running the game
 * It holds all of the game objects as well as handles input from the player
 */
public class GameController implements Runnable{
    
    // ArrayList for game objects
    private MoveController moveController = new MoveController();
    private GameObjectsController goc = new GameObjectsController();
    private GameBoardView bv = new GameBoardView(goc, moveController);
    private GOCManager gcm = new GOCManager();

    // gameboard
    ArrayList<NPC>[][] gameBoard = new ArrayList[6][10];
    Scanner reader = new Scanner(System.in);  

    // player
    private int level = 1;
    private boolean gameOver = false;
    private int zombieTot;
    private int spawned=0;
    private int waves=0;
    private int userWaves=0;
    private boolean undo = false;
    private boolean redo = false;

    // this will act as our clock for now
    private int timer = 0; 

    /**
     * Initialize varibales
     */
    public GameController(){
    	
        for(int i=0;i<6;i++){
            for(int j=0; j<10; j++){
                gameBoard[i][j] = new ArrayList<NPC>();
            }
        }
      
        goc.spawnLawnMowers();

        // spawn zombies
        for(int i=0; i<level; i++){
            goc.spawnZombies();
        }
        goc.setGameBoard(gameBoard);
        //set the games amount of zombies
        goc.setZombieTot(bv.gameZombies());

        goc.setUserWaves(bv.gameWaves());
        bv.setGC(this);
    }

    /**
     * Main game loop is to be implemented here
     * Check for collisions etc.
     */
    public void run(){
        gcm.initStack(goc);

        // show initi game state
        bv.updateGameBoard(gcm.getCurrentGOC());
        while(!checkEndGame()){
            
            // new turn create copy of old
            GameObjectsController tgoc = gcm.getCurrentGOC();
            gcm.updateStack(undo);

            // get the new goc to update or modify
            goc = gcm.getCurrentGOC();
    
            // DON'T modify the goc if we are redoing a turn
            if(gcm.shouldUpdate()){
                goc.collectSun();
                spawn();

                CollisionDetector.clearCollisions(goc);
                CollisionDetector.detectCollisions(goc);

                moveController.movePeas(goc);
                moveController.moveZombies(goc);
                moveController.moveLawnmowers(goc);
            
                // pea shooter shoots every 2 turns
                goc.shootPeas();
            }

            if(gcm.shouldUpdate()){
                goc.collectGarbage();
                goc.updateCoolDowns();
                goc.updateTime();
            }

            goc.checkEndWave();
            bv.updateGameBoard(goc); // pass modified goc to view

            // wait for next to be pressed
            while(!bv.isUpdated()){
                try {
                Thread.sleep(50);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
            bv.setNewTurn();
        }
        reader.close();
    }
    
    public void spawn() {
        goc.spawnZombies();
    }

    public boolean checkEndWave() {
    	// zombies dead
        if(goc.getZombies().size() != 0){
            for(Zombie z: goc.getZombies()) {
            	if(z.isAlive()) {
            		return false;
            	}
            }    
        }
		this.waves++;
        return true;
    }

    /**
     * Check to see if the game is over
     * @return boolean true if game is over
     */
    public boolean checkEndGame(){
 
        // zombies dead
        if(goc.getZombies().size() == 0 && goc.getCurrentWave() >= goc.getUserWave() && goc.checkEndWave()){
            bv.gameWon();
            return true;
        }

        if (gameOver) {
            bv.gameOver();
            return true;
        }
    	
    	//they lose if a zombie gets passed the lawn mower
    	for(Zombie z: goc.getZombies()) { 
    		int[] n = {0, z.getY()} ;
    		if(Arrays.equals(z.getLocation(), n)){
    			int y = z.getY();
    			if(this.gameBoard[0][z.getY()].get(0) instanceof Lawnmower) {
    				System.out.println("You Lost!");
    				return true;
    			}
    		}
    	}
        return false;
    }

    public void setUndo(){
        undo = true;
    }

    public void unsetUndo(){
        undo = false;
    }

    public static void main(String[] args){
        // use a thread to run the game
        Thread mainGameThread = new Thread(new GameController());
        mainGameThread.start();
    }
}
