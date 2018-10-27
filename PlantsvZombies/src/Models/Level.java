package Models;
/**
 * @author Kurt Burton-Rowe
 * @version 1.0
 * @date October 25, 2018
 */

public interface Level {
	/*Creates the logic of the board*/
	public void createBoard();
	/**
	 * Where to put the NPC on the board
	 * @param o The NPC object
	 * @param x Coordinate
	 * @param y Coordinate
	 */
	public void place(NPC o, int x, int y);
	
	/**
	 * All levels are the same length(x)
	 * @return The height of the level (y)
	 */
	public int height();
}
