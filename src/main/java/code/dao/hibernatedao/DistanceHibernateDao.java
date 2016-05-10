package code.dao.hibernatedao;

import code.dao.daointerface.IDistanceDao;
import code.model.Distance;
import code.model.DistancePK;
import org.hibernate.Query;

/**
 * Created by PC-Alyaksei on 01.05.2016.
 */
public class DistanceHibernateDao extends GenericHibernateDao<Distance, DistancePK> implements IDistanceDao {

    public DistanceHibernateDao() {
        super(Distance.class);
    }

}
