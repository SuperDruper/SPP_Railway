package org.apache.struts.dao;

import org.apache.struts.dao.hibernatedao.HibernateDaoFactory;

import java.io.Serializable;

/**
 * Created by PC-Alyaksei on 18.03.2016.
 */
// AbstractDaoFactory know which implementation should it use!!!
public abstract class AbstractDaoFactory {

    // In case of storage changing, change implementation here!
    private static AbstractDaoFactory factoryImp = new HibernateDaoFactory();


    public static AbstractDaoFactory getFactoryImp() {
        return factoryImp;
    }

    public static void setFactoryImp(AbstractDaoFactory factoryImp) {
        AbstractDaoFactory.factoryImp = factoryImp;
    }


    public static IDao getImplDao(Class<?> entityClass) {
        return getFactoryImp().getDao(entityClass);
    }

    public static <T, PK extends Serializable> IDao<T, PK> getParamImplDao(Class<T> entityClass) {
        return getFactoryImp().getParamDao(entityClass);
    }



    public abstract IDao getDao(Class<?> entityClass);

    public abstract <T, PK extends Serializable> IDao<T, PK> getParamDao(Class<T> entityClass);

}
