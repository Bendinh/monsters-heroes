package Heroes;

public class Sorcerer extends BaseHero {
    // Constructor
    public Sorcerer(String name, int experience, int money, int mana, int strength, int agility, int dexterity) {
        super(name, experience, money, mana, strength, agility, dexterity);

        // Favored Attributes
        this.dexterity = (int) (dexterity * 1.05);
        this.agility = (int) (agility * 1.05);
    }
}
