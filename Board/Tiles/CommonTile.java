package Board.Tiles;
import Board.BasePiece;
import java.util.Scanner;
import java.util.Random;
import Monsters.BaseMonster;
import Monsters.MonsterGenerator;
import Managers.BattleManager;
import Utility.Utility;

public class CommonTile extends BaseTile implements IMoveable {
    // Data
    protected BasePiece playerPiece;
    protected MonsterGenerator monsterGenerator;

    // Constructor
    public CommonTile(int row, int column, MonsterGenerator monsterGenerator) {
        super(row, column);
        this.backgroundColor = "\033[43m"; // Yellow background
        this.displayValue = this.backgroundColor + "   " + RESET;
        this.playerPiece = null;
        this.monsterGenerator = monsterGenerator;
    }
    //#region Getters and Setters
    // Get the player
    public BasePiece getPlayer() {
        return playerPiece;
    }
    // Set the player
    public void setPlayer(BasePiece playerPiece) {
        this.playerPiece = playerPiece;
    }
    //#endregion

    //#region Other Methods
    // Action when the tile is interacted with
    public int action(Scanner scanner) {
        int randomNumber = Utility.getRandomNumber(0, 9);
        if (randomNumber < 4) { // 40% chance of encountering monsters
            System.out.println("You have encountered monsters! Prepare for battle...");
            Utility.printNewLine();
            return this.battle(scanner);
        } else { // 60% chance of continuing on your journey peacefully
            System.out.println(" You continue on your journey peacefully...");
            Utility.printNewLine();
            return 0;
        }
    }

    @Override
    public String getDisplayValue() {
        if (this.playerPiece != null) {
            return this.backgroundColor + " " + this.playerPiece.getDisplayValue() + " " + RESET;
        }
        return super.getDisplayValue();
    }

    // Battle with the monsters
    public int battle(Scanner scanner) {
        BattleManager battleManager = new BattleManager(this.playerPiece.getPlayer(), this.monsterGenerator);
        return battleManager.startBattle(scanner);
    }
    //#endregion
}
