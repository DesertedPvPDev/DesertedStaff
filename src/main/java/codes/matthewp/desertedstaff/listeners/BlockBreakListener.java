package codes.matthewp.desertedstaff.listeners;

import codes.matthewp.desertedstaff.DesertedStaff;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    private DesertedStaff staff;

    public BlockBreakListener(DesertedStaff staff) {
        this.staff = staff;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        if (!staff.getDataManager().getBuildMode().contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
}
