package Items.Spells;

public class LightningSpell extends BaseSpell {
    // Constructor
    public LightningSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel, damage, manaCost);
        this.type = SpellType.LIGHTNING;
    }
    
}
