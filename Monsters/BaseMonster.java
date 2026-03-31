package Monsters;

import Heroes.BaseHero;
import Items.Armory;
import Utility.Utility;
import java.util.Scanner;

public class BaseMonster {
    // Data
    protected String name;
    protected int level;

    protected int health;
    protected int damage;
    protected int defense;
    protected int dodgeChance;

    // Constructor
    public BaseMonster(String name, int level, int damage, int defense, int dodgeChance) {
        this.name = name;
        this.level = level;
        this.health = this.level * 100;
        this.damage = damage;
        this.defense = defense;
        this.dodgeChance = dodgeChance;
    }

    //#region Getters and Setters
    // Get the monster's name
    public String getName() {
        return name;
    }

    // Get the monster's level
    public int getLevel() {
        return level;
    }

    // Get the monster's health
    public int getHealth() {
        return health;
    }

    // Get the monster's damage
    public int getDamage() {
        return damage;
    }

    // Get the monster's defense
    public int getDefense() {
        return defense;
    }

    // Get the monster's dodge chance
    public int getDodgeChance() {
        return dodgeChance;
    }

    //Set the monster's damage
    public void setDamage(int damage) {
        this.damage = damage;
    }

    //Set the monster's defense
    public void setDefense(int defense) {
        this.defense = defense;
    }

    //Set the monster's dodge chance
    public void setDodgeChance(int dodgeChance) {
        this.dodgeChance = dodgeChance;
    }
    //#endregion

    //#region Other Methods
    // Get Display Value
    public String getDisplayValue() {
        return this.name + " [ Level: " + this.level + ", HP: " + this.health + "]";
    }

    // Print the monster information
    public void printMonsterInformation() {
        System.out.println("Monster: " + this.name);
        System.out.println("Level: " + this.level);
        System.out.println("Health: " + this.health);
        System.out.println("Damage: " + this.damage);
        System.out.println("Defense: " + this.defense);
        System.out.println("Dodge Chance: " + this.dodgeChance);
    }

    // Take Damage
    public void takeDamage(int amount) {
        this.health -= amount;
    }

    // Attack Hero
    public void attackHero(Scanner scanner, BaseHero hero) {
        int dodgeChance = (int) (hero.getAgility() * 0.05); // dodge chance scaled to 5% of hero's agility

        int dodgeRoll = Utility.getRandomNumber(0, 100);
        if (dodgeRoll < dodgeChance) {
            System.out.print(hero.getName() + " dodged " + this.name + "'s attack! (Press Enter to continue)");
            scanner.nextLine();
            return;
        }

        int damageDealt = this.damage;

        hero.takeDamage(scanner, damageDealt, this.name);
    }
    //#endregion
}
