package Monsters;

public class Exoskeleton extends BaseMonster {
    // Constructor
    public Exoskeleton(String name, int level, int damage, int defense, int dodgeChance) {
        super(name, level, damage, defense, dodgeChance);

        // Favored Attributes
        this.defense = (int) (defense * 1.05);
    }
}
