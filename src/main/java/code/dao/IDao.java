package code.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

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

    public T findByFieldWithName(String fieldName, Object value);

    public void delete(T entity);

    public void deleteByPK(PK pk);

    public List<T> findAll();

    public void deleteAll();

    public List<T> getModelListByStringField(String fieldName, String fieldValue);

    public T getModelByUniqueStringField(String fieldName, String fieldValue);

    public Session getCurrentSession();
    public void setCurrentSession(Session currentSession);
    public Transaction getCurrentTransaction();
    public void setCurrentTransaction(Transaction currentTransaction);
}
