package Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Controllers.CollisionDetector;
import Controllers.MoveController;
import Models.*;
import Views.*;

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
    private GameBoardView bv = new GameBoardView(goc);

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
    // every turn this will be incremented
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
            Lawnmower lm = new Lawnmower(0,i);
            goc.addLawnMowers(lm);
        }

        // spawn zombies
        for(int i=0; i<level; i++){
            goc.spawnZombies();
        }
        goc.setGameBoard(gameBoard);
    }

    /**
     * Main game loop is to be implemented here
     * Check for collisions etc.
     */
    public void run(){
        zombieTot = 10;
        userWaves = 10;
        
        while(!checkEndGame()){
            goc.collectSun();
            updateGameBoard();
            bv.updateGameBoard();
            spawn();
            checkEndWave();
            CollisionDetector.detectCollisions(goc);
            moveController.movePeas(goc);
            CollisionDetector.detectCollisions(goc);
            moveController.moveZombies(goc);
            CollisionDetector.detectCollisions(goc);
            moveController.moveLawnmowers(goc);
            //spawn();
            // pea shooter shoots every 2 turns
            for(PeaShooter ps: goc.getPeaShooters()) {
                if(ps.getShootingRate()%2==0) {
                    goc.addPeas(ps.shoot());
                }
            }

            goc.reduceCoolDowns();
            goc.incrementTime();
            timer++;

            while(!bv.isUpdated()){
                try {
                Thread.sleep(50);
                } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
                }
                // System.out.println();;
            }
            bv.setNewTurn();
        }

        reader.close();
    }
    
    public void spawn() {
     	if(timer%3==0 && spawned<zombieTot/userWaves) {
             goc.spawnZombies();
     		spawned++;
     	}
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
        if(goc.getZombies().size() != 0 && waves == userWaves && checkEndWave()){
            System.out.println("You win!");
        	return true;
        }

        if (gameOver) {
        	System.out.println("Game over!");
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

    /**
     * Print the game grid
     */
    public void printGameBoard(){
        for(int i=0;i<6;i++){
            for(int j=0; j<10; j++){
                if (!gameBoard[i][j].isEmpty()){
                    String npcs = "";
                    for (NPC npc: gameBoard[i][j]){
                    	if(npc.isAlive()) {
                    		npcs += npc.toString();
                    	}
                    }

                    System.out.print("[ " + npcs + " ]");
                }else{
                    System.out.print("[    ]");
                }
            }
            System.out.println("\n");
        }
    }

    /**
     * Perform an action based on user input
     * 
     * @param input the user's input string
     */
    public void handleInput(){
        System.out.print("LEVEL: " + level + ", TURN: " + timer + ", sunPoints: " + goc.getSP());
        System.out.println(", sf cooldown: " + goc.getSFCoolDown() + ", ps cooldown: " + goc.getPSCoolDown());
        System.out.println("What would you like to do ?");
        System.out.println("buy sf x y: buy a sunflower for " + Sunflower.getCost());
        System.out.println("buy ps x y: buy a peashooter for " + NormalPea.getCost());
        System.out.println("q: Quit");
        System.out.println("Enter: do nothing");

        String input = reader.nextLine();
        String[] splitInput = input.split("\\s");

        switch(splitInput[0]){
            case "buy":
                goc.buyItem(splitInput);
                break;
            case "q":
                gameOver = true;
                break;
            default:
                System.out.println("doing nothing");
        }
    }

    public void updateGameBoard(){
        // reset gameboard
        gameBoard = new ArrayList[6][10];
        for(int i=0;i<6;i++){
            for(int j=0; j<10; j++){
                gameBoard[i][j] = new ArrayList<NPC>();
            }
        }

        setItemsLocation(goc.getLawnMowers());
        setItemsLocation(goc.getSunflowers());
        setItemsLocation(goc.getPeaShooters());
        setItemsLocation(goc.getPeas());
        setItemsLocation(goc.getZombies());
    }

    private void setItemsLocation(ArrayList arr){
        for(Object ob: arr){
            NPC np= (NPC)ob;
            int[] pos = np.getLocation();
            if (pos[0] < 10){
                gameBoard[pos[1]][pos[0]].add(np);
            }
        }
    }

    public static void main(String[] args){
        // use a thread to run the game
        Thread mainGameThread = new Thread(new GameController());
        mainGameThread.start();
    }
}
