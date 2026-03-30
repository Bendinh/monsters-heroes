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
package Board;
import Player.BasePlayer;

public class BasePiece {
    protected String displayValue;
    protected BasePlayer player;

    // Constructor
    public BasePiece(String displayValue, BasePlayer player) {
        this.displayValue = displayValue;
        this.player = player;
    }

    //#region Getters and Setters

    // Get the player
    public BasePlayer getPlayer() {
        return player;
    }

    // Set the player
    public void setPlayer(BasePlayer player) {
        this.player = player;
    }

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
