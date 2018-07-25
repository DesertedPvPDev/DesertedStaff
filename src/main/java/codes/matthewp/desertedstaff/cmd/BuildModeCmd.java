package codes.matthewp.desertedstaff.cmd;

import codes.matthewp.desertedstaff.DesertedStaff;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BuildModeCmd implements CommandExecutor {

    private DesertedStaff staff;

    public BuildModeCmd(DesertedStaff staff) {
        this.staff = staff;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("buildmode")) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if (p.hasPermission("desertedstaff.buildmode")) {
                    if (staff.getDataManager().getBuildMode().contains(p)) {
                        p.sendMessage(color(staff.getConfigData().getConfig().getString("buildOff")));
                        staff.getDataManager().removeBuildMode(p);
                        return true;
                    } else {
                        p.sendMessage(color(staff.getConfigData().getConfig().getString("buildOn")));
                        staff.getDataManager().addBuildMode(p);
                        return true;
                    }
                } else {
                    commandSender.sendMessage(color(staff.getConfigData().getConfig().getString("noPermission")));
                    return false;
                }
            } else {
                commandSender.sendMessage(color(staff.getConfigData().getConfig().getString("notPlayer")));
                return false;
            }
        }
        return false;
    }

    private String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
