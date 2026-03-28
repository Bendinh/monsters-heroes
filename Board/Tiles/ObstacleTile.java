package Board.Tiles;

public class ObstacleTile extends BaseTile {
    // Constructor
    public ObstacleTile(int row, int column) {
        super(row, column);
        this.backgroundColor = "\033[42m"; // Green background
        this.displayValue = backgroundColor + "|||" + RESET;
    }
}
