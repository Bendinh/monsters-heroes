package Items;

public interface IExpirable {

    //#region Other Methods
    // Get the cost to repair the item
    public int getCostToRepair();
    // Use the item
    public void use();

    // Get the uses left
    public int getUsesLeft();

    // Reset the uses left
    public void resetUsesLeft();
    //#endregion
}
