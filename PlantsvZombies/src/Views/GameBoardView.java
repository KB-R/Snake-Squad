package Views;

import gameModel.*;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import Characters.*;

/**
 * @author Maxime Ndutiye
 * The view of the plants vs zombies game
 */
public class GameBoardView extends JPanel{
    GameFrame gf;
    int width = 800;
    int height = 500;

    // Images and Icons
    String[] imageUrls = new String[] {"../bin/Images/ZOMBIE.png",
                                       "../bin/Images/GRASS.png",
                                       "../bin/Images/PEASHOOTER.png",
                                       "../bin/Images/SUNFLOWER.png",
                                       "../bin/Images/Lawnmower.png",
                                       "../bin/Images/BULLET.png",
                                       "../bin/Images/RunnerZombie.png",
                                       "../bin/Images/BucketZombie.png",
                                       "../bin/Images/Walnut.png",
                                       "../bin/Images/DoublePeaShooter.png"};
    ImageIcon zb,gr,ps,sf,lm,pe,rzb,pzb,dps,wn;

    ArrayList<JLabel> gameItems = new ArrayList<JLabel>();
    JMenuBar menubar = new JMenuBar();
    JMenu addItemsMenu = new JMenu("Add");

    private GameObjectsController goc;
    private MoveController mc;
    private JLayeredPane layeredPane;

    // Buttons 
    JButton addSunflower;
    JButton addPeaShooter;
    JButton addWalnut;
    JButton addDoublePeaShooter;
    JButton sunPoints;
    JButton sfCoolDown;
    JButton psCoolDown;
    JButton currentTime;

    JButton next;
    JButton undo;

    // Whether to add a sunflower or peashooter
    private String addItem = "";
    private boolean updatedGUI = false;

    public GameBoardView(GameObjectsController goc, MoveController mc){
        this.goc = goc;
        this.mc = mc; 

        // new game frame
        gf = new GameFrame("PvZ", width+50, height+80);
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(width, height));

        // create the game board 
        for (int i=0; i<6; i++){
            for (int j=0; j<10; j++){
                JLabel label = (j==9)? createColoredLabel(Color.GRAY) : createColoredLabel(Color.green);
                label.addMouseListener(new boardPress());
                label.setBounds(j*80, i*80, 80, 80);
                layeredPane.add(label, JLayeredPane.DEFAULT_LAYER);
            }
        }
        
        // add components
		addSunflower = new JButton("Sunflower");
        addPeaShooter = new JButton("Peashooter");
        addWalnut = new JButton("Walnut");
        addDoublePeaShooter = new JButton("Double PeaShooter");

        sunPoints = new JButton("sunpoints: 0");
        sfCoolDown = new JButton("sf cooldown: 0");
        psCoolDown = new JButton("ps cooldown: 0");
        currentTime = new JButton("time: 0");
        next = new JButton("Next");
        undo = new JButton("Undo");
    
        addItemsMenu.add(addSunflower);
        addItemsMenu.add(addPeaShooter);
        addItemsMenu.add(addDoublePeaShooter);
        addItemsMenu.add(addWalnut);
    
        menubar.add(sunPoints);
        menubar.add(sfCoolDown);
        menubar.add(psCoolDown);
        menubar.add(currentTime);

        addSunflower.addActionListener(new menupress());
        addPeaShooter.addActionListener(new menupress());
        addDoublePeaShooter.addActionListener(new menupress());
        addWalnut.addActionListener(new menupress());
        next.addActionListener(new nextAction());
        undo.addActionListener(new undoAction());

        
        menubar.add(next);
        menubar.add(undo);
        menubar.add(addItemsMenu);

