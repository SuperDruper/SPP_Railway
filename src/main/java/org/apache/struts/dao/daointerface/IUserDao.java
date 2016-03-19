package org.apache.struts.dao.daointerface;

import org.apache.struts.dao.IDao;
import org.apache.struts.model.User;

/**
 * Created by PC-Alyaksei on 18.03.2016.
 */
public interface IUserDao extends IDao<User, Integer> {
    User getUserByLogin(String login);
}
