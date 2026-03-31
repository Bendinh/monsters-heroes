/**
 * File: GameRunner.java
 * Description: Class for running the game session.
 *
 * Author: Minh Dinh
 * Created On: 2026-02-07
 *
 * Version History:
 * v1.0–2026–02–07 - Initial implementation
 * v1.1–2026–02–17 - Added explainRules process in runGameSession method before setting up the game
 * v1.2–2026–02–19 - Added Dots and Boxes game selection and breaks between user inputs for aesthetic purposes
 */

import java.util.Scanner;
import Board.BaseBoard;
import Managers.WorldManager;
import Player.BasePlayer;
import Utility.Utility;

public class GameRunner {
    // Data
    private boolean gameSessionOnline;
    private Scanner scanner;
    private WorldManager worldManager;
    private BasePlayer player;

    // Constructor
    public GameRunner() {
        this.gameSessionOnline = true;
        this.scanner = null;
        this.worldManager = null;
        this.player = null;
    }

    //#region Getters and Setters
    
    // Get the game session online status
    public boolean getGameSessionOnline() {
        return gameSessionOnline;
    }

    // Get the game manager
    public WorldManager getWorldManager() {
        return worldManager;
    }
    
    // Get the player
    public BasePlayer getPlayer() {
        return player;
    }

    // Set the game session online status
    public void setGameSessionOnline(boolean gameSessionOnline) {
        this.gameSessionOnline = gameSessionOnline;
    }

    // Set the game manager
    public void setWorldManager(WorldManager worldManager) {
        this.worldManager = worldManager;
    }

    // Set the player
    public void setPlayer(BasePlayer player) {
        this.player = player;
    }
    //#endregion

    // Run the game session
    public void runGameSession() {
        this.scanner = new Scanner(System.in); // Initialize the scanner for game session for user input
        this.initializePlayer(scanner); // Initialize the main player for the game session

        this.mainGameLoop(scanner);

        System.out.println("Thank you for playing " + this.getPlayer().getName() + "! See you next time!");
        this.scanner.close(); // Close the scanner
    }

    // Initialize the main player for the game session
    private void initializePlayer(Scanner scanner) {
        System.out.print("Welcome to Legends: Monsters and Heroes! What would you like to be referred as: ");
        String name = scanner.nextLine();
        Utility.printSeparator();
        System.out.print("How many heroes would you like to bring with you? (1-3): ");
        int numberOfHeroes = Utility.getValidIntegerInputFromOptions(scanner, new int[] {1, 2, 3});
        int[] typeOfHeroes = new int[numberOfHeroes];
        for (int i = 0; i < numberOfHeroes; i++) {
            String orderOfHero = "first";
            if (i == 1) {
                orderOfHero = "second";
            } else if (i == 2) {
                orderOfHero = "third";
            }
            System.out.print("What type of hero would you like to bring with you as your " + orderOfHero + " hero? (1. Warrior, 2. Paladin, 3. Sorcerer): ");
            int typeOfHero = Utility.getValidIntegerInputFromOptions(scanner, new int[] {1, 2, 3});
            typeOfHeroes[i] = typeOfHero - 1;
        }
        this.setPlayer(new BasePlayer(name, typeOfHeroes)); // Initialize the main player with their name and number of heroes

        System.out.println("Welcome, " + this.getPlayer().getName() + " to the world of Legends: Monsters and Heroes! Here is your hero party:");
        Utility.printDoubleSeparator();
        this.getPlayer().printHeroParty();
        Utility.printSeparator();
    }

    // Main game loop
    private void mainGameLoop(Scanner scanner) {
        System.out.println("Let's get started on your journey, " + this.getPlayer().getName() + "! (Press Enter to continue)");
        scanner.nextLine();

        int gameOver = 0;

        while (gameOver == 0) {
            this.worldManager = new WorldManager(this.getPlayer());
            gameOver = this.worldManager.startWorld(scanner);
        }
    }
    
}
