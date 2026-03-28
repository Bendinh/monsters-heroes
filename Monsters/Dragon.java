package Monsters;

public class Dragon extends BaseMonster {
    // Constructor
    public Dragon(String name, int level, int damage, int defense, int dodgeChance) {
        super(name, level, damage, defense, dodgeChance);

        // Favored Attributes
        this.damage = (int) (damage * 1.05);
    }
}
