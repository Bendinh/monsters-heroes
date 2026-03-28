package Board.Tiles;

public class CommonTile extends BaseTile implements IMoveable {
    // Data

    // Constructor
    public CommonTile(int row, int column) {
        super(row, column);
        this.backgroundColor = "\033[43m"; // Yellow background
        this.displayValue = backgroundColor + "   " + RESET;
    }

    //#region Other Methods
    // Action when the tile is interacted with
    public void action() {
        System.out.println("You have entered the common tile.");
    }
    //#endregion
    //#region Getters and Setters
}
