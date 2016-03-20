package org.apache.struts.service;

import org.apache.struts.dao.AbstractDaoFactory;
import org.apache.struts.dao.daointerface.IRaceStationsDao;
import org.apache.struts.dao.daointerface.IRoleDao;
import org.apache.struts.model.RaceStation;
import org.apache.struts.model.Ticket;

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
