package Monsters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MonsterGenerator {
    private static final String DRAGON_FILE = "Data/Dragons.txt";
    private static final String SPIRIT_FILE = "Data/Spirits.txt";
    private static final String EXOSKELETON_FILE = "Data/Exoskeletons.txt";

    private static final int DRAGON_INDEX = 0;
    private static final int SPIRIT_INDEX = 1;
    private static final int EXOSKELETON_INDEX = 2;

    private ArrayList<BaseMonster> monsters;

    public MonsterGenerator() {
        this.monsters = new ArrayList<BaseMonster>();

        loadMonstersFromTxt(DRAGON_FILE, DRAGON_INDEX);
        loadMonstersFromTxt(SPIRIT_FILE, SPIRIT_INDEX);
        loadMonstersFromTxt(EXOSKELETON_FILE, EXOSKELETON_INDEX);
    }

    private void loadMonstersFromTxt(String fileName, int monsterIndex) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine(); // Skip the header line
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length < 5) {
                    continue;
                }
                try {
                    String name = parts[0];
                    int level = Integer.parseInt(parts[1]);
                    int damage = Integer.parseInt(parts[2]);
                    int defense = Integer.parseInt(parts[3]);
                    int dodgeChance = Integer.parseInt(parts[4]);

                    switch (monsterIndex) {
                        case DRAGON_INDEX:
                            this.monsters.add(new Dragon(name, level, damage, defense, dodgeChance));
                            break;
                        case SPIRIT_INDEX:
                            this.monsters.add(new Spirit(name, level, damage, defense, dodgeChance));
                            break;
                        case EXOSKELETON_INDEX:
                            this.monsters.add(new Exoskeleton(name, level, damage, defense, dodgeChance));
                            break;
                        default:
                            break;
                    }
                } catch (Exception e) {
                    System.out.println("Due to an error, skipping line in " + fileName + ": " + line);
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading " + fileName);
            e.printStackTrace();
        }
    }

    public ArrayList<BaseMonster> generateMonsters(int numberOfMonsters, int monsterLevel) {
        ArrayList<BaseMonster> monsters = new ArrayList<BaseMonster>();
        for (BaseMonster monster : this.monsters) {
            if (monsters.size() < numberOfMonsters) {
                if (monster.getLevel() == monsterLevel) {
                    monsters.add(monster);
                }
            } else {
                break;
            }
        }
        return monsters;
    }
}
