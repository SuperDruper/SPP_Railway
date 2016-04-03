package code.dao.hibernatedao;

import code.dao.AbstractDaoFactory;
import code.model.*;
import code.dao.IDao;

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
        } else if(entityClass.equals(Train.class)) {
            return (IDao<T, PK>) new TrainHibernateDao();
        } else if(entityClass.equals(Ticket.class)) {
            return (IDao<T, PK>) new TicketHibernateDao();
        } else if(entityClass.equals(Route.class)) {
            return (IDao<T, PK>) new RouteHibernateDao();
        } else if(entityClass.equals(Role.class)) {
            return (IDao<T, PK>) new RoleHibernateDao();
        }  else if(entityClass.equals(RaceStation.class)) {
            return (IDao<T, PK>) new RaceStationHibernateDao();
        }  else if(entityClass.equals(Race.class)) {
            return (IDao<T, PK>) new RaceHibernateDao();
        }


        return new GenericHibernateDao<T, PK>(entityClass);
    }
}
