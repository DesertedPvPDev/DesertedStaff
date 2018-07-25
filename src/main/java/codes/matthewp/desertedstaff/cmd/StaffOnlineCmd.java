package codes.matthewp.desertedstaff.cmd;

import codes.matthewp.desertedstaff.DesertedStaff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffOnlineCmd implements CommandExecutor {

    private DesertedStaff staff;

    public StaffOnlineCmd(DesertedStaff staff) {
        this.staff = staff;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("staff")) {
            if (commandSender.hasPermission("desertedstaff.staffonline")) {

                String msg = color(staff.getConfigData().getConfig().getString("staffMsg"));

                String staffReplace = "";

                for(Player p : Bukkit.getOnlinePlayers()) {
                    if(p.hasPermission(staff.getConfigData().getConfig().getString("staffPerm")))
                    if (staffReplace.equals("")) {
                        staffReplace = staffReplace + p.getName();
                    } else {
                        staffReplace = staffReplace + ", " + p.getName();
                    }
                }

                msg = msg.replaceAll("%STAFF%", staffReplace);
                commandSender.sendMessage(msg);
                return true;
            } else {
                commandSender.sendMessage(color(staff.getConfigData().getConfig().getString("noPermission")));
                return false;
            }
        }
        return false;
    }

    private String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
