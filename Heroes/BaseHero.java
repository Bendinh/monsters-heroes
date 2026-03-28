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

}
