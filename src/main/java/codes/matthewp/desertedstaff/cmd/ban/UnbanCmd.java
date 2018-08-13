package codes.matthewp.desertedstaff.cmd.ban;

import codes.matthewp.desertedcore.string.ColorHelper;
import codes.matthewp.desertedstaff.DesertedStaff;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.UUID;

public class UnbanCmd implements CommandExecutor {

    private DesertedStaff staff;

    public UnbanCmd(DesertedStaff staff) {
        this.staff = staff;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("unban")) {
            if (commandSender.hasPermission("desertedstaff.unban")) {
                if(strings.length == 0) {
                    commandSender.sendMessage(ColorHelper.color(staff.getConfigData().getConfig().getString("unbanUsage")));
                    return false;
                }

                OfflinePlayer op = Bukkit.getOfflinePlayer(strings[1]);
                if (op.hasPlayedBefore()) {
                    UUID uuid = op.getUniqueId();
                    if(staff.getDataAccess().isUUIDBanned(uuid.toString())) {
                        staff.getDataAccess().unbanPlayer(uuid.toString());
                        commandSender.sendMessage(ColorHelper.color(staff.getConfigData().getConfig().getString("unbannedPlayer")
                                .replaceAll("%PLAYER%", op.getName())));
                        return true;
                    } else {
                        commandSender.sendMessage(ColorHelper.color(staff.getConfigData().getConfig().getString("notBanned")));
                        return false;
                    }
                } else {
                    commandSender.sendMessage(ColorHelper.color(staff.getConfigData().getConfig().getString("notBanned")));
                    return false;
                }
            }
        }
        return false;
    }
}