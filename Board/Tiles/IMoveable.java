package Board.Tiles;

import Board.BasePiece;
import java.util.Scanner;

public interface IMoveable {
    // Data

    //#region Other Methods
    // Action when the tile is interacted with
    public int action(Scanner scanner);

    // Get the player
    public BasePiece getPlayer();
    // Set the player
    public void setPlayer(BasePiece player);
    //#endregion
}
