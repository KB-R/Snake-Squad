package Models;

public class Zombie extends npc{
    int health;
    int speed;

    int x;
    int y; 
    int w;
    int h;

    public Zombie(int x, int y, int w, int h){

    }

    public void move(){
        this.x--;
    }
	