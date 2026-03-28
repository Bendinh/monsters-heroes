package Items.Spells;

public class IceSpell extends BaseSpell {
    // Constructor
    public IceSpell(String name, int cost, int requiredLevel, int damage, int manaCost) {
        super(name, cost, requiredLevel, damage, manaCost);
        this.type = SpellType.ICE;
    }
    
}
