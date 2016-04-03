package code.dao.daointerface;

import code.model.User;
import code.dao.IDao;

/**
 * Created by PC-Alyaksei on 18.03.2016.
 */
public interface IUserDao extends IDao<User, Integer> {
    User getUserByLogin(String login);

}
