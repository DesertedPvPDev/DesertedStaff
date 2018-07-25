package codes.matthewp.desertedstaff.data;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private List<Player> buildMode = new ArrayList<>();
    private List<Player> staffMode = new ArrayList<>();
    private List<Player> staffChatToggled = new ArrayList<>();

    public List<Player> getBuildMode() {
        return buildMode;
    }

    public List<Player> getStaffMode() {
        return staffMode;
    }

    public void addBuildMode(Player player) {
        buildMode.add(player);
    }

    public void removeBuildMode(Player player) {
        buildMode.remove(player);
    }

    public void addStaffMode(Player player) {
        staffMode.add(player);
    }

    public void removeStaffMode(Player player) {
        staffMode.remove(player);
    }

    public void addStaffChat(Player player) {
        staffChatToggled.add(player);
    }

    public void removeStaffChat(Player player) {
        staffChatToggled.remove(player);
    }

    public boolean isStaffChatOn(Player p) {
        return staffChatToggled.contains(p);
    }
}
