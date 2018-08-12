package codes.matthewp.desertedstaff.cmd.general;

import codes.matthewp.desertedstaff.DesertedStaff;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.stream.Collectors;

public class PunishmentCmd implements CommandExecutor {

    private DesertedStaff staff;

    public PunishmentCmd(DesertedStaff staff) {
        this.staff = staff;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("punishments")) {
            if (commandSender.hasPermission("desertedstaff.punishments")) {
                for(String str : colorList(staff.getConfigData().getConfig().getStringList("punishments"))) {
                    commandSender.sendMessage(str);
                }
                return true;
            } else {
                commandSender.sendMessage(color(staff.getConfigData().getConfig().getString("noPermission")));
                return false;
            }
        }
        return false;
    }

    private List<String> colorList(List<String> list) {
        return list.stream().map(s -> color(s)).collect(Collectors.toList());
    }

    private String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
