import java.util.Properties; 
import java.io.InputStream;    
import java.io.IOException;  

// Fatma_Zehra_Aktas_150124016
// Purpose: This class loads game settings from config.txt and provides global access to them.

public class ConfigLoader {


    private Properties properties;
    private static ConfigLoader instance;

    
    // Private constructor prevents creating objects externally
    private ConfigLoader() {

        properties = new Properties();

        loadConfig();
    }

    
 
    // Provides access to the same ConfigLoader object everywhere
    public static ConfigLoader getInstance() {

        if (instance == null) {
            instance = new ConfigLoader();
        }

        return instance;
    }

    
    // Loads values from config.txt
    private void loadConfig() {

        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.txt")) {

            if (input == null) {
                System.out.println("Error: config.txt file not found!");
                return;
            }
            properties.load(input);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    
    // Generic method for getting integer values from config file
    public int getInt(String key) {
    	
        String value = properties.getProperty(key, "0");
        String cleanValue = value.trim();

        // Convert String to int and return it
        return Integer.parseInt(cleanValue);
    }

    
    // Returns maximum health value
    public int getMaxHealth() {
        return getInt("maximum_health");
    }

    
    // Returns maximum vacuum value
    public int getMaxVacuum() {
        return getInt("maximum_vacuum");
    }

    
    // Returns damage value caused by entities
    public int getEntityDamage() {
        return getInt("entity_damage");
    }

    
    // Returns playable area width for a given level
    public int getWidth(int level) {
        return getInt("level_" + level + "_playable_area_width");
    }

    
    // Returns playable area height for a given level
    public int getHeight(int level) {
        return getInt("level_" + level + "_playable_area_height");
    }

    
    // Returns time limit for a level
    public int getLevelTime(int level) {
        return getInt("level_" + level + "_time");
    }

    
    // Returns ghost count for a level
    public int getGhostCount(int level) {
        return getInt("level_" + level + "_ghosts");
    }

    
    // Returns ripper count for a level
    public int getRipperCount(int level) {
        return getInt("level_" + level + "_rippers");
    }

    
    // Returns wisp count for a level
    public int getWispCount(int level) {
        return getInt("level_" + level + "_wisps");
    }

    
    // Returns health increase amount from health token
    public int getHealthTokenIncrease() {
        return getInt("health_token_increase");
    }

    
    // Returns vacuum increase amount from vacuum token
    public int getVacuumTokenIncrease() {
        return getInt("vacuum_token_increase");
    }

    
    // Returns eye token effect duration
    public int getEyeTokenDuration() {
        return getInt("eye_token_duration");
    }

    
    // Returns vacuum decrease amount
    public int getVacuumDecrease() {
        return getInt("vacuum_decrease");
    }

    
    // Returns vacuum increase amount
    public int getVacuumIncrease() {
        return getInt("vacuum_increase");
    }

}