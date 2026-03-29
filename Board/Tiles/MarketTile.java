package Board.Tiles;

import Board.BasePiece;

public class MarketTile extends BaseTile implements IMoveable {
    // Data
    protected BasePiece playerPiece;

    // Constructor
    public MarketTile(int row, int column) {
        super(row, column);
        this.backgroundColor = "\033[41m"; // Red background
        this.displayValue = backgroundColor + " M " + RESET;
        this.playerPiece = null;
    }

    //#region Getters and Setters
    // Get the player
    public BasePiece getPlayer() {
        return playerPiece;
    }
    // Set the player
    public void setPlayer(BasePiece player) {
        this.playerPiece = player;
    }
    //#endregion

    //#region Other Methods
    // Action when the tile is interacted with
    public void action() {
        System.out.println("You have entered the market.");
    }

    @Override
    public String getDisplayValue() {
        if (this.playerPiece != null) {
            return this.backgroundColor + " " + this.playerPiece.getDisplayValue() + " " + RESET;
        }
        return super.getDisplayValue();
    }
    //#endregion
}
