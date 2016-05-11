package code.service;

import code.dao.AbstractDaoFactory;
import code.dao.IDao;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PC-Alyaksei on 18.03.2016.
 */
public class GenericService <T, PK extends Serializable> {

    private IDao<T, PK> dao;
    private Class<T> entityClass;


    public GenericService(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.dao = AbstractDaoFactory.getParamImplDao(entityClass);
    }

    // In case of extending GenericService, you should override
    // getDao method with returning dao interface, that is used to work with dao objects!!!
    public IDao<T, PK> getDao() {
        return dao;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }


    public void persist(T entity) {
        getDao().openCurrentSessionWithTransaction();
        getDao().persist(entity);
        getDao().closeCurrentSessionWithTransaction();
    }

    public void update(T entity) {
        getDao().openCurrentSessionWithTransaction();
        getDao().update(entity);
        getDao().closeCurrentSessionWithTransaction();
    }

    public T findByPK(PK pk) {
        getDao().openCurrentSession();
        T entity = getDao().findByPK(pk);
        getDao().closeCurrentSession();
        return entity;
    }

    public void deleteByPK(PK pk) {
        getDao().openCurrentSessionWithTransaction();
        getDao().deleteByPK(pk);
        getDao().closeCurrentSessionWithTransaction();
    }

    public void delete(T entity) {
        getDao().openCurrentSessionWithTransaction();
        getDao().delete(entity);
        getDao().closeCurrentSessionWithTransaction();
    }

    public List<T> findAll() {
        getDao().openCurrentSession();
        List<T> entities = getDao().findAll();
        getDao().closeCurrentSession();
        return entities;
    }

    public void deleteAll() {
        getDao().openCurrentSessionWithTransaction();
        getDao().deleteAll();
        getDao().closeCurrentSessionWithTransaction();
    }

    public List<T> getModelListByStringField(String fieldName, String fieldValue) {
        getDao().openCurrentSessionWithTransaction();
        List<T> models = getDao().getModelListByStringField(fieldName, fieldValue);
        getDao().closeCurrentSessionWithTransaction();
        return models;
    }

    public T getModelByUniqueStringField(String fieldName, String fieldValue) {
        getDao().openCurrentSessionWithTransaction();
        T model = getDao().getModelByUniqueStringField(fieldName, fieldValue);
        getDao().closeCurrentSessionWithTransaction();
        return model;
    }

    public T getModelByUniqueStringField(String fieldName, Object fieldValue) {
        getDao().openCurrentSessionWithTransaction();
        T model = getDao().getModelByUniqueStringField(fieldName, fieldValue);
        getDao().closeCurrentSessionWithTransaction();
        return model;
    }
}
