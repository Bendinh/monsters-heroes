package Monsters;

public class Spirit extends BaseMonster {
    // Constructor
    public Spirit(String name, int level, int damage, int defense, int dodgeChance) {
        super(name, level, damage, defense, dodgeChance);

        // Favored Attributes
        this.dodgeChance = (int) (dodgeChance * 1.05);
    }
    
}
