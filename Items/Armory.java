package Items;

public class Armory extends BaseItem {
    // Data
    protected int damageReduction;

    // Constructor
    public Armory(String name, int cost, int requiredLevel, int damageReduction) {
        super(name, cost, requiredLevel);
        this.damageReduction = damageReduction;
    }
}