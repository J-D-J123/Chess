package Essentials;

import java.awt.Button;

/**
 * @authors         Joey J
 * Last Updated:    1/27/2024
 * 
 * Description:     The Board is supposted to generate a chess Board 
 *                  64 squares in total and 8 rows by 8 columns. This 
 *                  is going to be presented on a JPanel with a Menu and such. 
 */


public class Board {

    // add the menu to the frame 
    Menu menu = new Menu(); 

    // 8 rows and 8 columns
    private final int ROWS = 8;
    private final int COLUMNS = 8;

    // 2D array of Buttons
    private Button[][] board = new Button[ROWS][COLUMNS];

    public Board() {
        makeBoard();
    }

    /**
     * the make board() makes a board with 8 rows and 8 colums 
     * made up of 64 Buttons
     */
    public void makeBoard() {
        for(int i = 0; i < ROWS; i++) {
            for(int j = 0; j < COLUMNS; j++) {
                board[i][j] = new Button();
            }
        }
    }
}