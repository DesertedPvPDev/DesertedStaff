package codes.matthewp.desertedstaff.cmd.chat;

import codes.matthewp.desertedstaff.DesertedStaff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class QuickMessageCmd implements CommandExecutor {

    private DesertedStaff staff;

    public QuickMessageCmd(DesertedStaff staff) {
        this.staff = staff;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(s.equalsIgnoreCase("s")) {
            if(commandSender instanceof Player) {
                Player p = (Player) commandSender;
                if(p.hasPermission("desertedpvp.scquickmsg")){
                    StringBuilder builder = new StringBuilder();

                    for(String part : strings) {
                        builder.append(part + " ");
                    }

                    String staffMessage = color(staff.getConfigData().getConfig().getString("staffFormat"));
                    staffMessage = staffMessage.replaceAll("%PLAYER%", p.getName()).replaceAll("%MSG%", builder.toString());
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (player.hasPermission("desertedpvp.seestaffchat")) {
                            player.sendMessage(staffMessage);
                        }
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
