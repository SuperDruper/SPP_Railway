package code.dao.daointerface;

import code.model.Race;
import code.model.RaceStation;
import code.dao.IDao;
import code.model.Station;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public interface IRaceStationsDao extends IDao<RaceStation, Integer> {
    public List<RaceStation> getRaceStationsWithTimestampConstraints(Timestamp start, Timestamp end);

    // MARK - GETTERS
    public Station getStationForRaceStation(RaceStation raceStation);
    public Race getRaceForRaceStation(RaceStation raceStation);
    public RaceStation getRaceStationByID(Integer id);

    List<RaceStation> findByRaceId(int raceId);
}
