package Models;

import java.util.*;
/**
 * 
 * @author Kurt Burton-Rowe
 * @author Maxime Ndutiye
 * @version 1.0
 * @date October 25, 2018
 * 
 * The base model for all zombies that will appear in the game
 */

public class Zombie extends NPC implements Movable{

	protected int maxHealth;
    protected int speed;
    protected int x;
    protected int y;
    protected int[] arry;
    
    
    /*Zombie constructor to set where it spawns on the board and how many tiles it takes up*/
    public Zombie(int health, int speed) {
    	super(health, false);
    	this.maxHealth = health;
    	this.speed = speed;
    	this.x = 8;
    	this.y = (int)Math.random()*6;
    	setLocation(this.x,this.y);
    }
    
   
    /*This changes x coordinate of the zombie object*/
    public void move(){
        this.x--;
        setLocation(this.x, this.y);
    }
    
	@Override
	public int getVelocity() {
		// TODO Auto-generated method stub
		return speed;
	}

	@Override
	public void setVelocity(int newV) {
		// TODO Auto-generated method stub
		this.speed = newV;
	}
}