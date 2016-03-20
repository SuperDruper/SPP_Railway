package org.apache.struts.service;

import org.apache.struts.dao.AbstractDaoFactory;
import org.apache.struts.dao.daointerface.IRaceDao;
import org.apache.struts.dao.daointerface.IRaceStationsDao;
import org.apache.struts.model.Race;
import org.apache.struts.model.RaceStation;
import org.apache.struts.model.Ticket;

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
