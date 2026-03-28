package Heroes;

public class Paladin extends BaseHero {
    // Constructor
    public Paladin(String name, int experience, int money, int mana, int strength, int agility, int dexterity) {
        super(name, experience, money, mana, strength, agility, dexterity);

        // Favored Attributes
        this.strength = (int) (strength * 1.05);
        this.dexterity = (int) (dexterity * 1.05);
    }
    
}
