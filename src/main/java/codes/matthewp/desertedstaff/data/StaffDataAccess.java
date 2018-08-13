package codes.matthewp.desertedstaff.data;

import codes.matthewp.desertedcore.database.Database;
import codes.matthewp.desertedcore.database.DatabaseAccess;

public class StaffDataAccess extends DatabaseAccess {

    private StaffDataAccess ins;

    public StaffDataAccess(Database database) {
        super(database);
        ins = this;
    }

    @Override
    public void loadTables() {
        setHasLoaded(true);
    }
}
