package Heroes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class HeroGenerator {
    private static final String WARRIOR_FILE = "Data/Warriors.txt";
    private static final String PALADIN_FILE = "Data/Paladins.txt";
    private static final String SORCERER_FILE = "Data/Sorcerers.txt";

    private static final int WARRIOR_INDEX = 0;
    private static final int PALADIN_INDEX = 1;
    private static final int SORCERER_INDEX = 2;

    private ArrayList<Warrior> warriors;
    private ArrayList<Paladin> paladins;
    private ArrayList<Sorcerer> sorcerers;

    public HeroGenerator() {
        this.warriors = new ArrayList<Warrior>();
        this.paladins = new ArrayList<Paladin>();
        this.sorcerers = new ArrayList<Sorcerer>();

        loadHeroesFromTxt(WARRIOR_FILE, WARRIOR_INDEX);
        loadHeroesFromTxt(PALADIN_FILE, PALADIN_INDEX);
        loadHeroesFromTxt(SORCERER_FILE, SORCERER_INDEX);
    }

    private void loadHeroesFromTxt(String fileName, int heroIndex) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine(); // Skip the header line
            String line;
            while ((line = reader.readLine()) != null) { // Read each line of the file
                String[] parts = line.split("\\s+"); // Split the line into parts
                if (parts.length <  7) {
                    continue; // Skip the line if it doesn't have enough stats for the hero
                }
                try {
                    String name = parts[0]; // Get the name of the hero
                    int mana = Integer.parseInt(parts[1]); // Get the mana of the hero
                    int strength = Integer.parseInt(parts[2]); // Get the strength of the hero
                    int agility = Integer.parseInt(parts[3]); // Get the agility of the hero
                    int dexterity = Integer.parseInt(parts[4]); // Get the dexterity of the hero
                    int money = Integer.parseInt(parts[5]); // Get the money of the hero
                    int experience = Integer.parseInt(parts[6]); // Get the experience of the hero
                    switch (heroIndex) { // Add the hero to the appropriate list
                        case WARRIOR_INDEX:
                            warriors.add(new Warrior(name, experience, money, mana, strength, agility, dexterity)); // Add the warrior to the list
                            break;
                        case PALADIN_INDEX:
                            paladins.add(new Paladin(name, experience, money, mana, strength, agility, dexterity)); // Add the paladin to the list
                            break;
                        case SORCERER_INDEX:
                            sorcerers.add(new Sorcerer(name, experience, money, mana, strength, agility, dexterity)); // Add the sorcerer to the list
                            break;
                        default:
                            break; // Skip the line if the hero index is not valid
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

    public Warrior getRandomWarrior() {
        return warriors.get(new Random().nextInt(warriors.size()));
    }

    public Paladin getRandomPaladin() {
        return paladins.get(new Random().nextInt(paladins.size()));
    }

    public Sorcerer getRandomSorcerer() {
        return sorcerers.get(new Random().nextInt(sorcerers.size()));
    }
}
