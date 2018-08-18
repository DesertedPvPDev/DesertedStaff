package codes.matthewp.desertedstaff.listeners;

import codes.matthewp.desertedcore.string.ColorHelper;
import codes.matthewp.desertedstaff.DesertedStaff;
import codes.matthewp.desertedstaff.data.BanData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class JoinEvent implements Listener {

    private DesertedStaff staff;

    public JoinEvent(DesertedStaff staff) {
        this.staff = staff;
    }

    @EventHandler
    public void onJoin(AsyncPlayerPreLoginEvent e) {
        if (staff.getDataAccess().isUUIDBanned(e.getUniqueId().toString())) {
            BanData ban = staff.getDataAccess().getBan(e.getUniqueId().toString());
            StringBuilder kickBuilder = new StringBuilder();
            for (String bit : staff.getConfigData().getConfig().getStringList("banFormat")) {
                bit = bit.replaceAll("%REASON%", ban.getReason());
                bit = bit.replaceAll("%STAFF%", ban.getStaff());
                if (ban.getPerm().equalsIgnoreCase("true")) {
                    bit = bit.replaceAll("%END%", ColorHelper.color(staff.getConfigData().getConfig().getString("banNeverEnd")));
                }
                if (ban.getAppeal().equalsIgnoreCase("true")) {
                    bit = bit.replaceAll("%APPEAL%", ColorHelper.color(staff.getConfigData().getConfig().getString("yesAppeal")));
                } else {
                    bit = bit.replaceAll("%APPEAL%", ColorHelper.color(staff.getConfigData().getConfig().getString("noAppeal")));
                }
                kickBuilder.append(ColorHelper.color(bit + "\n"));
            }

            e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_BANNED, kickBuilder.toString());

        }
    }
}
