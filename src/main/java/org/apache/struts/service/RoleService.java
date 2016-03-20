package org.apache.struts.service;


import org.apache.struts.dao.AbstractDaoFactory;
import org.apache.struts.dao.daointerface.IRoleDao;
import org.apache.struts.dao.daointerface.IRouteDao;
import org.apache.struts.model.Role;
import org.apache.struts.model.Ticket;


/**
 * Created by PC-Alyaksei on 19.03.2016.
 */
public class RoleService extends GenericService<Role, Integer> {
    private static final Role ADMIN_ROLE = new RoleService().getRoleByName("admin");
    private static final Role USER_ROLE  = new RoleService().getRoleByName("user");

    private IRoleDao dao;

    public RoleService() {
        super(Role.class);
    }

    @Override
    public IRoleDao getDao() {
        if(dao == null) {
            dao = (IRoleDao) AbstractDaoFactory.getImplDao(Role.class);
        }

        return dao;
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
