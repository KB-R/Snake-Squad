package Controllers;

import java.util.ArrayList;
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
    private CollisionDetector controller = new CollisionDetector();
    private MoveController moveDetector = new MoveController();

    private ArrayList<Zombie> zombies = new ArrayList<Zombie>();
    private ArrayList<Sunflower> sunflowers = new ArrayList<Sunflower>();
    private ArrayList<PeaShooter> peaShooters = new ArrayList<PeaShooter>();
    
    // gameboard
    NPC[][] gameBoard = new NPC[6][10];
    Scanner reader = new Scanner(System.in);  

    // player
    private int sunPoints = 0;
    private long sunFlowerCooldown = 0;
    private long peaShooterCooldown = 0;
    private int level = 1;

    // this will act as our clock for now
    // every turn this will be incremented
    private int timer = 0; 

    public GameController(){

    }

    /**
     * Main game loop is to be implemented here
     * Check for collisions etc.
     */
    public void run(){
        System.out.println("running main thread");

        while(true){
            printGrid();
            handleInput();
            // move game objects
            // check collisions

            produceSun();

            if(checkEndGame()){
                break;
            }
            timer++;
        }
        reader.close();
    }

    /**
     * Check to see if the game is over
     * @return boolean true if game is over
     */
    public boolean checkEndGame(){
        return false;
    }

    /**
     * Print the game grid
     */
    public void printGrid(){
        for(int i=0;i<6;i++){
            for(int j=0; j<10; j++){
                if (gameBoard[i][j] != null){
                    System.out.print("[" + i + gameBoard[i][j] + j + "]");
                }else{
                    if (j > 0 && j < 9){
                        System.out.print("[    ]");
                    }else if (j == 0){
                        System.out.print("  LM  ");
                    }else {
                        System.out.print("      ");
                    }
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
        System.out.println("LEVEL: " + level + " TURN: " + timer + ", sunPoints: " + sunPoints);
        System.out.println("What would you like to do ?");
        System.out.println("buysf x y: buy a sunflower for " + Sunflower.getCost());
        System.out.println("buyps x y: buy a peashooter for " + NormalPea.getCost());
        System.out.println("Enter: do nothing");

        String input = reader.nextLine();
        String[] splitInput = input.split("\\s");

        switch(splitInput[0]){
            case "buysf":
                System.out.println("buy sunflower " + splitInput[1] + "," + splitInput[2]);

                try{
                    Integer xPos = Integer.parseInt(splitInput[1]);
                    Integer yPos = Integer.parseInt(splitInput[2]);
                    Sunflower newSunflower = new Sunflower(xPos, yPos);

                    buyItem(newSunflower, xPos, yPos);
                }catch (NumberFormatException nfe){
                    System.out.println("illegal input arguments");
                }
                break;
            case "buyps":
                System.out.println("buy peashooter " + splitInput[1] + "," + splitInput[2]);

                try{
                    Integer xPos = Integer.parseInt(splitInput[1]);
                    Integer yPos = Integer.parseInt(splitInput[2]);
                    NormalPea newPeaShooter = new NormalPea(xPos, yPos);

                    buyItem(newPeaShooter, xPos, yPos);
                }catch (NumberFormatException nfe){
                    System.out.println("illegal input arguments");
                }
                break;
            default:
                System.out.println("doing nothing");
        }
    }

    public void buyItem(NPC item,Integer xPos,Integer yPos){
        if (item.getCost() <= sunPoints && xPos > 0 && xPos < 9 && yPos < 6 && yPos >= 0){
            if (gameBoard[xPos][yPos] != null){
                System.out.println("Something is already in that position, or u arent allowed to place things here");
            }else{
                gameBoard[xPos][yPos] = item;
                sunPoints -= item.getCost();
            }
        }else{
            System.out.println("Couldn't process your purchase, your input was out of bounds");
        }
    }

    /**
     * Product sunPoints every 2 turns
     */
    private void produceSun(){
        if (timer % 2 == 0){
            sunPoints += 10;
        }
    }

    public static void main(String[] args){
        // use a thread to run the game
        Thread mainGameThread = new Thread(new GameController());
        mainGameThread.start();

        System.out.println("starting game");
    }
}
