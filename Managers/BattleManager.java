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
        ArrayList<BaseMonster> battleMonsters = new ArrayList<BaseMonster>(); // List of unique monsters in this specific battle
        for (BaseMonster refMonster : referenceMonsters) {
            BaseMonster newMonster = new BaseMonster(refMonster.getName(), refMonster.getLevel(), refMonster.getDamage(), refMonster.getDefense(), refMonster.getDodgeChance());
            battleMonsters.add(newMonster);
        }
        ArrayList<BaseHero> playerHeroes = player.getHeroParty();
        ArrayList<BaseHero> battleHeroes = new ArrayList<BaseHero>(); // List of heroes that are still alive in combat
        for (BaseHero refHero : playerHeroes) {
            battleHeroes.add(refHero);
        }
        while (!battleHeroes.isEmpty() && !battleMonsters.isEmpty()) {
            int result = this.turn(scanner, battleHeroes, battleMonsters);
            if (result == 1) {
                return 1;
            }
        }

        // Check for defeated heroes and monsters
        this.checkForDefeatedHeroes(scanner, battleHeroes, battleMonsters);
        this.checkForDefeatedMonsters(scanner, battleHeroes, battleMonsters);

        // Check if the player has defeated all the monsters
        if (battleMonsters.isEmpty()) {
            System.out.println("You have defeated all the monsters! You win! (Press Enter to continue)");
            scanner.nextLine();
            return 0;
        }

        // Check if the player has been defeated
        if (battleHeroes.isEmpty()) {
            System.out.println("All your heroes have been defeated! You lose! (Press Enter to continue)");
            scanner.nextLine();
            return 1;
        }

        return 0;
    }

    // A turn in the battle
    public int turn(Scanner scanner, ArrayList<BaseHero> heroes, ArrayList<BaseMonster> monsters) {
        this.printBattleInformation(heroes, monsters);

        // Let the heroes go first
        System.out.println("The heroes' turn!");
        Utility.printNewLine();
        for (BaseHero hero : heroes) {
            int result = this.heroTurn(scanner, hero, monsters);
            if (result == 1) {
                return 1;
            }
            this.checkForDefeatedMonsters(scanner, heroes, monsters);
        }
        Utility.printDoubleSeparator();

        // Let the monsters go next
        if (!monsters.isEmpty()) {
            System.out.println("The monsters' turn!");
            Utility.printNewLine();
            for (BaseMonster monster : monsters) {
                this.monsterTurn(scanner, monster, heroes);
                this.checkForDefeatedHeroes(scanner, heroes, monsters);
            }
        }

        // Restore some of heroes' stats after the turn
        for (BaseHero hero : heroes) {
            hero.restoreAfterRound(scanner);
        }
        Utility.printDoubleSeparator();
        return 0;
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

    // Monster turn
    public void monsterTurn(Scanner scanner, BaseMonster monster, ArrayList<BaseHero> heroes) {
        // Monsters can only attack; choose a random alive hero to target
        int chosenIndex = Utility.getRandomNumber(0, heroes.size() - 1);
        BaseHero chosenHero = heroes.get(chosenIndex);
        monster.attackHero(scanner, chosenHero);
    }

    // Check for defeated heroes after the monsters' turn
    public void checkForDefeatedHeroes(Scanner scanner, ArrayList<BaseHero> heroes, ArrayList<BaseMonster> monsters) {
        ArrayList<BaseHero> defeatedHeroes = new ArrayList<BaseHero>();
        for (BaseHero hero : heroes) {
            if (hero.isFainted()) {
                defeatedHeroes.add(hero);
                System.out.println(hero.getName() + " has been defeated!");
                scanner.nextLine();
            }
        }
        for (BaseHero hero : defeatedHeroes) {
            System.out.println(hero.getName() + " has fainted!");
            heroes.remove(hero);
        }
        if (!defeatedHeroes.isEmpty()) {
            Utility.printNewLine();
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
