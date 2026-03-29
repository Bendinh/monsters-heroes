package Board.Tiles;
/**
 * File: BaseTile.java
 * Description: Abstract class for a tile.
 *
 * Author: Minh Dinh
 * Created On: 2026-02-18
 *
 * Version History:
 * v1.0–2026–02–18 - Initial implementation
 */

public abstract class BaseTile {
    // Data
    protected int row;
    protected int column;
    protected String displayValue;
    protected String backgroundColor;

    protected static final String RESET = "\033[0m";

    // Constructor
    public BaseTile(int row, int column) {
        this.row = row;
        this.column = column;
    }

    //#region Getters and Setters
    // Get the row
    public int getRow() {
        return row;
    }
    
    // Get the column
    public int getColumn() {
        return column;
    }
    
    // Set the row
    public void setRow(int row) {
        this.row = row;
    }

    // Set the column
    public void setColumn(int column) {
        this.column = column;
    }
    //#endregion

    //#region Other Methods
    // Get the display value
    public String getDisplayValue() {
        return this.displayValue;
    }

    // Set the display value
    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }


    //#endregion
}
