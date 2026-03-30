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
package Board;

import Board.Tiles.BaseTile;
import Board.Tiles.CommonTile;
import Board.Tiles.ObstacleTile;
import Board.Tiles.MarketTile;
import Board.Tiles.IMoveable;
import Board.BasePiece;
import Items.ItemGenerator;
import Player.BasePlayer;
import java.util.Random;

public class BaseBoard {
    
    // Data
    protected int height;
    protected int width;
    protected BaseTile[][] boardTiles;
    protected ItemGenerator itemGenerator;

    protected BasePlayer player;
    protected int[] playerPosition;
    protected BasePiece playerPiece;

    protected static final String YELLOW_BACKGROUND = "\033[43m";
    protected static final String RESET = "\033[0m";

    // Constructor
    public BaseBoard(int height, int width, BasePlayer player, ItemGenerator itemGenerator) {
        this.height = height;
        this.width = width;
        this.boardTiles = new BaseTile[height][width];
        this.itemGenerator = itemGenerator;
        this.player = player;
        this.playerPiece = new BasePiece("P", this.player);

        this.createBoard();
        this.createObstacles();
        this.createMarkets();
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

    // Get the tile at player's position
    public BaseTile getTileAtPlayerPosition() {
        return this.boardTiles[this.playerPosition[0]][this.playerPosition[1]];
    }
    // #endregion

    //#region Other Methods
    // Generate the base board with all common tiles
    public void createBoard() {
        for (int r = 0; r < this.getHeight(); r++) {
            for (int c = 0; c < this.getWidth(); c++) {
                // Place the player on the start positions
                CommonTile startTile = new CommonTile(r, c);
                if (r==0 && c==0) {
                    startTile.setPlayer(this.playerPiece);
                    this.playerPosition = new int[] {r, c};
                } 
                this.boardTiles[r][c] = startTile;
            }
        }
    }

    // Add obstacles to the board
    public void createObstacles() {
        int obstacleCount = 0;
        int maxObstacles = ((this.getHeight() * this.getWidth()) / 10) * 3; // 30% of the board are obstacles

        while (obstacleCount < maxObstacles) {
            int r = new Random().nextInt(this.getHeight()-3) + 2; // Randomly select a row between 1 and the height-1
            int c = new Random().nextInt(this.getWidth()-2) + 1; // Randomly select a column between 1 and the width-1
            if (this.boardTiles[r][c] instanceof CommonTile) {
                this.boardTiles[r][c] = new ObstacleTile(r, c);
                obstacleCount++;
                
                // Add an obstacle to the top, bottom, left, and right of the obstacle at the chance of 0.5
                if (new Random().nextInt(4) == 0 && this.boardTiles[r-1][c] instanceof CommonTile && obstacleCount < maxObstacles) {
                    this.boardTiles[r-1][c] = new ObstacleTile(r-1, c);
                    obstacleCount++;
                }
                if (new Random().nextInt(4) == 0 && this.boardTiles[r+1][c] instanceof CommonTile && obstacleCount < maxObstacles) {
                    this.boardTiles[r+1][c] = new ObstacleTile(r+1, c);
                    obstacleCount++;
                }
                if (new Random().nextInt(4) == 0 && this.boardTiles[r][c-1] instanceof CommonTile && obstacleCount < maxObstacles) {
                    this.boardTiles[r][c-1] = new ObstacleTile(r, c-1);
                    obstacleCount++;
                }
                if (new Random().nextInt(4) == 0 && this.boardTiles[r][c+1] instanceof CommonTile && obstacleCount < maxObstacles) {
                    this.boardTiles[r][c+1] = new ObstacleTile(r, c+1);
                    obstacleCount++;
                }
            }

        }
    }

    // Add markets to the board
    public void createMarkets() {
        int marketCount = 0;
        int maxMarkets = (this.getHeight() * this.getWidth()) / 5; // 20% of the board are markets
        for (int r = 0; r < this.getHeight(); r++) {
            for (int c = 0; c < this.getWidth(); c++) {
                if (r == 0 && c == 0) {
                    continue;
                }
                if (this.boardTiles[r][c] instanceof CommonTile && marketCount < maxMarkets) {
                    if (new Random().nextDouble() < 0.2857) { // of the remaining 70% common tiles, market takes up 20/70
                        this.boardTiles[r][c] = new MarketTile(r, c, this.itemGenerator);
                        marketCount++;
                    }
                }
            }
        }
    }

    // Print the board
    // Print the current state of the board
    public void printBoard() {
        for (int row = 0; row < this.getHeight(); row++) {

            // Each row we print 2 levels of elements, the horizontal dividers, and the tiles and vertical dividers
            for (int i = 0; i < 2; i++) {

                if (i == 0) { // ------This is the horizontal divider level
                    // Print the tiles in the row
                    for (int column = 0; column < this.getWidth(); column++) {
                        System.out.print(YELLOW_BACKGROUND + "*---" + RESET);
                    }
                    System.out.print(YELLOW_BACKGROUND + "*" + RESET); // Print the right most border of the row

                } else { // ------This is the vertical divider level
                    // Print the tiles in the row
                    for (int column = 0; column < this.getWidth(); column++) {
                        System.out.print(YELLOW_BACKGROUND + "|" + RESET);
                        System.out.print(this.boardTiles[row][column].getDisplayValue());
                    }
                    System.out.print(YELLOW_BACKGROUND + "|" + RESET); // Print the right most border of the row
                }
                System.out.println(); // Print a new line to move to the next level
            }
        }

        // ---------Print the bottom border of the board
        for (int column = 0; column < this.getWidth(); column++) {
            System.out.print(YELLOW_BACKGROUND + "*---" + RESET);
        }
        System.out.print(YELLOW_BACKGROUND + "*" + RESET); // Print the bottom right corner of the board
        System.out.println();
    }

    // Moves the player in a straight line in the direction specified, either by 1 or 2 tiles depending on if it is a special move (jumping over the opposing player)
    public boolean attemptMovePlayer(String direction) {
        int[] currentPosition = this.playerPosition;
        int[] newPosition = new int[2];

        // Calculate the new position of the player
        switch (direction) {
            case "w":
                newPosition[0] = currentPosition[0] - 1;
                newPosition[1] = currentPosition[1];
                break;
            case "s":
                newPosition[0] = currentPosition[0] + 1;
                newPosition[1] = currentPosition[1];
                break;
            case "a":
                newPosition[0] = currentPosition[0];
                newPosition[1] = currentPosition[1] - 1;
                break;
            case "d":
                newPosition[0] = currentPosition[0];
                newPosition[1] = currentPosition[1] + 1;
                break;
        }

        // Check if the new position is valid
        if (newPosition[0] < 0 || newPosition[0] >= this.getHeight() || newPosition[1] < 0 || newPosition[1] >= this.getWidth()) {
            System.out.println("Invalid move. The new position is out of bounds.");
            return false;
        }

        // Check if the new position is blocked by an obstacle
        if (this.boardTiles[newPosition[0]][newPosition[1]] instanceof IMoveable) {
            ((IMoveable) this.boardTiles[currentPosition[0]][currentPosition[1]]).setPlayer(null);
            ((IMoveable) this.boardTiles[newPosition[0]][newPosition[1]]).setPlayer(playerPiece);
            this.playerPosition = newPosition;
            return true;
        } else {
            System.out.println("Invalid move. The forest is too dense to move through in that direction.");
            return false;
        }
    }

    //#endregion
}
