package org.apache.struts.service;


import org.apache.struts.model.Role;


/**
 * Created by PC-Alyaksei on 19.03.2016.
 */
public class RoleService extends GenericService<Role, Integer> {

    private static final Role ADMIN_ROLE = new RoleService().getRoleByName("admin");
    private static final Role USER_ROLE  = new RoleService().getRoleByName("user");



    public RoleService() {
        super(Role.class);
    }



    private Role getRoleByName(String name) {
        return getDao().getUniqueByStringField("name", name);
    }



    public static Role getAdminRole() {
        return ADMIN_ROLE;
    }

    public static Role getUserRole() {
        return USER_ROLE;
    }


}
