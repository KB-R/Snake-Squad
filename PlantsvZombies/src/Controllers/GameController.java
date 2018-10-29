package Controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Controllers.CollisionDetector;
import Controllers.MoveController;
import Models.*;

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

    // gameboard
    ArrayList<NPC>[][] gameBoard = new ArrayList[6][10];
    Scanner reader = new Scanner(System.in);  

    // player
    private int sunPoints = 0;
    private long sunFlowerCooldown = 0;
    private long peaShooterCooldown = 0;
    private long coolDown = 3;
    private int level = 1;
    private boolean gameOver = false;
    private int zombieTot = 0;

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
    }

    /**
     * Main game loop is to be implemented here
     * Check for collisions etc.
     */
    public void run(){
        System.out.println("running main thread");

        while(true){
            collectSun();
            updateGameBoard();
            printGameBoard();
            handleInput();
            CollisionDetector.detectCollisions(goc);
            moveController.moveObjects(goc);

            spawn();
            // pea shooter shoots every 2 turns
            for(PeaShooter ps: goc.getPeaShooters()) {
                if(ps.getShootingRate()%2==0) {
                    goc.addPeas(ps.shoot());
                }
            }

            if(checkEndGame()){
                break;
            }

            if (sunFlowerCooldown > 0)
                sunFlowerCooldown--;
            if (peaShooterCooldown > 0)
                peaShooterCooldown--;
            timer++;
        }
        reader.close();
    }
    
    public void spawn() {
    	if(timer%3==0 && zombieTot<4) {
            goc.spawnZombies();
    		zombieTot++;
    	}
    }
    
    /**
     * checks if all the zombie's are dead
     * @return True if all zombies are dead
     */
    public boolean dead() {
    	if (goc.getZombies().isEmpty()) {
    		return true;
    	}
    	return false;
    }

    /**
     * Check to see if the game is over
     * @return boolean true if game is over
     */
    public boolean checkEndGame(){

        // zombies dead
        if(goc.getZombies().size() == 0){
            return true;
        }

        if (gameOver)
            return true;

    	// they win if all the zombies are killed
    	if (zombieTot==5&&dead()) {
    		return true;
    	}
    	
    	//they lose if a zombie gets passed the lawn mower
    	for(Zombie z: goc.getZombies()) { 
    		int[] n = {0, z.getY()} ;
    		if(Arrays.equals(z.getLocation(), n)){
    			int y = z.getY();
    			if(this.gameBoard[0][z.getY()].get(0) instanceof Lawnmower) {
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
        System.out.print("LEVEL: " + level + ", TURN: " + timer + ", sunPoints: " + sunPoints);
        System.out.println(", sf cooldown: " + sunFlowerCooldown + ", ps cooldown: " + peaShooterCooldown);
        System.out.println("What would you like to do ?");
        System.out.println("buy sf x y: buy a sunflower for " + Sunflower.getCost());
        System.out.println("buy ps x y: buy a peashooter for " + NormalPea.getCost());
        System.out.println("q: Quit");
        System.out.println("Enter: do nothing");

        String input = reader.nextLine();
        String[] splitInput = input.split("\\s");

        switch(splitInput[0]){
            case "buy":
                buyItem(splitInput);
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

    public void buyItem(String[] splitInput){
        if(splitInput.length != 4){
            System.out.println("Bad input!");
            return;
        }

        System.out.println(splitInput[0] + " " + splitInput[1] + ":" + splitInput[2] + "," + splitInput[3]);
        try{
            Integer xPos = Integer.parseInt(splitInput[2]);
            Integer yPos = Integer.parseInt(splitInput[3]);
            NPC item = null;

            switch (splitInput[1]){
                case "sf":
                    item = (sunFlowerCooldown <= timer 
                        && sunPoints >= Sunflower.getCost()) ? new Sunflower(xPos, yPos, timer): null;
                    sunFlowerCooldown = timer + coolDown;
                    sunPoints -= Sunflower.getCost();
                    break;
                case "ps":
                    item = (peaShooterCooldown <= timer 
                            && sunPoints >= PeaShooter.getCost()) ?  new PeaShooter(xPos, yPos, 2): null;
                    peaShooterCooldown = timer + coolDown;
                    sunPoints -= PeaShooter.getCost();
                    break;
                default:
                    break;
            }

            if (item != null && xPos > 0 && xPos < 9 && yPos < 6 && yPos >= 0){
                if (gameBoard[yPos][xPos].isEmpty()){
                    switch (splitInput[1]){
                        case "sf":
                            goc.addSunflower((Sunflower)item);
                            break;
                        case "ps":
                            goc.addPeaShooter((PeaShooter)item);
                            break;
                        default:
                            break;
                    }
                }else{
                    System.out.println("Something is already in that position");
                }
            }else{
                System.out.println("Couldn't process your purchase");
            }
        }catch (NumberFormatException nfe){
            System.out.println("illegal input arguments");
        }
    }

    /**
     * Produce sunPoints every 2 turns
     */
    private void produceSun(){
        if (timer % 2 == 0){
            sunPoints += 10;
        }
    }

    /**
     * Collect sun points from all sunflowers
     */
    private void collectSun(){
        produceSun();

        for(Sunflower sf: goc.getSunflowers()){
            sunPoints += sf.produceSun(timer);
        }
    }

    public static void main(String[] args){
        // use a thread to run the game
        Thread mainGameThread = new Thread(new GameController());
        mainGameThread.start();

        System.out.println("starting game");
    }
}
