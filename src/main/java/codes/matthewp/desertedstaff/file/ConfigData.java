package codes.matthewp.desertedstaff.file;

import codes.matthewp.desertedstaff.DesertedStaff;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigData {

    private File configFile;
    private FileConfiguration config;
    private DesertedStaff staff;

    public ConfigData(DesertedStaff staff) {
        this.staff = staff;
        load();
    }

    private void load() {
        if(!staff.getDataFolder().exists()) {
            if(staff.getDataFolder().mkdir()) {
                staff.getLoggerImpl().info("Generated data folder.");
            }
        }

        configFile = new File(staff.getDataFolder() + File.separator + "config.yml");
        if(!configFile.exists()) {
            staff.saveResource("config.yml", false);
            staff.getLoggerImpl().info("Generated config file.");
        }

        config = YamlConfiguration.loadConfiguration(configFile);

        checkForNewConfig();
    }

    private void checkForNewConfig() {
        if(!getConfig().getString("configVersion").equals(staff.currentConfigVersion)) {
            staff.getLoggerImpl().warning("Config version does not match plugin config version...");
            configFile.renameTo(new File(staff.getDataFolder() + File.separator + "config-old.yml" ));
            load();
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }
}
