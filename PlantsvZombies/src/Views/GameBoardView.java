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
    int width = 500;
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

    JPanel jp = new JPanel();
    private ArrayList<JLabel>[][] gameBoardGridLabels = new ArrayList[6][10];
    private GameObjectsController goc;

    public void initializeGameBoard(){
        for(int i=0;i<6;i++){
            for(int j=0; j<10; j++){
                gameBoardGridLabels[i][j] = new ArrayList<JLabel>();
            }
        }
    }

    public GameBoardView(GameObjectsController goc){
        this.goc = goc;
        
        // initialize this panel
        gf = new GameFrame("PvZ", 1000, 600);
        getImages();
        initializeGameBoard();
        
        for(int i=0;i<6;i++){
            for(int j=0; j<10; j++){
                gameBoardGridLabels[i][j].add(new JLabel());
                jp.add(gameBoardGridLabels[i][j].get(0));
            }
        }
        updateGUI(); 
        jp.setLayout(new GridLayout(6,10));
        gf.add(jp);
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
        for(int i=0;i<6;i++){
            for(int j=0; j<10; j++){
                gameBoardGridLabels[i][j].get(0).setIcon(gr);
            }
        }
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
            if (pos[0] < 10){
                Icon ico = gr;
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
                gameBoardGridLabels[pos[1]][pos[0]].get(0).setIcon(ico);
            }
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
    
}