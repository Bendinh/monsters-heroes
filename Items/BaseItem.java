package Items;

public abstract class BaseItem {
    // Data
    protected String name;
    protected int cost;
    protected int requiredLevel;

    // Constructor
    public BaseItem(String name, int cost, int requiredLevel) {
        this.name = name;
        this.cost = cost;
        this.requiredLevel = requiredLevel;
    }

    //#region Getters and Setters

    // Get the item's name
    public String getName() {
        return name;
    }

    // Get the item's cost
    public int getCost() {
        return cost;
    }

    // Get the item's required level
    public int getRequiredLevel() {
        return requiredLevel;
    }

    //#endregion

    //#region Other Methods

    // Print the item
    public String toString() {
        return this.name + " - Cost: " + this.cost + " - Required Level: " + this.requiredLevel;
    }

    //#endregion
}
