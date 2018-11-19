package Views;

import java.awt.*;
import javax.swing.*;

/**
 * GameFrame is the frame that will hold the game board view
 * @author Maxime Ndutiye
 */
public class GameFrame extends JFrame{
	JButton addSunflower;
	JButton addPeaShooter;
	
	GameFrame(String title, int w, int h){
		super(title);
		setSize(w,h);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setLayout( new FlowLayout() );
	}
}	
