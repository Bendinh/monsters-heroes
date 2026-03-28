package Items.Spells;

import Items.BaseItem;

public abstract class BaseSpell extends BaseItem {

    enum SpellType {
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
}
