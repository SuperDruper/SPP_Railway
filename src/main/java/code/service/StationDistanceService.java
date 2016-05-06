package code.service;

import code.dao.AbstractDaoFactory;
import code.dao.daointerface.IDistanceDao;
import code.dao.daointerface.IRouteDao;
import code.model.Distance;
import code.model.DistancePK;
import code.model.Route;

/**
 * Created by dzmitry.antonenka on 05.05.2016.
 */
public class StationDistanceService extends GenericService<Distance, DistancePK> {
    private IDistanceDao dao;

    public StationDistanceService() {
        super(Distance.class);
    }

    @Override
    public IDistanceDao getDao() {
        if(dao == null) {
            dao = (IDistanceDao) AbstractDaoFactory.getImplDao(Distance.class);
        }

        return dao;
    }

    public Distance getDistanceByPK(DistancePK distancePK) {
       return new StationDistanceService().findByPK(distancePK);
    }

    public Distance getRouteByName(String name) {
        return getModelByUniqueStringField("name", name);
    }
}
