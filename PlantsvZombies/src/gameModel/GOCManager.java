package gameModel;

import java.util.Stack;

public class GOCManager{

    // game state stacks
    private Stack<GameObjectsController> next = new Stack();
    private Stack<GameObjectsController> prev = new Stack();
    private boolean undo = false;
    private boolean redo = false;

    /**
     * Update the stack
     * @param boolean undo whether undoing a turns
     */
    public void updateStack(boolean undo){
        this.undo = undo;
        if(undo){
            // pop until we get to init state
            if(!(next.size() == 1)){
                System.out.println("undo");
                prev.push(next.pop());
                redo = false;
            }
        }else if(!undo){
            if(prev.isEmpty()){
                // create a copy of the top of the stack 
                // that will later be modified
                System.out.println("next");
                next.push((GameObjectsController)next.peek().clone());
                redo = false; // we are not redoig a turn
            }else{
                // redoing a turn
                System.out.println("redo");
                next.push(prev.pop());
                redo = true;
            }
        }
    }

    /**
     * Get the current goc object
     * @return GameObjectController
     */
    public GameObjectsController getCurrentGOC(){
        return next.peek();
    }

    /**
     * Push the init game state onto the stack
     * @param GameObjectsController initGOC the init goc object
     */
    public void initStack(GameObjectsController initGOC){
        next.push(initGOC);
    }

    /**
     * Tells whether or not to run methods on GOC
     */
    public boolean shouldUpdate(){
        return !(undo || redo);
    }
}