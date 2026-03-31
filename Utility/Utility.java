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
package Utility;

import java.util.Scanner;
import java.util.Random;

public class Utility {
    // Data
    public static final String GREEN_FONT = "\033[32m";
    public static final String RED_FONT = "\033[31m";
    public static final String RESET = "\033[0m";

    // Constructor
    private Utility() {

    }

    //#region Other Methods

    // Print separator
    public static void printSeparator() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------"); // Print a separator to create spacing for aesthetic purposes
    }

    // Print double separator
    public static void printDoubleSeparator() {
        System.out.println("================================================================================================================="); // Print a double separator to create spacing for aesthetic purposes
    }

    // Print a new line
    public static void printNewLine() {
        System.out.println(); // Print a new line to create spacing for aesthetic purposes
    }

    // Get the valid integer input
    public static int getValidIntegerInput(Scanner scanner) {
        while (true) { // Continue until a valid integer is entered
            try {
                int input = scanner.nextInt();
                scanner.nextLine(); 
                System.out.println("-----------------------------------------------------------------------------------------------------------------"); // Print a new line to create spacing for aesthetic purposes
                return input; // Return the valid integer
            } catch (Exception e) { // If the input is not a valid integer, print an error message and continue the loop
                System.out.print("Invalid input. Please enter a number: ");
                scanner.nextLine();
            }
        }
    } 

    // Get the valid integer input from 0 to bound
    public static int getValidIntegerInputFrom0ToBound(Scanner scanner, int bound) {
        while (true) { // Continue until a valid integer is entered
            try {
                int input = scanner.nextInt();
                scanner.nextLine();
                System.out.println("-----------------------------------------------------------------------------------------------------------------"); // Print a new line to create spacing for aesthetic purposes
                if (input >= 0 && input <= bound) {
                    return input; // Return the valid integer
                }
                System.out.print("Invalid input. Please enter a number between 0 and " + bound + ": ");
            } catch (Exception e) { // If the input is not a valid integer, print an error message and continue the loop
                System.out.print("Invalid input. Please enter a number between 0 and " + bound + ": ");
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
                System.out.print("Invalid input. Please enter a number from the valid options: ");
            } catch (Exception e) { // If the input is not a valid integer, print an error message and continue the loop
                System.out.println("-----------------------------------------------------------------------------------------------------------------"); // Print a new line to create spacing for aesthetic purposes
                System.out.print("Invalid input. Please enter a number: ");
                scanner.nextLine();
            }
        }
    } 

    // Get valid string input from provided options
    public static String getValidStringInputFromOptions(Scanner scanner, String[] options) {
        while (true) { // Continue until a valid string is entered
            try {
                String input = scanner.nextLine();
                System.out.println("-----------------------------------------------------------------------------------------------------------------"); // Print a new line to create spacing for aesthetic purposes
                input = input.toLowerCase(); // Convert the input to lowercase
                for (String option : options) {
                    if (input.equals(option)) {
                        return input; // Return the valid string
                    }
                }
                System.out.print("Invalid input. Please enter a string from the valid options: ");
            } catch (Exception e) { // If the input is not a valid string, print an error message and continue the loop
                System.out.println("-----------------------------------------------------------------------------------------------------------------"); // Print a new line to create spacing for aesthetic purposes
                System.out.print("Invalid input. Please enter a string from the valid options: ");
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
                System.out.print("Invalid input. Please enter a valid yes/no: ");
            }
        }
    }

    // Get a random number between min and max
    public static int getRandomNumber(int min, int max) {
        return new Random().nextInt(max - min + 1) + min;
    }
    //#endregion
}
