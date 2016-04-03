package code.dao.daointerface;

import code.model.*;
import code.dao.IDao;

import java.util.List;

/**
 * Created by dzmitry.antonenka on 20.03.2016.
 */
public interface IRaceDao extends IDao<Race, Integer> {
    public List<Race> getAllRacesViaStationWithName(String stationName);
    public List<Ticket> getAllTicketsForRaceWithID(Integer id);

    public List<Race> getAllRaces();
    public Race getRaceById(Integer ID);

    //MARK  -  GETTERS
    public Route getRouteForRace(Race race);
    public java.util.Collection<RaceStation> getRaceStationsForRace(Race race);
    public java.util.Collection<Ticket> getAllTicketsForRace(Race race);
    public Train getTrainForRace(Race race);
}
