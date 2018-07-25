package codes.matthewp.desertedstaff.data;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private List<Player> buildMode = new ArrayList<>();
    private List<Player> staffMode = new ArrayList<>();

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

}
