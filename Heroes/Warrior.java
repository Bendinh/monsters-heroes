package Heroes;

public class Warrior extends BaseHero {
    // Constructor
    public Warrior(String name, int experience, int money, int mana, int strength, int agility, int dexterity) {
        super(name, experience, money, mana, strength, agility, dexterity);

        // Favored Attributes
        this.strength = (int) (strength * 1.05);
        this.agility = (int) (agility * 1.05);
    }
}
