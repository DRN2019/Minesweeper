import java.lang.Math;

public class Board{
    int mineCt; // Number of mines
    int boardX; // Width of Board
    int boardY; // Height of Board

    boolean[][] board = new boolean[boardX][boardY]; // 2D Boolean Array of which tiles have mines
    boolean[][] visible = new boolean[boardX][boardY]; // 2D Boolean Array of which tiles are uncovered

    public Board(int mineCt, int boardX, int boardY){
        // Initializing vars
        this.mineCt = mineCt;
        this.boardX = boardX;
        this.boardY = boardY;
    }

    public void initMines(){
        int count = 0;
        while (count < mineCt){
            int index = (int) Math.round(Math.random()*256);
            if(!board[index/boardX][index%boardX]){
                board[index/boardX][index%boardX] = true;
                count--;
            }
        }
    }

    public void reveal(int x, int y){
        // Reveal Part
        visible[x][y] = true;

        // Recursively Clearing Blank Tiles
        if(surround(x,y) == 0){
            for(int i = -1; i <= 1; i++){
                for(int j = -1; j <= 1; j++){
                    if(!(x + i < 0 || x + i > boardX) && !(y + j < 0 || y + j > boardY)){
                        reveal(x + i, y + i);
                    }
                }
            }
        }
    }

    public int surround(int x, int y){
        int count = 0;
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(!(x + i < 0 || x + i > boardX) && !(y + j < 0 || y + j > boardY)){
                    if(board[x + i][y + j]){
                        count++;
                    }
                }
            }
        }

        return count;
    }



}