package code.dao.hibernatedao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by PC-Alyaksei on 14.03.2016.
 */
public class HibernateUtils {

    private static HibernateUtils ourInstance = new HibernateUtils();

    public static HibernateUtils getInstance() {
        return ourInstance;
    }


    private SessionFactory factory;

    public SessionFactory getFactory() { return factory; }


    private HibernateUtils() {
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public Session openSession() {
        return factory.openSession();
    }

    public void closeSession() {
         factory.close();
    }
    public boolean isSessionClosed() { return factory.isClosed(); }
}
