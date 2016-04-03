package code.service;

import code.dao.AbstractDaoFactory;
import code.dao.daointerface.IRouteDao;
import code.model.Route;

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
