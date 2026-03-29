package Heroes;
import Items.BaseItem;
import Items.Armory;
import Items.Weaponry;

import java.util.ArrayList;

public abstract class BaseHero {
    // Data
    protected String name;
    protected int level;
    protected int experience;
    protected int money;
    protected ArrayList<BaseItem> inventory;
    protected Armory equippedArmory;
    protected Weaponry equippedWeaponry;


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
        this.equippedWeaponry = null;

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

    public Weaponry getEquippedWeaponry() {
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
}
