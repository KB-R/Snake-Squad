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
import Controller.GameController;

/**
 * @author Maxime Ndutiye
 * @author Kurt Burton
 * The view of the plants vs zombies game
 */
public class GameBoardView extends JPanel{
    GameFrame gf;
    int width = 810;
    int height = 500;
    Color darkGreen = new Color(9, 122, 28);
    Color lightGreen = new Color(42,153,61);
    Color night_lightGreen = new Color(27,68,34);
    Color night_darkGreen = new Color(4, 51, 12);
    GameController gc;
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
    private static final String[] levels = {"Day", "Night"};
    private boolean dayLevel=true;
    // Buttons 
    JButton addSunflower;
    JButton addPeaShooter;
    JButton addWalnut;
    JButton addDoublePeaShooter;
    JButton sunPoints;
    JButton sfCoolDown;
    JButton psCoolDown;
    JButton currentTime;
    JButton saveGame;
    JButton loadGame;

    JButton next;
    JButton undo;

    private boolean undoT;
    private boolean redoT;


    // Whether to add a sunflower or peashooter
    private String addItem = "";
    private boolean updatedGUI = false;

    String filepath = "";
    public GameBoardView(GameObjectsController goc, MoveController mc){
        JLabel label;
        this.goc = goc;
        this.mc = mc; 

        // new game frame
        gf = new GameFrame("PvZ", width+50, height+80);
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(width, height));
        setLevel();
        // create the game board 
        for (int i=0; i<6; i++){
            for (int j=0; j<10; j++){
                if (j==9){
                    label = createColoredLabel(Color.GRAY); //Zombie spawning area
                }else if(!this.dayLevel){
                    label = ((i+j)%2 == 0) ? createColoredLabel(night_lightGreen): 
                    createColoredLabel(night_darkGreen); //night level 
                }else{
                    label = ((i+j)%2 == 0) ? createColoredLabel(lightGreen): 
                    createColoredLabel(darkGreen); //day level 
                }
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
        saveGame = new JButton("Save");
        loadGame = new JButton("Load");

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
    
        menubar.add(saveGame);
        menubar.add(loadGame);
        menubar.add(sunPoints);
        menubar.add(sfCoolDown);
        menubar.add(psCoolDown);
        menubar.add(currentTime);

        saveGame.addActionListener(new saveAction());
        loadGame.addActionListener(new loadAction());
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
            System.out.println("ioe" + ioe);
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
    public void updateGameBoard(GameObjectsController newGoc){
        setGOC(newGoc); // use updated GOC
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
     * Return sfCoolDown button
     * @return
     */
    public JButton getsfCoolDown(){
        return sfCoolDown;
    }

    /**
     * Return psCoolDown button
     * @return JButton
     */
    public JButton getpsCoolDown(){
        return psCoolDown;
    }

    /**
     * Return sunPoints button
     * @return JButton
     */
    public JButton getsunPoints(){
        return sunPoints;
    }

    /**
     * Return time button
     * @return JButton 
     */
    public JButton getcurrentTime(){
        return currentTime;
    }

    /**
     * Return next button
     * @return JButton
     */
    public JButton getNextButton(){
        return next;
    }

    /**
     * Take a reference to undo 
     * @param undoT
     */
    public void setUndo(boolean undoT){
        this.undoT = undoT;
    }

    /**
     * Take a reference to redo
     * @param redoT
     */
    public void setRedo(boolean redoT){
        this.redoT = redoT;
    }

    /**
     * Update the GOC
     * @param goc
     */
    public void setGOC(GameObjectsController goc){
        this.goc = goc;
    }

    public void setGC(GameController gc){
        this.gc = gc;
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
        if (color == lightGreen || color == darkGreen){
            label.setBorder(BorderFactory.createLineBorder(lightGreen));
        }else if (color == night_darkGreen || color == night_lightGreen){
            label.setBorder(BorderFactory.createLineBorder(night_lightGreen));
        }else{
            label.setBorder(BorderFactory.createLineBorder(Color.black));
        }
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
     * Actionlistener to handle saving game state
     */
	class saveAction implements ActionListener {
		public void actionPerformed(ActionEvent e) { 
            JFileChooser c = new JFileChooser();
            int rVal = c.showSaveDialog(GameBoardView.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
              filepath = c.getCurrentDirectory().toString() + "/" +  c.getSelectedFile().getName();
              System.out.println("saving " + filepath);
              gc.saveGame(filepath);
            }
		}
    }

    /**
     * Actionlistener to handle loading game state
     */
	class loadAction implements ActionListener {
		public void actionPerformed(ActionEvent e) { 
            JFileChooser c = new JFileChooser();
            int rVal = c.showOpenDialog(GameBoardView.this);
            if (rVal == JFileChooser.APPROVE_OPTION) {
              filepath = c.getCurrentDirectory().toString() + "/" +  c.getSelectedFile().getName();
              System.out.println("opening " + filepath);
              gc.loadGame(filepath);
            }
		}
    }

    /**
     * Actionlistener to handle events for going to the next turn in the game
     */
    class nextAction implements ActionListener {
		public void actionPerformed(ActionEvent e) { 
            gc.unsetUndo();
            setDone();
		} 
    }

    /**
     * Actionlistener to handle events for going to the next turn in the game
     */
    class undoAction implements ActionListener {
		public void actionPerformed(ActionEvent e) { 
            gc.setUndo();
            setDone();
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
     * Force the GameBoardView to update the gui
     */
    public void forceUpdate(){
        updatedGUI = true;
    }

    /**
     * Tell the gameboard that it is done drawing the frame
     */
    private void setDone(){
        updatedGUI = true;
    }

    /**
     * Get the users requested amount of zombies. Must be between 10 and 50.
     * @return Game's amount  of zombies.
     */
    public int gameZombies(){
        boolean validNum =false;
        int zombies=0;
        while(!validNum){
            try{
                zombies =Integer.parseInt( JOptionPane.showInputDialog("How many Zombies would you like this game?\n" 
                + "Valid inputs are between 10 and 50."));
            
                if (zombies<10 || zombies>50){
                    JOptionPane.showMessageDialog(null,"Please select a number within the parameters.","Invalid Input",JOptionPane.WARNING_MESSAGE);
                }else{
                    validNum =true;
                }
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Your number format is incorrect.","Number format",JOptionPane.ERROR_MESSAGE);
            }
        }
        return zombies;
    }

    /**
     * Get the users requested amount of zombies. Must be between 10 and 50.
     * @return Game's amount  of zombies.
     */
    public int gameWaves(){
        boolean validNum =false;
        int waves=0;
        while(!validNum){
            try{
                waves =Integer.parseInt( JOptionPane.showInputDialog("How many waves of zombies would you like this game?\n"
                 + "Valid inputs are between 1 and 5."));
            
                if (waves<1 || waves>5){
                    JOptionPane.showMessageDialog(null,"Please select a number within the parameters.","Invalid Input",JOptionPane.WARNING_MESSAGE);
                }else{
                    validNum =true;
                }
            } catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Your number format is incorrect.","Number format",JOptionPane.ERROR_MESSAGE);
            }
        }
        return waves;
    }

    /**
     * Set the users requested level. Day or Night.
     * @return Game's amount  of zombies.
     */
    public void setLevel(){
        String levelChoice;
        try{
            levelChoice = (String) JOptionPane.showInputDialog(null, "Do you want a day level or night level?", "Level Selection"
            ,JOptionPane.QUESTION_MESSAGE, null, levels, levels[0]);
        
            if (levelChoice == levels[0]){
                this.dayLevel = true;
            } else if (levelChoice == levels[1]){
                this.dayLevel = false; 
            } 
        } catch(Exception e){
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null,"Your input format is incorrect.","Input format",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /** 
     * Get the user's level selection.
     * @return User level selection.True if its day.
    */
    public boolean getLevel(){
        return this.dayLevel;
    }


    /**
     * Tells the player that the game is done.
     */
    public void gameOver(){
        JOptionPane.showMessageDialog(null, "YOU GOT YOUR BRAINS EATEN!\n"+"Thanks for playing our game. We hope you enjoyed!");
    }
    /**
     * Tells the player that the game is done.
     */
    public void gameWon(){
        JOptionPane.showMessageDialog(null, "YOU WON!\n"+"Thanks for playing our game. We hope you enjoyed!");
    }

}