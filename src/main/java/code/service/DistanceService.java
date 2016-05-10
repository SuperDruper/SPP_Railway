package code.service;

import code.dao.AbstractDaoFactory;
import code.dao.IDao;
import code.dao.daointerface.IDistanceDao;
import code.dao.daointerface.IRaceStationsDao;
import code.model.Distance;
import code.model.DistancePK;
import code.model.RaceStation;

/**
 * Created by PC-Alyaksei on 01.05.2016.
 */
public class DistanceService extends GenericService<Distance, DistancePK> {

    private IDistanceDao dao;

    public DistanceService() {
        super(Distance.class);
    }

    @Override
    public Distance findByPK(DistancePK pk) {
        int stIdFrom = pk.getStIdFrom();
        int stIdTo = pk.getStIdTo();

        if (stIdFrom <= stIdTo) {
            return super.findByPK(pk);
        }
        else {
            return super.findByPK(new DistancePK(stIdTo, stIdFrom));
        }
    }

    public int getDistance (DistancePK pk) {
        int stIdFrom = pk.getStIdFrom();
        int stIdTo = pk.getStIdTo();

        if (stIdFrom == stIdTo) {
            return 0;
        }
        else {
            return findByPK(pk).getDistance();
        }
    }


    @Override
    public IDao<Distance, DistancePK> getDao() {
        if(dao == null) {
            dao = (IDistanceDao) AbstractDaoFactory.getImplDao(Distance.class);
        }
        return dao;
    }

}
