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

public class GameRunner {
    // Data
    private boolean gameSessionOnline;
    private Scanner scanner;
    private BaseGameManager gameManager;
    private BasePlayer player;

    // Constructor
    public GameRunner() {
        this.gameSessionOnline = true;
        this.scanner = null;
        this.gameManager = null;
        this.player = null;
    }

    //#region Getters and Setters
    
    // Get the game session online status
    public boolean getGameSessionOnline() {
        return gameSessionOnline;
    }

    // Get the game manager
    public BaseGameManager getGameManager() {
        return gameManager;
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
    public void setGameManager(BaseGameManager gameManager) {
        this.gameManager = gameManager;
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

        // while (this.getGameSessionOnline()) { // Game session is online until the player decides to stop playing

        //     this.selectGame(scanner); // Select the game

        //     this.getGameManager().explainRules(scanner); // Explain the rules of the game
        //     this.getGameManager().setupGame(scanner, this.getPlayer()); // Setup the game
        //     this.getGameManager().startGame(scanner); // Start the game
        //     this.getGameManager().endGame(); // End the game

        //     System.out.println("Would you like to play again? (y/n)"); // Ask the player if they want to play again
        //     this.setGameSessionOnline(Utility.yesNoChoice(scanner)); // Get the player's choice
        // }

        // System.out.println("Thank you for playing " + this.getPlayer().getName() + "! See you next time!");
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
    }

    // Select the game
    private void selectGame(Scanner scanner) {
        System.out.println("Okay, " + this.getPlayer().getName() + "! What game would you like to play?");
        System.out.println("Enter a number from the following options:");
        System.out.println("1. Slider Puzzle");
        System.out.println("2. Dots and Boxes");
        
        while (true) { // Continue until a valid choice is entered
            int choice = Utility.getValidIntegerInput(scanner);
            switch (choice) {
                default: // Invalid choice
                    System.out.println("Invalid choice. Please enter a valid number.");
                    break;
            }
        }
    } 
}
