package tests.hibernatedao;

import code.dao.hibernatedao.HibernateUtils;
import org.junit.Assert;
import org.junit.Test;


public class HibernateUtilsTest {
    @Test
    public void testGetInstance() throws Exception {
        Assert.assertNotNull(HibernateUtils.getInstance());
    }

    @Test
    public void testOpenSession() throws Exception {
        Assert.assertNotNull(HibernateUtils.getInstance().openSession());
        HibernateUtils.getInstance().closeSession();
    }

    @Test
    public void testIsSessionClosed () throws Exception {
        HibernateUtils.getInstance().closeSession();
        Assert.assertTrue(HibernateUtils.getInstance().isSessionClosed());
    }

    @Test
    public void testGetFactory () throws Exception {
       Assert.assertNotNull(HibernateUtils.getInstance().getFactory());
    }
}