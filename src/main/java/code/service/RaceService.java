package code.service;

import code.dao.AbstractDaoFactory;
import code.dao.daointerface.IRaceDao;
import code.model.Race;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public class RaceService extends GenericService<Race, Integer> {
    private IRaceDao dao;

    public RaceService() {
        super(Race.class);
    }

    @Override
    public IRaceDao getDao() {
        if(dao == null) {
            dao = (IRaceDao) AbstractDaoFactory.getImplDao(RaceService.class);
        }

        return dao;
    }
}
