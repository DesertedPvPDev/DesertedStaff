package codes.matthewp.desertedstaff.cmd;

import codes.matthewp.desertedstaff.DesertedStaff;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffModeCmd implements CommandExecutor {

    private DesertedStaff staff;

    public StaffModeCmd(DesertedStaff staff) {
        this.staff = staff;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("staffmode")) {
            if (commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if (p.hasPermission("desertedstaff.staffmode")) {
                    if (staff.getDataManager().getStaffMode().contains(p)) {
                        p.sendMessage(color(staff.getConfigData().getConfig().getString("staffOff")));
                        staff.getDataManager().removeStaffMode(p);
                        p.setGameMode(GameMode.SURVIVAL);
                        return true;
                    } else {
                        p.sendMessage(color(staff.getConfigData().getConfig().getString("staffOn")));
                        staff.getDataManager().addStaffMode(p);
                        p.setGameMode(GameMode.SPECTATOR);
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
