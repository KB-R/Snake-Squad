package Controllers;

import java.util.ArrayList;
import java.util.Random;
import Models.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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
    private ArrayList<DoublePeaShooter> doublePeaShooters;
    private ArrayList<Walnut> walnuts;

    private int sunPoints = 0;

    private long sunFlowerCooldown = 0;
    private long peaShooterCooldown = 0;
    private long walnutCoolDown = 0;
    private long coolDown = 3; // cooldown cost for new plants
    private int timer = 0; 
    private boolean undo = false; // currently undergoing an undo action

    ArrayList<NPC>[][] gameBoard = new ArrayList[6][10];

    // zombie spawns
    private int zombieTot = 10;
    private int spawned = 0;
    private int userWaves = 1;
    private Random random = new Random();

    public GameObjectsController(){
        // create NPC arraylists
        zombies = new ArrayList<Zombie>();
        sunflowers = new ArrayList<Sunflower>();
        peaShooters = new ArrayList<PeaShooter>();
        peas = new ArrayList<NormalPea>();
        lawnmowers = new ArrayList<Lawnmower>();
        doublePeaShooters = new ArrayList<DoublePeaShooter>(); 
        walnuts = new ArrayList<Walnut>();
    }

    /**
     * Spawn a zombies
     */
    public void spawnZombies(){
        int zombieType = random.nextInt(3);
        if(getTime()%3==0 && spawned<zombieTot/userWaves) {
            Zombie zb = null;
            switch(zombieType){
                case 0:
                    zb = new NormalZombie(getTime());
                    break;
                case 1:
                    zb = new PylonZombie(getTime());
                    break;
                case 2:
                    zb = new RunZombie(getTime());
                    break;
            }
            
            if (zb != null){
                zombies.add(zb);
                spawned++;
            }
        }
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
        removeItems(doublePeaShooters);
        removeItems(walnuts);
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
                
                // Reward back costs and cooldowns
                Class objClass = np.getClass();
                boolean isPlant = np instanceof Sunflower || 
                                np instanceof PeaShooter || np instanceof Walnut;
                
                // update spawn counter accordingly
                if (np instanceof Zombie){
                    spawned--;
                }

                // if a plant
                if (isPlant){
                    try{
                        Method method = objClass.getMethod("getCost");
                        sunPoints += (Integer) method.invoke(np);
                    }catch(SecurityException e){
                        System.out.println(e);
                    }catch (NoSuchMethodException e) {
                        System.out.println(e);
                    }catch(IllegalAccessException e){
                        System.out.println(e);
                    }catch(InvocationTargetException e){                       
                        System.out.println(e);
                    }
                }
            
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
     * Add DoublePeaShooters
     * @param dps DoublePeaShooter to add
     */
    public void addDoublePeaShooter(DoublePeaShooter dps){
        doublePeaShooters.add(dps);
    }

    /**
     * Add Walnut
     * @param wn Walnut to add
     */
    public void addWalnut(Walnut wn){
        walnuts.add(wn);
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
     * Get all the mowers
     * @return ArrayList<LawnMower> lawnmowers
     */
    public ArrayList<DoublePeaShooter> getDoublePeaShooters(){
        return doublePeaShooters;
    }

    /**
     * Get all the walnuts
     * @return ArrayList<LawnMower> lawnmowers
     */
    public ArrayList<Walnut> getWalnuts(){
        return walnuts;
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
    public void updateTime(){
        if (undo){
            timer--;
        }else{
            timer++;
        }
    }

    /**
     * Reduce the cooldown for buying items
     */
    public void updateCoolDowns(){
        int ammount = undo? -1:1;
        if (sunFlowerCooldown > 0)
            sunFlowerCooldown += ammount;
        if (peaShooterCooldown > 0)
            peaShooterCooldown += ammount;
    }

    /**
     * Get the current game time
     * @param int the current time
     */
    public int getTime(){
        return timer;
    }
    
    /**
     * Set undo action
     */
    public void setUndo(){
        undo = true;
    }

    /**
     * Unset undo action
     */
    public void unsetUndo(){
        undo = false;
    }

    /**
     * Shoot peas from the PeaShooters
     */
    public void shootPeas(){
        
        // don't shoot pead if undoing a turn
        if (undo)
            return;
        
        for(PeaShooter ps: getPeaShooters()) {
            if(ps.getShootingRate()%2==0) {
                addPeas(ps.shoot(getTime()));
            }
        }

        for(PeaShooter ps: getDoublePeaShooters()) {
            if(ps.getShootingRate()%2==0) {
                addPeas(ps.shoot(getTime()));
            }
        }
    }
    /**
     * Buy an item
     * @param splitInput A string containing information on what to buy
     */
    public void buyItem(String[] splitInput){
        try{
            System.out.println("one " + splitInput[1]);
            Integer xPos = Integer.parseInt(splitInput[2]);
            Integer yPos = Integer.parseInt(splitInput[3]);
            NPC item = null;

            switch (splitInput[1]){
                case "sf":
                    item = (sunFlowerCooldown <= timer 
                        && sunPoints >= Sunflower.getCost()) ? new Sunflower(xPos, yPos, timer): null;
                    break;
                case "ps":
                    item = (peaShooterCooldown <= timer 
                            && sunPoints >= PeaShooter.getCost()) ?  new PeaShooter(xPos, yPos, 2, timer): null;
                    break;
                case "dps":
                    item = (peaShooterCooldown <= timer 
                            && sunPoints >= DoublePeaShooter.getCost()) ?  new DoublePeaShooter(xPos, yPos, 4, timer): null;
                    break;
                case "wn":
                    item = (walnutCoolDown <= timer 
                            && sunPoints >= Walnut.getCost()) ?  new Walnut(xPos, yPos, timer): null;
                    break;
                default:
                    break;
            }

            if (item != null && xPos > 0 && xPos < 9 && yPos < 6 && yPos >= 0){
                if (gameBoard[yPos][xPos].isEmpty()){
                    switch (splitInput[1]){
                        case "sf":
                            addSunflower((Sunflower)item);
                            sunFlowerCooldown = timer + coolDown;
                            sunPoints -= Sunflower.getCost();
                            break;
                        case "ps":
                            addPeaShooter((PeaShooter)item);
                            peaShooterCooldown = timer + coolDown;
                            sunPoints -= PeaShooter.getCost();
                            break;
                        case "dps":
                            addDoublePeaShooter((DoublePeaShooter)item);
                            peaShooterCooldown = timer + coolDown;
                            sunPoints -= DoublePeaShooter.getCost();
                            break;
                        case "wn":
                            addWalnut((Walnut)item);
                            walnutCoolDown = timer + coolDown;
                            sunPoints -= Walnut.getCost();
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
