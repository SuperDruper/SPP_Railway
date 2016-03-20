package org.apache.struts.dao.daointerface;

import org.apache.struts.dao.IDao;
import org.apache.struts.model.Race;
import org.apache.struts.model.RaceStation;
import org.apache.struts.model.Station;
import org.apache.struts.model.User;

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
}
