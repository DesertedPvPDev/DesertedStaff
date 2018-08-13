package codes.matthewp.desertedstaff.cmd.ban;

import codes.matthewp.desertedstaff.DesertedStaff;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class TempBanCmd implements CommandExecutor {

    private DesertedStaff staff;

    public TempBanCmd(DesertedStaff staff) {
        this.staff = staff;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(s.equalsIgnoreCase("")) {
            if(commandSender.hasPermission("")) {

            }
        }
        return false;
    }}
