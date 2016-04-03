package code.dao.hibernatedao;

import code.dao.daointerface.IRoleDao;
import code.model.User;
import code.model.Role;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public class RoleHibernateDao  extends GenericHibernateDao<Role, Integer> implements IRoleDao {
    public RoleHibernateDao() {
        super(Role.class);
    }

    public List<User> getAllEntityWithRole(Role role) {
        Session session = getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("role", role));
        return criteria.list();
    }

    public List<User> getAllEntityWithRoleName(String roleName) {
        Session session = getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("role.name", roleName));
        return criteria.list();
    }
}
