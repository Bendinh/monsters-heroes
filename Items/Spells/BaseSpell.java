package Items.Spells;

import Items.BaseItem;

public abstract class BaseSpell extends BaseItem {

    public enum SpellType {
        FIRE,
        ICE,
        LIGHTNING,
    }
    // Data
    protected SpellType type;
    protected int damage;
    protected int manaCost;

    // Constructor
    public BaseSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel);
        this.damage = damage;
        this.manaCost = manaCost;
    }

    //#region Getters and Setters
    public SpellType getType() {
        return this.type;
    }

    public int getDamage() {
        return this.damage;
    }

    public int getManaCost() {
        return this.manaCost;
    }
    //#endregion

    //#region Other Methods

    // Print the spell
    public String toString() {
        return "[" + this.type.toString() + " Spell] " + this.name + " (Price: " + this.cost + ", Level: " + this.requiredLevel + ", Damage: " + this.damage + ", Mana Cost: " + this.manaCost + ")";
    }

    //#endregion
}
