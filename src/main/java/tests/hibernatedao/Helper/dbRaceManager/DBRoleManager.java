package tests.hibernatedao.Helper.dbRaceManager;

import code.model.Role;
import tests.hibernatedao.Helper.DatabaseGeneratorHelper;

/**
 * Created by dzmitry.antonenka oon 03.04.2016.
 */
public class DBRoleManager {
    public DatabaseGeneratorHelper databaseGeneratorHelper;
    Role role;

    public Role getRole() {
        if(role == null) {
            role = databaseGeneratorHelper.generateTestRole();
        }
        return role;
    }

    public Object getRoleId() {
        return new Integer(role.getId());
    }

    public void deleteRole() {
        databaseGeneratorHelper.deleteRole(role);
        role = null;
    }

    public void updateRole() {
        role.setName("Test 2!");
    }
}
