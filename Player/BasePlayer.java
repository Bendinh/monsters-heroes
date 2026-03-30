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
            System.out.println("Hero: " + hero.getName());
            System.out.println("Level: " + hero.getLevel());
            System.out.println("Experience: " + hero.getExperience());
            System.out.println("Experience to next level: " + hero.getExperienceToNextLevel());
            Utility.printNewLine();
            System.out.println("Health: " + hero.getHealth());
            System.out.println("Mana: " + hero.getMana());
            System.out.println("Strength: " + hero.getStrength());
            System.out.println("Agility: " + hero.getAgility());
            System.out.println("Dexterity: " + hero.getDexterity());
            Utility.printNewLine();
            System.out.println("Money: " + hero.getMoney());
            System.out.println("Equipped Weapon: " + (hero.getEquippedWeaponry() != null ? hero.getEquippedWeaponry().getName() : "None"));
            System.out.println("Equipped Armor: " + (hero.getEquippedArmory() != null ? hero.getEquippedArmory().getName() : "None"));
            System.out.print("Inventory: ");
            if (hero.getInventory().size() == 0) {
                System.out.print("None");
            } else {
                for (BaseItem item : hero.getInventory()) {
                    System.out.print(item.getName() + ", ");
                }
            }
            Utility.printNewLine();
            Utility.printDoubleSeparator();
        }
    }

    //#endregion
}
