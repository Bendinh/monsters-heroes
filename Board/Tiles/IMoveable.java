package Board.Tiles;

import Board.BasePiece;

public interface IMoveable {
    // Data

    //#region Other Methods
    // Action when the tile is interacted with
    public void action();

    // Get the player
    public BasePiece getPlayer();
    // Set the player
    public void setPlayer(BasePiece player);
    //#endregion
}
