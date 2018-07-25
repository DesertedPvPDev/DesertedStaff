package codes.matthewp.desertedstaff;

import codes.matthewp.desertedstaff.cmd.BuildModeCmd;
import codes.matthewp.desertedstaff.cmd.PunishmentCmd;
import codes.matthewp.desertedstaff.cmd.StaffModeCmd;
import codes.matthewp.desertedstaff.cmd.StaffOnlineCmd;
import codes.matthewp.desertedstaff.data.DataManager;
import codes.matthewp.desertedstaff.file.ConfigData;
import codes.matthewp.desertedstaff.listeners.BlockBreakListener;
import codes.matthewp.desertedstaff.listeners.BlockPlaceListener;
import codes.matthewp.desertedstaff.listeners.CropTrampleListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class DesertedStaff extends JavaPlugin {

    private Logger logger = Logger.getLogger("DesertedStaff");
    private DataManager dataManager;
    private ConfigData configData;
    public String currentConfigVersion = "0.0.1";
    private boolean buildModeEnable = true;

    @Override
    public void onEnable() {
        dataManager = new DataManager();
        configData = new ConfigData(this);
        buildModeEnable = getConfigData().getConfig().getBoolean("enableBuildMode");
        registerCommands();
        registerListeners();
        logger.info("DesertedStaff enabled.");
    }

    @Override
    public void onDisable() {
        logger.info("DesertedStaff disabled");
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public ConfigData getConfigData() {
        return configData;
    }

    public Logger getLoggerImpl() {
        return logger;
    }

    private void registerListeners() {
        if (buildModeEnable) {
            getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
            getServer().getPluginManager().registerEvents(new BlockPlaceListener(this), this);
            getServer().getPluginManager().registerEvents(new CropTrampleListener(this), this);
        }
    }

    private void registerCommands() {
        if(buildModeEnable) {
            getCommand("buildmode").setExecutor(new BuildModeCmd(this));
        } else {
            logger.warning("Build mode command will not be present. It is disabled in config.");
        }
        getCommand("staff").setExecutor(new StaffOnlineCmd(this));
        getCommand("staffmode").setExecutor(new StaffModeCmd(this));
        getCommand("punishments").setExecutor(new PunishmentCmd(this));
    }
}
