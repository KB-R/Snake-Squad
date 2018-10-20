
public class GameController implements Runnable{

    /**
     * Main game loop is to be implemented here
     * Check for collisions etc.
     */
    public void run(){
        
    }

    public void checkCollisions(){

    }
    public static void main(string[] args){
        // use a thread to run the game
        Thread mainGameThread = new Thread(new GameController());
        mainGameThread.start();
    }
}
