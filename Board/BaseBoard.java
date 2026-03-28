package Board;

import Board.Tiles.BaseTile;

/**
 * File: Board.java
 * Description: Abstract class for a board.
 *
 * Author: Minh Dinh
 * Created On: 2026-02-07
 *
 * Version History:
 * v1.0–2026–02–07 - Initial implementation
 */

public class BaseBoard {
    
    // Data
    protected int height;
    protected int width;
    protected BaseTile[][] tiles;

    // Constructor
    public BaseBoard(int height, int width) {
        this.height = height;
        this.width = width;
        this.tiles = new BaseTile[height][width];
    }

    //#region Getters and Setters
    // Get the height
    public int getHeight() {
        return height;
    }

    // Get the width
    public int getWidth() {
        return width;
    }
    
    // Set the height
    public void setHeight(int height) {
        this.height = height;
    }

    // Set the width
    public void setWidth(int width) {
        this.width = width;
    }
    // #endregion

    //#region Other Methods
    // Generate the board
    public void generateBoard() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                this.tiles[i][j] = null;
            }
        }
    }

    // Print the board
    public void printBoard() {

    }

    //#endregion
}