        getImages();
        gf.add(layeredPane);
        gf.setJMenuBar(menubar);
        gf.setVisible(true);
    }

    /** 
     * Get all necessary images and create icons from them 
     */
    private void getImages(){
        try{
            for (int i=0; i<10; i++){
                Image img = ImageIO.read(new File(imageUrls[i]));
                img = getScaledImage(img, 50, 80);

                switch(i){
                    case 0:
                        zb = new ImageIcon(img);
                        break;
                    case 1:
                        gr = new ImageIcon(img);
                        break;
                    case 2:
                        ps = new ImageIcon(img);
                        break;
                    case 3:
                        sf = new ImageIcon(img);
                        break;
                    case 4:
                        lm = new ImageIcon(img);
                        break;
                    case 5:
                        pe = new ImageIcon(img);
                        break;
                    case 6:
                        rzb = new ImageIcon(img);
                        break;
                    case 7:
                        pzb = new ImageIcon(img);
                    case 8:
                        wn = new ImageIcon(img);
                    case 9:
                        dps = new ImageIcon(img);
                        break;
                }
            }   
        }catch(IOException ioe){
            System.out.println("ioe");
        }
    }

    /**
     * Clear the board of dead objects and repaint on screen
     */
    public void updateGUI(){
        //  clear board
        for(JLabel lb : gameItems){
            layeredPane.remove(lb);
        }
        layeredPane.validate();
        layeredPane.repaint();
        sfCoolDown.setText("sf cooldown: " + Long.toString(goc.getSFCoolDown()));
        psCoolDown.setText("ps cooldown: " + Long.toString(goc.getPSCoolDown()));
        sunPoints.setText("sunpoints: " + Long.toString(goc.getSP()));
        currentTime.setText("time: " + Integer.toString(goc.getTime()));
    } 

    /**
     * Update the GUI gamebaord
     */
    public void updateGameBoard(){
        updateGUI();
        setIconAtLocation(goc.getLawnMowers(), "lm");
        setIconAtLocation(goc.getSunflowers(), "sf");
        setIconAtLocation(goc.getPeaShooters(), "ps");
        setIconAtLocation(goc.getPeas(),"pe");
        setIconAtLocation(goc.getZombies(),"zb");
        setIconAtLocation(goc.getDoublePeaShooters(), "dps");
        setIconAtLocation(goc.getWalnuts(), "wn");
    }

    /**
     * update the GUI based on the position of items in the GOC
     * @param arr an arr of NPCs
     * @param npcType The type of NPC
     */
    private void setIconAtLocation(ArrayList arr, String npcType){
        for(Object ob: arr){
            NPC np= (NPC)ob;
            int[] pos = np.getLocation();
            Icon ico = null;

            if (pos[0] < 10){
                switch(npcType){
                    case "lm":
                        ico = lm;
                        break;
                    case "sf":
                        ico = sf;
                        break;
                    case "ps":
                        ico = ps;
                        break;
                    case "pe":
                        ico = pe;
                        break;
                    case "zb":
                        if (ob instanceof NormalZombie){
                            ico = zb;
                        }else if(ob instanceof RunZombie){
                            ico = rzb;
                        }else if (ob instanceof PylonZombie){
                            ico = pzb;
                        }
                        break;
                    case "dps":
                        ico = dps;
                        break;
                    case "wn":
                        ico = wn;
                        break;
                    default:
                        break;
                }
            }
            
            JLabel lb = new JLabel(ico);
            lb.setBounds(pos[0]*80,pos[1]*80,80,80);
            gameItems.add(lb);
            layeredPane.add(lb, JLayeredPane.DRAG_LAYER);
        }
    }

    /**
     * Return a scaled image
     * @param srcImg The original image
     * @param w the new width of the image
     * @param h the new height of the image
     * @return
     */
    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
    
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
    
        return resizedImg;
    }

    /**
     * Create a square based on the input color
     * @param color The color of the square
     * @return JLabel with the background color specified
     */
    private JLabel createColoredLabel(Color color) {
        JLabel label = new JLabel();
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(color);
        label.setForeground(Color.black);
        label.setBorder(BorderFactory.createLineBorder(Color.black));
        label.setPreferredSize(new Dimension(140, 140));
        return label;
    }

    /**
     * Mouse listener to handle event of clicking on the GUI board
     */
    private class boardPress implements MouseListener{
        public void mouseReleased(MouseEvent e){
            JLabel lb = (JLabel)e.getSource();
            int x = lb.getX()/80;
            int y = lb.getY()/80;

            String[] input = null;
            switch(addItem){
                case "sf":
                    input = new String("buy sf " + x + " " + y).split("\\s");
                    break;
                case "ps":
                    input = new String("buy ps " + x + " " + y).split("\\s");
                    break;
                case "dps":
                    input = new String("buy dps " + x + " " + y).split("\\s");
                    break;
                case "wn":
                    input = new String("buy wn " + x + " " + y).split("\\s");
                    break;
            }
            if (input != null)
                goc.buyItem(input);
            addItem = "";
            updateButtonStatuses();
        }

        public void mouseClicked(MouseEvent e){}
        public void mousePressed(MouseEvent e){}
        public void mouseEntered(MouseEvent e){}
        public void mouseExited(MouseEvent e){}
    }

    /**
     * Set the buttons color based on whether is it active or not
     * @param btn
     * @param active
     */
    public void updateButtonStatuses(){
        // clear last option
        addSunflower.setForeground(Color.BLACK);
        addPeaShooter.setForeground(Color.BLACK);
        addDoublePeaShooter.setForeground(Color.BLACK);
        addWalnut.setForeground(Color.BLACK);

        // set new option
        switch(addItem){
            case "sf":
                addSunflower.setForeground(Color.RED);
                break;
            case "ps":
                addPeaShooter.setForeground(Color.RED);
                break;
            case "dps":
                addDoublePeaShooter.setForeground(Color.RED);
                break;
            case "wn":
                addWalnut.setForeground(Color.RED);
                break;
        }
    }

    /**
     * Actionlistener to handle clicks on menubar items
     */
	class menupress implements ActionListener {
		public void actionPerformed(ActionEvent e) { 
			if(e.getSource() == addSunflower){   
                addItem = "sf";
			}else if(e.getSource() == addPeaShooter){
                addItem = "ps";
            }else if(e.getSource() == addWalnut){
                addItem = "wn";
            }else if(e.getSource() == addDoublePeaShooter){
                addItem = "dps";
            }
            updateButtonStatuses();
		}
    }

    /**
     * Actionlistener to handle events for going to the next turn in the game
     */
    class nextAction implements ActionListener {
		public void actionPerformed(ActionEvent e) { 
            mc.unsetUndo();
            goc.unsetUndo();
            setDone();
		} 
    }

    /**
     * Actionlistener to handle events for going to the next turn in the game
     */
    class undoAction implements ActionListener {
		public void actionPerformed(ActionEvent e) { 
            if (goc.getTime() > 0){
                mc.setUndo();
                goc.setUndo();
                setDone();
            }
		} 
    }

    /**
     * Returns wheather the gameboard is ready to draw the next frame
     * @return
     */
    public boolean isUpdated(){
        return updatedGUI;
    }

    /**
     * Tells the gameboard that a new turn has started
     */
    public void setNewTurn(){
        updatedGUI = false;
    }

    /**
     * Tell the gameboard that it is done drawing the frame
     */
    private void setDone(){
        updatedGUI = true;
    }
    /**
     * Tells the player that the game is done.
     */
    public void endGame(){
        JOptionPane.showMessageDialog(null, "Thanks for playing our game. We hope you enjoyed!");
    }

}