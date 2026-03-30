package Items;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import Items.Spells.BaseSpell;
import Items.Spells.FireSpell;
import Items.Spells.IceSpell;
import Items.Spells.LightningSpell;

public class ItemGenerator {
    private static final String ARMORY_FILE = "Data/Armory.txt";
    private static final String WEAPONRY_FILE = "Data/Weaponry.txt";
    private static final String POTIONS_FILE = "Data/Potions.txt";
    private static final String FIRE_SPELLS_FILE = "Data/FireSpells.txt";
    private static final String ICE_SPELLS_FILE = "Data/IceSpells.txt";
    private static final String LIGHTNING_SPELLS_FILE = "Data/LightningSpells.txt";

    private static final int ARMORY_INDEX = 0;
    private static final int WEAPONRY_INDEX = 1;
    private static final int POTIONS_INDEX = 2;
    private static final int FIRE_SPELLS_INDEX = 3;
    private static final int ICE_SPELLS_INDEX = 4;
    private static final int LIGHTNING_SPELLS_INDEX = 5;

    private ArrayList<Armory> armory;
    private ArrayList<Weaponry> weaponry;
    private ArrayList<Potions> potions;
    private ArrayList<BaseSpell> spells;

    public ItemGenerator() {
        this.armory = new ArrayList<Armory>();
        this.weaponry = new ArrayList<Weaponry>();
        this.potions = new ArrayList<Potions>();
        this.spells = new ArrayList<BaseSpell>();

        loadItemsFromTxt(ARMORY_FILE, ARMORY_INDEX);
        loadItemsFromTxt(WEAPONRY_FILE, WEAPONRY_INDEX);
        loadItemsFromTxt(POTIONS_FILE, POTIONS_INDEX);
        loadItemsFromTxt(FIRE_SPELLS_FILE, FIRE_SPELLS_INDEX);
        loadItemsFromTxt(ICE_SPELLS_FILE, ICE_SPELLS_INDEX);
        loadItemsFromTxt(LIGHTNING_SPELLS_FILE, LIGHTNING_SPELLS_INDEX);
    }

    private void loadItemsFromTxt(String fileName, int itemIndex) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            reader.readLine(); // Skip the header line
            String line;
            while ((line = reader.readLine()) != null) { // Read each line of the file
                String[] parts = line.split("\\s+"); // Split the line into parts
                try {
                    switch (itemIndex) {
                        case ARMORY_INDEX:
                            if (parts.length <  4) {
                                continue; // Skip the line if it doesn't have enough stats for the armor
                            }
                            armory.add(new Armory(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3])));
                            break;
                        case WEAPONRY_INDEX:
                            if (parts.length <  5) {
                                continue; // Skip the line if it doesn't have enough stats for the weapon
                            }
                            weaponry.add(new Weaponry(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
                            break;
                        case POTIONS_INDEX:
                            if (parts.length <  5) {
                                continue; // Skip the line if it doesn't have enough stats for the potion
                            }
                            potions.add(new Potions(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), parts[4]));
                            break;
                        case FIRE_SPELLS_INDEX:
                            if (parts.length <  5) {
                                continue; // Skip the line if it doesn't have enough stats for the spell
                            }
                            spells.add(new FireSpell(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
                            break;
                        case ICE_SPELLS_INDEX:
                            if (parts.length <  5) {
                                continue; // Skip the line if it doesn't have enough stats for the spell
                            }
                            spells.add(new IceSpell(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
                            break;
                        case LIGHTNING_SPELLS_INDEX:
                            if (parts.length <  5) {
                                continue; // Skip the line if it doesn't have enough stats for the spell
                            }
                            spells.add(new LightningSpell(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
                            break;
                        default:
                            break; // Skip the line if the item index is not valid
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

    // Generate random items for a specific shop
    public ArrayList<BaseItem> generateRandomItemsForShop() {
        ArrayList<BaseItem> randomItems = new ArrayList<BaseItem>();
        randomItems.addAll(getRandomArmors());
        randomItems.addAll(getRandomWeapons());
        randomItems.addAll(getRandomPotions());
        randomItems.addAll(getRandomSpells());
        return randomItems;
    }

    // Get 2 random armors
    public ArrayList<Armory> getRandomArmors() {
        ArrayList<Armory> randomArmors = new ArrayList<Armory>();
        for (int i = 0; i < 2; i++) {
            randomArmors.add(armory.get(new Random().nextInt(armory.size())));
        }
        return randomArmors;
    }

    // Get 2 random weapons
    public ArrayList<Weaponry> getRandomWeapons() {
        ArrayList<Weaponry> randomWeapons = new ArrayList<Weaponry>();
        for (int i = 0; i < 2; i++) {
            randomWeapons.add(weaponry.get(new Random().nextInt(weaponry.size())));
        }
        return randomWeapons;
    }

    // Get 2 random potions
    public ArrayList<Potions> getRandomPotions() {
        ArrayList<Potions> randomPotions = new ArrayList<Potions>();
        for (int i = 0; i < 2; i++) {
            randomPotions.add(potions.get(new Random().nextInt(potions.size())));
        }
        return randomPotions;
    }

    // Get 2 random spells
    public ArrayList<BaseSpell> getRandomSpells() {
        ArrayList<BaseSpell> randomSpells = new ArrayList<BaseSpell>();
        for (int i = 0; i < 2; i++) {
            randomSpells.add(spells.get(new Random().nextInt(spells.size())));
        }
        return randomSpells;
    }
}
