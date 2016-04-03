package code.dao.hibernatedao;

import code.dao.daointerface.IRouteDao;
import code.model.Race;
import code.model.Route;
import code.model.Station;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;

import java.util.Collection;
import java.util.List;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public class RouteHibernateDao extends GenericHibernateDao<Route, Integer> implements IRouteDao {
    private HibernateUtils factory = HibernateUtils.getInstance();

    public RouteHibernateDao() { super(Route.class); }

    public List<Route> getAllRoutes() {
        return super.findAll();
    }
    public List<Route> getAllRoutesViaStation(Station station) {
            return null;
    }

    public List<Race> getRacesForRouteWithId(Integer id) {
        Session session = getCurrentSession();

        Criteria criteria = session.createCriteria(Route.class, "route");
        criteria.createAlias("route.races", "race");
        criteria.add(Restrictions.eq("race.id", id));
        criteria.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);

        return criteria.list();
    }
    public Collection<Race> getRacesForRoute(Route route) {
        return route.getRaces();
    }

    public Route getRouteByID(Integer ID) {
        return super.findByPK(ID);
    }
    public Collection<Race> getAllRacesForRoute(Route route) {
        return route.getRaces();
    }
}
