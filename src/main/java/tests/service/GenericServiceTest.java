package tests.service;

import code.dao.IDao;
import code.model.*;
import code.service.GenericService;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import tests.hibernatedao.Helper.DatabaseGeneratorHelper;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

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

        this.databaseGeneratorHelper.deleteEntityForClass(entityClass);
    }

    @org.junit.Test
    public void testUpdate() throws Exception {
        T entityToSave = (T) this.databaseGeneratorHelper.getDatabaseEntityForEntityClass(entityClass);
        PK entityPK = (PK) this.databaseGeneratorHelper.getIdForObjectWithClass(entityClass);
        this.genericService.persist(entityToSave);

        T originObject = genericService.findByPK((PK) databaseGeneratorHelper.getIdForObjectWithClass(entityClass));
        Assert.assertNotNull(originObject); //object is not nil to update

        databaseGeneratorHelper.updateEntityWithClass(entityClass);
        genericService.update(((T)databaseGeneratorHelper.getDatabaseEntityForEntityClass(entityClass)));
        T changedObject = genericService.findByPK((PK) databaseGeneratorHelper.getIdForObjectWithClass(entityClass));

        Assert.assertNotSame(originObject, changedObject);
        databaseGeneratorHelper.deleteEntityForClass(entityClass);
    }

    @org.junit.Test
    public void testFindByPK() throws Exception {
        T objectToSave = (T) this.databaseGeneratorHelper.getDatabaseEntityForEntityClass(entityClass);
        PK objectID = (PK) this.databaseGeneratorHelper.getIdForObjectWithClass(entityClass);
        genericService.persist(objectToSave);

        T savedObject = genericService.findByPK(objectID);

        Assert.assertEquals(savedObject, objectToSave);
        genericService.deleteByPK(objectID);
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

        databaseGeneratorHelper.deleteEntityForClass(entityClass);
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

        databaseGeneratorHelper.deleteEntityForClass(entityClass);
    }

    @org.junit.Test
    public void testFindAll() throws Exception {
        T entityToSave = (T) this.databaseGeneratorHelper.getDatabaseEntityForEntityClass(entityClass);
        PK entityPK = (PK) this.databaseGeneratorHelper.getIdForObjectWithClass(entityClass);
        this.genericService.persist(entityToSave);

        PK savedObjectId = (PK) databaseGeneratorHelper.getIdForObjectWithClass(entityClass);

        List<T> objectList = genericService.findAll();
        boolean isFoundObject = false;
        for (T object : objectList) {
            PK objectFromListPK =  (PK) databaseGeneratorHelper.getIdForObjectWithClass(entityClass);
            if(savedObjectId.equals(objectFromListPK)) {
                isFoundObject = true;
                break;
            }
        }

        Assert.assertTrue(isFoundObject);

        databaseGeneratorHelper.deleteEntityForClass(entityClass);
    }

    @org.junit.Test
    public void testDeleteAll() throws Exception {
        T entityToSave = (T) databaseGeneratorHelper.getDatabaseEntityForEntityClass(entityClass);
        this.genericService.persist(entityToSave);

        T savedEntity = genericService.findByPK((PK) databaseGeneratorHelper.getIdForObjectWithClass(entityClass));
        Assert.assertEquals(entityToSave, savedEntity);

        this.genericService.deleteAll();
        List<T> objects = this.genericService.findAll();

        Assert.assertTrue(objects.isEmpty());
        databaseGeneratorHelper.deleteEntityForClass(entityClass);
    }
}