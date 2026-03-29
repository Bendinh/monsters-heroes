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
}
