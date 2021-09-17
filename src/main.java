import javax.swing.*;

public class main{
    public static void main(String[] args){
        // Local Declarations
        boolean gameOver = false;


        while(!gameOver){


            gameOver = true;
        }

        /*
        In the start, the game has tto ask the user which gamemode they want to play on
        The parameters for each game mode are as follows:
        Easy: 9x9 10 mines
        Medium: 16x16 40 mines
        Advanced: 24x24 99 mines

        On the first move, the user should be guaranteed a safe square. Either initialize the mines AFTER the user clicks or initialize the mines before the user makes their first move and move the mine to a random spot if the user happens to click on a mine
        If it initializes the mines after the first move, then it might lag for a bit once the user makes the first mobe, especially if the init algoritgm is slow. On the other hand, initializing it before can cause some problems with only clearing 1 tile on the first move.

         */
    }
}