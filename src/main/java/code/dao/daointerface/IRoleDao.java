package code.dao.daointerface;

import code.dao.IDao;
import code.model.Role;
import code.model.User;

import java.util.List;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public interface IRoleDao extends IDao<Role, Integer> {
    public List<User> getAllEntityWithRole(Role role);
    public List<User> getAllEntityWithRoleName(String roleName);
}
