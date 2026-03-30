package Heroes;
import Items.BaseItem;
import Items.Armory;
import Items.Weaponry;
import Items.Potions;
import Utility.Utility;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class BaseHero {
    // Data
    protected String name;
    protected int level;
    protected int experience;
    protected int money;
    protected ArrayList<BaseItem> inventory;
    protected Armory equippedArmory;
    protected ArrayList<Weaponry> equippedWeaponry;
    protected int freeHands;


    // Battle Stats
    protected int health;
    protected int mana;
    protected int strength;
    protected int agility;
    protected int dexterity;

    // Additional Stats
    protected int experienceToNextLevel;

    // Constructor
    public BaseHero(String name, int experience, int money, int mana, int strength, int agility, int dexterity) {
        this.name = name;
        this.level = 1;
        this.experience = experience;
        this.money = money;
        this.inventory = new ArrayList<BaseItem>();
        this.equippedArmory = null;
        this.equippedWeaponry = new ArrayList<Weaponry>();
        this.freeHands = 2;

        this.health = this.level * 100;
        this.mana = mana;
        this.strength = strength;
        this.agility = agility;
        this.dexterity = dexterity;

        this.experienceToNextLevel = this.level * 10;
    }

    //#region Getters and Setters

    // Get the hero's name
    public String getName() {
        return name;
    }

    // Get the hero's level
    public int getLevel() {
        return level;
    }

    // Get the hero's experience
    public int getExperience() {
        return experience;
    }
    
    // Get the hero's money
    public int getMoney() {
        return money;
    }

    // Get the hero's health
    public int getHealth() {
        return health;
    }

    // Get the hero's mana
    public int getMana() {
        return mana;
    }

    public ArrayList<Weaponry> getEquippedWeaponry() {
        return equippedWeaponry;
    }

    public Armory getEquippedArmory() {
        return equippedArmory;
    }

    // Get the hero's inventory
    public ArrayList<BaseItem> getInventory() {
        return inventory;
    }

    // Get the hero's strength
    public int getStrength() {
        return strength;
    }

    // Get the hero's agility
    public int getAgility() {
        return agility;
    }

    // Get the hero's dexterity
    public int getDexterity() {
        return dexterity;
    }

    // Get the hero's experience to next level
    public int getExperienceToNextLevel() {
        return experienceToNextLevel;
    }

    // Set the hero's money
    public void setMoney(int money) {
        this.money = money;
    }

    //#endregion

    //#region Other Methods

    // Get Hero Display Value in Market
    public String getDisplayValueMarketBuy() {
        return this.name + " [ Level: " + this.level + ", Gold: " + this.money + "]";
    }

    // Get Hero Display Value in Inventory
    public String getDisplayValueInventory() {
        return this.name + " [ Level: " + this.level + ", HP: " + this.health + ", MP: " + this.mana + "]";
    }

    // Print Inventory
    public void printHeroInformation() {
        System.out.println("Hero: " + this.name);
        System.out.println("Level: " + this.level);
        System.out.println("Experience: " + this.experience);
        System.out.println("Experience to next level: " + this.experienceToNextLevel);
        Utility.printNewLine();
        System.out.println("Health: " + this.health);
        System.out.println("Mana: " + this.mana);
        System.out.println("Strength: " + this.strength);
        System.out.println("Agility: " + this.agility);
        System.out.println("Dexterity: " + this.dexterity);
        Utility.printNewLine();
        System.out.println("Money: " + this.money);

        System.out.print("Equipped Weapons: ");
        if (this.equippedWeaponry.size() == 0) {
            System.out.print("None");
        } else {
            for (Weaponry weapon : this.equippedWeaponry) {
                System.out.print(weapon.getName() + ", ");
            }
        }
        System.out.println();

        System.out.println("Equipped Armor: " + (this.equippedArmory != null ? this.equippedArmory.getName() : "None"));
        System.out.print("Inventory: ");
        if (this.inventory.size() == 0) {
            System.out.print("None");
        } else {
            for (BaseItem item : this.inventory) {
                System.out.print(item.getName() + ", ");
            }
        }
        Utility.printNewLine();
        Utility.printDoubleSeparator();
    }

    // Buy Item
    public void addItemToInventory(BaseItem item) {
        this.inventory.add(item);
    }

    // Manage Inventory
    public int manageInventory(Scanner scanner) {
        boolean finishedManaging = false;
        while (!finishedManaging) {
            System.out.println("What would you like to do with the hero's inventory?");
            System.out.println("A - Equip Armor");
            System.out.println("W - Equip Weapon");
            System.out.println("P - Use Potion");
            System.out.println("I - View Inventory");
            System.out.println("E - Exit Inventory");
            System.out.println("Q - Quit Game");

            System.out.print("Enter your option: ");
            String move = Utility.getValidStringInputFromOptions(scanner, new String[] {"a", "w", "p", "i", "e", "q"});
            switch (move) {
                case "a":
                    this.equipArmor(scanner);
                    break;
                case "w":
                    this.equipWeapon(scanner);
                    break;
                case "p":
                    this.usePotion(scanner);
                    break;
                case "i":
                    this.printHeroInformation();
                    break;
                case "e":
                    finishedManaging = true;
                    break;
                case "q":
                    return 1;
                default:
                    System.out.println("Invalid move. Please try again.");
                    break;
            }
        }
        return 0;
    }

    // Equip Armor
    public void equipArmor(Scanner scanner) {
        ArrayList<Armory> armors = new ArrayList<Armory>();
        for (BaseItem item : this.inventory) {
            if (item instanceof Armory) {
                armors.add((Armory) item);
            }
        }
        if (armors.size() == 0 && this.equippedArmory == null) {
            System.out.println("Hero has no armor in inventory and nothing equipped.");
            Utility.printNewLine();
            return;
        }
        if (armors.size() > 0) {
            for (int i = 0; i < armors.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + armors.get(i).toString()); // Print the armors
            }
        } else {
            System.out.println("No armor in inventory.");
        }
        Utility.printNewLine();
        boolean finishedEquipping = false;
        while (!finishedEquipping) {
            if (armors.size() > 0) {
                System.out.print("Enter the armor index to equip, or 'u' to unequip armor, or '0' to go back to inventory menu: ");
            } else {
                System.out.print("Enter 'u' to unequip armor, or '0' to go back to inventory menu: ");
            }
            String[] options = new String[armors.size() + 2];
            for (int i = 0; i < armors.size(); i++) {
                options[i] = String.valueOf(i + 1);
            }
            options[armors.size()] = "u";
            options[armors.size() + 1] = "0";
            String armorIndex = Utility.getValidStringInputFromOptions(scanner, options);
            if (armorIndex.equals("0")) {
                finishedEquipping = true;
                continue;
            }
            if (armorIndex.equals("u")) {
                if (this.equippedArmory == null) {
                    System.out.println("No armor is equipped.");
                    Utility.printNewLine();
                    continue;
                }
                this.inventory.add(this.equippedArmory);
                this.equippedArmory = null;
                System.out.println("Armor unequipped successfully.");
                Utility.printNewLine();
                finishedEquipping = true;
                continue;
            }
            Armory chosen = armors.get(Integer.parseInt(armorIndex) - 1);
            if (this.level < chosen.getRequiredLevel()) {
                System.out.println("Hero does not have the required level to equip this armor.");
                Utility.printNewLine();
                continue;
            }
            if (!this.inventory.remove(chosen)) {
                System.out.println("Could not find that armor in inventory.");
                Utility.printNewLine();
                continue;
            }
            if (this.equippedArmory != null) {
                this.inventory.add(this.equippedArmory);
            }
            this.equippedArmory = chosen;
            System.out.println("Armor equipped successfully.");
            Utility.printNewLine();
            finishedEquipping = true;
        }
    }

    // Equip Weapon
    public void equipWeapon(Scanner scanner) {
        ArrayList<Weaponry> weapons = new ArrayList<Weaponry>();
        for (BaseItem item : this.inventory) {
            if (item instanceof Weaponry) {
                weapons.add((Weaponry) item);
            }
        }
        if (weapons.size() == 0 && this.equippedWeaponry.size() == 0) {
            System.out.println("Hero has no weapons in inventory and nothing equipped.");
            Utility.printNewLine();
            return;
        }
        if (weapons.size() > 0) {
            for (int i = 0; i < weapons.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + weapons.get(i).toString());
            }
        } else {
            System.out.println("No weapons in inventory.");
        }
        Utility.printNewLine();
        boolean finishedEquipping = false;
        while (!finishedEquipping) {
            if (weapons.size() > 0) {
                System.out.print("Enter the weapon index to equip, or 'u' to unequip weapon, or '0' to go back to inventory menu: ");
            } else {
                System.out.print("Enter 'u' to unequip weapon, or '0' to go back to inventory menu: ");
            }

            String[] options = new String[weapons.size() + 2];
            for (int i = 0; i < weapons.size(); i++) {
                options[i] = String.valueOf(i + 1);
            }
            options[weapons.size()] = "u";
            options[weapons.size() + 1] = "0";
            String weaponIndex = Utility.getValidStringInputFromOptions(scanner, options);

            // Go back to inventory menu
            if (weaponIndex.equals("0")) {
                finishedEquipping = true;
                continue;
            }

            // Unequip weapon
            if (weaponIndex.equals("u")) {
                if (this.equippedWeaponry.size() == 0) {
                    System.out.println("No weapon is equipped.");
                    Utility.printNewLine();
                    continue;
                }
                for (Weaponry weapon : this.equippedWeaponry) {
                    this.inventory.add(weapon);
                }
                this.equippedWeaponry.clear();
                this.freeHands = 2; // Reset free hands to 2
                System.out.println("Weapon unequipped successfully.");
                Utility.printNewLine();
                finishedEquipping = true;
                continue;
            }

            // Equip weapon
            Weaponry chosen = weapons.get(Integer.parseInt(weaponIndex) - 1);
            int handsNeeded = chosen.getRequiredHands();
            if (handsNeeded > this.freeHands) {
                System.out.println("Not enough free hands to equip this weapon. Unequip weapons first.");
                Utility.printNewLine();
                continue;
            }
            if (this.level < chosen.getRequiredLevel()) {
                System.out.println("Hero does not have the required level to equip this weapon.");
                Utility.printNewLine();
                continue;
            }
            this.inventory.remove(chosen); // Remove the weapon from the inventory and equip it
            this.equippedWeaponry.add(chosen); // Add the weapon to the equipped weaponry   
            this.freeHands -= handsNeeded; // Decrease the free hands by the number of hands needed
            System.out.println("Weapon equipped successfully.");
            Utility.printNewLine();
            finishedEquipping = true;
        }
    }

    // Use Potion
    public void usePotion(Scanner scanner) {
        ArrayList<Potions> potions = new ArrayList<Potions>();
        for (BaseItem item : this.inventory) {
            if (item instanceof Potions) {
                potions.add((Potions) item);
            }
        }
        if (potions.size() == 0) {
            System.out.println("Hero has no potions in inventory.");
            Utility.printNewLine();
            return;
        }
        for (int i = 0; i < potions.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + potions.get(i).toString());
        }
        Utility.printNewLine();
        boolean finishedUsing = false;
        while (!finishedUsing) {
            System.out.print("Enter the potion index to use, or '0' to go back to inventory menu: ");
            int potionIndex = Utility.getValidIntegerInputFrom0ToBound(scanner, potions.size() + 1);
            if (potionIndex == 0) {
                finishedUsing = true;
                continue;
            }
            Potions chosen = potions.get(potionIndex - 1);
            if (this.level < chosen.getRequiredLevel()) {
                System.out.println("Hero does not have the required level to use this potion.");
                Utility.printNewLine();
                continue;
            }
            this.inventory.remove(chosen); // Remove the potion from the inventory
            this.applyPotionEffects(chosen); // Apply the potion effects to the hero
            System.out.println("Potion used successfully.");
            Utility.printNewLine();
            finishedUsing = true;
        }
    }

    private void applyPotionEffects(Potions potion) {
        int increase = potion.getAttributeIncrease();
        for (String attribute : potion.getAttributeAffected()) {
            String trimmedAttribute = attribute.trim();
            if (trimmedAttribute.equalsIgnoreCase("Health")) {
                this.health += increase;
            } else if (trimmedAttribute.equalsIgnoreCase("Mana")) {
                this.mana += increase;
            } else if (trimmedAttribute.equalsIgnoreCase("Strength")) {
                this.strength += increase;
            } else if (trimmedAttribute.equalsIgnoreCase("Agility")) {
                this.agility += increase;
            } else if (trimmedAttribute.equalsIgnoreCase("Dexterity")) {
                this.dexterity += increase;
            }
        }
    }
    //#endregion
}
