import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class ConfigLoader {
    private Properties properties;
    private static ConfigLoader instance;

    private ConfigLoader() {
        properties = new Properties();
        loadConfig();
    }

    // Singleton pattern: Her yerden aynı config'e ulaşmak için
    public static ConfigLoader getInstance() {
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }

    private void loadConfig() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.txt")) {
            if (input == null) {
                System.out.println("Hata: config.txt dosyası bulunamadı!");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public int getInt(String key) {
        String value = properties.getProperty(key, "0"); 
        String cleanValue = value.trim();               
        return Integer.parseInt(cleanValue);            
    }


    public int getMaxHealth() { 
    	return getInt("maximum_health"); 
    	} 
    
    
    public int getMaxVacuum() { 
    	return getInt("maximum_vacuum"); 
    	} 
    
    public int getEntityDamage() { 
    	return getInt("entity_damage"); 
    } 
    
   

    public int getWidth(int level) {
        return getInt("level_" + level + "_playable_area_width"); 
    }
    
    public int getHeight(int level) {
        return getInt("level_" + level + "_playable_area_height"); 
    }
    
    public int getLevelTime(int level) {
        return getInt("level_" + level + "_time"); 
    }
    
    public int getGhostCount(int level) {
        return getInt("level_" + level + "_ghosts"); 
    }

    public int getRipperCount(int level) {
        return getInt("level_" + level + "_rippers"); 
    }
    
    public int getWispCount(int level) {
        return getInt("level_" + level + "_wisps"); 
    }
    

    public int getHealthTokenIncrease() { 
    	return getInt("health_token_increase");
    } 
    
    public int getVacuumTokenIncrease() { 
    	return getInt("vacuum_token_increase");
    } 
    
    public int getEyeTokenDuration() { 
    	return getInt("eye_token_duration");
    } 
    public int getVacuumDecrease() {
    	return getInt("vacuum_decrease");
    }
    public int getVacuumIncrease() {
    	return getInt("vacuum_increase");
    }
    
}