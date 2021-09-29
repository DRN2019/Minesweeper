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

public class main extends JFrame implements MouseListener, MouseMotionListener {

    // Initialize all images
    ImageIcon easyIcon = new ImageIcon("src/images/Easy.png");
    ImageIcon medIcon = new ImageIcon("src/images/Medium.png");
    ImageIcon hardIcon = new ImageIcon("src/images/Hard.png");
    ImageIcon easyDownIcon = new ImageIcon("src/images/EasyDown.png");
    ImageIcon medDownIcon = new ImageIcon("src/images/MediumDown.png");
    ImageIcon hardDownIcon = new ImageIcon("src/images/HardDown.png");
    ImageIcon tileIcon = new ImageIcon("src/images/tile.png");
    ImageIcon tileDownIcon = new ImageIcon("src/images/tileDown.png");
    ImageIcon tileClearIcon = new ImageIcon("src/images/tileClear.png");



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
        mousePosition.setText("Mouse moved to coordinates : ["+e.getX()+","+e.getY()+"]");
    }

    public void paint(Graphics g) {
        Image temp;
        Image easy;
        Image med;
        Image hard;

        easyIcon = new ImageIcon("src/images/Easy.png");
        medIcon = new ImageIcon("src/images/Medium.png");
        hardIcon = new ImageIcon("src/images/Hard.png");

        temp = easyIcon.getImage();
        easy = temp.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        easyIcon = new ImageIcon(easy);
        g.drawImage(easy, 250, 150, this);

        temp = medIcon.getImage();
        med = temp.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        medIcon = new ImageIcon(med);
        g.drawImage(med, 250, 275, this);

        temp = hardIcon.getImage();
        hard = temp.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH);
        hardIcon = new ImageIcon(hard);
        g.drawImage(hard, 250, 400, this);





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

        /*
        main m = new main();
        JFrame f = new JFrame();
        JPanel panel = new JPanel();
        panel.addMouseListener(new MouseAdapter() {// provides empty implementation of all
            // MouseListener`s methods, allowing us to
            // override only those which interests us
            @Override //I override only one method for presentation
            public void mousePressed(MouseEvent e) {
                System.out.println(e.getX() + "," + e.getY());
            }
        });
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(m);
        f.setSize(700, 700);
        f.setVisible(true);
        do {
            f.invalidate();
            f.validate();
            f.repaint();
        } while(!gameOver);

        */
        new main().start();


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



}

