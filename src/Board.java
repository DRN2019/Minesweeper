import java.lang.Math;

public class Board{
    private int mineCt; // Number of mines
    private int boardX; // Width of Board
    private int boardY; // Height of Board
    private int minesLeft;

    private boolean[][] board = new boolean[boardX][boardY]; // 2D Boolean Array of which tiles have mines
    private boolean[][] visible = new boolean[boardX][boardY]; // 2D Boolean Array of which tiles are uncovered
    private boolean[][] flagged = new boolean[boardX][boardY]; // 2D Boolean Array of which tiles are flagged

    public Board(int mineCt, int boardX, int boardY){
        // Initializing vars
        this.mineCt = mineCt;
        this.boardX = boardX;
        this.boardY = boardY;
    }

    // Initializes the mines in the board AFTER user's first move to avoid losing on first click
    // Ensures that the tiles immediately surrounding the first tile will be clear (4-9 guaranteed)
    // initX = first tile X pos; initY = first tile Y pos
    public void initMines(int initX, int initY){
        int count = 0;
        while (count < mineCt){
            int index = (int) Math.round(Math.random()*256);
            if(!(Math.abs(index/boardX - initX) <= 1 && Math.abs(index%boardX - initY) <= 1)) {
                if (!board[index / boardX][index % boardX]) {
                    board[index / boardX][index % boardX] = true;
                    count--;
                }
            }
        }
    }

    // Return function for mines left on the board
    public int getMinesLeft(){
        return minesLeft;
    }

    // Return function for the board
    public boolean[][] getBoard(){
        return board;
    }

    // Return function for visible tiles
    public boolean[][] getVisible(){
        return visible;
    }

    // Return function for flagged tiles
    public boolean[][] getFlagged(){
        return flagged;
    }

    // Function that counts the number of mines surrounding a tile
    // Returns an int of how many surrounding tiles
    public int surround(int x, int y){
        int count = 0;
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(!(x + i < 0 || x + i >= boardX) && !(y + j < 0 || y + j >= boardY)){
                    if(board[x + i][y + j]){
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // Function that interacts with the user's uncovering of tiles
    // Returns true if user clicked on a mine; Returns false of user didn't
    public boolean uncover(int x, int y){
        // Uncovered mine tile
        if(!flagged[x][y]){
            if(board[x][y]){
                return true;
            }
            else{
                reveal(x,y);
                return false;
            }
        }
        return false;
    }

    // Reveals the tiles and also recursively clears blank tiles if surrounding mine count is 0
    public void reveal(int x, int y) {
        // Reveal Part
        visible[x][y] = true;

        // Recursively Clearing Blank Tiles
        if (surround(x, y) == 0) {
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if (!(x + i < 0 || x + i > boardX) && !(y + j < 0 || y + j > boardY)) {
                        reveal(x + i, y + i);
                    }
                }
            }
        }
    }

    // Allows the user to flag or unflag a tile as a mine
    public void flag(int x, int y){
        if (flagged[x][y]) {
            flagged[x][y] = false;
            minesLeft++;
        } else {
            if (minesLeft > 0) {
                flagged[x][y] = true;
                minesLeft--;
            }
        }
    }


}