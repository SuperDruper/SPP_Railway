package code.dao.hibernatedao;

import code.dao.daointerface.IRaceStationsDao;
import code.model.Race;
import code.model.RaceStation;
import code.model.Station;
import org.hibernate.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public class RaceStationHibernateDao extends GenericHibernateDao<RaceStation, Integer> implements IRaceStationsDao {
    public RaceStationHibernateDao() {
        super(RaceStation.class);
    }


    public List<RaceStation> getRaceStationsWithTimestampConstraints(Timestamp start, Timestamp end) {
        // TODO
        return null;
    }

    // MARK - GETTERS

    public Station getStationForRaceStation(RaceStation raceStation) {
        return raceStation.getStation();
    }
    public Race getRaceForRaceStation(RaceStation raceStation) {
        return raceStation.getRace();
    }
    public RaceStation getRaceStationByID(Integer id) {
        return super.findByPK(id);
    }

    private static final String FIND_BY_RACE_ID_IN_ASC_ORDER_BY_DATE_HQL =
            "SELECT rs FROM RaceStation rs WHERE rs.race.id = ? ORDER BY rs.arriving ASC";
    @Override
    public List<RaceStation> findByRaceId(int raceId) {
        Query query = getCurrentSession().createQuery(FIND_BY_RACE_ID_IN_ASC_ORDER_BY_DATE_HQL);
        query.setInteger(0, raceId);
        return query.list();
    }

    private static final String FIND_START_RACE_STATION_BY_RACE_ID_HQL =
            "SELECT rs FROM RaceStation rs WHERE rs.race.id = ? AND rs.arriving = null";
    @Override
    public RaceStation getDepartureRaceStationByRacePK(int racePK) {
        Query query = getCurrentSession().createQuery(FIND_START_RACE_STATION_BY_RACE_ID_HQL);
        query.setInteger(0, racePK);
        return (RaceStation) query.uniqueResult();
    }

    private static final String FIND_FINISH_RACE_STATION_BY_RACE_ID_HQL =
            "SELECT rs FROM RaceStation rs WHERE rs.race.id = ? AND rs.depature = null";
    @Override
    public RaceStation getArriveRaceStationByRacePK(int racePK) {
        Query query = getCurrentSession().createQuery(FIND_FINISH_RACE_STATION_BY_RACE_ID_HQL);
        query.setInteger(0, racePK);
        return (RaceStation) query.uniqueResult();
    }
}
