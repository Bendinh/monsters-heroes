package Player;
/**
 * File: Player.java
 * Description: Class for a player in the game.
 *
 * Author: Minh Dinh
 * Created On: 2026-02-07
 *
 * Version History:
 * v1.0–2026–02–07 - Initial implementation
 */
import Heroes.BaseHero;
import Heroes.HeroGenerator;
import Items.BaseItem;
import Utility.Utility;
import java.util.Scanner;

public class BasePlayer {
    // Data
    private String name;
    private BaseHero[] heroParty;


    // Constructor
    public BasePlayer(String name, int[] typeOfHeroes) {
        this.setName(name);

        // Generates the hero party based on the type of heroes the player chose
        this.heroParty = new BaseHero[typeOfHeroes.length];
        HeroGenerator heroGenerator = new HeroGenerator();
        for (int i = 0; i < typeOfHeroes.length; i++) {
            switch (typeOfHeroes[i]) {
                case 0:
                    this.heroParty[i] = heroGenerator.getRandomWarrior();
                    break;
                case 1:
                    this.heroParty[i] = heroGenerator.getRandomPaladin();
                    break;
                case 2:
                    this.heroParty[i] = heroGenerator.getRandomSorcerer();
                    break;
            }
        }
    }

    //#region Getters and Setters

    // Get the player's name
    public String getName() {
        return name;
    }

    // Set the player's name
    public void setName(String name) {
        this.name = name;
    }

    // Get the player's hero party
    public BaseHero[] getHeroParty() {
        return heroParty;
    }

    // Set the player's hero party
    public void setHeroParty(BaseHero[] heroParty) {
        this.heroParty = heroParty;
    }

    //#endregion

    //#region Other Methods

    // Get the hero at index
    public BaseHero getHeroAtIndex(int index) {
        return heroParty[index];
    }

    // Print the player's hero party
    public void printHeroParty() {
        for (BaseHero hero : heroParty) {
            hero.printHeroInformation();
        }
    }

    // Manage the hero party inventory
    public int manageHeroPartyInventory(Scanner scanner) {
        this.printHeroParty();
        boolean finishedManaging = false;
        while (!finishedManaging) {
            System.out.println("Which hero's inventory would you like to manage?");
            int[] heroIndexes = new int[this.heroParty.length];
            for (int i = 0; i < this.heroParty.length; i++) {
                System.out.println("[" + (i + 1) + "] " + this.heroParty[i].getDisplayValueInventory());
                heroIndexes[i] = i + 1;
            }
            Utility.printNewLine();
            System.out.print("Enter the hero index, or '0' to go back to the world map: ");
            int heroIndex = Utility.getValidIntegerInputFrom0ToBound(scanner, heroIndexes.length);
            if (heroIndex == 0) {
                finishedManaging = true;
                continue;
            }
            if (this.getHeroAtIndex(heroIndex - 1).manageInventory(scanner) == 1) {
                return 1;
            }
        }
        return 0;
    }

    //#endregion
}
