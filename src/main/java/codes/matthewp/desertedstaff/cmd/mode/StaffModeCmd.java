package codes.matthewp.desertedstaff.cmd.mode;

import codes.matthewp.desertedpvp.DesertedPvP;
import codes.matthewp.desertedstaff.DesertedStaff;
import codes.matthewp.desertedstaff.util.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

                        for(Player player : Bukkit.getOnlinePlayers()) {
                            if (!player.hasPermission("desertedstaff.staffmode")) {
                                player.showPlayer(p);
                            }
                        }

                        if(DesertedStaff.pvpEnabled) {
                            DesertedPvP.getInstace().getUserManager().getUser(p).restPlayer();
                        }

                        return true;
                    } else {
                        p.sendMessage(color(staff.getConfigData().getConfig().getString("staffOn")));
                        staff.getDataManager().addStaffMode(p);

                        p.setGameMode(GameMode.SURVIVAL);

                        p.setAllowFlight(true);
                        p.setFlying(true);
                        p.getInventory().clear();

                        for(Player player : Bukkit.getOnlinePlayers()) {
                            if(!player.hasPermission("desertedstaff.staffmode")) {
                                player.hidePlayer(p);
                            }
                        }

                        ItemStack tpCompass = new ItemFactory(Material.COMPASS)
                                .setName("&6Teleport compass")
                                .addLore("&2Use me to quickly teleport across the map.").addLore("&cCurrently under development")
                                .build();



                        p.getInventory().setItem(0, tpCompass);

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
