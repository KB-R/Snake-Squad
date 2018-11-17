package Views;

import Controllers.*;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import Models.NPC;

public class GameBoardView extends JPanel{
    GameFrame gf;
    int width = 800;
    int height = 500;

    String[] imageUrls = new String[] {"../src/Images/ZOMBIE.png",
                                       "../src/Images/GRASS.png",
                                       "../src/Images/PEASHOOTER.png",
                                       "../src/Images/SUNFLOWER.jpg",
                                       "../src/Images/Lawnmower.png",
                                       "../src/Images/BULLET.png"};
    ImageIcon zb;
    ImageIcon gr;
    ImageIcon ps;
    ImageIcon sf;
    ImageIcon lm;
    ImageIcon pe;

    ArrayList<JLabel> gameItems = new ArrayList<JLabel>();

    JPanel jp = new JPanel();
    private GameObjectsController goc;
    private JLayeredPane layeredPane;

    JButton addSunflower;
    JButton addPeaShooter;

    JButton sunPoints;
    JButton sfCoolDown;
    JButton psCoolDown;
    JButton next;

    private boolean addSF = false;
    private boolean addPS = false;

    public void initializeGameBoard(){
       
    }

    private boolean updatedGUI = false;

    public GameBoardView(GameObjectsController goc){
        this.goc = goc;
        gf = new GameFrame("PvZ", width+50, height+80);
        layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(width, height));

        for (int i=0; i<6; i++){
            for (int j=0; j<10; j++){
                JLabel label = (j==9)? createColoredLabel(Color.GRAY) : createColoredLabel(Color.green);
                label.addMouseListener(new boardPress());
                label.setBounds(j*80, i*80, 80, 80);
                layeredPane.add(label, JLayeredPane.DEFAULT_LAYER);
            }
        }

        JMenuBar menubar = new JMenuBar();
		
		addSunflower = new JButton("add Sunflower");
        addPeaShooter = new JButton("add Peashooter");
        addSunflower.setOpaque(true);
        addPeaShooter.setOpaque(true);
        addSunflower.setBackground(Color.BLUE);
        addPeaShooter.setBackground(Color.BLUE);
        
        sunPoints = new JButton("0");
        sfCoolDown = new JButton("0");
        psCoolDown = new JButton("0");
        
        menubar.add(sunPoints);
        menubar.add(sfCoolDown);
        menubar.add(psCoolDown);

        addSunflower.addActionListener(new menupress());
        addPeaShooter.addActionListener(new menupress());
    
        next = new JButton("Next");
        next.addActionListener(new nextAction());
        menubar.add(next);
        menubar.add(addSunflower);
		menubar.add(addPeaShooter);

        getImages();
        add(layeredPane);
        gf.add(layeredPane);
        gf.setJMenuBar(menubar);
        gf.setVisible(true);
    }

    private void getImages(){
        try{
            for (int i=0; i<6; i++){
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
                }
            }   
        }catch(IOException ioe){
            System.out.println("ioe");
        }
    }

    public void updateGUI(){
        //  clear board
        for(JLabel lb : gameItems){
            layeredPane.remove(lb);
        }
        layeredPane.validate();
        layeredPane.repaint();

        sfCoolDown.setText(Long.toString(goc.getSFCoolDown()));
        psCoolDown.setText(Long.toString(goc.getPSCoolDown()));
        sunPoints.setText(Long.toString(goc.getSP()));
    }

    public void updateGameBoard(){
        updateGUI();
        setIconAtLocation(goc.getLawnMowers(), "lm");
        setIconAtLocation(goc.getSunflowers(), "sf");
        setIconAtLocation(goc.getPeaShooters(), "ps");
        setIconAtLocation(goc.getPeas(),"pe");
        setIconAtLocation(goc.getZombies(),"zb");
    }

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
                        ico = zb;
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

    private Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
    
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
    
        return resizedImg;
    }

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

    private class boardPress implements MouseListener{
        public void mouseReleased(MouseEvent e){
            JLabel lb = (JLabel)e.getSource();
            int x = lb.getX()/80;
            int y = lb.getY()/80;

            System.out.println("Clicked me " + lb.getX() + " " + lb.getY());

            if (addSF == true){
                String[] input = new String("buy sf " + x + " " + y).split("\\s");
                goc.buyItem(input);
            }else if(addPS == true){
                String[] input = new String("buy ps " + x + " " + y).split("\\s");
                goc.buyItem(input);
            }
        }

        public void mouseClicked(MouseEvent e){}
        public void mousePressed(MouseEvent e){}
        public void mouseEntered(MouseEvent e){}
        public void mouseExited(MouseEvent e){}
    }

	class menupress implements ActionListener {
		public void actionPerformed(ActionEvent e) { 
			if(e.getSource() == addSunflower){   
                addSF = (addSF) ? false:true;
                if(addSF){
                    addPS = false;
                }
			}else if(e.getSource() == addPeaShooter){
                addPS = (addPS) ? false:true;
                if(addPS){
                    addSF = false;
                }
			}
		} 
    }

    class nextAction implements ActionListener {
		public void actionPerformed(ActionEvent e) { 
            setDone();
		} 
    }

    public boolean isUpdated(){
        return updatedGUI;
    }

    public void setNewTurn(){
        updatedGUI = false;
    }

    private void setDone(){
        updatedGUI = true;
    }

}