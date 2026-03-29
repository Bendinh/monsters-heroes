package Board.Tiles;
import Board.BasePiece;

public class CommonTile extends BaseTile implements IMoveable {
    // Data
    protected BasePiece playerPiece;

    // Constructor
    public CommonTile(int row, int column) {
        super(row, column);
        this.backgroundColor = "\033[43m"; // Yellow background
        this.displayValue = this.backgroundColor + "   " + RESET;
        this.playerPiece = null;
    }
    //#region Getters and Setters
    // Get the player
    public BasePiece getPlayer() {
        return playerPiece;
    }
    // Set the player
    public void setPlayer(BasePiece playerPiece) {
        this.playerPiece = playerPiece;
    }
    //#endregion

    //#region Other Methods
    // Action when the tile is interacted with
    public void action() {
        System.out.println("You have entered the common tile.");
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
