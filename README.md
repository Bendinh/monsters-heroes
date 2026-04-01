# CS611-Assignment 4
## Legends: Monsters and Heroes
---------------------------------------------------------------------------
- Name: Minh Dinh
- Email: minhd5@bu.edu
- Student ID: U88347208


## Files
---------------------------------------------------------------------------


This section should be all of the source code files that have a .java extension. You should also include a brief description of what the class does.

// Base Classes
- BaseBoard.java: This abstract class provides the structure for different types of game boards to follow and implement.
- BaseGameManager.java: This abstract class provides the structure for different types of game managers to follow and implement based on the type of board game that it is managing.
- BasePiece.java: This abstract class provides the structure for a piece on the board for the different types of pieces of a board games
- BasePlayer.java: This abstract class provides the structure for a player in a board game.
- BaseTile.java: This abstract class provides the structure for a tile on the board for the different types of tiles of a board games.

// Dots and Boxes
- DotsAndBoxesBoard.java: This class extends the BaseBoard class and implements the board logic for the Dots and Boxes game.
- DotsAndBoxesGameManager.java: This class extends the BaseGameManager class and implements the game logic and flow for the Dots and Boxes game.
- DotsAndBoxesPiece.java: This class extends the BasePiece class and implements the piece logic for the Dots and Boxes game.
- DotsAndBoxesPlayer.java: This class extends the BasePlayer class and implements the player logic for the Dots and Boxes game.
- DotsAndBoxesTile.java: This class extends the BaseTile class and implements the tile logic for the Dots and Boxes game.

// Slider Puzzle
- SliderPuzzleBoard.java: This class extends the BaseBoard class and implements the board logic for the Slider Puzzle game.
- SliderPuzzleGameManager.java: This class extends the BaseGameManager class and implements the game logic and flow for the Slider Puzzle game.
- SliderPuzzlePiece.java: This class extends the BasePiece class and implements the piece logic for the Slider Puzzle game.
- SliderPuzzlePlayer.java: This class extends the BasePlayer class and implements the player logic for the Slider Puzzle game.
- SliderPuzzleTile.java: This class extends the BaseTile class and implements the tile logic for the Slider Puzzle game.

// Other Classes
- GameRunner.java: This class is responsible for running the game session and managing the game session flow, from player creation to game selection and execution.
- Utility.java: This class provides utility methods for the game, such as getting valid integer input from the user.
- test.java: This class is responsible for testing the game



## Notes
---------------------------------------------------------------------------
Please explain the cool features of your program. Anything that you feel like you did a good job at or were creative about, explain it in bullets here. Additionally, any design decisions should be made here.

// Assingment 1 Updates //

- The program is a board game runner that allows users to play board games from the list of available games. The game session is online until the player decides to stop playing. The current implemented game is the Slider Puzzle game, with more games able to be added in the future.

- Slider Puzzle is a board game where the player is given a board with a shuffled set of pieces with a single empty space. The player is able to move the pieces around the board to solve the puzzle by ending up with the pieces in ascending order.

- The size of the board can be customized by the user (3x3, 4x4, 5x7, etc.), with the constraint being that the board height and width is greater than 1. The board can also be a ridiculously large (4+ digits), but the user must ensure to zoom out to see the entire board in the terminal. The board will by deault have 3 empty spaces for the pieces value, but will be adjusted based on the increased values of the pieces.

- After generating the board, the board will be randomly generated in a shuffled order, and then checked if it is solvable. If it is not solvable, the board will be fixed it by swapping 2 consecutive pieces to flip the inversion count to make it solvable.

-  The GameManager class is an abstract blueprint for different types of game managers to follow and implement based on the type of board game that it is managing. It contains methods for setting up the game, starting the game, and ending the game. Currently SliderPuzzleGameManager is the only GameManager that is implemented, but we can add more GameManager classes to manage other types of board games. The Board class and the SliderPuzzleBoard class have a similar relationship.

- The Piece class and the Player class are currently simple classes that represent a piece and a player. As the Slider Puzzle game is a single player game with simple pieces, the current implementation of these classes are sufficient for the game. As we implement more board games, these classes can be extended to more unique pieces and players with more complex stats and restrictions.

- The Utility class provides utility methods for the game that are used in many classes, such as getting valid integer input from the user. This allows for reusability of code and prevention of code duplication.

// Assingment 2 Updates //

- The program is updates to include the Dots and Boxes game.

- With more than one game implemented, the base classes have been updated to include all the foundational components that are shared between the different types of board games.

- Added explanations to the program to help users understand the rules of the game and how to play the individual games.

- For the Dots and Boxes game, the board is a grid of dots, edges, and boxes. The board is customizable by the user, with the constraint being that the board height and width is greater than 0, and also less than 10. The board can be generated to be larger than 9x9, but to ensure the row/column numbering guides is intuitive, I have constrained the board size to be less than 10.

- When the user customizes the board size, it will be based on how many boxes are in each dimension for the users to fill. However, the logic board that the program understands is composed of the dots, lines, and boxes. The program will translate the user's input of the coordiantes to the logic board coordinates. Thus, each line the user draws is essentially its individual piece placed on a tile on the logic board. 

