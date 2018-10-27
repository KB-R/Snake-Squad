package Controllers;

import java.util.ArrayList;
import java.util.List;

import Controllers.CollisionDetector;
import Controllers.MoveController;
import Models.*;

public class GameController implements Runnable{
    private CollisionDetector controller = new CollisionDetector();
    private MoveController moveDetector = new MoveController();

    public ArrayList<Zombie> zombies = new ArrayList<Zombie>();
    public ArrayList<Sunflower> sunflowers = new ArrayList<Sunflower>();
    public ArrayList<PeaShooter> peaShooters = new ArrayList<PeaShooter>();
    
    // gameboard arraylist
    public List<List<NPC>> gameBoard = new ArrayList<>();

    /**
     * Main game loop is to be implemented here
     * Check for collisions etc.
     */
    public void run(){
        System.out.println("running main thread");

        // while(true){
            // creat game objects

            // move game objects

            // check collisions

            // check endgame
        // }
    }

    /**
     * Check to see if the game is over
     * @return boolean true if game is over
     */
    public boolean checkEndGame(){
        return false;
    }

    public static void main(String[] args){
        // use a thread to run the game
        Thread mainGameThread = new Thread(new GameController());
        mainGameThread.start();

        System.out.println("starting game");
    }
}