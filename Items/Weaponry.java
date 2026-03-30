package Items;

public class Weaponry extends BaseItem implements IExpirable {
    // Data
    protected int damage;
    protected int requiredHands;
    protected int usesLeft;

    // Constructor
    public Weaponry(String name, int cost, int requiredLevel, int damage, int requiredHands) {
        super(name, cost, requiredLevel);
        this.damage = damage;
        this.requiredHands = requiredHands;
        this.usesLeft = 10; // 10 uses left by default
    }

    //#region Other Methods

    // Print the armor
    public String toString() {
        return "[Weapon] " + this.name + " (" + this.usesLeft + "/10)" + " (Price: " + this.cost + ", Level: " + this.requiredLevel + ", Damage: " + this.damage + ", Hands: " + this.requiredHands + ")";
    }

    // Use the weapon
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

    // Get the cost to repair the weapon
    public int getCostToRepair() {
        return this.cost / 2;
    }

    //#endregion
}
