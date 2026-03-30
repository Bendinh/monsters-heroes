package Items;

public interface IExpirable {

    //#region Other Methods
    // Use the item
    public void use();

    // Get the uses left
    public int getUsesLeft();

    // Reset the uses left
    public void resetUsesLeft();
    //#endregion
}
