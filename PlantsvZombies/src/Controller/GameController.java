package Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

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
    private CollisionDetector collisionController = new CollisionDetector();
    private MoveController moveController = new MoveController();
    private GameObjectsController goc = new GameObjectsController();
    private GameBoardView bv = new GameBoardView(goc, moveController);

    private Stack<GameObjectsController> undoTurn = new Stack<>();
    private Stack<GameObjectsController> redoTurn = new Stack<>();

    private int count =0;
    private int redoCount =0;
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

        // add lawn mowers
        for(int i=0;i<6;i++){
            Lawnmower lm = new Lawnmower(0,i, goc.getTime());
            goc.addLawnMowers(lm);
        }

        // spawn zombies
        for(int i=0; i<level; i++){
            goc.spawnZombies();
        }
        goc.setGameBoard(gameBoard);
        //set the games amount of zombies
        goc.setZombieTot(bv.gameZombies());

        goc.setUserWaves(bv.gameWaves());

        bv.addUndoListener(new undoAction());
        bv.addNextListener(new nextAction());
        bv.addRedoListener(new redoAction());
    }

    /**
     * Main game loop is to be implemented here
     * Check for collisions etc.
     */
    public void run(){        
        while(!checkEndGame()){
            goc.collectSun();
            bv.updateGameBoard(goc);
            spawn();

            CollisionDetector.clearCollisions(goc);
            CollisionDetector.detectCollisions(goc);

            moveController.movePeas(goc);
            moveController.moveZombies(goc);
            moveController.moveLawnmowers(goc);
        
            // pea shooter shoots every 2 turns
            goc.shootPeas();
         
            // wait for next to be pressed
            while(!bv.isUpdated()){
                try {
                Thread.sleep(50);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }

            bv.setNewTurn();
            goc.collectGarbage();
            goc.updateCoolDowns();
            goc.updateTime();
            goc.checkEndWave();
            
            timer++;
        }
        reader.close();
    }
    /*Spawns zombies*/
    public void spawn() {
        goc.spawnZombies();
    }

    /**
     * Actionlistener to handle events for going to the next turn in the game
     */
    class nextAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {     
                count++;
                redoCount = count;
                newTurn();
                //moveController.nextTurn();
                bv.setDone();
            
        }
    }
    /**
     * Actionlistener to handle events for going to the next turn in the game
     */
    class undoAction implements ActionListener {
		public void actionPerformed(ActionEvent e) { 
            if (goc.getTime() > 0){
                redoCount--;
                moveController.setUndo();
               // goc.setUndo();
                goc = prevTurn();
                //moveController = moveController.prevTurn();
                //bv.updateView(goc, moveController);
                bv.setDone();
            }
		} 
    }
    /**
     * Actionlistener to handle events for going to the next turn in the game
     */
    class redoAction implements ActionListener {
		public void actionPerformed(ActionEvent e) { 
            if (redoCount < count){
                redoCount++;
                goc = nextTurn();
                bv.setDone();
            }
		} 
    }
    /**
     * Pushes the current version of the GOC onto the stack. And deletes the previous moves.
     */
    public void newTurn(){
        undoTurn.push(goc);
        if(!redoTurn.isEmpty())
            redoTurn.clear();
    }
    /**
     * Returns to the previous GOC
     * @return The object controller from the previous turn.
     */
    public GameObjectsController prevTurn() {
        if (!undoTurn.isEmpty()) {
        	redoTurn.push(undoTurn.peek());
        }
        return undoTurn.pop();
    }
    /**
     * Returns to the next GOC
     * @return The object controller from the next turn.
     */
    public GameObjectsController nextTurn(){
        undoTurn.push(redoTurn.peek());
        return redoTurn.pop();
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

    public static void main(String[] args){
        // use a thread to run the game
        Thread mainGameThread = new Thread(new GameController());
        mainGameThread.start();
    }
}
