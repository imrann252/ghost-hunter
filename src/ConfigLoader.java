import java.util.Properties; 
import java.io.InputStream;    
import java.io.IOException;  

// Fatma_Zehra_Aktas_150124016
// Purpose: This class loads game settings from config.txt and provides global access to them.

public class ConfigLoader {

    // Stores all configuration values
    private Properties properties;


    // Only one ConfigLoader object will exist
    private static ConfigLoader instance;

    
    // Private constructor prevents creating objects externally
    private ConfigLoader() {

        // Create Properties object
        properties = new Properties();

        // Load configuration file
        loadConfig();
    }

    
 
    // Provides access to the same ConfigLoader object everywhere
    public static ConfigLoader getInstance() {

        // Create instance only if it does not exist
        if (instance == null) {
            instance = new ConfigLoader();
        }

        // Return existing instance
        return instance;
    }

    
    // Loads values from config.txt
    private void loadConfig() {

        // try-with-resources automatically closes input stream
        try (InputStream input =
                 getClass().getClassLoader().getResourceAsStream("config.txt")) {

            // If file cannot be found
            if (input == null) {
                System.out.println("Error: config.txt file not found!");
                return;
            }

            // Load all key=value pairs into properties object
            properties.load(input);

        } catch (IOException ex) {

            // Print error if file reading fails
            ex.printStackTrace();
        }
    }

    
    // Generic method for getting integer values from config file
    public int getInt(String key) {

        // Get value using key
        // Default value is "0" if key does not exist
        String value = properties.getProperty(key, "0");

        // Remove extra spaces
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
    // Example: level_1_playable_area_width
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