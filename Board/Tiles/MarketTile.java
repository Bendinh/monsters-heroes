package Board.Tiles;

import Board.BasePiece;
import Items.ItemGenerator;
import Items.BaseItem;
import Utility.Utility;
import java.util.ArrayList;
import java.util.Scanner;
import Heroes.BaseHero;
import Items.IExpirable;

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
        System.out.println("I - Check Inventory");
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
                    this.playerPiece.getPlayer().printHeroParty();
                    validMove = true;
                    break;
                case "b":
                    this.buyItem(scanner);
                    validMove = true;
                    break;
                case "s":
                    this.sellItem(scanner);
                    validMove = true;
                    break;
                case "r":
                    this.repairItem(scanner);
                    validMove = true;
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
        int[] heroIndexes = new int[this.playerPiece.getPlayer().getHeroParty().size()];
        for (int i = 0; i < this.playerPiece.getPlayer().getHeroParty().size(); i++) {
            System.out.println("[" + (i + 1) + "] " + this.playerPiece.getPlayer().getHeroParty().get(i).getDisplayValueMarketBuy());
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
            if (buyerHero.getMoney() >= this.items.get(itemIndex - 1).getCost()) { // Check if the hero has enough money to buy the item
                BaseItem item = this.items.remove(itemIndex - 1); // Remove the item from the market
                buyerHero.setMoney(buyerHero.getMoney() - item.getCost()); // Subtract the cost of the item from the hero's money
                buyerHero.addItemToInventory(item); // Add the item to the hero's inventory
                System.out.println("Item bought successfully.");
                Utility.printNewLine();
                finishedBuying = true;
            } else {
                System.out.println("Hero does not have enough gold to buy this item."); // If the hero does not have enough money, print an error message
                finishedBuying = false;
            }
        }
    }

    // Sell Item Flow
    public void sellItem(Scanner scanner) {
        System.out.println("Select hero to sell the item for:");
        int[] heroIndexes = new int[this.playerPiece.getPlayer().getHeroParty().size()];
        for (int i = 0; i < this.playerPiece.getPlayer().getHeroParty().size(); i++) {
            System.out.println("[" + (i + 1) + "] " + this.playerPiece.getPlayer().getHeroParty().get(i).getDisplayValueMarketBuy());
            heroIndexes[i] = i + 1;
        }
        Utility.printNewLine();
        System.out.print("Enter the hero index: ");
        int heroIndex = Utility.getValidIntegerInputFromOptions(scanner, heroIndexes);

        boolean finishedSelling = false;
        while (!finishedSelling) {
            BaseHero sellerHero = this.playerPiece.getPlayer().getHeroAtIndex(heroIndex - 1);
            if (sellerHero.getInventory().size() == 0) { // Check if the hero has any items to sell
                System.out.println("Hero does not have any items to sell.");
                Utility.printNewLine();
                finishedSelling = true;
                continue;
            }
            for (int i = 0; i < sellerHero.getInventory().size(); i++) { // Print the items in the hero's inventory
                System.out.println("[" + (i + 1) + "] " + sellerHero.getInventory().get(i).toString());
            }
            Utility.printNewLine();
            System.out.print("Select item index to sell (for half of the cost), or '0' to go back to market menu: ");
            int itemIndex = Utility.getValidIntegerInputFrom0ToBound(scanner, sellerHero.getInventory().size());
            if (itemIndex == 0) { // If the user wants to go back to the market menu, exit the loop
                finishedSelling = true;
                continue;
            }
            BaseItem item = sellerHero.getInventory().remove(itemIndex - 1); // Remove the item from the hero's inventory
            sellerHero.setMoney(sellerHero.getMoney() + item.getCost() / 2); // Add half of the cost of the item to the hero's money
            this.items.add(item); // Add the item to the market
            System.out.println("Item sold successfully.");
            Utility.printNewLine();
            finishedSelling = true;
        }
    }

    // Repair Item Flow
    public void repairItem(Scanner scanner) {
        System.out.println("Select hero to repair the item for:");
        int[] heroIndexes = new int[this.playerPiece.getPlayer().getHeroParty().size()];
        for (int i = 0; i < this.playerPiece.getPlayer().getHeroParty().size(); i++) {
            System.out.println("[" + (i + 1) + "] " + this.playerPiece.getPlayer().getHeroParty().get(i).getDisplayValueMarketBuy());
            heroIndexes[i] = i + 1;
        }
        Utility.printNewLine();
        System.out.print("Enter the hero index: ");
        int heroIndex = Utility.getValidIntegerInputFromOptions(scanner, heroIndexes);

        boolean finishedRepairing = false;
        while (!finishedRepairing) {
            BaseHero repairHero = this.playerPiece.getPlayer().getHeroAtIndex(heroIndex - 1);
            ArrayList<IExpirable> expirableItems = new ArrayList<IExpirable>();
            for (BaseItem item : repairHero.getInventory()) { // Iterate through the hero's inventory and add references of user's expirable items to the list
                if (item instanceof IExpirable) {
                    expirableItems.add((IExpirable) item); // Add references of user's expirable items to the list
                }
            }
            if (expirableItems.size() == 0) { // Check if the hero has any items to repair
                System.out.println("Hero does not have any items to repair.");
                Utility.printNewLine();
                finishedRepairing = true;
                continue;
            }
            for (int i = 0; i < expirableItems.size(); i++) { // Print the expirable items in the hero's inventory
                System.out.println("[" + (i + 1) + "] " + expirableItems.get(i).toString());
            }
            Utility.printNewLine();
            System.out.print("Select item index to repair (for half of the cost), or '0' to go back to market menu: ");
            int itemIndex = Utility.getValidIntegerInputFrom0ToBound(scanner, expirableItems.size());
            if (itemIndex == 0) { // If the user wants to go back to the market menu, exit the loop
                finishedRepairing = true;
                continue;
            }
            IExpirable item = expirableItems.get(itemIndex - 1); // Get the expirable item to repair
            if (item.getUsesLeft() == 10) { // Check if the item is still in good condition
                System.out.println("Item does not need to be repaired.");
                Utility.printNewLine();
                finishedRepairing = true;
                continue;
            }
            if (repairHero.getMoney() >= item.getCostToRepair()) { // Check if the hero has enough money to repair the item
                repairHero.setMoney(repairHero.getMoney() - item.getCostToRepair()); // Subtract the cost to repair the item from the hero's money
                item.resetUsesLeft(); // Reset the uses left of the item
                System.out.println("Item repaired successfully.");
                Utility.printNewLine();
                finishedRepairing = true;
            } else {
                System.out.println("Hero does not have enough gold to repair this item."); // If the hero does not have enough money, print an error message
                finishedRepairing = false;
            }
        }
    }
    //#endregion
}
