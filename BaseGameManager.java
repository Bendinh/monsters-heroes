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

public abstract class BaseGameManager {

    // Constructor
    public BaseGameManager() {

    }
    
    //#region Getters and Setters

    //#endregion

    //#region Other Methods

    // Setup the game
    public abstract void setupGame(Scanner scanner, BasePlayer player);

    // Explain the rules of the game
    public abstract void explainRules(Scanner scanner);

    // Start the game
    public abstract void startGame(Scanner scanner);

    // End the game
    public abstract void endGame(); 

    //#endregion
}
