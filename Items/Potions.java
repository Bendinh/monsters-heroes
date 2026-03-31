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

    //#region Getters and Setters

    public int getAttributeIncrease() {
        return this.attributeIncrease;
    }

    public String[] getAttributeAffected() {
        return this.attributeAffected;
    }

    //#endregion

    //#region Other Methods

    @Override
    public BaseItem copy() {
        Potions copied = new Potions(this.name, this.cost, this.requiredLevel, this.attributeIncrease, String.join("/", this.attributeAffected));
        return copied;
    }

    // Print the armor
    public String toString() {
        return "[Potion] " + this.name + " (Price: " + this.cost + ", Level: " + this.requiredLevel + ", Attribute Increase: " + this.attributeIncrease + ", Attribute Affected: " + String.join("/", this.attributeAffected) + ")";
    }

    //#endregion
}
