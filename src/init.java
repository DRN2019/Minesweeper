import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class init extends JFrame implements MouseListener, MouseMotionListener {

    // Define image size constants
    private static final int WINDOW_WIDTH = 700;
    private static final int WINDOW_HEIGHT = 700;
    private static final int LEVEL_WIDTH = 200;
    private static final int LEVEL_HEIGHT = 100;
    private static final int TILE_WIDTH = 25;
    private static final int TILE_HEIGHT = 25;

    // Define image positioning constants
    private static final int EASY_X = 250;
    private static final int EASY_Y = 150;
    private static final int MEDIUM_X = 250;
    private static final int MEDIUM_Y = 275;
    private static final int HARD_X = 250;
    private static final int HARD_Y = 400;

    // Initialize all imageIcons
    ImageIcon tempIcon;
    Image temp;
    Image easy;
    Image med;
    Image hard;

    private ImageIcon easyIcon = new ImageIcon("src/images/Easy.png");
    private ImageIcon medIcon = new ImageIcon("src/images/Medium.png");
    private ImageIcon hardIcon = new ImageIcon("src/images/Hard.png");
    private ImageIcon easyDownIcon = new ImageIcon("src/images/EasyDown.png");
    private ImageIcon medDownIcon = new ImageIcon("src/images/MediumDown.png");
    private ImageIcon hardDownIcon = new ImageIcon("src/images/HardDown.png");
    private ImageIcon tileIcon = new ImageIcon("src/images/tile.png");
    private ImageIcon tileDownIcon = new ImageIcon("src/images/tileDown.png");
    private ImageIcon tileClearIcon = new ImageIcon("src/images/tileClear.png");

    // Initialize local variables
    private boolean overEasy;
    private boolean overMedium;
    private boolean overHard;
    private boolean startMenu;

    JLabel mousePosition;
    @Override public void mouseClicked(MouseEvent e) {
        String click = "";
        if(e.getButton() == MouseEvent.BUTTON1) {
            click = "Left Click";
        }
        if(e.getButton() == MouseEvent.BUTTON2) {
            click = "Middle Click";
        }
        if(e.getButton() == MouseEvent.BUTTON3) {
            click = "Right Click";
        }
        mousePosition.setText("Mouse " + click + " at coordinate : ["+e.getX()+","+e.getY()+"]");
    }

    @Override public void mouseEntered(MouseEvent e) {

        mousePosition.setText("Current mouse Coordinates : ["+e.getX()+","+e.getY()+"]");

    }

    @Override public void mouseExited(MouseEvent e) {
        mousePosition.setText("Mouse outside access window");
    }

    @Override public void mousePressed(MouseEvent e) {
        mousePosition.setText("Mouse pressed at coordinates : ["+e.getX()+","+e.getY()+"]");
    }

    @Override public void mouseReleased(MouseEvent e) {
        mousePosition.setText("Current mouse coordinates : ["+e.getX()+","+e.getY()+"]");
    }

    @Override public void mouseDragged(MouseEvent e) {
        mousePosition.setText("Mouse dragged at coordinates : ["+e.getX()+","+e.getY()+"]");
    }

    @Override public void mouseMoved(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        overEasy = hoverEasy(x, y);
        overMedium = hoverMedium(x, y);
        overHard = hoverHard(x, y);

        repaint();
        mousePosition.setText("Mouse moved to coordinates : ["+e.getX()+","+e.getY()+"]");

    }

    // Paints the JPanel
    public void paint(Graphics g) {
        Image temp;
        Image easy;
        Image med;
        Image hard;

        if(startMenu) {
            if (overEasy) {
                temp = easyDownIcon.getImage();
                easy = temp.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
                easyDownIcon = new ImageIcon(easy);
            } else {
                temp = easyIcon.getImage();
                easy = temp.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
                easyIcon = new ImageIcon(easy);
            }
            g.drawImage(easy, 250, 150, this);

            if (overMedium) {
                temp = medDownIcon.getImage();
                med = temp.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
                medDownIcon = new ImageIcon(med);
            } else {
                temp = medIcon.getImage();
                med = temp.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
                medIcon = new ImageIcon(med);
            }
            g.drawImage(med, 250, 275, this);

            if (overHard) {
                temp = hardDownIcon.getImage();
                hard = temp.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
                hardDownIcon = new ImageIcon(hard);
            } else {
                temp = hardIcon.getImage();
                hard = temp.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
                hardIcon = new ImageIcon(hard);
            }
            g.drawImage(hard, 250, 400, this);
        }

    }
    public static void main(String[] args){
        // Local Declarations
        boolean gameOver = false;

        /*
        In the start, the game has tto ask the user which gamemode they want to play on
        The parameters for each game mode are as follows:
        Easy: 9x9 10 mines
        Medium: 16x16 40 mines
        Advanced: 24x24 99 mines
        On the first move, the user should be guaranteed a safe square. Either initialize the mines AFTER the user clicks or initialize the mines before the user makes their first move and move the mine to a random spot if the user happens to click on a mine
        If it initializes the mines after the first move, then it might lag for a bit once the user makes the first mobe, especially if the init algoritgm is slow. On the other hand, initializing it before can cause some problems with only clearing 1 tile on the first move.
         */
        // Start Game
        init Main = new init();
        Main.startMenu = true;
        Main.start();
    }

    public void start() {
        mousePosition = new JLabel();
        addMouseListener( this ); // listens for own mouse and
        addMouseMotionListener( this ); // mouse-motion events
        setLayout(new FlowLayout(1));
        add(mousePosition);
        //Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(600, 600);
        setVisible( true );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public Image scaleImage(ImageIcon image){
        Image temp;
        Image scaled;

        temp = image.getImage();
        scaled = temp.getScaledInstance(LEVEL_WIDTH, LEVEL_HEIGHT, java.awt.Image.SCALE_SMOOTH);
        return scaled;
    }

    // Function that tests if mouse is hovering over Easy button
    public boolean hoverEasy(int x, int y){
        return (x >= EASY_X && x <= EASY_X + LEVEL_WIDTH)
                && (y >= EASY_Y && y <= EASY_Y + LEVEL_HEIGHT);
    }

    // Function that tests if mouse is hovering over Medium button
    public boolean hoverMedium(int x, int y){
        return (x >= MEDIUM_X && x <= MEDIUM_X + LEVEL_WIDTH)
                && (y >= MEDIUM_Y && y <= MEDIUM_Y + LEVEL_HEIGHT);
    }

    // Function that tests if mouse is hovering over Hard button
    public boolean hoverHard(int x, int y){
        return (x >= HARD_X && x <= HARD_X + LEVEL_WIDTH)
                && (y >= HARD_Y && y <= HARD_Y + LEVEL_HEIGHT);
    }
}

