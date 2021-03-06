package codes.matthewp.desertedstaff.data;

import codes.matthewp.desertedcore.database.Database;
import codes.matthewp.desertedcore.database.DatabaseAccess;
import codes.matthewp.desertedstaff.DesertedStaff;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.*;

public class StaffDataAccess extends DatabaseAccess {

    private StaffDataAccess ins;

    private DesertedStaff staff;

    public StaffDataAccess(Database database, DesertedStaff staff) {
        super(database);
        this.staff = staff;
        ins = this;
    }

    public BanData getBan(String uuid) {
        String sqlQuery = "SELECT * FROM `user_bans` WHERE uuid = ?";

        try (Connection con = db.getConnection(this);
             PreparedStatement statement = con.prepareStatement(sqlQuery);
        ) {
            statement.setString(1, uuid);
            ResultSet set = statement.executeQuery();
            if (!set.isBeforeFirst()) {
                return null;
            } else {
                while (set.next()) {
                    return new BanData(set.getString("uuid"),
                            set.getString("staff"),
                            set.getString("reason"),
                            set.getString("start"),
                            set.getString("end"),
                            set.getString("ip"),
                            set.getString("appeal"),
                            set.getString("perm"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void unbanPlayer(String uuid) {
        String unban = "DELETE FROM `user_bans` WHERE `user_bans`.`uuid` = ?";
        try {
            Connection con = db.getConnection(this);
            PreparedStatement statement = con.prepareStatement(unban);
            statement.setString(1, uuid);
            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isUUIDBanned(String uuid) {
        String sqlQuery = "SELECT * FROM `user_bans` WHERE uuid = ?";
        try {
            Connection con = db.getConnection(this);
            PreparedStatement statement = con.prepareStatement(sqlQuery);
            statement.setString(1, uuid);
            ResultSet set = statement.executeQuery();

            if (!set.isBeforeFirst()) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean isBanned(Player p) {
        String sqlQuery = "SELECT * FROM `user_bans` WHERE uuid = ?";
        try {
            Connection con = db.getConnection(this);
            PreparedStatement statement = con.prepareStatement(sqlQuery);
            statement.setString(1, p.getUniqueId().toString());
            ResultSet set = statement.executeQuery();

            if (!set.isBeforeFirst()) {
                return false;
            } else {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }


    public void banPlayer(Player p, String reason, String staff, String appeal) {

        String ban = "INSERT INTO `user_bans` (`uuid`, `reason`, `start`, `end`, `staff`, `appeal`, `ip`, `perm`) VALUES" +
                " (?, ?, ?, ?, ?, ?, ?, 'true')";

        Bukkit.getScheduler().scheduleSyncDelayedTask(this.staff, new Runnable() {
            @Override
            public void run() {

                try {
                    Connection con = db.getConnection(ins);
                    PreparedStatement statement = con.prepareStatement(ban);
                    statement.setString(1, p.getUniqueId().toString());
                    statement.setString(2, reason);
                    statement.setString(3, String.valueOf(System.currentTimeMillis()));
                    statement.setString(4, String.valueOf(System.currentTimeMillis()));
                    statement.setString(5, staff);
                    statement.setString(6, appeal);
                    statement.setString(7, "null");

                    statement.executeUpdate();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }, 20L);
    }

    @Override
    public void loadTables() {
        setHasLoaded(true);

        //String bans = "CREATE TABLE IF NOT EXISTS `user_bans` ( `uuid` VARCHAR(255) CHARACTER SET latin7 COLLATE latin7_bin, `reason` VARCHAR(255) CHARACTER SET latin7 COLLATE latin7_bin, `start` VARCHAR(255) CHARACTER SET latin7 COLLATE latin7_bin, `end` VARCHAR(255) CHARACTER SET latin7 COLLATE latin7_bin, `staff` VARCHAR(255) CHARACTER SET latin7 COLLATE latin7_bin, `appeal` VARCHAR(255) CHARACTER SET latin7 COLLATE latin7_bin, `ip` VARCHAR(255) CHARACTER SET latin7 COLLATE latin7_bin, `perm` VARCHAR(255) CHARACTER SET latin7 COLLATE latin7_bin, PRIMARY KEY (`uuid`) ) ENGINE = InnoDB;";
        String bans = "CREATE TABLE IF NOT EXISTS `user_bans` ( `uuid` VARCHAR(191), `reason` VARCHAR(191), `start` VARCHAR(191), `end` VARCHAR(191), `staff` VARCHAR(191), `appeal` VARCHAR(191), `ip` VARCHAR(191), `perm` VARCHAR(191), PRIMARY KEY (`uuid`) ) ENGINE = InnoDB;";
        String blacklist = "";
        String mutes = "";

        try {
            Connection conn = db.getConnection(this);

            Statement bansStmt = conn.createStatement();
            bansStmt.executeUpdate(bans);

            //  Statement blackListStmt = conn.createStatement();
            //  blackListStmt.executeUpdate(blacklist);

            //   Statement muteStmt = conn.createStatement();
            //   muteStmt.executeUpdate(mutes);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
