/**
 * File: GameManager.java
 * Description: Abstract class for a game manager.
 *
 * Author: Minh Dinh
 * Created On: 2026-02-07
 *
 * Version History:
 * v1.0–2026–02–07 - Initial implementation
 * v1.1–2026–02–17 - Added  abstract explainRules method
 */

import java.util.Scanner;
import Board.BaseBoard;

public class WorldManager {
    // Data
    private BaseBoard board;
    private BasePlayer player;

    // Constructor
    public WorldManager(BasePlayer player) {
        this.board = new BaseBoard(8, 8);
        this.player = player;
    }
    
    //#region Getters and Setters

    //#endregion

    //#region Other Methods

    // Explain the rules of the game
    public void explainWorldRules(Scanner scanner) {

    }

    // Start the game
    public int startWorld(Scanner scanner) {
        int gameOver = 0;
        while (gameOver == 0) {
            this.board.printBoard();
            Utility.printNewLine();
            gameOver = this.getUserMove(scanner);
        }
        return gameOver;
    }

    // End the game
    public void endWorld(){

    }

    // Get User Move
    public int getUserMove(Scanner scanner) {
        System.out.println("What would you like to do?");
        System.out.println("W/A/S/D - Move");
        System.out.println("I - Manage Inventory");
        System.out.println("M - Enter Market (only if on a Market Tile)");
        System.out.println("H - Help/Information");
        System.out.println("Q - Quit Game");

        boolean validMove = false;
        while (!validMove) {
            System.out.print("Enter your move: ");
            String move = Utility.getValidStringInputFromOptions(scanner, new String[] {"w", "a", "s", "d", "i", "m", "h", "q"});
            switch (move) {
                case "w":
                    validMove = this.board.attemptMovePlayer("w");
                    break;
                case "a":
                    validMove = this.board.attemptMovePlayer("a");
                    break;
                case "s":
                    validMove = this.board.attemptMovePlayer("s");
                    break;
                case "d":
                    validMove = this.board.attemptMovePlayer("d");
                    break;
                case "i":
                    break;
                case "m":
                    break;
                case "h":
                    break;
                case "q":
                    return 1;
                default:
                    System.out.println("Invalid move. Please try again.");
                    break;
            }
        }
        return 0;
    }
    //#endregion
}
