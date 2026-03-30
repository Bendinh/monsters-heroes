package Board.Tiles;

import Board.BasePiece;
import Items.ItemGenerator;
import Items.BaseItem;
import Utility.Utility;
import java.util.ArrayList;
import java.util.Scanner;
import Heroes.BaseHero;

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
    public void setPlayer(BasePiece player) {
        this.playerPiece = player;
    }
    //#endregion

    //#region Other Methods
    // Action when the tile is interacted with
    public int action(Scanner scanner) {
        int userMove = 0;
        while (userMove == 0) {
            System.out.println("Welcome to the market! Here are the items available:");
            this.printItems();
            userMove = this.getUserMove(scanner);
        }
        return userMove;
    }

    @Override
    public String getDisplayValue() {
        if (this.playerPiece != null) {
            return this.backgroundColor + " " + this.playerPiece.getDisplayValue() + " " + RESET;
        }
        return super.getDisplayValue();
    }

    // Print the items in the market
    public void printItems() {
        Utility.printNewLine();
        for (int i = 0; i < this.items.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + this.items.get(i).toString());
        }
        Utility.printNewLine();
    }

    // Get User Move
    public int getUserMove(Scanner scanner) {
        System.out.println("What would you like to do in the market?");
        System.out.println("I - Manage Inventory");
        System.out.println("B - Buy Item");
        System.out.println("S - Sell Item");
        System.out.println("R - Repair Item");
        System.out.println("E - Exit Market");
        System.out.println("Q - Quit Game");

        boolean validMove = false;
        while (!validMove) {
            System.out.print("Enter your move: ");
            String move = Utility.getValidStringInputFromOptions(scanner, new String[] {"i", "b", "s", "r", "e", "q"});
            switch (move) {
                case "i":
                    break;
                case "b":
                    this.buyItem(scanner);
                    validMove = true;
                    break;
                case "s":
                    break;
                case "r":
                    break;
                case "e":
                    return 2;
                case "q":
                    return 1;
                default:
                    System.out.println("Invalid move. Please try again.");
                    break;
            }
        }
        return 0;
    }

    // Buy Item Flow
    public void buyItem(Scanner scanner) {
        System.out.println("Select hero to buy the item for:");
        int[] heroIndexes = new int[this.playerPiece.getPlayer().getHeroParty().length];
        for (int i = 0; i < this.playerPiece.getPlayer().getHeroParty().length; i++) {
            System.out.println("[" + (i + 1) + "] " + this.playerPiece.getPlayer().getHeroParty()[i].getDisplayValueMarketBuy());
            heroIndexes[i] = i + 1;
        }
        Utility.printNewLine();
        System.out.print("Enter the hero index: ");
        int heroIndex = Utility.getValidIntegerInputFromOptions(scanner, heroIndexes);

        boolean finishedBuying = false;
        while (!finishedBuying) {
            BaseHero buyerHero = this.playerPiece.getPlayer().getHeroAtIndex(heroIndex - 1);
            System.out.print("Select item index to buy, or '0' to go back to market menu: ");
            int itemIndex = Utility.getValidIntegerInputFrom0ToBound(scanner, this.items.size());
            if (itemIndex == 0) {
                finishedBuying = true;
                continue;
            }
            if (buyerHero.getMoney() >= this.items.get(itemIndex - 1).getCost()) {
                BaseItem item = this.items.remove(itemIndex - 1);
                buyerHero.buyItem(item);
                System.out.println("Item bought successfully.");
                finishedBuying = true;
            } else {
                System.out.println("Hero does not have enough gold to buy this item.");
                finishedBuying = false;
            }
        }

    }
    //#endregion
}
