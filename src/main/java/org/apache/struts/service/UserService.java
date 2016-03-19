package org.apache.struts.service;

import org.apache.struts.dao.AbstractDaoFactory;
import org.apache.struts.dao.daointerface.IUserDao;
import org.apache.struts.dao.hibernatedao.UserHibernateDao;
import org.apache.struts.model.User;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
public class UserService extends GenericService<User, Integer> {

    // It isn't necessary to create IEntityDao (EntityDaoInterface) for every entity,
    // but if you're creating service (not using GenericService), then you have to
    // create special interface.
    // Ok, I'm not sure, but think yourself whether you're sure,
    // that you won't use entity special functions in future?!
    private IUserDao dao;


    public UserService() {
        super(User.class);
    }


    public User getUserByLogin(String login) {
        return getDao().getUserByLogin(login);
    }

    // In case of extending GenericService, you should override
    // getDao method with returning dao interface, that is used to work with dao objects!!!
    // And please, don't return null!!!
    @Override
    public IUserDao getDao() {
        if (dao == null) {
            dao = (IUserDao) AbstractDaoFactory.getImplDao(User.class);
        }
        return dao;
    }

}
