/**
 * File: Utility.java
 * Description: Utility class for the game.
 *
 * Author: Minh Dinh
 * Created On: 2026-02-07
 *
 * Version History:
 * v1.0–2026–02–07 - Initial implementation
 * v1.1–2026–02–19 - Added breaks between user inputs for aesthetic purposes
 */

import java.util.Scanner;

public class Utility {
    // Data

    // Constructor
    private Utility() {

    }

    //#region Other Methods
    // Get the valid integer input
    public static int getValidIntegerInput(Scanner scanner) {
        while (true) { // Continue until a valid integer is entered
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); 
                System.out.println("-----------------------------------------------------------------------------------------------------------------"); // Print a new line to create spacing for aesthetic purposes
                return input; // Return the valid integer
            } catch (Exception e) { // If the input is not a valid integer, print an error message and continue the loop
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    } 

    // Get the valid integer input from provided options
    public static int getValidIntegerInputFromOptions(Scanner scanner, int[] options) {
        while (true) { // Continue until a valid integer is entered
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); 
                System.out.println("-----------------------------------------------------------------------------------------------------------------"); // Print a new line to create spacing for aesthetic purposes
                for (int option : options) {
                    if (input == option) {
                        return input; // Return the valid integer
                    }
                }
                System.out.println("Invalid input. Please enter a number from the valid options.");
            } catch (Exception e) { // If the input is not a valid integer, print an error message and continue the loop
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    } 

    // Get the valid yes/no input
    public static boolean yesNoChoice(Scanner scanner) {
        while (true) { // Continue until a valid yes/no is entered
            String input = scanner.nextLine();
            System.out.println("-----------------------------------------------------------------------------------------------------------------"); // Print a new line to create spacing for aesthetic purposes
            input = input.toLowerCase(); // Convert the input to lowercase
            if (input.equals("y") || input.equals("n") || input.equals("yes") || input.equals("no")) {
                return input.equals("y") || input.equals("yes"); // Return true if the input is y/yes, false if the input is n/no
            } else {
                System.out.println("Invalid input. Please enter a valid yes/no.");
            }
        }
    }
    //#endregion
}
