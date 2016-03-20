package org.apache.struts.dao.hibernatedao;

/**
 * Created by PC-Alyaksei on 18.03.2016.
 */

import org.apache.struts.dao.daointerface.IUserDao;
import org.apache.struts.model.Race;
import org.apache.struts.model.Ticket;
import org.apache.struts.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;

import java.util.Collection;
import java.util.List;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
public class UserHibernateDao extends GenericHibernateDao<User, Integer> implements IUserDao {

    private HibernateUtils factory = HibernateUtils.getInstance();


    public UserHibernateDao() {
        super(User.class);
    }

    /*
    public User getUserById(int id) {
        Session session = factory.openSession();
        Transaction tx = null;
        User user = null;
        try{
            tx = session.beginTransaction();
            user = (User)session.get(User.class, id);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return user;
    }

    public void persist(User user){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public void update(User user) {
        Session session = factory.openSession();
        Transaction tx = null;

        try{
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public List<User> list() {
        Session session = factory.openSession();
        Transaction tx = null;
        List<User> users = null;
        try{
            tx = session.beginTransaction();
            users = session.createQuery("FROM User").list();
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return users;
    }

    public void delete(int pk){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            User dist = (User)session.get(User.class, pk);
            session.delete(dist);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }*/

    Collection<Ticket> getTicketsForUser(User user) {
          return user.getTickets();
    }

    List<Ticket> getTicketsForUserWithID(Integer userID) {
        Session session = getCurrentSession();

        Criteria criteria = session.createCriteria(Ticket.class, "ticket");
        criteria.createAlias("ticket.user", "user");
        criteria.add(Restrictions.eq("user.id", userID));

        return criteria.list();
    }

    @Override
    public User getUserByLogin(String login) {
        return (User) getCurrentSession().createCriteria(User.class)
                .add(Restrictions.eq("login", login)).uniqueResult();
    }
}
