package gameModel;

import java.util.ArrayList;
import java.util.Random;
import Characters.*;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Stack;

/**
 * @author Maxime Ndutiye
 * Game Objects controller keeps track of all the game objects
 */
public class GameObjectsController implements Cloneable, Serializable{
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
    private int currentWave =1;
    private int waitTimer=0;
    private boolean firstWave = true;
    private boolean userLevel = false;
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
     * Set the amount of zombie will be in the game.
     * @param userSelection The amount of zombies that the user wants. It has to be at least 10.
     */
    public void setZombieTot(int userSelection){
        if (userSelection>10 && userSelection<50){
            this.zombieTot = userSelection;
        }
    }

    /**
     * Set the amount of waves of zombies will be in the game.
     * @param userSelection The amount of waves of zombies that the user wants. It has to be at least 10.
     */
    public void setUserWaves(int userSelection){
        if (userSelection>1 && userSelection<6){
            this.userWaves = userSelection;
        }
    }


    /**
     * Set the amount of waves of zombies will be in the game.
     * @param userSelection The amount of waves of zombies that the user wants. It has to be at least 10.
     */
    public void setUserLevel(boolean setUserLevel){
        this.userLevel = setUserLevel;
    }

    /**
     * Checks if the wave has ended.
     * @return All zombies in the current wave are killed.
     */
    public boolean checkEndWave() {
    	// zombies dead
        if(spawned != 0){
            for(Zombie z: getZombies()) {
            	if(z.isAlive()) {
            		return false;
            	}
            }    
        }
        firstWave = false;
        waited();
        if(waited()){
            this.currentWave++; 
        }
        return true;
    }

    /**
     * Checks if the game waited to spawn the next wave of zombies
     * @return Waited 10 turns
     */
    public boolean waited(){
        waitTimer++;
        if (waitTimer==20){
            waitTimer =0;
            return true;
        }
        return false;
    }

    /**
     * Spawn zombies
     */
    public void spawnZombies(){
        int zombieType = random.nextInt(3);
        if(getTime()%3==0 && spawned<zombieTot/userWaves  && currentWave<=userWaves && ( firstWave || (!(checkEndWave()) && waited()) )) {
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
    public int getCurrentWave(){
        return currentWave;
    }
    public int getUserWave(){
        return userWaves;
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

    public void spawnLawnMowers(){
        // add lawn mowers
        for(int i=0;i<6;i++){
            Lawnmower lm = new Lawnmower(0,i, getTime());
            addLawnMowers(lm);
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
    public void updateSunflowers(ArrayList<Sunflower> sf){
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
     * Update the walnuts
     * @param zb the ArrayList<Zombie>
     */
    public void updateWalnuts(ArrayList<Walnut> zb){
        walnuts = zb;
    }

    /**
     * Update the doublePeaShooters
     * @param zb the ArrayList<Zombie>
     */
    public void updateDoublePeashooters(ArrayList<DoublePeaShooter> zb){
        doublePeaShooters = zb;
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
        timer++;
    }

    /**
     * Reduce the cooldown for buying items
     */
    public void updateCoolDowns(){
        if (sunFlowerCooldown > 0)
            sunFlowerCooldown -= 1;
        if (peaShooterCooldown > 0)
            peaShooterCooldown -= 1;
        if (walnutCoolDown > 0)
            walnutCoolDown -= 1;
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
        
        // don't shoot peas if undoing a turn
        if (undo)
            return;
        //only shoot if there is a zombie in your lane
        for(PeaShooter ps: getPeaShooters()) {
            for(Zombie z: getZombies()){    
                if(ps.getY()==z.getY() && ps.getShootingRate()%3==0) {
                    addPeas(ps.shoot(getTime()));
                }
            }
        }

        for(PeaShooter ps: getDoublePeaShooters()) {
            for(Zombie z: getZombies()){
                if(ps.getY()==z.getY() && ps.getShootingRate()%2==0) {
                    addPeas(ps.shoot(getTime()));
                }
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
                            sunFlowerCooldown += coolDown;
                            sunPoints -= Sunflower.getCost();
                            break;
                        case "ps":
                            addPeaShooter((PeaShooter)item);
                            peaShooterCooldown += coolDown;
                            sunPoints -= PeaShooter.getCost();
                            break;
                        case "dps":
                            addDoublePeaShooter((DoublePeaShooter)item);
                            peaShooterCooldown += coolDown;
                            sunPoints -= DoublePeaShooter.getCost();
                            break;
                        case "wn":
                            addWalnut((Walnut)item);
                            walnutCoolDown += coolDown;
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

    public Object clone(){
        try{
            // new temporary goc
            GameObjectsController T = (GameObjectsController) super.clone();

            ArrayList<Zombie> zba = new ArrayList<>();
            ArrayList<Sunflower> sfa = new ArrayList<>();
            ArrayList<PeaShooter> psa = new ArrayList<>();
            ArrayList<NormalPea> pa = new ArrayList<>();
            ArrayList<Lawnmower> lma = new ArrayList<>();
            ArrayList<DoublePeaShooter> dpa = new ArrayList<>();
            ArrayList<Walnut> wna = new ArrayList<>();

            for (Zombie zb: getZombies()){
                zba.add((Zombie)zb.clone());
            }

            for (Sunflower zb: getSunflowers()){
                sfa.add((Sunflower)zb.clone());
            }

            for (PeaShooter zb: getPeaShooters()){
                psa.add((PeaShooter)zb.clone());
            }
        
            for (NormalPea zb: getPeas()){
                pa.add((NormalPea)zb.clone());
            }

            for (Lawnmower zb: getLawnMowers()){
                lma.add((Lawnmower)zb.clone());
            }

            for (DoublePeaShooter zb: getDoublePeaShooters()){
                dpa.add((DoublePeaShooter)zb.clone());
            }
        
            for (Walnut zb: getWalnuts()){
                wna.add((Walnut)zb.clone());
            }
    
            // create deep copies of game objects
            T.updateZombies(new ArrayList<Zombie>(zba));
            T.updateSunflowers(new ArrayList<Sunflower>(sfa));
            T.updatePeaShooters(new ArrayList<PeaShooter>(psa));
            T.updatePeas(new ArrayList<NormalPea>(pa));
            T.updateLawnMowers(new ArrayList<Lawnmower>(lma));
            T.updateDoublePeashooters(new ArrayList<DoublePeaShooter>(dpa));
            T.updateWalnuts(new ArrayList<Walnut>(wna));

            return T;
        }catch(Exception e){
            System.out.println(e);
        }
        return null;
    }
}
