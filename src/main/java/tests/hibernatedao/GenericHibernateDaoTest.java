package tests.hibernatedao;

import code.dao.hibernatedao.GenericHibernateDao;
import code.model.*;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class GenericHibernateDaoTest<T, PK extends Serializable> extends Assert {
    Class<T> entityClass;
    GenericHibernateDao<T, PK> genericHibernateDao;

    public GenericHibernateDaoTest(Class<T> entityClass)
    {
        this.entityClass = entityClass;
        this.genericHibernateDao = new GenericHibernateDao<T, PK>(entityClass);
    }

    @Parameterized.Parameters
    public static List<Object> listOfGenericObjects() {
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

    private final int idConstant = 3;
    private final String nameConstant = "TestName";

//    public PK generateObjectToChangeAndReturnID() throws Exception {
//        if(entityClass.equals(Role.class)) {
//            Role role = new Role();
//            role.setId(idConstant);
//            role.setName(nameConstant);
//            role.setUsers(null);
//            this.objecetToChange = (T) role;
//
//            return (PK)(new Integer(role.getId()));
//        } else if(entityClass.equals(Race.class)) {
//            Race race = new Race();
//            race.setId(idConstant);
//
//            return (PK)(new Integer(race.getId()));
//        } else if(entityClass.equals(RaceStation.class)) {
//            RaceStation raceStation = new RaceStation();
//            raceStation.setId(idConstant);
//
//            return (PK)(new Integer(raceStation.getId()));
//        } else if(entityClass.equals(Route.class)) {
//            Route route = new Route();
//            route.setId(idConstant);
//
//            return (PK)(new Integer(route.getId()));
//        } else if(entityClass.equals(Ticket.class)) {
//            Ticket ticket = new Ticket();
//            ticket.setId(idConstant);
//
//            return (PK)(new Integer(ticket.getId()));
//        } else if(entityClass.equals(Train.class)) {
//            Train train = new Train();
//            train.setId(idConstant);
//
//            return (PK)(new Integer(train.getId()));
//        } else if(entityClass.equals(User.class)) {
//            User user = new User();
//            user.setId(idConstant);
//            user.setName(nameConstant);
//
//            return (PK)(new Integer(user.getId()));
//        } else {
//            throw new IOException("Uknown generic class as root set !");
//        }
//    }
//    public PK getIdForGenericObject(T object) throws Exception {
//        if(object instanceof Role) {
//            Role role = (Role)object;
//            return (PK)(new Integer(role.getId()));
//        } else if(object instanceof Race) {
//            Race race = (Race)object;
//            return (PK)(new Integer(race.getId()));
//        } else if(object instanceof RaceStation) {
//            RaceStation raceStation = (RaceStation)object;
//            return (PK)(new Integer(raceStation.getId()));
//        } else if(object instanceof Route) {
//            Route route = (Route)object;
//            return (PK)(new Integer(route.getId()));
//        } else if(object instanceof Ticket) {
//            Ticket ticket = (Ticket)object;
//            return (PK)(new Integer(ticket.getId()));
//        } else if(object instanceof Train) {
//            Train train = (Train)object;
//            return (PK)(new Integer(train.getId()));
//        } else if(object instanceof User) {
//            User user = (User)object;
//            return (PK)(new Integer(user.getId()));
//        } else {
//            throw new IOException("Uknown generic class as root set !");
//        }
//    }

    @Test
    public void testOpenCurrentSession() throws Exception {
        Session session = this.genericHibernateDao.openCurrentSession();
        Assert.assertNotNull(session);
    }

    @Test
    public void testOpenCurrentSessionWithTransaction() throws Exception {
        Session session = this.genericHibernateDao.openCurrentSessionWithTransaction();
        Assert.assertNotNull(session);
    }

    @Test
    public void testCloseCurrentSession() throws Exception {
        //Can throw exception
        this.genericHibernateDao.closeCurrentSession();
    }

    @Test
    public void testCloseCurrentSessionWithTransaction() throws Exception {
        //Can throw exception
        this.genericHibernateDao.closeCurrentSessionWithTransaction();
    }

    @Test
    public void testPersist() throws Exception {

    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testFindByPK() throws Exception {

    }

    @Test
    public void testDelete() throws Exception {

    }

    @Test
    public void testDeleteByPK() throws Exception {

    }

    @Test
    public void testFindAll() throws Exception {

    }

    @Test
    public void testDeleteAll() throws Exception {

    }

    @Test
    public void testGetListByStringField() throws Exception {

    }

    @Test
    public void testGetUniqueByStringField() throws Exception {

    }

    @Test
    public void testGetCurrentSession() throws Exception {

    }

    @Test
    public void testSetCurrentSession() throws Exception {

    }

    @Test
    public void testGetCurrentTransaction() throws Exception {

    }

    @Test
    public void testSetCurrentTransaction() throws Exception {

    }

    @Test
    public void testGetEntityClass() throws Exception {

    }
}