- The dots are non-interactable, acting as guides for users to deduce the coordinates of the lines they want to draw. The lines are interactable, and the user can draw a line by typing the coordinates of the dots they want to connect. The program will then check if the line is valid, and if it is, it will draw the line on the board. The boxes are indirectly interactable, as the user can only draw a line to form a box, and the program will then check if the box is formed, and if it is, it will color the box with the player's color.

## How to compile and run
---------------------------------------------------------------------------
Your directions on how to run the code. Make sure to be as thorough as possible! Ideally should resemble the lines below


1. Navigate to the directory "BoardGames" after unzipping the files
2. Run the following instructions:
javac --release 8 test.java
java test


## Input/Output Example
---------------------------------------------------------------------------
Please give us a full execution of what we should see on the screen. Label each text with input and output. For example:


```
Output:
[+] Let's play a game! What would you like to be referred as: 

Input:
Minh

Output:
[+] -----------------------------------------------------------------------------------------------------------------
[+] Okay, Minh! What game would you like to play?
[+] Enter a number from the following options:
[+] 1. Slider Puzzle
[+] 2. Dots and Boxes

Input:
2

Output:
[+] -----------------------------------------------------------------------------------------------------------------
[+] Dots and Boxes is a game between two players, where they take turns connecting the dots to form boxes.
[+] The player can connect the dots by typing the coordinates of the dots they want to connect. 
[+] For example, if the player wants to connect the dots at (0, 0) and (0, 1), they would type '0 0 0 1'.
[+] The game will continue until the board is filled with boxes. The player who forms the most boxes wins.
[+] Do you understand the rules? (y/n)

Input:
y

Output:
[+] -----------------------------------------------------------------------------------------------------------------
[+] Let's play Dots and Boxes, Minh!
[+] What is the name of the second player?

Input:
Bill

Output:
[+] -----------------------------------------------------------------------------------------------------------------
[+] Alright, this will be a match between Minh and Bill!
[+] Minh's moves will be in red, and Bill's moves will be in blue.
[+] What would you like the height of the board to be? (# of boxes to fill, must be greater than 0) : 

(Not shown in the text here, but the player names will be printed in the color of the player)

Input:
2

Output:
[+] -----------------------------------------------------------------------------------------------------------------
[+] What would you like the width of the board to be? (# of boxes to fill, must be greater than 0) : 

Input:
1

Output:
[+] -----------------------------------------------------------------------------------------------------------------
[+] Good choice! Let's start the game!


    0   1

0   *   *
         
1   *   *
         
2   *   *

Minh: 0 boxes.
Bill: 0 boxes.

Minh, from what coordinates do you want to draw a line? (e.g. '0 0 0 1') : 

Input:
0 0 0 1

Output:
[+] -----------------------------------------------------------------------------------------------------------------

    0   1

0   *---*
         
1   *   *
         
2   *   *

[+] Minh: 0 boxes.
[+] Bill: 0 boxes.

[+] Bill, from what coordinates do you want to draw a line? (e.g. '0 0 0 1') :

(Not shown in the text here, but the linwa will be printed in the color of the player who drew that line)

Input:
0 0 1 0

Output:
[+] -----------------------------------------------------------------------------------------------------------------

    0   1

0   *---*
    |    
1   *   *
         
2   *   *

[+] Minh: 0 boxes.
[+] Bill: 0 boxes.

[+] Minh, from what coordinates do you want to draw a line? (e.g. '0 0 0 1') :

Input:
0 1 1 1

Output:
[+] -----------------------------------------------------------------------------------------------------------------

    0   1

0   *---*
    |   |
1   *   *
         
2   *   *

[+] Minh: 0 boxes.
[+] Bill: 0 boxes.

[+] Bill, from what coordinates do you want to draw a line? (e.g. '0 0 0 1') :

Input:
1 0 1 1

Output:
[+] -----------------------------------------------------------------------------------------------------------------
[+] Bill has formed a box! Bill gets to take their turn again.
 
    0   1

0   *---*
    |   |
1   *---*
         
2   *   *

[+] Minh: 0 boxes.
[+] Bill: 1 boxes.

[+] Bill, from what coordinates do you want to draw a line? (e.g. '0 0 0 1') :

(Not shown in the text here, but the background of the box will be colored with the player's color)

Input:
1 1 2 1

Output:
[+] -----------------------------------------------------------------------------------------------------------------

    0   1

0   *---*
    |   |
1   *---*
        |
2   *   *

[+] Minh: 0 boxes.
[+] Bill: 1 boxes.

[+] Minh, from what coordinates do you want to draw a line? (e.g. '0 0 0 1') :

Input:
2 0 1 0

Output:
[+] -----------------------------------------------------------------------------------------------------------------

    0   1

0   *---*
    |   |
1   *---*
    |   |
2   *   *

[+] Minh: 0 boxes.
[+] Bill: 1 boxes.

[+] Bill, from what coordinates do you want to draw a line? (e.g. '0 0 0 1') :

Input:
2 0 2 1

Output:
[+] -----------------------------------------------------------------------------------------------------------------
[+] Bill has formed a box! Bill gets to take their turn again.

    0   1

0   *---*
    |   |
1   *---*
    |   |
2   *---*

[+] Minh: 0 boxes.
[+] Bill: 2 boxes.

[+] Bill has won the game!

[+] Would you like to play again? (y/n)

Input:
n

Output:
[+] -----------------------------------------------------------------------------------------------------------------
[+] Thank you for playing Minh! See you next time!