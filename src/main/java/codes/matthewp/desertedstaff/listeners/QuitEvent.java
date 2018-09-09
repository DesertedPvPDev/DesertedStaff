package codes.matthewp.desertedstaff.listeners;

import codes.matthewp.desertedstaff.DesertedStaff;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Mario on 9/9/2018.
 */
public class QuitEvent implements Listener{
    private DesertedStaff staff;

    public QuitEvent(DesertedStaff staff) {
        this.staff = staff;
    }


    @EventHandler
    public void onQuitEvent(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        for(Player staffM: staff.getDataManager().getStaffMode()) {
            p.showPlayer(staffM);
        }
    }
}
