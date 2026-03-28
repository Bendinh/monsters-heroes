package Board;
/**
 * File: Piece.java
 * Description: Class for a piece in the game.
 *
 * Author: Minh Dinh
 * Created On: 2026-02-07
 *
 * Version History:
 * v1.0–2026–02–07 - Initial implementation
 * v1.1–2026–02–18 - Added display value
 */

public abstract class BasePiece {
    protected String displayValue;

    // Constructor
    public BasePiece(String displayValue) {
        this.displayValue = displayValue;
    }

    //#region Getters and Setters

    // Get the piece's value
    public String getDisplayValue() {
        return displayValue;
    }

    // Set the piece's display value
    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }
    //#endregion
}
