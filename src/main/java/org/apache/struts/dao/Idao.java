package org.apache.struts.dao;

import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * Created by PC-Alyaksei on 17.03.2016.
 */
public interface IDao <T, PK extends Serializable> {

    public Session openCurrentSession();

    public Session openCurrentSessionWithTransaction();

    public void closeCurrentSession();

    public void closeCurrentSessionWithTransaction();

    public void persist(T entity);

    public void update(T entity);

    public T findByPK(PK pk);

    public void delete(T entity);

    public void deleteByPK(PK pk);

    public List<T> findAll();

    public void deleteAll();

    public List<T> getListByStringField(String fieldName, String fieldValue);

    public T getUniqueByStringField(String fieldName, String fieldValue);
}
