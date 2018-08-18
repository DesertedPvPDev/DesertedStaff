package codes.matthewp.desertedstaff;

import codes.matthewp.desertedcore.DesertedCore;
import codes.matthewp.desertedcore.database.Database;
import codes.matthewp.desertedstaff.cmd.ban.BanCmd;
import codes.matthewp.desertedstaff.cmd.ban.UnbanCmd;
import codes.matthewp.desertedstaff.cmd.chat.QuickMessageCmd;
import codes.matthewp.desertedstaff.cmd.chat.StaffChatCmd;
import codes.matthewp.desertedstaff.cmd.general.PunishmentCmd;
import codes.matthewp.desertedstaff.cmd.general.StaffOnlineCmd;
import codes.matthewp.desertedstaff.cmd.mode.BuildModeCmd;
import codes.matthewp.desertedstaff.cmd.mode.StaffModeCmd;
import codes.matthewp.desertedstaff.data.DataManager;
import codes.matthewp.desertedstaff.data.StaffDataAccess;
import codes.matthewp.desertedstaff.file.ConfigData;
import codes.matthewp.desertedstaff.listeners.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class DesertedStaff extends JavaPlugin {

    private Logger logger = Logger.getLogger("DesertedStaff");

    private DataManager dataManager;
    private ConfigData configData;

    public String currentConfigVersion = "0.0.3";

    private boolean buildModeEnable = true;

    private StaffDataAccess dataAccess;

    @Override
    public void onEnable() {
        dataManager = new DataManager();
        configData = new ConfigData(this);
        buildModeEnable = getConfigData().getConfig().getBoolean("enableBuildMode");
        dataAccess = new StaffDataAccess(DesertedCore.getCore().getDB(), this);
        registerCommands();
        registerListeners();

        logger.info("DesertedStaff enabled.");
    }

    @Override
    public void onDisable() {
        logger.info("DesertedStaff disabled");
    }

    private void registerListeners() {
        if (buildModeEnable) {
            getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
            getServer().getPluginManager().registerEvents(new BlockPlaceListener(this), this);
            getServer().getPluginManager().registerEvents(new CropTrampleListener(this), this);
            getServer().getPluginManager().registerEvents(new ChatEvent(this), this);
        }
      //  getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
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
        getCommand("staffchat").setExecutor(new StaffChatCmd(this));
        getCommand("s").setExecutor(new QuickMessageCmd(this));
        //getCommand("ban").setExecutor(new BanCmd(this));
       // getCommand("unban").setExecutor(new UnbanCmd(this));
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

    public StaffDataAccess getDataAccess() {
        return dataAccess;
    }
}
