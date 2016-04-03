package tests.hibernatedao;

import org.apache.struts.dao.IDao;
import org.apache.struts.dao.hibernatedao.*;
import org.apache.struts.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class HibernateDaoFactoryTest {
    Class<?> entityClass;
    HibernateDaoFactory hibernateDaoFactory = new HibernateDaoFactory();

    public HibernateDaoFactoryTest(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    @Test
    public void testGetDao() throws Exception {
        Assert.assertNotNull(this.entityClass);
    }

    @Test
    public void testGetParamDao() throws Exception {
        IDao iDAO = hibernateDaoFactory.getDao(entityClass);

        if(entityClass.equals(Role.class)) {
            Assert.assertTrue(iDAO instanceof RoleHibernateDao);
        } else if(entityClass.equals(Race.class)) {
            Assert.assertTrue(iDAO instanceof RaceHibernateDao);
        } else if(entityClass.equals(RaceStation.class)) {
            Assert.assertTrue(iDAO instanceof RaceStationHibernateDao);
        } else if(entityClass.equals(Route.class)) {
            Assert.assertTrue(iDAO instanceof RouteHibernateDao);
        } else if(entityClass.equals(Ticket.class)) {
            Assert.assertTrue(iDAO instanceof TicketHibernateDao);
        } else if(entityClass.equals(Train.class)) {
            Assert.assertTrue(iDAO instanceof TrainHibernateDao);
        } else if(entityClass.equals(User.class)) {
            Assert.assertTrue(iDAO instanceof UserHibernateDao);
        } else {
            Assert.assertNotNull(iDAO);
        }
    }

    @Parameterized.Parameters
    public static List<Object> listOfGenricObjects() {
        return Arrays.asList(new Object[]{
                Role.class,
                Race.class,
                RaceStation.class,
                Route.class,
                Ticket.class,
                Train.class,
                User.class,
        });
    }
}