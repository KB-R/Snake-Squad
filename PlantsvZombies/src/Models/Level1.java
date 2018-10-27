package Models;
/**
 * @author Kurt Burton-Rowe
 * @version 1.0
 * @date October 26, 2018
 */
public class Level1 implements Level{
	private NPC[] board;
	private static int y=0;
	
	@Override
	public void createBoard() {
		NPC[] b = new NPC[10];
		this.board = b;
	}

	@Override
	public void place(NPC o, int x, int y) {
		this.board[x]=o;
	}

	
}
