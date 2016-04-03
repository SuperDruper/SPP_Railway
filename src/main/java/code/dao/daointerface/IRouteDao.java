package code.dao.daointerface;

import code.model.Route;
import code.dao.IDao;
import code.model.Race;
import code.model.Station;

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
