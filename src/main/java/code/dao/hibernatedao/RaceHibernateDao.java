package code.dao.hibernatedao;

import code.dao.daointerface.IRaceDao;
import code.model.*;
import code.model.race.RaceSearchData;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;

import java.sql.Timestamp;
import java.util.ArrayList;
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

    //  MARK  -  GETTERS
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


    private static final String GET_RACES_USING_SEARCH_DATA_HQL =
            "SELECT r FROM Race r inner join r.raceStations rs " +
            "WHERE rs.station.id = :stDepartureId and Date(rs.depature) = Date(:stDepartureDate)";
    @Override
    public List<Race> findRaces(RaceSearchData searchData) {
        Timestamp timestamp = new Timestamp(searchData.getRaceDate().getTime());

        Query query = getCurrentSession().createQuery(GET_RACES_USING_SEARCH_DATA_HQL);
        query.setInteger("stDepartureId", searchData.getDepartureStationId());
        query.setTimestamp("stDepartureDate", timestamp);

        List<Race> races  = query.list();
        List<Race> result = new ArrayList<>();

        int stationId;
        Timestamp departureTimestamp = null;
        Timestamp arriveTimestamp;
        for (Race race : races) {
            arriveTimestamp = null;

            for (RaceStation raceStation : race.getRaceStations()) {
                stationId = raceStation.getStation().getId();

                if (stationId == searchData.getArriveStationId()) {
                    arriveTimestamp = raceStation.getArriving();
                }
                if (stationId == searchData.getDepartureStationId()) {
                    departureTimestamp = raceStation.getDepature();
                }
            }


            if (arriveTimestamp != null && departureTimestamp.compareTo(arriveTimestamp) == -1) {
                result.add(race);
            }
        }

        return result;
    }

    private static final String GET_RACE_WITH_TICKETS_HQL =
            "SELECT r FROM Race r left join fetch r.tickets WHERE r.id = ?";
    @Override
    public Race findByPKWithDetails(int pk) {
        Query query = getCurrentSession().createQuery(GET_RACE_WITH_TICKETS_HQL);
        query.setInteger(0, pk);
        return (Race) query.uniqueResult();
    }

}