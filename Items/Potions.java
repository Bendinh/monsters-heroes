package Items;

public class Potions extends BaseItem {
    // Data
    protected int attributeIncrease;
    protected String[] attributeAffected;

    // Constructor
    public Potions(String name, int cost, int requiredLevel, int attributeIncrease, String attributeAffected) {
        super(name, cost, requiredLevel);
        this.attributeIncrease = attributeIncrease;
        this.attributeAffected = attributeAffected.split("/");
    }
}
