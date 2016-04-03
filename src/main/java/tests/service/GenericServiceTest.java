package tests.service;

import code.dao.IDao;
import code.model.*;
import code.service.GenericService;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import tests.hibernatedao.DatabaseGeneratorHelper;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class GenericServiceTest <T, PK extends Serializable> extends Assert {
    Class<T> entityClass;
    GenericService<T, PK> genericService;
    DatabaseGeneratorHelper databaseGeneratorHelper;

    public GenericServiceTest(Class<T> entityClass, Class<PK> pkClass)
    {
        this.entityClass = entityClass;
        this.genericService = new GenericService<T, PK>(entityClass);
        this.databaseGeneratorHelper = new DatabaseGeneratorHelper();

        System.out.println("Created generic service with class : " + this.genericService.getEntityClass());
    }

    @Parameterized.Parameters
    public static List<Object[]> listOfGenricObjects()
    {
        return Arrays.asList(new Object[][] {
                { Role.class, Integer.class },
                { Race.class, Integer.class },
                { RaceStation.class, Integer.class },
                { Route.class, Integer.class },
                { Ticket.class, Integer.class },
                { Train.class, Integer.class },
                { User.class, Integer.class },
        });
    }

    @org.junit.Test
    public void testGetDao() throws Exception {
        IDao dao = this.genericService.getDao();
        Assert.assertNotNull(dao);
    }

    @org.junit.Test
    public void testGetEntityClass() throws Exception {
        Assert.assertNotNull(this.genericService.getEntityClass());
    }

    @org.junit.Test
    public void testPersist() throws Exception {
        T entityToSave = (T) this.databaseGeneratorHelper.getDatabaseEntityForEntityClass(entityClass);
        PK entityPK = (PK) this.databaseGeneratorHelper.getIdForObjectWithClass(entityClass);

        this.genericService.persist(entityToSave);
        T savedEntity = this.genericService.findByPK(entityPK);

        Assert.assertNotNull(savedEntity);
    }
    @org.junit.Test
    public void testUpdate() throws Exception {
        testPersist();

        T originObject = genericService.findByPK((PK) databaseGeneratorHelper.getIdForObjectWithClass(entityClass));
        Assert.assertNotNull(originObject); //object is not nil to update

        genericService.update((T) databaseGeneratorHelper.updateForObjectWithClass(entityClass));
        T changedObject = genericService.findByPK((PK) databaseGeneratorHelper.getIdForObjectWithClass(entityClass));

        Assert.assertEquals(originObject, changedObject);
    }

    @org.junit.Test
    public void testFindByPK() throws Exception {
        Object object = this.genericService.findByPK(null);
        Assert.assertNull(object);

        T objectToSave = (T) this.databaseGeneratorHelper.getDatabaseEntityForEntityClass(entityClass);
        PK objectID = (PK) this.databaseGeneratorHelper.getIdForObjectWithClass(entityClass);
        genericService.persist(objectToSave);

        T savedObject = genericService.findByPK(objectID);

        Assert.assertEquals(savedObject, objectToSave);
    }

    @org.junit.Test
    public void testDeleteByPK() throws Exception {
        T objectToSave = (T) this.databaseGeneratorHelper.getDatabaseEntityForEntityClass(entityClass);
        PK objectID = (PK) this.databaseGeneratorHelper.getIdForObjectWithClass(entityClass);
        genericService.persist(objectToSave);

        T savedObject = genericService.findByPK(objectID);
        Assert.assertEquals(savedObject, objectToSave);

        genericService.deleteByPK(objectID);
        T foundObject = genericService.findByPK(objectID);
        Assert.assertNull(foundObject);
    }

    @org.junit.Test
    public void testDelete() throws Exception {
        T objectToSave = (T) this.databaseGeneratorHelper.getDatabaseEntityForEntityClass(entityClass);
        PK objectID = (PK) this.databaseGeneratorHelper.getIdForObjectWithClass(entityClass);
        genericService.persist(objectToSave);

        T savedObject = genericService.findByPK(objectID);
        Assert.assertEquals(savedObject, objectToSave);

        genericService.deleteByPK(objectID);
        T foundObject = genericService.findByPK(objectID);
        Assert.assertNull(foundObject);
    }

    @org.junit.Test
    public void testFindAll() throws Exception {
        testPersist();
        T objectSaved = (T) databaseGeneratorHelper.getIdForObjectWithClass(entityClass);

        List<T> objectList = genericService.findAll();
        boolean isFoundObject = false;
        for (T object : objectList) {
            if(object.equals(objectSaved)) {
                isFoundObject = true;
                break;
            }
        }

        Assert.assertTrue(isFoundObject);
    }

    @org.junit.Test
    public void testDeleteAll() throws Exception {
        //Do I need it ?? Or I can just move it into hibernateDAOTest class.
        this.genericService.deleteAll();
        List<T> objects = this.genericService.findAll();

        Assert.assertNull(objects);
    }
}