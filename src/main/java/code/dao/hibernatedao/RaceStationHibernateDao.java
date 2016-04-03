package code.dao.hibernatedao;

import code.dao.daointerface.IRaceStationsDao;
import code.model.Race;
import code.model.RaceStation;
import code.model.Station;

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
}
