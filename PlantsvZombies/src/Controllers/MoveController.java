package Controllers;

import java.util.ArrayList;

/**
 * Handle the movement of all movable objects in the game
 */
public class MoveController{
    private static ArrayList<Object> movables;

    public MoveController(){
        movables = new ArrayList<Object>();
    }

    /**
     * Add a new movable object
     * @param movable
     */
    public static void addMovables(Object movable){
        movables.add(movable);
    }

    public ArrayList<Object> getMovables(){
        return movables;
    }

    /**
     * Move objects
     */
    public void moveObjects(){

    }
}