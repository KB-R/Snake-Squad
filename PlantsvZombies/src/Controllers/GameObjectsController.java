package Controllers;

import java.util.ArrayList;
import Models.*;

/**
 * @author Maxime Ndutiye
 * Game Objects controller keeps track of all the game objects
 */
public class GameObjectsController{
    private ArrayList<Zombie> zombies;
    private ArrayList<Sunflower> sunflowers;
    private ArrayList<PeaShooter> peaShooters;
    private ArrayList<NormalPea> peas;
    private ArrayList<Lawnmower> lawnmowers;
    private int sunPoints = 0;
    private long sunFlowerCooldown = 0;
    private long peaShooterCooldown = 0;
    private long coolDown = 3;
    private int timer = 0; 

    ArrayList<NPC>[][] gameBoard = new ArrayList[6][10];

    public GameObjectsController(){
        // create NPC arraylists
        zombies = new ArrayList<Zombie>();
        sunflowers = new ArrayList<Sunflower>();
        peaShooters = new ArrayList<PeaShooter>();
        peas = new ArrayList<NormalPea>();
        lawnmowers = new ArrayList<Lawnmower>();
    }

    /**
     * Spawn some zombies given a level
     */
    public void spawnZombies(){
        Zombie zb = new NormalZombie(getTime());
        zombies.add(zb);
    }

    /**
     * Collect all objects that have 0 life
     */
    public void collectGarbage(){
        removeItems(zombies);
        removeItems(sunflowers);
        removeItems(peaShooters);
        removeItems(peas);
        removeItems(lawnmowers);
    }

    public void setGameBoard(ArrayList<NPC>[][] gameBoard){
        this.gameBoard = gameBoard;
    }

    private void removeItems(ArrayList arr){
        for(int i=0; i<arr.size(); i++){
            NPC np= (NPC)arr.get(i);

            // remove if dead
            if(np.getCurrentHealth() == 0){
                arr.remove(np);
            }

            // remove if off of board
            if(np.getLocation()[0] > 9){
                arr.remove(np);
            }

            // remove objects that were spawed after the current time
            if(np.getTimeSpawned() > getTime()){
                arr.remove(np);
            }
        }
    }

    /**
     * Add sunflowers
     * @param sf Sunflower to add
     */
    public void addSunflower(Sunflower sf){
        sunflowers.add(sf);
    }

    /**
     * Add Zombies
     * @param zb Zombie to add
     */
    public void addZombie(Zombie zb){
        zombies.add(zb);
    }

    /**
     * Add PeaShooters
     * @param ps PeaShooter to add
     */
    public void addPeaShooter(PeaShooter ps){
        peaShooters.add(ps);
    }

    /**
     * Add a pea
     * @param np NormalPea
     */
    public void addPeas(NormalPea np){
        peas.add(np);
    }

    /**
     * Add a lawnmower
     * @param lm Lawnmower
     */
    public void addLawnMowers(Lawnmower lm){
        lawnmowers.add(lm);
    }

    /**
     * get all Sunflowers
     * @return ArrayList<Sunflower> sunflower
     */
    public ArrayList<Sunflower> getSunflowers(){
        return sunflowers;
    }

    /**
     * Get all Zombies
     * @return ArrayList<Zombie>
     */
    public ArrayList<Zombie> getZombies(){
        return zombies;
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
    public void collectSun(){
        produceSun();

        for(Sunflower sf: getSunflowers()){
            sunPoints += sf.produceSun(timer);
        }
    }

    /**
     * Get all PeaShooters
     * @return ArrayList<PeaShooter> PeaShooters
     */
    public ArrayList<PeaShooter> getPeaShooters(){
        return peaShooters;
    }

    /**
     * Get the number of turns left until the player can buy sunflowers
     * @return long the number of turns to wait
     */
    public long getSFCoolDown(){
        return sunFlowerCooldown;
    }

    /**
     * Get the number of turns left until the player can buy peashooters
     * @return long the number of turns to wait
     */
    public long getPSCoolDown(){
        return peaShooterCooldown;
    }

    /**
     * Get the number of sunpoints the player has
     * @return
     */
    public long getSP(){
        return sunPoints;
    }

    /**
     * Get all the peas
     * @return ArrayList<NormalPea> NormalPeas
     */
    public ArrayList<NormalPea> getPeas(){
        return peas;
    }

    /**
     * Get all the mowers
     * @return ArrayList<LawnMower> lawnmowers
     */
    public ArrayList<Lawnmower> getLawnMowers(){
        return lawnmowers;
    }

    /**
     * Update the sunflowers
     * @param sf ArrayList<Sunflower>
     */
    public void updateSunflower(ArrayList<Sunflower> sf){
        sunflowers = sf;
    }

    /**
     * Update the peashooters
     * @param ps ArrayList<PeaShooter>
     */
    public void updatePeaShooters(ArrayList<PeaShooter> ps){
        peaShooters = ps;
    }

    /**
     * Update the peas
     * @param np ArrayList<NormalPea>
     */
    public void updatePeas(ArrayList<NormalPea> np){
        peas = np;
    }

    /**
     * Update the zombies
     * @param zb the ArrayList<Zombie>
     */
    public void updateZombies(ArrayList<Zombie> zb){
        zombies = zb;
    }

    /**
     * Update the lawnmowers
     * @param lm the ArrayList<Lawnmower>
     */
    public void updateLawnMowers(ArrayList<Lawnmower> lm){
        lawnmowers = lm;
    }

    /**
     * Increment turns
     */
    public void incrementTime(){
        timer++;
    }

    /**
     * Reduce the cooldown for buying items
     */
    public void reduceCoolDowns(){
        if (sunFlowerCooldown > 0)
            sunFlowerCooldown--;
        if (peaShooterCooldown > 0)
            peaShooterCooldown--;
    }

    /**
     * Get the current game time
     * @param int the current time
     */
    public int getTime(){
        return timer;
    }

    /**
     * Buy an item
     * @param splitInput A string containing information on what to buy
     */
    public void buyItem(String[] splitInput){
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
                            && sunPoints >= PeaShooter.getCost()) ?  new PeaShooter(xPos, yPos, 2, timer): null;
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
                            addSunflower((Sunflower)item);
                            break;
                        case "ps":
                            addPeaShooter((PeaShooter)item);
                            break;
                        default:
                            break;
                    }
                }
            }
        }catch (NumberFormatException nfe){
            System.out.println("illegal input arguments");
        }
    }
}
