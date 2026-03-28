package Monsters;

public abstract class BaseMonster {
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
}
