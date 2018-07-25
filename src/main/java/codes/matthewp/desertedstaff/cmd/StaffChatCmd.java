package codes.matthewp.desertedstaff.cmd;

import codes.matthewp.desertedstaff.DesertedStaff;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StaffChatCmd implements CommandExecutor {

    private DesertedStaff staff;

    public StaffChatCmd(DesertedStaff staff) {
        this.staff = staff;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("staffchat")) {
            if (commandSender.hasPermission("desertedpvp.staffchat")) {
                if(commandSender instanceof Player) {
                    Player p = (Player) commandSender;
                    if(staff.getDataManager().isStaffChatOn(p)) {
                        staff.getDataManager().removeStaffChat(p);
                        commandSender.sendMessage(color(staff.getConfigData().getConfig().getString("staffCOff")));
                        return true;
                    } else {
                        staff.getDataManager().addStaffChat(p);
                        commandSender.sendMessage(color(staff.getConfigData().getConfig().getString("staffCOn")));
                        return true;
                    }
                } else {
                    commandSender.sendMessage(color(staff.getConfigData().getConfig().getString("notPlayer")));
                    return false;
                }
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
