package org.apache.struts.dao.daointerface;

import org.apache.struts.dao.IDao;
import org.apache.struts.model.Role;
import org.apache.struts.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public interface IRoleDao extends IDao<Role, Integer> {
    public List<User> getAllEntityWithRole(Role role);
    public List<User> getAllEntityWithRoleName(String roleName);
}
