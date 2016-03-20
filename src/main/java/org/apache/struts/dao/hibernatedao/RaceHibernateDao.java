package org.apache.struts.dao.hibernatedao;

import org.apache.struts.dao.daointerface.IRaceDao;
import org.apache.struts.dao.daointerface.IRouteDao;
import org.apache.struts.model.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Collection;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import java.util.List;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public class RaceHibernateDao extends GenericHibernateDao<Race, Integer> implements IRaceDao {
    private HibernateUtils factory = HibernateUtils.getInstance();

    public RaceHibernateDao() { super(Race.class); }

    public List<Race> getAllRacesViaStationWithName(String stationName) {
        Session session = getCurrentSession();

        Criteria criteria = session.createCriteria(Race.class, "race");
        criteria.createAlias("race.raceStations", "station");
        criteria.add(Restrictions.eq("station.name", stationName));
        criteria.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);

        return criteria.list();
    }

    public List<Ticket> getAllTicketsForRaceWithID(Integer id)
    {
        Session session = getCurrentSession();

        Criteria criteria = session.createCriteria(Train.class, "train");
        criteria.createAlias("train.races", "race");
        criteria.add(Restrictions.eq("race.id", id));
        criteria.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);

        return null;
    }

    public List<Race> getAllRaces() {
        return super.findAll();
    }
    public Race getRaceById(Integer ID) {
        return super.findByPK(ID);
    }

    //MARK  -  GETTERS

    public Route getRouteForRace(Race race) {
        return race.getRoute();
    }
    public java.util.Collection<RaceStation> getRaceStationsForRace(Race race) {
        return race.getRaceStations();
    }
    public java.util.Collection<Ticket> getAllTicketsForRace(Race race) {
        return race.getTickets();
    }
    public Train getTrainForRace(Race race) {
        return race.getTrain();
    }
}