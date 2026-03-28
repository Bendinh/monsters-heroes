/**
 * File: Player.java
 * Description: Class for a player in the game.
 *
 * Author: Minh Dinh
 * Created On: 2026-02-07
 *
 * Version History:
 * v1.0–2026–02–07 - Initial implementation
 */
import Heroes.BaseHero;
import Heroes.HeroGenerator;

public class BasePlayer {
    // Data
    private String name;
    private BaseHero[] heroParty;


    // Constructor
    public BasePlayer(String name, int[] typeOfHeroes) {
        this.setName(name);

        this.heroParty = new BaseHero[typeOfHeroes.length];
        HeroGenerator heroGenerator = new HeroGenerator();
        for (int i = 0; i < typeOfHeroes.length; i++) {
            switch (typeOfHeroes[i]) {
                case 0:
                    this.heroParty[i] = heroGenerator.getRandomWarrior();
                    break;
                case 1:
                    this.heroParty[i] = heroGenerator.getRandomPaladin();
                    break;
                case 2:
                    this.heroParty[i] = heroGenerator.getRandomSorcerer();
                    break;
            }
        }
    }

    //#region Getters and Setters

    // Get the player's name
    public String getName() {
        return name;
    }

    // Set the player's name
    public void setName(String name) {
        this.name = name;
    }

    //#endregion

    //#region Other Methods

    //#endregion
}
