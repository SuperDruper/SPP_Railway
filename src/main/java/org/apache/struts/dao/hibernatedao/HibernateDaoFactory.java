package org.apache.struts.dao.hibernatedao;

import org.apache.struts.dao.AbstractDaoFactory;
import org.apache.struts.dao.IDao;
import org.apache.struts.dao.hibernatedao.GenericHibernateDao;
import org.apache.struts.dao.hibernatedao.UserHibernateDao;
import org.apache.struts.model.User;

import java.io.Serializable;

/**
 * Created by PC-Alyaksei on 18.03.2016.
 */
public class HibernateDaoFactory extends AbstractDaoFactory {

    @Override
    public IDao getDao(Class<?> entityClass) {
        return getParamDao(entityClass);
    }

    // If for some entity there is dao class, that implements IDao
    // interface and it is not a GenericHibernateDao,
    // then this method must return not GenericHibernate Dao,
    // but instance of class, that implements IDao interface!!!
    @Override
    public <T, PK extends Serializable> IDao<T, PK> getParamDao(Class<T> entityClass) {
        if (entityClass.equals(User.class)) {
            return (IDao<T, PK>) new UserHibernateDao();
        }

        return new GenericHibernateDao<T, PK>(entityClass);
    }
}
