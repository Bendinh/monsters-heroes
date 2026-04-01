package Board.Tiles;

import Board.BasePiece;
import Items.ItemGenerator;
import Items.BaseItem;
import Managers.MarketManager;

import java.util.ArrayList;
import java.util.Scanner;

public class MarketTile extends BaseTile implements IMoveable {
    // Data
    protected BasePiece playerPiece;
    protected ArrayList<BaseItem> items;

    // Constructor
    public MarketTile(int row, int column, ItemGenerator itemGenerator) {
        super(row, column);
        this.backgroundColor = "\033[41m"; // Red background
        this.displayValue = backgroundColor + " M " + RESET;
        this.playerPiece = null;
        this.items = itemGenerator.generateRandomItemsForShop();
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
    public int action(Scanner scanner) {
        MarketManager marketManager = new MarketManager(this.playerPiece.getPlayer(), this.items);
        return marketManager.startMarket(scanner);
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
