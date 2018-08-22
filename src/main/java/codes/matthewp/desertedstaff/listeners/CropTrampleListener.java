package codes.matthewp.desertedstaff.listeners;

import codes.matthewp.desertedstaff.DesertedStaff;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class CropTrampleListener implements Listener {

    private DesertedStaff staff;

    public CropTrampleListener(DesertedStaff staff) {
        this.staff = staff;
    }

    @EventHandler
    public void onTrample(PlayerInteractEvent e) {
        if (e.getAction() == Action.PHYSICAL) {
            if(e.getClickedBlock().getType() == Material.CROPS) {
                if (!staff.getDataManager().getBuildMode().contains(e.getPlayer())) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
