package org.apache.struts.service;

import org.apache.struts.dao.AbstractDaoFactory;
import org.apache.struts.dao.daointerface.IRouteDao;
import org.apache.struts.dao.daointerface.ITicketDao;
import org.apache.struts.model.Route;
import org.apache.struts.model.Ticket;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public class RouteService extends GenericService<Route, Integer> {
    private IRouteDao dao;

    public RouteService() {
        super(Route.class);
    }

    @Override
    public IRouteDao getDao() {
        if(dao == null) {
            dao = (IRouteDao) AbstractDaoFactory.getImplDao(Route.class);
        }

        return dao;
    }
}
