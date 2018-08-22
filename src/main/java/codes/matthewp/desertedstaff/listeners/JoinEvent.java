package codes.matthewp.desertedstaff.listeners;

import codes.matthewp.desertedstaff.DesertedStaff;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    private DesertedStaff staff;

    public JoinEvent(DesertedStaff staff) {
        this.staff = staff;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        if(!e.getPlayer().hasPermission("desertedstaff.staffmode")) {
            for(Player p : staff.getDataManager().getStaffMode()) {
                e.getPlayer().hidePlayer(p);
            }
        }
    }
}
