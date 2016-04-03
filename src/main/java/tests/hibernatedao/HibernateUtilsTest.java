package tests.hibernatedao;

import org.apache.struts.dao.hibernatedao.HibernateUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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