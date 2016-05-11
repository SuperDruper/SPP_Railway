package code.dao.hibernatedao;

import code.dao.IDao;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import sun.rmi.runtime.Log;

import java.io.Serializable;
import java.util.List;


/**
 * Created by PC-Alyaksei on 17.03.2016.
 */
public class GenericHibernateDao<T, PK extends Serializable> implements IDao<T, PK> {

    private HibernateUtils utils = HibernateUtils.getInstance();
    private Session currentSession;
    private Transaction currentTransaction;
    private Class<T> entityClass;


    public GenericHibernateDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }


    @Override
    public Session openCurrentSession() {
        currentSession = utils.openSession();
        if (currentSession == null) {
            System.err.println(utils==null);
        }
        return currentSession;
    }

    @Override
    public Session openCurrentSessionWithTransaction() {
        currentSession = utils.openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    @Override
    public void closeCurrentSession() {
        currentSession.close();
    }

    @Override
    public void closeCurrentSessionWithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }



    @Override
    public void persist(T entity) {
        getCurrentSession().persist(entity);
    }

    @Override
    public void update(T entity) {
        getCurrentSession().update(entity);
    }

    @Override
    public T findByPK(PK pk) {
        return getCurrentSession().get(getEntityClass(), pk);
    }

    @Override
    public T findByFieldWithName(String fieldName, Object value) {
        return null;
    }

    /**
     * This method deletes instance, but not it's dependencies.
     * In case of using this , method to entity with dependencies exception will be thrown!
     * To delete entity with it's dependencies use deleteByPK(entity) method!
     * @param entity - entity, which PK will be used for deletion
     */
    @Override
    public void delete(T entity) {
        getCurrentSession().delete(entity);
    }

    @Override
    public void deleteByPK(PK pk) {
        System.out.print("pk = " + pk);
        Object persistentInstance = getCurrentSession().get(getEntityClass(), pk);
        if (persistentInstance != null) {
            getCurrentSession().delete(persistentInstance);
        }
    }

    @Override
    public List<T> findAll() {
        return getCurrentSession().createCriteria(getEntityClass()).list();
    }

    @Override
    public void deleteAll() {
        List<T> entityList = findAll();
        for (T entity : entityList) {
            delete(entity);
        }
    }

    @Override
    public List<T> getModelListByStringField(String fieldName, String fieldValue) {
        return getCurrentSession().createCriteria(entityClass)
                .add(Restrictions.eq(fieldName, fieldValue)).list();
    }

    @Override
    public T getModelByUniqueStringField(String fieldName, String fieldValue) {
        return (T) getCurrentSession().createCriteria(entityClass)
                .add(Restrictions.eq(fieldName, fieldValue)).uniqueResult();
    }

    @Override
    public T getModelByUniqueStringField(String fieldName, Object fieldValue) {
        return (T) getCurrentSession().createCriteria(entityClass)
                .add(Restrictions.eq(fieldName, fieldValue)).uniqueResult();
    }

    // GETTERS AND SETTERS
    public Session getCurrentSession() {
        return currentSession;
    }
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
    public Class<T> getEntityClass() {
        return entityClass;
    }
}
