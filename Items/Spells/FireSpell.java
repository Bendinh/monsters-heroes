package Items.Spells;

public class FireSpell extends BaseSpell {
    // Constructor
    public FireSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel, damage, manaCost);
        this.type = SpellType.FIRE;
    }

    @Override
    public FireSpell copy() {
        return new FireSpell(this.name, this.cost, this.requiredLevel, this.damage, this.manaCost);
    }
}
