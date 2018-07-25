package codes.matthewp.desertedstaff.listeners;

import codes.matthewp.desertedstaff.DesertedStaff;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener  implements Listener {

    private DesertedStaff staff;

    public BlockPlaceListener(DesertedStaff staff) {
        this.staff = staff;
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (!staff.getDataManager().getBuildMode().contains(e.getPlayer())) {
            e.setCancelled(true);
        }
    }
}
