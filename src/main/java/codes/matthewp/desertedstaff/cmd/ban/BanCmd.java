package codes.matthewp.desertedstaff.cmd.ban;

import codes.matthewp.desertedcore.string.ColorHelper;
import codes.matthewp.desertedstaff.DesertedStaff;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BanCmd implements CommandExecutor {

    private DesertedStaff staff;

    public BanCmd(DesertedStaff staff) {
        this.staff = staff;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (s.equalsIgnoreCase("ban")) {
            if (commandSender.hasPermission("desertedstaff.ban")) {
                if (strings.length == 0) {
                    commandSender.sendMessage(ColorHelper.color(staff.getConfigData().getConfig().getString("banUsage")));
                    return false;
                }

                if (Bukkit.getPlayer(strings[0]) == null) {
                    commandSender.sendMessage(ColorHelper.color(staff.getConfigData().getConfig().getString("notOnline")));
                    return false;
                }

                Player toBeBanned = Bukkit.getPlayer(strings[0]);

                if(staff.getDataAccess().isBanned(toBeBanned)) {
                    commandSender.sendMessage(ColorHelper.color(staff.getConfigData().getConfig().getString("pBanned")));
                    return false;
                }

                StringBuilder builder = new StringBuilder();
                String reason;

                String appeal = "false";

                if (strings.length == 1) {
                    reason = staff.getConfigData().getConfig().getString("defaultBan");
                } else {
                    for (int i = 1; i < strings.length; i++) {
                        if (!strings[i].equalsIgnoreCase("#appeal")) {
                            builder.append(strings[i] + " ");
                        } else {
                            appeal = "true";
                        }

                    }
                    reason = builder.toString();
                }

                staff.getDataAccess().banPlayer(toBeBanned, reason, commandSender.getName(), appeal);

                commandSender.sendMessage(ColorHelper.color(staff.getConfigData().getConfig().getString("bannedPlayer")
                        .replaceAll("%PLAYER%", toBeBanned.getName())));
                for(Player p : Bukkit.getOnlinePlayers()) {
                    if(p.hasPermission("desertedstaff.bannotif")) {
                        p.sendMessage(ColorHelper.color(staff.getConfigData().getConfig().getString("staffBan")
                                .replaceAll("%STAFF%", commandSender.getName()).replaceAll("%PLAYER%", toBeBanned.getName())));
                    }
                }

                StringBuilder kickBuilder = new StringBuilder();

                for(String bit : staff.getConfigData().getConfig().getStringList("banFormat")) {
                    bit = bit.replaceAll("%REASON%", reason);
                    bit = bit.replaceAll("%STAFF%", commandSender.getName());
                    bit = bit.replaceAll("%END%", ColorHelper.color(staff.getConfigData().getConfig().getString("banNeverEnd")));
                    if(appeal.equalsIgnoreCase("true")) {
                        bit = bit.replaceAll("%APPEAL%", ColorHelper.color(staff.getConfigData().getConfig().getString("yesAppeal")));
                    } else {
                        bit = bit.replaceAll("%APPEAL%", ColorHelper.color(staff.getConfigData().getConfig().getString("noAppeal")));
                    }
                    kickBuilder.append(ColorHelper.color(bit + "\n"));
                }

                toBeBanned.kickPlayer(kickBuilder.toString());
                return true;

            }
        }
        return false;
    }
}
