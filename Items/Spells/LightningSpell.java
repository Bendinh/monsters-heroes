package Items.Spells;

public class LightningSpell extends BaseSpell {
    // Constructor
    public LightningSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel, damage, manaCost);
        this.type = SpellType.LIGHTNING;
    }

    @Override
    public LightningSpell copy() {
        return new LightningSpell(this.name, this.cost, this.requiredLevel, this.damage, this.manaCost);
    }
    
}
