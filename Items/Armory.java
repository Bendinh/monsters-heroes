package Items;

public class Armory extends BaseItem implements IExpirable {
    // Data
    protected int damageReduction;
    protected int usesLeft;

    // Constructor
    public Armory(String name, int cost, int requiredLevel, int damageReduction) {
        super(name, cost, requiredLevel);
        this.damageReduction = damageReduction;
        this.usesLeft = 10; // 10 uses left by default
    }

    //#region Other Methods

    // Print the armor
    public String toString() {
        return "[Armor] " + this.name + " (" + this.usesLeft + "/10)" + " (Price: " + this.cost + ", Level: " + this.requiredLevel + ", Damage Reduction: " + this.damageReduction + ")";
    }

    // Use the armor
    public void use() {
        this.usesLeft--;
    }

    // Get the uses left
    public int getUsesLeft() {
        return this.usesLeft;
    }

    // Reset the uses left
    public void resetUsesLeft() {
        this.usesLeft = 10;
    }

    //#endregion
}