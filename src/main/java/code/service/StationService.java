package code.service;

import code.dao.AbstractDaoFactory;
import code.dao.daointerface.IRouteDao;
import code.dao.daointerface.IStationDao;
import code.model.Route;
import code.model.Station;

/**
 * Created by dzmitry.antonenka on 07.05.2016.
 */
public class StationService extends GenericService<Station, Integer> {
    private IStationDao dao;

    public StationService() {
        super(Station.class);
    }

    @Override
    public IStationDao getDao() {
        if(dao == null) {
            dao = (IStationDao) AbstractDaoFactory.getImplDao(Station.class);
        }

        return dao;
    }
}
