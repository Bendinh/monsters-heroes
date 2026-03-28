package Board.Tiles;

public class MarketTile extends BaseTile implements IMoveable {
    // Constructor
    public MarketTile(int row, int column) {
        super(row, column);
        this.backgroundColor = "\033[41m"; // Red background
        this.displayValue = backgroundColor + " M " + RESET;
    }

    //#region Other Methods
    // Action when the tile is interacted with
    public void action() {
        System.out.println("You have entered the market.");
    }
    //#endregion
}
