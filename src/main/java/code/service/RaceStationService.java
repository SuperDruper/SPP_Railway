package code.service;

import code.dao.AbstractDaoFactory;
import code.dao.daointerface.IRaceStationsDao;
import code.model.RaceStation;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public class RaceStationService extends GenericService<RaceStation, Integer> {
    private IRaceStationsDao dao;

    public RaceStationService() {
        super(RaceStation.class);
    }

    @Override
    public IRaceStationsDao getDao() {
        if(dao == null) {
            dao = (IRaceStationsDao) AbstractDaoFactory.getImplDao(RaceStation.class);
        }

        return dao;
    }
}
