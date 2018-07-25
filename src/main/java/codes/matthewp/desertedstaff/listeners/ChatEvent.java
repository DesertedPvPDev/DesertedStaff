package codes.matthewp.desertedstaff.listeners;

import codes.matthewp.desertedstaff.DesertedStaff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatEvent implements Listener {

    private DesertedStaff staff;

    public ChatEvent(DesertedStaff staff) {
        this.staff = staff;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        if (staff.getDataManager().isStaffChatOn(e.getPlayer())) {
            e.setCancelled(true);
            String staffMessage = color(staff.getConfigData().getConfig().getString("staffFormat"));
            staffMessage = staffMessage.replaceAll("%PLAYER%", e.getPlayer().getName()).replaceAll("%MSG%", e.getMessage());
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPermission("desertedpvp.seestaffchat")) {
                    p.sendMessage(staffMessage);
                }
            }
        }
    }

    private String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
