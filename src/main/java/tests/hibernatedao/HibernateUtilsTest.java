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
    }
}