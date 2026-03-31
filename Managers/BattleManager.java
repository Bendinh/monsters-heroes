package Managers;

import Player.BasePlayer;
import Monsters.MonsterGenerator;
import Monsters.BaseMonster;
import java.util.Scanner;
import java.util.ArrayList;
import Heroes.BaseHero;
import Utility.Utility;

public class BattleManager {
    // Data
    protected BasePlayer player;
    protected MonsterGenerator monsterGenerator;

    // Constructor
    public BattleManager(BasePlayer player, MonsterGenerator monsterGenerator) {
        this.player = player;
        this.monsterGenerator = monsterGenerator;
    }

    //#region Other Methods
    // Start the battle
    public int startBattle(Scanner scanner) {
        int numberOfHeroes = player.getHeroParty().size();
        int maxHeroLevel = player.getMaxHeroLevel();
        ArrayList<BaseMonster> referenceMonsters = this.monsterGenerator.generateMonsters(numberOfHeroes, maxHeroLevel);
        ArrayList<BaseMonster> battleMonsters = new ArrayList<BaseMonster>();
        for (BaseMonster refMonster : referenceMonsters) {
            BaseMonster newMonster = new BaseMonster(refMonster.getName(), refMonster.getLevel(), refMonster.getDamage(), refMonster.getDefense(), refMonster.getDodgeChance());
            battleMonsters.add(newMonster);
        }
        ArrayList<BaseHero> heroes = player.getHeroParty();
        while (!heroes.isEmpty() && !battleMonsters.isEmpty()) {
            this.turn(scanner, heroes, battleMonsters);
            return 0; //TODO: REMOVE THIS ONCE IMPLEMENTING THE BATTLE SYSTEM
        }
        return 0;
    }

    // A turn in the battle
    public void turn(Scanner scanner, ArrayList<BaseHero> heroes, ArrayList<BaseMonster> monsters) {
        this.printBattleInformation(heroes, monsters);

        // Let the heroes go first
        System.out.println("The heroes go first!");
        Utility.printNewLine();
        for (BaseHero hero : heroes) {
            this.heroTurn(scanner, hero, monsters);
            this.checkForDefeatedMonsters(scanner, heroes, monsters);
        }
        Utility.printDoubleSeparator();
        System.out.println("The monsters go next!");
        Utility.printNewLine();
    }

    public int heroTurn(Scanner scanner, BaseHero hero, ArrayList<BaseMonster> monsters) {
        boolean finishedTurn = false;
        while (!finishedTurn) {
            System.out.println("What would " + hero.getName() + " do?");
            System.out.println("1 - Attack");
            System.out.println("2 - Use Spell");
            System.out.println("3 - Use Potion");
            System.out.println("4 - Equip Armor");
            System.out.println("5 - Equip Weapon");
            System.out.println("6 - View Info");
            System.out.println("0 - Quit");
            System.out.print("Enter your move: ");
            int move = Utility.getValidIntegerInputFrom0ToBound(scanner, 6);

            int result = 0;
            switch (move) {
                case 1:
                    result = hero.attackMonster(scanner, monsters);
                    if (result == 1) { // If the hero attacks a monster, the turn is finished
                        finishedTurn = true;
                    }
                    break;
                case 2:
                    result = hero.useSpell(scanner, monsters);
                    if (result == 1) { // If the hero uses a spell, the turn is finished
                        finishedTurn = true;
                    }
                    break;
                case 3:
                    result = hero.usePotion(scanner);
                    if (result == 1) { // If the hero uses a potion, the turn is finished
                        finishedTurn = true;
                    }
                    break;
                case 4:
                    result = hero.equipArmor(scanner);
                    if (result == 1) { // If the hero equips armor, the turn is finished
                        finishedTurn = true;
                    }
                    break;
                case 5:
                    result = hero.equipWeapon(scanner);
                    if (result == 1) { // If the hero equips a weapon, the turn is finished
                        finishedTurn = true;
                    }
                    break;
                case 6:
                    hero.printHeroInformation();
                    Utility.printNewLine();
                    break;
                case 0:
                    return 1;
                default:
                    System.out.println("Invalid move. Please try again.");
                    break;
            }
        }
        return 0;
    }

    // Check for defeated monsters after the heroes' turn
    public void checkForDefeatedMonsters(Scanner scanner, ArrayList<BaseHero> heroes, ArrayList<BaseMonster> monsters) {
        ArrayList<BaseMonster> defeatedMonsters = new ArrayList<BaseMonster>();
        for (BaseMonster monster : monsters) { // check for defeated monsters
            if (monster.getHealth() <= 0) {
                System.out.println(monster.getName() + " has been defeated!");
                System.out.println("Current heroes receive 2 experience points each and " + monster.getLevel()*100 + " gold pieces each! (Enter to continue)");
                defeatedMonsters.add(monster);
                scanner.nextLine();
                for (BaseHero aliveHero : heroes) { // give experience and gold to the heroes
                    aliveHero.addExperience(2);
                    aliveHero.addMoney(monster.getLevel()*100);
                }
            }
        }
        for (BaseMonster monster : defeatedMonsters) { // remove defeated monsters from the list
            if (monster.getHealth() <= 0) {
                monsters.remove(monster); // remove defeated monsters from the battle
            }
        }
    }

    // Print the battle information
    public void printBattleInformation(ArrayList<BaseHero> heroes, ArrayList<BaseMonster> monsters) {
        System.out.println(Utility.GREEN_FONT + "Heroes:" + Utility.RESET);
        Utility.printNewLine();
        for (BaseHero hero : heroes) {
            hero.printHeroInformationBattle();
            Utility.printNewLine();
        }
        Utility.printDoubleSeparator();
        System.out.println(Utility.RED_FONT + "Monsters:" + Utility.RESET);
        Utility.printNewLine();
        for (BaseMonster monster : monsters) {
            monster.printMonsterInformation();
            Utility.printNewLine();
        }
        Utility.printDoubleSeparator();
    }
    //#endregion
}
