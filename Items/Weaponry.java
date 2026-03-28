package Items;

public class Weaponry extends BaseItem {
    // Data
    protected int damage;
    protected int requiredHands;

    // Constructor
    public Weaponry(String name, int cost, int requiredLevel, int damage, int requiredHands) {
        super(name, cost, requiredLevel);
        this.damage = damage;
        this.requiredHands = requiredHands;
    }
}
