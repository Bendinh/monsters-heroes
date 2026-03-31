package Items.Spells;

public class IceSpell extends BaseSpell {
    // Constructor
    public IceSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel, damage, manaCost);
        this.type = SpellType.ICE;
    }

    @Override
    public IceSpell copy() {
        return new IceSpell(this.name, this.cost, this.requiredLevel, this.damage, this.manaCost);
    }
    
}
