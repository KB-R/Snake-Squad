package Models;
/**
 * 
 * @author Kurt Burton-Rowe
 * @version 1.0
 * @date October 25, 2018
 *
 *
 */
public interface Level {
	
	public void createBoard();
	
	public void place(NPC o, int x, int y);
}