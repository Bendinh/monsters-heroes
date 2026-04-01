package Managers;

import Heroes.BaseHero;
import Items.BaseItem;
import Items.IExpirable;
import Player.BasePlayer;
import Utility.Utility;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles market menus and buy / sell / repair flows. The shop's item list is shared by reference
 * with the {@link Board.Tiles.MarketTile} that opened the market.
 */
public class MarketManager {
    private final BasePlayer player;
    private final ArrayList<BaseItem> shopItems;

    public MarketManager(BasePlayer player, ArrayList<BaseItem> shopItems) {
        this.player = player;
        this.shopItems = shopItems;
    }

    /**
     * Runs the market until the player exits or quits.
     *
     * @return 0 should not occur from outer loop; inner menu returns 0 to stay in market,
     *         1 quit game, 2 exit market
     */
    public int startMarket(Scanner scanner) {
        int userMove = 0;
        while (userMove == 0) {
            System.out.println("Welcome to the market! Here are the items available:");
            printShopItems();
            userMove = getUserMove(scanner);
        }
        return userMove;
    }

    private void printShopItems() {
        Utility.printNewLine();
        for (int i = 0; i < this.shopItems.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + this.shopItems.get(i).toString());
        }
        Utility.printNewLine();
    }

    private int getUserMove(Scanner scanner) {
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
                    this.player.printHeroParty();
                    validMove = true;
                    break;
                case "b":
                    buyItem(scanner);
                    validMove = true;
                    break;
                case "s":
                    sellItem(scanner);
                    validMove = true;
                    break;
                case "r":
                    repairItem(scanner);
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

    private void buyItem(Scanner scanner) {
        System.out.println("Select hero to buy the item for:");
        int[] heroIndexes = new int[this.player.getHeroParty().size()];
        for (int i = 0; i < this.player.getHeroParty().size(); i++) {
            System.out.println("[" + (i + 1) + "] " + this.player.getHeroParty().get(i).getDisplayValueMarketBuy());
            heroIndexes[i] = i + 1;
        }
        Utility.printNewLine();
        System.out.print("Enter the hero index: ");
        int heroIndex = Utility.getValidIntegerInputFromOptions(scanner, heroIndexes);

        boolean finishedBuying = false;
        while (!finishedBuying) {
            BaseHero buyerHero = this.player.getHeroAtIndex(heroIndex - 1);
            System.out.print("Select item index to buy, or '0' to go back to market menu: ");
            int itemIndex = Utility.getValidIntegerInputFrom0ToBound(scanner, this.shopItems.size());
            if (itemIndex == 0) {
                finishedBuying = true;
                continue;
            }
            if (buyerHero.getMoney() >= this.shopItems.get(itemIndex - 1).getCost()) {
                if (buyerHero.getLevel() < this.shopItems.get(itemIndex - 1).getRequiredLevel()) {
                    System.out.println("Hero does not have the required level to buy this item.");
                    Utility.printNewLine();
                    finishedBuying = true;
                    continue;
                }
                BaseItem item = this.shopItems.remove(itemIndex - 1);
                buyerHero.setMoney(buyerHero.getMoney() - item.getCost());
                BaseItem boughtItem = item.copy();
                buyerHero.addItemToInventory(boughtItem);
                System.out.println("Item bought successfully.");
                Utility.printNewLine();
                finishedBuying = true;
            } else {
                System.out.println("Hero does not have enough gold to buy this item.");
                finishedBuying = false;
            }
        }
    }

    private void sellItem(Scanner scanner) {
        System.out.println("Select hero to sell the item for:");
        int[] heroIndexes = new int[this.player.getHeroParty().size()];
        for (int i = 0; i < this.player.getHeroParty().size(); i++) {
            System.out.println("[" + (i + 1) + "] " + this.player.getHeroParty().get(i).getDisplayValueMarketBuy());
            heroIndexes[i] = i + 1;
        }
        Utility.printNewLine();
        System.out.print("Enter the hero index: ");
        int heroIndex = Utility.getValidIntegerInputFromOptions(scanner, heroIndexes);

        boolean finishedSelling = false;
        while (!finishedSelling) {
            BaseHero sellerHero = this.player.getHeroAtIndex(heroIndex - 1);
            if (sellerHero.getInventory().size() == 0) {
                System.out.println("Hero does not have any items to sell.");
                Utility.printNewLine();
                finishedSelling = true;
                continue;
            }
            for (int i = 0; i < sellerHero.getInventory().size(); i++) {
                System.out.println("[" + (i + 1) + "] " + sellerHero.getInventory().get(i).toString());
            }
            Utility.printNewLine();
            System.out.print("Select item index to sell (for half of the cost), or '0' to go back to market menu: ");
            int itemIndex = Utility.getValidIntegerInputFrom0ToBound(scanner, sellerHero.getInventory().size());
            if (itemIndex == 0) {
                finishedSelling = true;
                continue;
            }
            BaseItem item = sellerHero.getInventory().remove(itemIndex - 1);
            sellerHero.setMoney(sellerHero.getMoney() + item.getCost() / 2);
            this.shopItems.add(item);
            System.out.println("Item sold successfully.");
            Utility.printNewLine();
            finishedSelling = true;
        }
    }

    private void repairItem(Scanner scanner) {
        System.out.println("Select hero to repair the item for:");
        int[] heroIndexes = new int[this.player.getHeroParty().size()];
        for (int i = 0; i < this.player.getHeroParty().size(); i++) {
            System.out.println("[" + (i + 1) + "] " + this.player.getHeroParty().get(i).getDisplayValueMarketBuy());
            heroIndexes[i] = i + 1;
        }
        Utility.printNewLine();
        System.out.print("Enter the hero index: ");
        int heroIndex = Utility.getValidIntegerInputFromOptions(scanner, heroIndexes);

        boolean finishedRepairing = false;
        while (!finishedRepairing) {
            BaseHero repairHero = this.player.getHeroAtIndex(heroIndex - 1);
            ArrayList<IExpirable> expirableItems = new ArrayList<IExpirable>();
            for (BaseItem item : repairHero.getInventory()) {
                if (item instanceof IExpirable) {
                    expirableItems.add((IExpirable) item);
                }
            }
            if (expirableItems.size() == 0) {
                System.out.println("Hero does not have any items to repair.");
                Utility.printNewLine();
                finishedRepairing = true;
                continue;
            }
            for (int i = 0; i < expirableItems.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + expirableItems.get(i).toString());
            }
            Utility.printNewLine();
            System.out.print("Select item index to repair (for half of the cost), or '0' to go back to market menu: ");
            int itemIndex = Utility.getValidIntegerInputFrom0ToBound(scanner, expirableItems.size());
            if (itemIndex == 0) {
                finishedRepairing = true;
                continue;
            }
            IExpirable item = expirableItems.get(itemIndex - 1);
            if (item.getUsesLeft() == 10) {
                System.out.println("Item does not need to be repaired.");
                Utility.printNewLine();
                finishedRepairing = true;
                continue;
            }
            if (repairHero.getMoney() >= item.getCostToRepair()) {
                repairHero.setMoney(repairHero.getMoney() - item.getCostToRepair());
                item.resetUsesLeft();
                System.out.println("Item repaired successfully.");
                Utility.printNewLine();
                finishedRepairing = true;
            } else {
                System.out.println("Hero does not have enough gold to repair this item.");
                finishedRepairing = false;
            }
        }
    }
}
