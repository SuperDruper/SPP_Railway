package org.apache.struts.dao.daointerface;

import org.apache.struts.dao.IDao;
import org.apache.struts.model.Race;
import org.apache.struts.model.Route;
import org.apache.struts.model.Station;
import org.apache.struts.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;

import java.util.Collection;
import java.util.List;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public interface IRouteDao extends IDao<Route, Integer> {
    public List<Route> getAllRoutes();
    public List<Route> getAllRoutesViaStation(Station station);

    public List<Race> getRacesForRouteWithId(Integer id);
    public Collection<Race> getRacesForRoute(Route route);

    public Route getRouteByID(Integer ID);
    public Collection<Race> getAllRacesForRoute(Route route);
}
