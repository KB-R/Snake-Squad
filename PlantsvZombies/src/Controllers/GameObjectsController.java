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

    public GameObjectsController(){
        zombies = new ArrayList<Zombie>();
        sunflowers = new ArrayList<Sunflower>();
        peaShooters = new ArrayList<PeaShooter>();
        peas = new ArrayList<NormalPea>();
        lawnmowers = new ArrayList<Lawnmower>();
    }

    /**
     * Spawn some zombies given a level
     * @param number the number of zombies to spawn
     * @param level the current level of the game
     */
    public void spawnZombies(int number, int level){
        for(int i=0; i<number; i++){
            Zombie zb = new NormalZombie(level);
            zombies.add(zb);
        }
    }

    public void collectGarbage(){
        removeItems(zombies);
        removeItems(sunflowers);
        removeItems(peaShooters);
        removeItems(peas);
        removeItems(lawnmowers);
    }

    private void removeItems(ArrayList arr){
        for(Object ob: arr){
            NPC np= (NPC)ob;
            if(np.getCurrentHealth() == 0){
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
     * Get all PeaShooters
     * @return ArrayList<PeaShooter> PeaShooters
     */
    public ArrayList<PeaShooter> getPeaShooters(){
        return peaShooters;
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
}